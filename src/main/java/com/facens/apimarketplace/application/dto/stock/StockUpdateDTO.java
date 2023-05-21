package com.facens.apimarketplace.application.dto.stock;

import com.facens.apimarketplace.application.validation.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateDTO {

    @NotNull(message = ValidationMessages.REQUIRED_AMOUNT)
    private Integer amount;

}
