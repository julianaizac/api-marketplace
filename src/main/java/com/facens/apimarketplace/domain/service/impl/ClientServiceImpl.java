package com.facens.apimarketplace.domain.service.impl;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.factories.ClientFactory;
import com.facens.apimarketplace.infrastructure.repository.ClientRepository;
import com.facens.apimarketplace.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public ClientDTO saveClient(ClientInsertDTO clientInsertDTO) {
        Client client = ClientFactory.createClient(clientInsertDTO);
        Client clientSaved = repository.save(client);
        return ClientFactory.createClientDTO(clientSaved);
    }
}
