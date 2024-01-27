package com.example.CadastroDeProdutos.domain.model;

import com.example.CadastroDeProdutos.domain.dto.FornecedorDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class Fornecedor {
    private String fornecedor;
    private String email;
    private String pais;
    private String uf;

    public Fornecedor() {}

    public Fornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor.fornecedor();
        this.email = fornecedor.email();
        this.pais = fornecedor.pais();
        this.uf = fornecedor.uf();
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void update(FornecedorDTO fornecedor) {
        if(fornecedor.fornecedor() != null) {
            this.fornecedor = fornecedor.fornecedor();
        }
        if(fornecedor.email() != null) {
            this.email = fornecedor.email();
        }
        if(fornecedor.pais() != null) {
            this.pais = fornecedor.pais();
        }
        if(fornecedor.uf() != null) {
            this.uf = fornecedor.uf();
        }
    }
}
