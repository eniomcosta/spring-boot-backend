package com.eniomcosta.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniomcosta.cursomc.domains.Categoria;
import com.eniomcosta.cursomc.domains.Produto;
import com.eniomcosta.cursomc.repositories.CategoriaRepository;
import com.eniomcosta.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Produto> findAll(){
		List<Produto> obj = repo.findAll();
		
		return obj;
	}
	
}
