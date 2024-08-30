package com.codecool.szidzse.solarwatch;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codecool.szidzse.solarwatch.model.entity.Member;
import com.codecool.szidzse.solarwatch.model.entity.Role;
import com.codecool.szidzse.solarwatch.model.entity.RoleType;
import com.codecool.szidzse.solarwatch.model.payload.RegisterRequest;
import com.codecool.szidzse.solarwatch.repository.MemberRepository;
import com.codecool.szidzse.solarwatch.repository.RoleRepository;
import com.codecool.szidzse.solarwatch.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MemberControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        memberRepository.deleteAll();
        roleRepository.deleteAll();

        Role role = new Role(RoleType.ROLE_USER);
        roleRepository.save(role);

        Member member = new Member(
                "Jane",
                "Doe",
                "janedoe@example.com",
                passwordEncoder.encode("password")
        );
        member.setRoles(Set.of(role));
        memberRepository.save(member);
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "John",
                "Doe",
                "johndoe@example.com",
                "password"
        );

        ResultActions response = mockMvc
                .perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)));

        response.andExpect(status().isCreated());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "Jane",
                "Doe",
                "janedoe@example.com",
                "password"
        );


        ResultActions response = mockMvc
                .perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)));

        response.andExpect(status().isBadRequest());
    }
}
