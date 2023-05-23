package com.facens.apimarketplace.domain.objectvalue;

import com.facens.apimarketplace.application.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Data
@Builder
@Embeddable
@NoArgsConstructor
public class Price {

    @Column(name = "price")
    private BigDecimal price;

    public Price(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Price não pode ser menor igual que 0", HttpStatus.BAD_REQUEST);
        }
        this.price = price;
    }
}
