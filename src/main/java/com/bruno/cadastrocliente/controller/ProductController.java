package com.bruno.cadastrocliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bruno.cadastrocliente.model.Product;
import com.bruno.cadastrocliente.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200") // frontend Angular
public class ProductController {

    @Autowired
    private ProductRepository repository;

   @GetMapping
    public ResponseEntity<List<Product>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

       @PostMapping("/salvar")
    public ResponseEntity<Product> salvar(@RequestBody Product product) {
        return ResponseEntity.ok(repository.save(product));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
