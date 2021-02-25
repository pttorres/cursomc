package com.piettrotorres.cursomc.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.piettrotorres.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = 1L;

	private LocalDateTime dataVencimento;
	private LocalDateTime dataPagamento;

	public PagamentoComBoleto() {}

	public PagamentoComBoleto(Integer id, Pedido pedido, EstadoPagamento estado, LocalDateTime dataVencimento,
			LocalDateTime dataPagamento) {
		super(id, pedido, estado);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
