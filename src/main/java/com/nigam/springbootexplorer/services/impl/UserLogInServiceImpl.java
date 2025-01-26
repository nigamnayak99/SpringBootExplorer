package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.LogInRequestDTO;
import com.nigam.springbootexplorer.dto.LogInResponseDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.entity.User;
import com.nigam.springbootexplorer.services.UserDetailService;
import com.nigam.springbootexplorer.services.UserLogInService;
import com.nigam.springbootexplorer.util.JWTTokenHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLogInServiceImpl implements UserLogInService {

    private final AuthenticationManager authenticationManager;

    private final JWTTokenHelper jwtTokenHelper;

    private final UserDetailService userDetailService;

    @Override
    public LogInResponseDTO login(LogInRequestDTO logInDTO, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getUsername(), logInDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String jwtAccessToken = jwtTokenHelper.generateAccessToken(user);
        String jwtRefreshToken = jwtTokenHelper.generateRefreshToken(user);
        Cookie cookie = new Cookie("refreshToken", jwtRefreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return LogInResponseDTO.builder()
                .userDTO(UserDTO.make(user))
                    .accessToken(jwtAccessToken)
                        .refreshToken(jwtRefreshToken)
                            .build();
    }

    @Override
    public LogInResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtTokenHelper.getUserIdFromToken(refreshToken);
        User user = userDetailService.findByUserId(userId);
        String jwtAccessToken = jwtTokenHelper.generateAccessToken(user);

        return LogInResponseDTO.builder()
                .userDTO(UserDTO.make(user))
                    .accessToken(jwtAccessToken)
                        .refreshToken(refreshToken)
                            .build();
    }
}
