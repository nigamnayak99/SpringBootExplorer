package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.LogInDTO;
import com.nigam.springbootexplorer.dto.UserDTO;

public interface UserLogInService {
    public UserDTO login(LogInDTO logInDTO);
}
