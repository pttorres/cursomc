package com.piettrotorres.cursomc.dto;

import java.io.Serializable;

import com.piettrotorres.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CidadeDTO() {}
	public CidadeDTO(Cidade cidade) {
		this.nome = cidade.getNome();
		this.id = cidade.getId();
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
