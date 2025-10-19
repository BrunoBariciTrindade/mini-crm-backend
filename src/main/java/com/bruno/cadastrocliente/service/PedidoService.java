package com.bruno.cadastrocliente.service;

import com.bruno.cadastrocliente.model.CartItem;
import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.model.ItemPedido;
import com.bruno.cadastrocliente.model.Pedido;
import com.bruno.cadastrocliente.repository.CartItemRepository;
import com.bruno.cadastrocliente.repository.ClienteRepository;
import com.bruno.cadastrocliente.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CartItemRepository cartItemRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository,
            CartItemRepository cartItemRepository,
            ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.cartItemRepository = cartItemRepository;
        this.clienteRepository = clienteRepository;
    }

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

      public Pedido finalizarPedido(Long clienteId) {

        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<CartItem> carrinho = cartItemRepository.findByCliente(cliente);

        if (carrinho.isEmpty()) {
            throw new RuntimeException("Carrinho está vazio!");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(0.0);

        for (CartItem c : carrinho) {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduct(c.getProduct());
            item.setQuantidade(c.getQuantidade());
            item.setPrecoUnitario(c.getProduct().getPrice());

            pedido.getItens().add(item);

            double subtotal = c.getQuantidade() * c.getProduct().getPrice();
            pedido.setTotal(pedido.getTotal() + subtotal);
        }

        // Limpa o carrinho após criar o pedido
        cartItemRepository.deleteAll(carrinho);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return pedidoRepository.findByCliente(cliente);
    }

    
}
