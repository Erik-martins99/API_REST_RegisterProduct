package com.example.CadastroDeProdutos.domain.model;

import com.example.CadastroDeProdutos.domain.dto.RegisterProductDTO;
import com.example.CadastroDeProdutos.domain.dto.RequestProductDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private String marca;
    @Embedded
    private Fornecedor fornecedor;
    private boolean ativo;

    public Produto() {}

    public Produto(RegisterProductDTO data) {
        this.ativo = true;
        this.nome = data.nome();
        this.preco = data.preco();
        this.marca = data.marca();
        this.fornecedor = new Fornecedor(data.fornecedor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void update(RequestProductDTO data) {
        if(data.nome() != null){
            this.nome = data.nome();
        }
        if(data.preco() != null){
            this.preco = data.preco();
        }
        if(data.marca() != null){
            this.marca = data.marca();
        }
        if(data.fornecedor() != null){
            this.fornecedor.update(data.fornecedor());
        }

    }

    public void deactivate() {
        this.ativo = false;
    }

    public void activate() {
        this.ativo = true;
    }
}
