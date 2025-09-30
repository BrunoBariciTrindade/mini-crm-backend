package com.bruno.cadastrocliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.service.ClienteService;
import com.bruno.cadastrocliente.service.EnderecoService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteModel> cadastrarCliente(@RequestBody ClienteModel cliente) {
        ClienteModel salvo = clienteService.postClient(cliente);
        return ResponseEntity.ok(salvo);
    }

	@GetMapping("/consultarTodosClientes")
	public List<ClienteModel> getAllClients(){
		return clienteService.getAllClients();
	}
	@GetMapping("/consultarClientePorId/{id}")
	public Optional<ClienteModel> getClientById(@PathVariable("id") Long id)
	{
		return clienteService.getClientById(id);
	}
	
	@GetMapping("/exists")
    public boolean verificarSeCpfExiste(@RequestParam String cpf) {
        return clienteService.existsByCpf(cpf);
    }

}
