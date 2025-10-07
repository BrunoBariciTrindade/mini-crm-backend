package com.bruno.cadastrocliente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.cadastrocliente.Dtos.LoginDto.LoginDTO;
import com.bruno.cadastrocliente.config.JwtUtil;
import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.service.ClienteService;
import com.bruno.cadastrocliente.service.EnderecoService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired 
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteModel> cadastrarCliente(@RequestBody ClienteModel cliente) {
        ClienteModel salvo = clienteService.save(cliente);
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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
	    Optional<ClienteModel> clienteOpt = clienteService.findByuserName(loginDTO.getUserName());

	    if (clienteOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
	    }

	    ClienteModel cliente = clienteOpt.get();

	    // Verifica a senha usando o bean injetado
	    if (!passwordEncoder.matches(loginDTO.getSenha(), cliente.getSenha())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
	    }

	    // Gera o token JWT
	    String token = jwtUtil.generateToken(cliente.getUserName());

	    // Retorna apenas os dados necessários
	    Map<String, Object> response = new HashMap<>();
	    response.put("token", token);
	    response.put("clienteId", cliente.getCliente_id());
	    response.put("nome", cliente.getNome());

	    return ResponseEntity.ok(response);
	}



}
