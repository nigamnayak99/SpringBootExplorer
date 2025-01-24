package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    UserDTO signUp(SignUpDTO signUpDTO);

}
