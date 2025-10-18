package com.bruno.cadastrocliente.repository;
import com.bruno.cadastrocliente.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
