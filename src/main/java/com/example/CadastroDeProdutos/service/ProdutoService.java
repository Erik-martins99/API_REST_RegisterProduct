package com.example.CadastroDeProdutos.service;

import com.example.CadastroDeProdutos.domain.dto.FornecedorDTO;
import com.example.CadastroDeProdutos.domain.dto.RegisterProductDTO;
import com.example.CadastroDeProdutos.domain.dto.RequestProductDTO;
import com.example.CadastroDeProdutos.domain.model.Produto;
import com.example.CadastroDeProdutos.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto RegisterProduct(RegisterProductDTO data) {
        var produto = new Produto(data);
        repository.save(produto);
        return produto;
    }

    public Page<RequestProductDTO> findAll(Pageable paginacao) {
        Page<Produto> produtos = repository.findAllByAtivoTrue(paginacao);
        return produtos
                .map(RequestProductDTO:: new);
    }

    public RegisterProductDTO findById(Long id) {
        Optional<Produto> produto = repository.findById(id);
        if(produto.isPresent()){
            var p = produto.get();
            return new RegisterProductDTO(p.getNome(),
                    p.getPreco(), p.getMarca(),
                    new FornecedorDTO(p.getFornecedor()));
        }
        return null;
    }

    public Produto updateProduct(RequestProductDTO data) {
        var produto = repository.getReferenceById(data.id());
        produto.update(data);
        return produto;
    }

    public Produto deactivateProduct(Long id) {
        var produto = repository.getReferenceById(id);
        produto.deactivate();
        repository.save(produto);
        return produto;
    }

    public Produto activateProduct(Long id) {
        var produto = repository.getReferenceById(id);
        produto.activate();
        repository.save(produto);
        return produto;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
