package com.tvSoftware.newton;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.enums.Perfil;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.repositories.ChamadoRepository;
import com.tvSoftware.newton.repositories.ClienteRepository;
import com.tvSoftware.newton.repositories.TecnicoRepository;

@SpringBootApplication
public class NewtonApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NewtonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Teste de dados para alimentar o banco de dados
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "32098888877", "valdir@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "33333333333", "torvalds@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
