package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    UserDTO signUp(SignUpDTO signUpDTO);
    User findByUserId(Long userId);
}
