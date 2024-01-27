package com.example.CadastroDeProdutos.domain.dto;

import com.example.CadastroDeProdutos.domain.model.Produto;

public record StatusProdutoDTO(Long id,
                               String nome,
                               boolean ativo) {

    public StatusProdutoDTO(Produto p) {
        this(p.getId(), p.getNome(), p.isAtivo());
    }
}
