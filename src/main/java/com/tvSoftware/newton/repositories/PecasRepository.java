package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Pecas;

//Pesistencia de dados

public interface PecasRepository extends JpaRepository<Pecas, Integer> {
	Optional<Pecas> findByNome(String nome);
}
