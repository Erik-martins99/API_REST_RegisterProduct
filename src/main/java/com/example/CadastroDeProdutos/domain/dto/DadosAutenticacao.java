package com.example.CadastroDeProdutos.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank(message = "Login invalido!") String login,
                                @NotBlank(message = "Login invalido!") String senha) {
}
