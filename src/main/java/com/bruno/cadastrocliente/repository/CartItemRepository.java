package com.bruno.cadastrocliente.repository;

import com.bruno.cadastrocliente.model.CartItem;
import com.bruno.cadastrocliente.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    
    List<CartItem> findByCliente(ClienteModel cliente);

}
