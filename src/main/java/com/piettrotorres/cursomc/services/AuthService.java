package com.piettrotorres.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.piettrotorres.cursomc.domain.Cliente;
import com.piettrotorres.cursomc.repositories.ClienteRepository;
import com.piettrotorres.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;
	
	private Random rdm = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail "+ email + " não cadastrado!");
		}
		
		String password = newPassword(10);	
		cliente.setSenha(pe.encode(password));
		clienteRepository.save(cliente);
		
		emailService.sendHTMLNewPassword(cliente, password);
		
	}
	
	private String newPassword(int tam) {
		
		char c[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		
		char password [] = new char[tam];
		
		for (int i = 0; i < tam; i++) {
			password[i] = c[rdm.nextInt(c.length)]; 
		}
		
		return new String(password);
	}

}
