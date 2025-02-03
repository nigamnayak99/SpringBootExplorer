package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserDetailService extends UserDetailsService {

    UserDTO signUp(SignUpDTO signUpDTO);
    User findByUserId(Long userId);
    Optional<User> findByUserEmail(String userEmail);
    User save(User newUser);
}
