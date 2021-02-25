package com.piettrotorres.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piettrotorres.cursomc.domain.Pedido;
import com.piettrotorres.cursomc.repositories.PedidoRepository;
import com.piettrotorres.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + 
		id + ", Tipo " + Pedido.class.getName()));
	}
}
