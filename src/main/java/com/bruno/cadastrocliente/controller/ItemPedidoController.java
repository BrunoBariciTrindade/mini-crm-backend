package com.bruno.cadastrocliente.controller;

import com.bruno.cadastrocliente.model.ItemPedido;
import com.bruno.cadastrocliente.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itempedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping("/salvar")
    public ResponseEntity<ItemPedido> salvarItem(@RequestBody ItemPedido itemPedido) {
        ItemPedido itemSalvo = itemPedidoService.salvarItem(itemPedido);
        return ResponseEntity.ok(itemSalvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemPedido>> listarItens() {
        List<ItemPedido> itens = itemPedidoService.listarItens();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id) {
        ItemPedido itemPedido = itemPedidoService.buscarPorId(id);
        if (itemPedido != null) {
            return ResponseEntity.ok(itemPedido);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemPedidoService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }
}
