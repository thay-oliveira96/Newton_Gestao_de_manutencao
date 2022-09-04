package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Defeitos;

//Pesistencia de dados

public interface DefeitosRepository extends JpaRepository<Defeitos, Integer> {
	Optional<Defeitos> findByDescricao(String descricao);
}
