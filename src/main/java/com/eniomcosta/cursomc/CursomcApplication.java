package com.eniomcosta.cursomc;

import java.text.SimpleDateFormat;
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
import com.eniomcosta.cursomc.domains.ItemPedido;
import com.eniomcosta.cursomc.domains.Pagamento;
import com.eniomcosta.cursomc.domains.PagamentoBoleto;
import com.eniomcosta.cursomc.domains.PagamentoCartao;
import com.eniomcosta.cursomc.domains.Pedido;
import com.eniomcosta.cursomc.domains.Produto;
import com.eniomcosta.cursomc.domains.enums.EstadoPagamento;
import com.eniomcosta.cursomc.domains.enums.TipoCliente;
import com.eniomcosta.cursomc.repositories.CategoriaRepository;
import com.eniomcosta.cursomc.repositories.CidadeRepository;
import com.eniomcosta.cursomc.repositories.ClienteRepository;
import com.eniomcosta.cursomc.repositories.EnderecoRepository;
import com.eniomcosta.cursomc.repositories.EstadoRepository;
import com.eniomcosta.cursomc.repositories.ItemPedidoRepository;
import com.eniomcosta.cursomc.repositories.PagamentoRepository;
import com.eniomcosta.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

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
		
		/** INICIO PEDIDOS E PAGAMENTOS */
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 13:32:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35:00"), cli1, e2);
		
		Pagamento pagt1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);
		
		Pagamento pagt2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 10:10:10"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagt1,pagt2));
		
		/** FIM PEDIDOS E PAGAMENTOS */
		
		/** INICIO ITEMPEDIDO */
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		/** FIM ITEMPEDIDO */
		
	}
	
	
}
