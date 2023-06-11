package com.facens.apimarketplace.domain.service;
import com.facens.apimarketplace.domain.service.impl.ClientServiceImpl;
import com.facens.apimarketplace.infrastructure.repository.ClientRepository;
import com.facens.apimarketplace.mocks.MocksClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    @InjectMocks
    @Spy
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
    }

    @DisplayName("Deve salvar cliente")
    @Test
    void teste1(){
        when(repository.save(any()))
                .thenReturn(MocksClient.createClient(MocksClient.CLIENT_ID, MocksClient.CLIENT_NAME,
                        MocksClient.CLIENT_CPF, MocksClient.CLIENT_EMAIL));
        Assertions.assertNotNull(service.saveClient(MocksClient.createClientInsert(MocksClient.CLIENT_NAME,
                MocksClient.CLIENT_CPF, MocksClient.CLIENT_EMAIL)));
    }

}
