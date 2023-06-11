package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.application.dto.client.ClientDTO;
import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.application.dto.product.ProductDTO;
import com.facens.apimarketplace.application.dto.product.ProductUpdateDTO;
import com.facens.apimarketplace.domain.entities.Client;
import com.facens.apimarketplace.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientInsertDTO clientInsertDTO){
        ClientDTO client = service.saveClient(clientInsertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClientById(@PathVariable UUID id, @RequestBody ClientUpdateDTO clientUpdateDTO){
        ClientDTO client = service.updateClientById(id, clientUpdateDTO);
        return ResponseEntity.ok(client);
    }

}
