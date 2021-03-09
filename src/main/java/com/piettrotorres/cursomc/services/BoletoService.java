package com.piettrotorres.cursomc.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.piettrotorres.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
		pagto.setDataVencimento(instanteDoPedido.plusDays(7).toLocalDate());		
	}

}
