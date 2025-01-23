package com.nigam.springbootexplorer.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests( auth -> auth
                        .requestMatchers("/products").permitAll()
                        .requestMatchers("/employee/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService inMemoryUserDetailsService() {
        UserDetails inMemoryUser1 = User.withUsername("Anu")
                .password(encode().encode("pass1")) // Use encode() here
                .roles("USER")
                .build();

        UserDetails inMemoryAdminUser1 = User.withUsername("Nigam2")
                .password(encode().encode("pass2")) // Use encode() here
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(inMemoryUser1, inMemoryAdminUser1);
    }

    @Bean
    PasswordEncoder encode() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
