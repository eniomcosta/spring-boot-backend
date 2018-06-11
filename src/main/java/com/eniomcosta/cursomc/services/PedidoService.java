package com.eniomcosta.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniomcosta.cursomc.domains.Pedido;
import com.eniomcosta.cursomc.repositories.PedidoRepository;
import com.eniomcosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: "+id+", Tipo: "+Pedido.class.getName()));
	}
	
	public List<Pedido> findAll(){
		List<Pedido> obj = repo.findAll();
		
		return obj;
	}
	
}
