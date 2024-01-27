package com.example.CadastroDeProdutos.controller;

import com.example.CadastroDeProdutos.domain.dto.RegisterProductDTO;
import com.example.CadastroDeProdutos.domain.dto.RequestProductDTO;
import com.example.CadastroDeProdutos.domain.dto.StatusProdutoDTO;
import com.example.CadastroDeProdutos.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity RegisterProduct(@RequestBody @Valid RegisterProductDTO data, UriComponentsBuilder uriBuilder) {
        var produto = service.RegisterProduct(data);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequestProductDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<RequestProductDTO>> findAll(Pageable paginacao){
        var produtos = service.findAll(paginacao);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        var produto = service.findById(id);
        if(produto != null){
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody RequestProductDTO data) {
        var produto = service.updateProduct(data);
        return  ResponseEntity.ok(new RequestProductDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deactivateProduct(@PathVariable Long id) {
        var produto = service.deactivateProduct(id);
        return ResponseEntity.ok(new StatusProdutoDTO(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity activateProduct(@PathVariable Long id) {
        var produto = service.activateProduct(id);
        return ResponseEntity.ok(new StatusProdutoDTO(produto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
