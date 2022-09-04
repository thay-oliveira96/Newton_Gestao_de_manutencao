package com.tvSoftware.newton.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Departamentos;

//Pesistencia de dados

public interface DepartamentosRepository extends JpaRepository<Departamentos, Integer> {
	Optional<Departamentos> findByNome(String nome);
}
