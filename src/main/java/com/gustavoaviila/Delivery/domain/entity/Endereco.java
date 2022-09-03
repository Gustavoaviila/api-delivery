package com.gustavoaviila.Delivery.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = -428045536909035514L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String rua;
	private String numero;
	private String cidade;
	private String bairro;
	
	@OneToMany
	private Cliente cliente;
	
}
