package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	//Opção para encontrar pessoa por CPF
	Optional<Pessoa> findByCpf(String cpf);
	
	//Opção para encontrar pessoa por E-mail
	Optional<Pessoa> findByEmail(String email);
}
