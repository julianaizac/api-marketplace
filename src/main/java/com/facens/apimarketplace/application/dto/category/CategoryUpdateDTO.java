package com.facens.apimarketplace.application.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryUpdateDTO {

    private String name;
    private String description;

}
