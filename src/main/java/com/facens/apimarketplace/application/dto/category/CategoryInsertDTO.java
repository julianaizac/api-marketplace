package com.facens.apimarketplace.application.dto.category;

import com.facens.apimarketplace.application.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInsertDTO {

    @NotBlank(message = ValidationMessages.REQUIRED_NAME)
    private String name;

    @NotBlank(message = ValidationMessages.REQUIRED_DESCRIPTION)
    private String description;

}
