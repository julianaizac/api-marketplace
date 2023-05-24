package com.facens.apimarketplace.application.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientInsertDTO {

    private String name;
    private String cpf;
    private String email;

}
