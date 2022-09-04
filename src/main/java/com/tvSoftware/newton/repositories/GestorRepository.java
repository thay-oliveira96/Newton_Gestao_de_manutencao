package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Gestor;

//Pesistencia de dados

public interface GestorRepository extends JpaRepository<Gestor, Integer> {

}
