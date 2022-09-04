package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Pessoa;

//Pesistencia de dados

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);

}
