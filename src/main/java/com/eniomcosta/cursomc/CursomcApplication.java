package com.eniomcosta.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eniomcosta.cursomc.domains.Categoria;
import com.eniomcosta.cursomc.domains.Cidade;
import com.eniomcosta.cursomc.domains.Cliente;
import com.eniomcosta.cursomc.domains.Endereco;
import com.eniomcosta.cursomc.domains.Estado;
import com.eniomcosta.cursomc.domains.Produto;
import com.eniomcosta.cursomc.domains.enums.TipoCliente;
import com.eniomcosta.cursomc.repositories.CategoriaRepository;
import com.eniomcosta.cursomc.repositories.CidadeRepository;
import com.eniomcosta.cursomc.repositories.ClienteRepository;
import com.eniomcosta.cursomc.repositories.EnderecoRepository;
import com.eniomcosta.cursomc.repositories.EstadoRepository;
import com.eniomcosta.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private EstadoRepository estRepo;
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/** INICIO CATEGORIAS E PRODUTOS */
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador" , 2000.00);
		Produto p2 = new Produto(null,"Impressora" , 800.00);
		Produto p3 = new Produto(null,"Mouse" , 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		/** FIM CATEGORIAS E PRODUTOS */
		
		/** INICIO ESTADOS E CIDADES */
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estRepo.saveAll(Arrays.asList(est1,est2));
		cidRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		/** FIM ESTADOS E CIDADES */
		
		
		/** INICIO CLIENTES E ENDERECO */
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","123456",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("111222333","444555666"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "123-A", "", "Bairro Coisado", "010203", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "321-B", "Sem complemento", "Sernhor Silva com S", "040506", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(cli1);
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		/** FIM CLIENTES E ENDERECO */
		
	}
	
	
}
