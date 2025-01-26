package com.nigam.springbootexplorer.dto;

import com.nigam.springbootexplorer.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
public class UserDTO {


    @NotBlank(message = "id can not be blank")
    private Long id;

    @NotBlank(message = "First Name can't be null")
    private String firstname;

    @NotBlank(message = "last Name can't be null")
    private String lastname;

    @NotBlank(message = "Username can not be blank")
    private String username;

    @Email
    @NotBlank(message = "Email can't be null")
    String email;


    public static UserDTO make(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
