package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Tecnico;

//Pesistencia de dados

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
