package com.tvSoftware.newton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvSoftware.newton.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
