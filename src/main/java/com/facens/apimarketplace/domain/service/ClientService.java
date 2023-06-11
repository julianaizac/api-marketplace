package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.application.dto.product.ProductDTO;

import java.util.UUID;

public interface ClientService {
    ClientDTO saveClient(ClientInsertDTO clientInsertDTO);
    ClientDTO updateClientById(UUID id, ClientUpdateDTO clientUpdateDTO);
}
