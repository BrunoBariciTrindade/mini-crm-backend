package com.bruno.cadastrocliente.repository;


import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
 List<Pedido> findByCliente(ClienteModel cliente);

}