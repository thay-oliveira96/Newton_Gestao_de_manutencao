package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Maquina;

//Pesistencia de dados

public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {
	Optional<Maquina> findByNome(String nome);
}