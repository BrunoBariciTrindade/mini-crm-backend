package com.bruno.cadastrocliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bruno.cadastrocliente.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel,Long>{
	
	boolean existsByCpf(String cpf);

	Optional<ClienteModel> findBycpf(Object cpf);

	Optional<ClienteModel> findByuserName(String userName);

}
