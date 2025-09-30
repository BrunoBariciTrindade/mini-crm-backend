package com.bruno.cadastrocliente.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "endereco_id")

public class Endereco {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long endereco_id;
	    private String cep;
		private String logradouro;
	    private String complemento;
	    private String bairro;
	    private String uf;            
	    private String codigoIbge;
	    private String cidade;
	    private String ddd;
	    private String codigoSiafi;
	 
	    public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		@ManyToOne
	    @JoinColumn(name = "cliente_id")  
	    private ClienteModel clienteModel;
	    
	    public Long getEndereco_id() {
			return endereco_id;
		}
		public void setEndereco_id(Long endereco_id) {
			this.endereco_id = endereco_id;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getLogradouro() {
			return logradouro;
		}
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		
		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
		}
		public String getCodigoIbge() {
			return codigoIbge;
		}
		public void setCodigoIbge(String codigoIbge) {
			this.codigoIbge = codigoIbge;
		}
		
		public String getDdd() {
			return ddd;
		}
		public void setDdd(String ddd) {
			this.ddd = ddd;
		}
		public String getCodigoSiafi() {
			return codigoSiafi;
		}
		public void setCodigoSiafi(String codigoSiafi) {
			this.codigoSiafi = codigoSiafi;
		}
		public ClienteModel getClienteModel() {
			return clienteModel;
		}
		public void setClienteModel(ClienteModel clienteModel) {
			this.clienteModel = clienteModel;
		}
		public String getCidade() {
		
			return cidade;
		}
		
	

	
}
