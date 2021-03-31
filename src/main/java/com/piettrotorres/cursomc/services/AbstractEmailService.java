package com.piettrotorres.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.piettrotorres.cursomc.domain.Cliente;
import com.piettrotorres.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;

	/*Envio de ordem de pedidos*/
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setText(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setTo(obj.getCliente().getEmail());
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void sendOrderConfirmationHTMLEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHTMLEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}

	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	/*Redefinicao de senha*/
	@Override
	public void sendNewPassword(Cliente obj, String newPassword) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromCliente(obj, newPassword);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromCliente(Cliente obj, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom(sender);
		sm.setTo(obj.getEmail());
		sm.setSubject("Redefinição de senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPassword);
		return sm;
	}
	
	@Override
	public void sendHTMLNewPassword(Cliente obj, String newPassword) {
		try {
			MimeMessage mm = prepareMimeMessageFromCliente(obj, newPassword);
			sendHTMLEmail(mm);
		} catch (MessagingException e) {
			sendNewPassword(obj, newPassword);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromCliente(Cliente obj, String newPassword) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Redefinição de senha");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateCliente(obj, newPassword), true);
		return mimeMessage;
	}

	protected String htmlFromTemplateCliente(Cliente obj, String newPassword) {
		Context context = new Context();
		context.setVariable("cliente", obj);
		context.setVariable("senha", newPassword);
		return templateEngine.process("email/newPassword", context);
	}
	
}
