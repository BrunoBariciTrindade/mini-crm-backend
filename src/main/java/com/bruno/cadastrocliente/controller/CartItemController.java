package com.bruno.cadastrocliente.controller;
import com.bruno.cadastrocliente.model.CartItem;
import com.bruno.cadastrocliente.model.Product;
import com.bruno.cadastrocliente.repository.CartItemRepository;
import com.bruno.cadastrocliente.repository.ProductRepository;
import com.bruno.cadastrocliente.service.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.bruno.cadastrocliente.Dtos.CarItemDTO;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartItemController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    private final CartItemService service;

    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<CartItem> listar() {
        return service.listar();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarItem(@RequestBody CarItemDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + dto.getProductId()));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());

        CartItem savedItem = cartItemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}