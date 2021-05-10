package com.piettrotorres.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piettrotorres.cursomc.domain.Estado;
import com.piettrotorres.cursomc.repositories.EstadoRepository;
import com.piettrotorres.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + 
		id + ", Tipo " + Estado.class.getName()));
	}
	
}
