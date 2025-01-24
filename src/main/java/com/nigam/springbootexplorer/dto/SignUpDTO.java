package com.nigam.springbootexplorer.dto;

import com.nigam.springbootexplorer.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpDTO {

    @NotBlank(message = "First Name can't be null")
    private String firstname;

    @NotBlank(message = "last Name can't be null")
    private String lastname;

    @NotBlank(message = "User Name can't be null")
    String username;

    @Email
    @NotBlank(message = "Email can't be null")
    String email;

    @NotBlank(message = "Password can't be null")
    String password;

    public static User makeUser(SignUpDTO signUpDTO) {
        return User.builder()
                .firstname(signUpDTO.getFirstname())
                .lastname(signUpDTO.getLastname())
                .username(signUpDTO.getUsername())
                .email(signUpDTO.getEmail())
                .password(signUpDTO.getPassword()).build();
    }

}
