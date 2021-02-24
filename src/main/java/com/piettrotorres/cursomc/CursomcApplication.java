package com.piettrotorres.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.piettrotorres.cursomc.domain.Categoria;
import com.piettrotorres.cursomc.domain.Cidade;
import com.piettrotorres.cursomc.domain.Cliente;
import com.piettrotorres.cursomc.domain.Endereco;
import com.piettrotorres.cursomc.domain.Estado;
import com.piettrotorres.cursomc.domain.Produto;
import com.piettrotorres.cursomc.domain.enums.TipoCliente;
import com.piettrotorres.cursomc.repositories.CategoriaRepository;
import com.piettrotorres.cursomc.repositories.CidadeRepository;
import com.piettrotorres.cursomc.repositories.ClienteRepository;
import com.piettrotorres.cursomc.repositories.EnderecoRepository;
import com.piettrotorres.cursomc.repositories.EstadoRepository;
import com.piettrotorres.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado e1 = new Estado(null, "São Paulo");
		Estado e2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e1);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "14128815508", TipoCliente.PESSOAFISICA);
//		Cliente cl2 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36381755", TipoCliente.PESSOAFISICA)
		
		cl1.getTelefones().addAll(Arrays.asList("31375454", "994015508"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "24858688", cl1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "58800688", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		e1.getCidades().addAll(Arrays.asList(c1,c2));
		e2.getCidades().addAll(Arrays.asList(c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
	

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
