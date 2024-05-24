package com.nalas.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegiserDTO {
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    //TODO: Patter seguro de contraseña
    @NotBlank
    private String password;
}
