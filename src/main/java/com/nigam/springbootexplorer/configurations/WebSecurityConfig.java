package com.nigam.springbootexplorer.configurations;


import com.nigam.springbootexplorer.filters.JwtAuthFilter;
import com.nigam.springbootexplorer.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity the HttpSecurity object for configuring security
     * @return the configured SecurityFilterChain
     * @throws Exception in case of configuration issues
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                /*
                 * Configure session management to use stateless sessions.
                 * Stateless sessions are essential for RESTful APIs since
                 * they do not maintain any server-side session state.
                 */
                .sessionManagement(sessionConfigPolicy ->
                        sessionConfigPolicy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                /*
                 * Define authorization rules for incoming requests:
                 * - Allow unrestricted access to all endpoints under "/auth/**".
                 * - Require authentication for all other requests.
                 */
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/home.html").permitAll()
                        .anyRequest().authenticated()
                )

                /*
                 * Disable CSRF protection.
                 * This is safe for stateless REST APIs since they do not use cookies for authentication.
                 * CSRF protection is more relevant for stateful web applications with user sessions.
                 */
                .csrf(AbstractHttpConfigurer::disable)

                /*
                 * Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter.
                 * This ensures that:
                 * - Incoming requests are processed by the JwtAuthFilter first.
                 * - Authenticated users are set in the SecurityContext.
                 * - UsernamePasswordAuthenticationFilter is only triggered if necessary.
                 */
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .oauth2Login(oauth2config -> oauth2config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );

        /*
         * (Optional) Uncomment this line to enable the default form-based login mechanism.
         * This is typically not needed for REST APIs, but it might be useful for web apps.
         */
        // .formLogin(Customizer.withDefaults());

        // Build and return the configured SecurityFilterChain
        return httpSecurity.build();
    }



    /*
     * Disable InMemoryUserDetail Service.
     * This method demonstrates an example of an in-memory user store for authentication.
     * It's commented out because in-memory user details are not ideal for production
     * environments due to lack of persistence and scalability.
     */
//    @Bean
//    UserDetailsService inMemoryUserDetailsService() {
//
//        /*
//         * Create an in-memory user with the role "USER".
//         * The password is encoded using the password encoder from the encode() method.
//         */
//        UserDetails inMemoryUser1 = User.withUsername("Anu")
//                .password(encode().encode("pass1")) // Use encode() to hash passwords securely
//                .roles("USER")
//                .build();
//
//        /*
//         * Create another in-memory user with the role "ADMIN".
//         * Similarly, the password is securely encoded.
//         */
//        UserDetails inMemoryAdminUser1 = User.withUsername("Nigam2")
//                .password(encode().encode("pass2")) // Use encode() here
//                .roles("ADMIN")
//                .build();
//
//        /*
//         * Return an InMemoryUserDetailsManager containing the defined users.
//         * This is primarily used for testing or small-scale applications.
//         */
//        return new InMemoryUserDetailsManager(inMemoryUser1, inMemoryAdminUser1);
//    }

    /**
     * Exposes the AuthenticationManager bean.
     * This is necessary for the application to manage authentication.
     *
     * @param authenticationConfiguration provides the current authentication configuration
     * @return the AuthenticationManager bean
     * @throws Exception in case of configuration issues
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        /*
         * Retrieve and return the AuthenticationManager from the provided AuthenticationConfiguration.
         * This enables the application to handle authentication with custom or default configurations.
         */
        return authenticationConfiguration.getAuthenticationManager();
    }

}
