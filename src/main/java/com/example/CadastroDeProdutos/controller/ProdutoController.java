package com.example.CadastroDeProdutos.controller;

import com.example.CadastroDeProdutos.domain.dto.RegisterProductDTO;
import com.example.CadastroDeProdutos.domain.dto.RequestProductDTO;
import com.example.CadastroDeProdutos.domain.dto.StatusProdutoDTO;
import com.example.CadastroDeProdutos.domain.model.Produto;
import com.example.CadastroDeProdutos.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity RegisterProduct(@RequestBody @Valid RegisterProductDTO data, UriComponentsBuilder uriBuilder) {
        var produto = new Produto(data);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequestProductDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<RequestProductDTO>> findAll(Pageable paginacao){
        var produtos = repository.findAllByAtivoTrue(paginacao);
        return ResponseEntity.ok(produtos.map(RequestProductDTO:: new));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new RegisterProductDTO(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody RequestProductDTO data) {
        var produto = repository.getReferenceById(data.id());
        produto.update(data);
        return  ResponseEntity.ok(new RequestProductDTO(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivateProduct(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.deactivate();
        return ResponseEntity.ok(new StatusProdutoDTO(produto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity activateProduct(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.activate();
        return ResponseEntity.ok(new StatusProdutoDTO(produto));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
