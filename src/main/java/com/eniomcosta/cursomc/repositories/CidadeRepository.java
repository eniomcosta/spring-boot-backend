package com.eniomcosta.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniomcosta.cursomc.domains.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{	}
