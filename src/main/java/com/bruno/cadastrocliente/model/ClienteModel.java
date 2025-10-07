package com.bruno.cadastrocliente.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cliente_id")
public class ClienteModel {
    
	public ClienteModel(Long cliente_id, Endereco endereco, String cep, String cpf, String genero,
			LocalDate dataNascimento, String email, String telefone, String uf, String login, String senha,
			String nome) {
		super();
		this.cliente_id = cliente_id;
		this.endereco = endereco;
		this.cep = cep;
		this.cpf = cpf;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.telefone = telefone;
		this.uf = uf;
		this.userName = userName;
		this.senha = senha;
		this.nome = nome;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cliente_id;
	@OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "endereco_id")
	private Endereco endereco ;
	private String cep;
	private String cpf;
	private String genero;
	private LocalDate dataNascimento;
	private String email;
	private String telefone;
	private String uf;
	private String userName;
	private String senha;
  
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	private String nome;
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ClienteModel() {
		// TODO Auto-generated constructor stub
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setCliente_id(long cliente_id) {
		this.cliente_id = cliente_id;
	}
}
