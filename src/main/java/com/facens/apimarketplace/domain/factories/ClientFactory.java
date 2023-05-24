package com.facens.apimarketplace.domain.factories;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.valueobject.CPF;
import com.facens.apimarketplace.domain.valueobject.Email;

public class ClientFactory {

    public static Client createClient(ClientInsertDTO clientInsertDTO){
        return Client.builder()
                .name(clientInsertDTO.getName())
                .cpf(CPF.builder().cpf(clientInsertDTO.getCpf()).build())
                .email(Email.builder().email(clientInsertDTO.getEmail()).build())
                .build();
    }

    public static ClientDTO createClientDTO(Client client){
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf().getCpf())
                .email(client.getEmail().getEmail())
                .build();

    }

}
