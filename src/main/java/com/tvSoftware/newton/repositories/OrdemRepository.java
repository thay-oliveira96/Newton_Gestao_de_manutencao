package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Ordem;

//Pesistencia de dados

public interface OrdemRepository extends JpaRepository<Ordem, Integer> {

}