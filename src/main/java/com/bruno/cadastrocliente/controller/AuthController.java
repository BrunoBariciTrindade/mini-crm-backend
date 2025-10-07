package com.bruno.cadastrocliente.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.repository.ClienteRepository;
import com.bruno.cadastrocliente.service.ClienteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<ClienteModel> userOpt = clienteRepository.findBycpf(loginRequest.getCpf());

        if(userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }

        ClienteModel user = userOpt.get();

        // Verifica se a senha bate
        if(passwordEncoder.matches(loginRequest.getSenha(), user.getSenha())) {
            // Aqui você pode gerar token JWT ou retornar info do usuário
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClienteModel request) {
        // Verifica se já existe um cliente com o mesmo CPF
        if (clienteRepository.findBycpf(request.getCpf()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setSenha(passwordEncoder.encode(request.getSenha())); // Encripta a senha

        // Salva o cliente
        clienteService.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
    

    public static class LoginRequest {
        private String cpf;
        private String senha;
        // getters e setters
		public Object getCpf() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
    }
}
