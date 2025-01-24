package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.SignUpDTO;
import com.nigam.springbootexplorer.dto.UserDTO;
import com.nigam.springbootexplorer.entity.User;
import com.nigam.springbootexplorer.exceptions.ResourceNotFoundException;
import com.nigam.springbootexplorer.repository.UserRepository;
import com.nigam.springbootexplorer.services.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid UserName"));
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> existingUserWithEmail = userRepository.findByEmail(signUpDTO.getEmail());
        if (existingUserWithEmail.isPresent()) throw new BadCredentialsException("User Already exists with user name");
        User user = SignUpDTO.makeUser(signUpDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserDTO.make(user);
    }

}
