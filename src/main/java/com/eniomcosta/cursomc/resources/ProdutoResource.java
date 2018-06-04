package com.eniomcosta.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eniomcosta.cursomc.domains.Categoria;
import com.eniomcosta.cursomc.domains.Produto;
import com.eniomcosta.cursomc.services.CategoriaService;
import com.eniomcosta.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Produto obj = service.buscar(id);
		return ResponseEntity.ok(obj);
		
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		List<Produto> obj = service.findAll();
		
		return ResponseEntity.ok(obj);
		
	}
	
}
