package com.eniomcosta.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniomcosta.cursomc.domains.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{	}
