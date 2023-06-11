package com.facens.apimarketplace.mocks;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.valueobject.CPF;
import com.facens.apimarketplace.domain.valueobject.Email;

import java.util.UUID;

public class MocksClient {

    public static final UUID CLIENT_ID = UUID.randomUUID();
    public static final String CLIENT_NAME = "João";
    public static final String CLIENT_CPF = "908.031.702-03";
    public static final String CLIENT_EMAIL = "joao@outlook.com";

    public static Client createClient(UUID clientId, String clientName, String clientCpf, String clientEmail){
        return Client.builder()
                .id(clientId)
                .name(clientName)
                .cpf(new CPF(clientCpf))
                .email(new Email(clientEmail))
                .build();
    }

    public static ClientDTO createClientDTO(UUID clientId, String clientName, String clientCpf, String clientEmail){
        return ClientDTO.builder()
                .id(clientId)
                .name(clientName)
                .cpf(clientCpf)
                .email(clientEmail)
                .build();
    }

    public static ClientInsertDTO createClientInsert(String clientName, String clientCpf, String clientEmail){
        return ClientInsertDTO.builder()
                .name(clientName)
                .cpf(clientCpf)
                .email(clientEmail)
                .build();
    }

    public static ClientUpdateDTO createClientUpdateDTO(String clientEmail){
        return ClientUpdateDTO.builder()
                .email(clientEmail)
                .build();
    }


}
