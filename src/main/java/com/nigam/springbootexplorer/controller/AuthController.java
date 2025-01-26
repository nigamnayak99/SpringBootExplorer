package com.nigam.springbootexplorer.controller;

import com.nigam.springbootexplorer.dto.LogInRequestDTO;
import com.nigam.springbootexplorer.dto.LogInResponseDTO;
import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.rest.RestResponse;
import com.nigam.springbootexplorer.services.UserDetailService;
import com.nigam.springbootexplorer.services.UserLogInService;
import com.nigam.springbootexplorer.util.Utility;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailService userDetailService;

    private final UserLogInService userLogInService;

    @PostMapping("/signup")
    public RestResponse signUp(@RequestBody SignUpDTO signUpDTO) {
        log.debug("---------signup: Begin----");
        RestResponse restResponse = Utility.initializeRestResponse(null);
        UserDTO userDTO = userDetailService.signUp(signUpDTO);
        restResponse.setResponseData(userDTO);
        log.debug("---------signup: End------");
        return restResponse;
    }

    @PostMapping("/login")
    public RestResponse login(@RequestBody LogInRequestDTO logInRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        log.debug("---------login: Begin----");
        RestResponse restResponse = Utility.initializeRestResponse(null);
        LogInResponseDTO logInResponseDTO = userLogInService.login(logInRequestDTO, response);
        restResponse.setResponseData(logInResponseDTO);
        log.debug("---------login: End------");
        return restResponse;
    }

    @PostMapping("/refresh")
    public RestResponse refresh(HttpServletRequest request) {
        log.debug("---------refresh: Begin----");
        RestResponse restResponse = Utility.initializeRestResponse(null);
        String refreshToken =  Arrays.stream(request.getCookies())
                                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                                    .findFirst()
                                    .map(cookie -> cookie.getValue())
                                    .orElseThrow(()-> new RuntimeException("Refresh Token Could not be found"));
        LogInResponseDTO logInResponseDTO = userLogInService.refreshToken(refreshToken);
        restResponse.setResponseData(logInResponseDTO);
        log.debug("---------refresh: End------");
        return restResponse;
    }
}
