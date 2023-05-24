package com.facens.apimarketplace.domain.service;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;

public interface ClientService {
    ClientDTO saveClient(ClientInsertDTO clientInsertDTO);
}
