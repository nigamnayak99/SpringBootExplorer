package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.LogInDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.entity.User;
import com.nigam.springbootexplorer.services.UserLogInService;
import com.nigam.springbootexplorer.util.JWTTokenHelper;
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

    @Override
    public UserDTO login(LogInDTO logInDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getUsername(), logInDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user);
        UserDTO userDTO = UserDTO.make(user);
        userDTO.setToken(jwtToken);
        return userDTO;
    }
}
