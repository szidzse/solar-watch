package com.codecool.szidzse.solarwatch;

import com.codecool.szidzse.solarwatch.model.entity.Role;
import com.codecool.szidzse.solarwatch.model.entity.RoleType;
import com.codecool.szidzse.solarwatch.model.payload.RegisterRequest;
import com.codecool.szidzse.solarwatch.repository.MemberRepository;
import com.codecool.szidzse.solarwatch.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerRegisterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void registerNewUserShouldReturnCreatedStatus() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "Test",
                "User",
                "test.user@gmail.com",
                "test"
        );

        when(memberRepository.existsByEmail(registerRequest.getEmail())).thenReturn(false);
        when(roleRepository.findByRoleType(RoleType.ROLE_USER))
                .thenReturn(Optional.of(new Role(RoleType.ROLE_USER)));

        mockMvc.perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "firstName": "Test",
                            "lastName": "User",
                            "email": "test.user@gmail.com",
                            "password": "test"
                        }
                        """))
                .andExpect(status().isCreated());
    }
}
