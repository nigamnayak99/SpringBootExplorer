package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.LogInRequestDTO;
import com.nigam.springbootexplorer.dto.LogInResponseDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface UserLogInService {
    LogInResponseDTO login(LogInRequestDTO logInDTO, HttpServletResponse response);
    LogInResponseDTO refreshToken(String refreshToken);
}
