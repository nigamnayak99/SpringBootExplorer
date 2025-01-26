package com.nigam.springbootexplorer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponseDTO {

    @NotBlank(message = "UserDTO can not be null")
    private UserDTO userDTO;

    @NotBlank(message = "AccessToken can not be blank")
    private String accessToken;

    @NotBlank(message = "RefreshToken can not be blank")
    private String refreshToken;

}
