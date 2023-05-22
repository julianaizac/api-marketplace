package com.facens.apimarketplace.application.controller;

import ch.qos.logback.core.net.ObjectWriter;
import com.facens.apimarketplace.domain.service.CategoryService;
import com.facens.apimarketplace.mocks.MocksCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import static com.facens.apimarketplace.mocks.MocksCategory.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
class CategoryControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    @Spy
    private CategoryController controller;

    @Mock
    private CategoryService service;

    private static final String URI = "/categories";

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Deve buscar categorias")
    @Test
    void teste1() throws Exception {
        when(service.getCategories()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar categoria por id")
    @Test
    void teste2() throws Exception {
        when(service.getCategoryById(CATEGORY_ID))
                .thenReturn(MocksCategory.createCategoryDTO(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", CATEGORY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
