package com.example.CadastroDeProdutos.domain.dto;

import com.example.CadastroDeProdutos.domain.model.Fornecedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(@NotBlank String fornecedor,
                            @NotBlank @Email String email,
                            @NotBlank String pais,
                            @NotBlank String uf) {

    public FornecedorDTO(Fornecedor fornecedor) {
        this(fornecedor.getFornecedor(),
                fornecedor.getEmail(),
                fornecedor.getPais(),
                fornecedor.getUf());
    }
}
