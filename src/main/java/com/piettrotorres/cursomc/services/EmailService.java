package com.piettrotorres.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.piettrotorres.cursomc.domain.Cliente;
import com.piettrotorres.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHTMLEmail(Pedido obj);

	void sendHTMLEmail(MimeMessage msg);

	void sendHTMLNewPassword(Cliente cliente, String newPassword);

	void sendNewPassword(Cliente cliente, String newPassword);

}
