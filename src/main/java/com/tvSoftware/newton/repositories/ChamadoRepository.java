package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Chamado;

//Pesistencia de dados

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}