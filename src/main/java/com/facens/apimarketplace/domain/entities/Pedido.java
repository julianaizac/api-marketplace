package com.facens.apimarketplace.domain.entities;

import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
public class Pedido {

    private UUID id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataConclusao;
    private BigDecimal total;

}
