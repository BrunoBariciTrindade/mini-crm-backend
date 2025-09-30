package com.bruno.cadastrocliente.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bruno.cadastrocliente.model.Endereco;

@Service
public class EnderecoService {

    public Endereco buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject(url, Endereco.class);

        if (endereco == null || endereco.getCep() == null) {
            throw new RuntimeException("CEP inválido ou não encontrado.");
        }

        return endereco;
    }


}

