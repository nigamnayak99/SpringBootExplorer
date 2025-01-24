package com.nigam.springbootexplorer.controller;

import com.nigam.springbootexplorer.dto.LogInDTO;
import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.rest.RestResponse;
import com.nigam.springbootexplorer.services.UserDetailService;
import com.nigam.springbootexplorer.services.UserLogInService;
import com.nigam.springbootexplorer.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RestResponse login(@RequestBody LogInDTO logInDTO) {
        log.debug("---------login: Begin----");
        RestResponse restResponse = Utility.initializeRestResponse(null);
        UserDTO userDTO = userLogInService.login(logInDTO);
        restResponse.setResponseData(userDTO);
        log.debug("---------login: End------");
        return restResponse;
    }
}
