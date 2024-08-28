package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.model.entity.Member;
import com.codecool.szidzse.solarwatch.model.entity.Role;
import com.codecool.szidzse.solarwatch.model.entity.RoleType;
import com.codecool.szidzse.solarwatch.model.payload.JwtResponse;
import com.codecool.szidzse.solarwatch.model.payload.LoginRequest;
import com.codecool.szidzse.solarwatch.model.payload.RegisterRequest;
import com.codecool.szidzse.solarwatch.repository.MemberRepository;
import com.codecool.szidzse.solarwatch.repository.RoleRepository;
import com.codecool.szidzse.solarwatch.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public MemberController(MemberRepository memberRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody RegisterRequest registerRequest) {
        if (memberRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Member member = new Member(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );

        Optional<Role> defaultRoleType = roleRepository.findByRoleType(RoleType.ROLE_USER);

        if (defaultRoleType.isPresent()) {
            member.setRoles(Set.of(defaultRoleType.get()));
        } else {
            Role defaultRole = new Role(RoleType.ROLE_USER);
            roleRepository.save(defaultRole);
            member.setRoles(Set.of(defaultRole));
        }

        memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }
}
