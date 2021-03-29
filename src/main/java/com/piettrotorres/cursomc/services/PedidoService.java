package com.piettrotorres.cursomc.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.piettrotorres.cursomc.domain.Cliente;
import com.piettrotorres.cursomc.domain.ItemPedido;
import com.piettrotorres.cursomc.domain.PagamentoComBoleto;
import com.piettrotorres.cursomc.domain.Pedido;
import com.piettrotorres.cursomc.domain.enums.EstadoPagamento;
import com.piettrotorres.cursomc.repositories.ItemPedidoRepository;
import com.piettrotorres.cursomc.repositories.PedidoRepository;
import com.piettrotorres.cursomc.security.UserSS;
import com.piettrotorres.cursomc.services.exception.AuthorizationException;
import com.piettrotorres.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + 
		id + ", Tipo " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHTMLEmail(obj);
		return obj;
		
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user == null)
			throw new AuthorizationException("Acesso negado");
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
	
}
