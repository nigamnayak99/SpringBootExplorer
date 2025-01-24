package com.nigam.springbootexplorer.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement(sessionConfigPolicy ->
                        sessionConfigPolicy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                    .csrf(AbstractHttpConfigurer::disable);   /* Disable CSRF Config */
                     /* Disable Default Form Login */
//                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


    /* Disable InMemoryUserDetail */
//    @Bean
//    UserDetailsService inMemoryUserDetailsService() {
//        UserDetails inMemoryUser1 = User.withUsername("Anu")
//                .password(encode().encode("pass1")) // Use encode() here
//                .roles("USER")
//                .build();
//
//        UserDetails inMemoryAdminUser1 = User.withUsername("Nigam2")
//                .password(encode().encode("pass2")) // Use encode() here
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(inMemoryUser1, inMemoryAdminUser1);
//    }

    @Bean
    PasswordEncoder encode() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
