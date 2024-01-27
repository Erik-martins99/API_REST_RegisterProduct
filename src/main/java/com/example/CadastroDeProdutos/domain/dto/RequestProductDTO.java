package com.example.CadastroDeProdutos.domain.dto;

import com.example.CadastroDeProdutos.domain.model.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductDTO(@NotNull Long id,
                                @NotBlank String nome,
                                @NotNull Double preco,
                                @NotBlank String marca,
                                @Valid FornecedorDTO fornecedor) {

    public RequestProductDTO(Produto p) {
        this(p.getId(), p.getNome(), p.getPreco(), p.getMarca(),
                new FornecedorDTO(p.getFornecedor()));
    }
}
