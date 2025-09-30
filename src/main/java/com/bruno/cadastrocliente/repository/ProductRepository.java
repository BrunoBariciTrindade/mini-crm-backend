package com.bruno.cadastrocliente.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.cadastrocliente.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
