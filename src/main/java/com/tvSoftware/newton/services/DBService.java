package com.tvSoftware.newton.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Defeitos;
import com.tvSoftware.newton.domain.Departamentos;
import com.tvSoftware.newton.domain.Gestor;
import com.tvSoftware.newton.domain.Maquina;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.enums.CategoriaManutencao;
import com.tvSoftware.newton.domain.enums.Perfil;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.domain.enums.TipoManutencao;
import com.tvSoftware.newton.repositories.ChamadoRepository;
import com.tvSoftware.newton.repositories.DefeitosRepository;
import com.tvSoftware.newton.repositories.DepartamentosRepository;
import com.tvSoftware.newton.repositories.MaquinaRepository;
import com.tvSoftware.newton.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private MaquinaRepository maquinaRepository;
	@Autowired
	private DepartamentosRepository departamentoRepository;
	@Autowired
	private DefeitosRepository defeitosRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Tecnico tec1 = new Tecnico(null, "Administrador Sistema", "550.482.150-95", "admin@newton.com", encoder.encode("#Asdf1231"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
		
		Gestor gest1 = new Gestor(null, "Douglas Oliveira", "683.597.666-73", "douglas.oliveira@mail.com", encoder.encode("123"));
		gest1.addPerfil(Perfil.ADMIN);
		Departamentos dpto1 = new Departamentos(null, "Producao");
		
		Defeitos d1 = new Defeitos(null, "Quebra de corrente");
		
		Maquina m1 = new Maquina(null, "maquina 3", dpto1, "Maquina da fabricacao geral");
 
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 1", "jssk", tec1, cli1, gest1, m1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 2", "jssk",tec1, cli2, gest1, m1);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 3", "jssk",tec2, cli3, gest1, m1);
		Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 4", "jssk", tec3, cli3, gest1, m1);
		Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 5", "jssk", tec2, cli1, gest1, m1);
		Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, TipoManutencao.CORRETIVA, CategoriaManutencao.MECANICA, d1, "Teste chamado 6", "jssk", tec1, cli5, gest1, m1);
		

		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5, gest1));
		defeitosRepository.saveAll(Arrays.asList(d1));
		departamentoRepository.saveAll(Arrays.asList(dpto1));
		maquinaRepository.saveAll(Arrays.asList(m1));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
}
