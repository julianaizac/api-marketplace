package com.facens.apimarketplace.domain.service.impl;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.application.exception.BadRequestException;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.factories.ClientFactory;
import com.facens.apimarketplace.infrastructure.repository.ClientRepository;
import com.facens.apimarketplace.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    private String notFoundMessage = "Não existe o cliente na base de dados.";

    public ClientDTO getClientById(UUID id) {
        Client client = repository.findById(id).orElseThrow(() -> new BadRequestException(notFoundMessage, BAD_REQUEST));
        return ClientFactory.createFromModel(client);
    }

    @Override
    public ClientDTO saveClient(ClientInsertDTO clientInsertDTO) {
        Client client = ClientFactory.createClient(clientInsertDTO);
        Client clientSaved = repository.save(client);
        return ClientFactory.createClientDTO(clientSaved);
    }

    @Override
    public ClientDTO updateClientById(UUID id, ClientUpdateDTO clientUpdateDTO) {
        ClientDTO existingClient = getClientById(id);
        Client client = ClientFactory.createFromUpdateDTO(clientUpdateDTO, existingClient);
        Client clientSaved = repository.save(client);
        return ClientFactory.createFromModel(clientSaved);
    }


}
