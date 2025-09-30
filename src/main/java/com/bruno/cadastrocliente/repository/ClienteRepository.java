package com.bruno.cadastrocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bruno.cadastrocliente.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel,Long>{

	boolean existsByCpf(String cpf);

}
