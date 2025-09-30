package com.bruno.cadastrocliente.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.model.Endereco;
import com.bruno.cadastrocliente.repository.ClienteRepository;
import com.bruno.cadastrocliente.repository.EnderecoRepository;

@Service
public class ClienteService {
    @Autowired
	 ClienteRepository clienteRepository;
     Endereco endereco;
    @Autowired
    EnderecoService enderecoService;
    @Autowired
    EnderecoRepository enderecoRepository;
    
    public List<ClienteModel> getAllClients(){
    	return clienteRepository.findAll();
    }
    
    public Optional<ClienteModel> getClientById(long id) {
    	
    	return clienteRepository.findById(id);
    }
    

    public ClienteModel postClient(ClienteModel cliente) {
                   
                   Endereco completo = enderecoService.buscarEnderecoPorCep(cliente.getCep());
                      
                   cliente.setEndereco(completo);
                    
                   clienteRepository.save(cliente);
                    
                   completo.setClienteModel(cliente);
                    
                   enderecoRepository.save(completo);
                    
        return cliente ;
    }

	public boolean existsByCpf(String cpf) {
		
		return clienteRepository.existsByCpf(cpf);
		
	}
    

           
}       
        
 
   

