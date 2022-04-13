package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
