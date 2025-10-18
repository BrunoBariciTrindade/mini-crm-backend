package com.bruno.cadastrocliente.service;


import com.bruno.cadastrocliente.model.Pedido;
import com.bruno.cadastrocliente.repository.PedidoRepository;       
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
    
@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        // Certifique-se de que cada item tenha o pedido definido
        if (pedido.getItens() != null) {
            pedido.getItens().forEach(item -> item.setPedido(pedido));
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}