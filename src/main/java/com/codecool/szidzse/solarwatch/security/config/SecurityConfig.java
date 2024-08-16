package com.codecool.szidzse.solarwatch.security.config;

import com.codecool.szidzse.solarwatch.security.filter.JwtAuthenticationFilter;
import com.codecool.szidzse.solarwatch.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/cities/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/cities/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/cities/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/cities/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/sunrise-sunset/any").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/sunrise-sunset/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/sunrise-sunset/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/sunrise-sunset/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsServiceImpl)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
