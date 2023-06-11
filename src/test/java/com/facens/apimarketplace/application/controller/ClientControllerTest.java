package com.facens.apimarketplace.application.controller;

import com.facens.apimarketplace.application.dto.client.ClientInsertDTO;
import com.facens.apimarketplace.application.dto.client.ClientUpdateDTO;
import com.facens.apimarketplace.domain.service.impl.ClientServiceImpl;
import com.facens.apimarketplace.mocks.MocksClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
class ClientControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    @Spy
    private ClientController controller;

    @Mock
    private ClientServiceImpl service;

    private static final String URI = "/clients";

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter  ow;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @DisplayName("Deve salvar cliente")
    @Test
    void teste1() throws Exception{

        ClientInsertDTO clientInsertDTO = MocksClient.createClientInsert(MocksClient.CLIENT_NAME, MocksClient.CLIENT_CPF,
                MocksClient.CLIENT_EMAIL) ;
        String requestBody = ow.writeValueAsString(clientInsertDTO);

        Mockito.when(service.saveClient(any())).thenReturn(MocksClient.createClientDTO(MocksClient.CLIENT_ID,
                MocksClient.CLIENT_NAME, MocksClient.CLIENT_CPF, MocksClient.CLIENT_EMAIL));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

    }

    @DisplayName("Deve editar cliente")
    @Test
    void teste2() throws Exception{

        ClientUpdateDTO clientUpdateDTO = MocksClient.createClientUpdateDTO(MocksClient.CLIENT_EMAIL);
        String requestBody = ow.writeValueAsString(clientUpdateDTO);

        Mockito.when(service.updateClientById(any(), any()))
                .thenReturn(MocksClient.createClientDTO(MocksClient.CLIENT_ID, MocksClient.CLIENT_NAME,
                        MocksClient.CLIENT_CPF, MocksClient.CLIENT_EMAIL));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.patch(URI + ("/{id}"), MocksClient.CLIENT_ID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }

}
