package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Cliente;

//Pesistencia de dados

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
