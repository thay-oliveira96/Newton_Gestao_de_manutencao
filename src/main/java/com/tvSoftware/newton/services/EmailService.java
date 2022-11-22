package com.tvSoftware.newton.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Ordem;

@Service
public class EmailService {

	public void enviar(Ordem ordem, String emailDestinatario) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("newton.sistema@gmail.com", "bkzkhsfrsqmbgzra"));
			email.setSSLOnConnect(true);

			email.setFrom("newton.sistema@gmail.com");
			email.setSubject("Novo Chamado de nº: " + ordem.getId() + " " + " Aberto dia: " + ordem.getDataAbertura());
			email.setMsg("Chamado: " + ordem.getId() + "\nMaquina: " + ordem.getMaquina() +
					"\nTitulo da Ocorrência: " + ordem.getDefeitos() +
					"\nEstado: " + ordem.getParada());
			email.addTo(emailDestinatario);
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
