package com.example.CadastroDeProdutos.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterProductDTO(@NotBlank String nome,
                                 @NotNull Double preco,
                                 @NotBlank String marca,
                                 @Valid FornecedorDTO fornecedor) {
}
