package com.piettrotorres.cursomc.dto;

import java.io.Serializable;

import com.piettrotorres.cursomc.domain.Estado;

public class EstadoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public EstadoDTO() {}

	public EstadoDTO(Estado estado) {
		this.nome = estado.getNome();
		this.id = estado.getId();
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
