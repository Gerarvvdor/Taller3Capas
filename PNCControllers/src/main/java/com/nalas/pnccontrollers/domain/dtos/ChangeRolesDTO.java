package com.nalas.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ChangeRolesDTO {

    @NotBlank
    private String identifier;

    @NotNull
    private List<@NotBlank String> roles;

}
