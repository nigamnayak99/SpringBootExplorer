package com.nigam.springbootexplorer.handlers;

import com.nigam.springbootexplorer.entity.User;
import com.nigam.springbootexplorer.services.UserDetailService;
import com.nigam.springbootexplorer.util.JWTTokenHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserDetailService userDetailService;
    private final JWTTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        Optional<User> userExists = userDetailService.findByUserEmail(email);
        log.info(oAuth2User.getAttributes().toString());
        User user ;
        if (userExists.isPresent()) {
            user = userExists.get();
        } else {
            user = User.builder()
                    .email(email)
                    .lastname(oAuth2User.getAttribute("family_name"))
                    .firstname(oAuth2User.getAttribute("given_name"))
                    .build();
            user = userDetailService.save(user);
        }

        String accessToken = jwtTokenHelper.generateAccessToken(user);
        String refreshToken = jwtTokenHelper.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        String frontendurl = "http://localhost:9005/home.html?token="+accessToken;

        getRedirectStrategy().sendRedirect(request, response, frontendurl);

    }

}
