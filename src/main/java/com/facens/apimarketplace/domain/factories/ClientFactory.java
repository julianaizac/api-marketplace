package com.facens.apimarketplace.domain.factories;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.valueobject.CPF;
import com.facens.apimarketplace.domain.valueobject.Email;

import java.util.Objects;

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

    public static ClientDTO createFromModel(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf().getCpf())
                .email(client.getEmail().getEmail())
                .build();
    }

    public static Client createFromUpdateDTO(ClientUpdateDTO clientUpdateDTO, ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .cpf(new CPF(clientDTO.getCpf()))
                .email(new Email(Objects.nonNull(clientUpdateDTO.getEmail()) ? clientUpdateDTO.getEmail() : clientDTO.getEmail()))
                .build();
    }
}
