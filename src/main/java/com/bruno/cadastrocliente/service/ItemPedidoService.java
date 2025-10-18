package com.bruno.cadastrocliente.service;

import com.bruno.cadastrocliente.model.ItemPedido;
import com.bruno.cadastrocliente.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedido salvarItem(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public List<ItemPedido> listarItens() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido buscarPorId(Long id) {
        return itemPedidoRepository.findById(id).orElse(null);
    }

    public void deletarItem(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}