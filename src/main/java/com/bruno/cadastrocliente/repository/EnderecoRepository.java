package com.bruno.cadastrocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.cadastrocliente.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

}
