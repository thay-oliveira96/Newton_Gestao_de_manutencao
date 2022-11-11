package com.tvSoftware.newton.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Chamado;

@Service
public class EmailService {

	public void enviar(Chamado chamado, String emailDestinatario) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("newton.sistema@gmail.com", "bkzkhsfrsqmbgzra"));
			email.setSSLOnConnect(true);

			email.setFrom("newton.sistema@gmail.com");
			email.setSubject("Chamado Aberto dia: " + chamado.getDataAbertura());
			email.setMsg("Chamado: " + chamado.getId() + " foi aberto com sucesso \n" +
			"Prioridade: " + chamado.getPrioridade() + "\nStatus: " + chamado.getStatus());
			email.addTo(emailDestinatario);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}