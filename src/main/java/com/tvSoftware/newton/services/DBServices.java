package com.tvSoftware.newton.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.enums.Perfil;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.repositories.ChamadoRepository;
import com.tvSoftware.newton.repositories.ClienteRepository;
import com.tvSoftware.newton.repositories.TecnicoRepository;

//injeção de dependencias

/*
 * Na injeção de dependencia os spring tem o papel de gerenciar e destruir assim que necessario os dados
 * essa tarefa é delegada ao spring
 * */
@Service
public class DBServices {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
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
