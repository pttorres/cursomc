package com.piettrotorres.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piettrotorres.cursomc.domain.Cidade;
import com.piettrotorres.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findCidades(Integer estadoId) {
		return  repo.findByEstado(estadoId);
	}
	
}
