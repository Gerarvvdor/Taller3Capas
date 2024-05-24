package com.nalas.pnccontrollers.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveCategoryDTO {
    @NotEmpty
    @Pattern(regexp = "^CT_[A-Z]{3}$")
    private String code;

    @NotEmpty
    private String name;
}
