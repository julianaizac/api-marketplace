package com.facens.apimarketplace.application.dto.product;

import com.facens.apimarketplace.application.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductInsertDTO {

    @NotBlank(message = ValidationMessages.REQUIRED_NAME)
    private String name;

    @NotBlank(message = ValidationMessages.REQUIRED_DESCRIPTION)
    private String description;

    @NotBlank(message = ValidationMessages.REQUIRED_PRICE)
    private BigDecimal price;

    @NotBlank(message = ValidationMessages.REQUIRED_CATEGORYID)
    private UUID categoryId;

}
