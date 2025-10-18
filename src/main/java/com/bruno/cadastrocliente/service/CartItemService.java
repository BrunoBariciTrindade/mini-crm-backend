package com.bruno.cadastrocliente.service;

import com.bruno.cadastrocliente.model.CartItem;
import com.bruno.cadastrocliente.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository repository;
   
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> listar() {
        return repository.findAll();
    }

    public CartItem salvar(CartItem item) {
        return repository.save(item);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}