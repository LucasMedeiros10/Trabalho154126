package br.univel.testes;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.univel.classes.Cliente;
import br.univel.classes.Produto;
import br.univel.classes.SerializadorImpl;
import br.univel.classes.Venda;
import br.univel.excecoes.SerializadorException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerializadorImplTest {
	static Cliente c1, c2;
	static Produto p1, p2;
	static Venda v1, v2;
	
	static SerializadorImpl<List<Cliente>> sListaCliente;
	static SerializadorImpl<List<Produto>> sListaProduto;
	static SerializadorImpl<List<Venda>> sListaVenda;
	
	static SerializadorImpl<Cliente> sCliente;
	static SerializadorImpl<Produto> sProduto;
	static SerializadorImpl<Venda> sVenda;
	
	static List<Cliente> listaClientes;
	static List<Produto> listaProdutos;
	static List<Venda> listaVendas;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c1            = new Cliente();
		c2            = new Cliente();
		
		p1			  = new Produto();
		p2			  = new Produto();
		
		v1			  = new Venda();
		v2			  = new Venda();
			
		
		listaClientes = new ArrayList<Cliente>();
		listaProdutos = new ArrayList<Produto>();
		listaVendas   = new ArrayList<Venda>();
		
		sListaCliente = new SerializadorImpl<List<Cliente>>();
		sListaProduto = new SerializadorImpl<List<Produto>>();
		sListaVenda   = new SerializadorImpl<List<Venda>>();
		sCliente      = new SerializadorImpl<Cliente>();
		sProduto      = new SerializadorImpl<Produto>();
		sVenda        = new SerializadorImpl<Venda>();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c1 			  = null;
		c2            = null;

		p1 			  = null;
		p2            = null;
		v1 			  = null;
		v2            = null;		
		
		listaClientes = null;
		listaProdutos = null;
		listaVendas   = null;
		
		sCliente      = null;
		sProduto      = null;
		sVenda        = null;
		sListaCliente = null;
		sListaProduto = null;
		sListaVenda   = null;		
	}

	@Before
	public void setUp() throws Exception {

		c1.setId(1);
		c1.setNome("Lucas");
		c1.setEndereco("Rua ABC");
		c1.setNumero(200);
		c1.setBairro("Parque São Paulo");
		c1.setCep("85803550");
		c1.setCelular("99999999");
		c1.setTelefone("323323232");
		c1.setCidade("Cascavel");
		c1.setEstado("PR");
		c1.setComplemento("");
		
		c2.setId(2);
		c2.setNome("Luis");
		c2.setEndereco("Rua ABC");
		c2.setNumero(200);
		c2.setBairro("Neva");
		c2.setCep("85803550");
		c2.setCelular("99999999");
		c2.setTelefone("323323232");
		c2.setCidade("Cascavel");
		c2.setEstado("PR");
		c2.setComplemento("");	
		
		listaClientes.add(c1);
		listaClientes.add(c2);		
	
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setDescricao("Produto teste 1");
		p1.setPreco(new BigDecimal(2.5));
		
		Produto p2 = new Produto();
		p2.setId(2);
		p2.setDescricao("Produto teste 2");
		p2.setPreco(new BigDecimal(1.99));	
		listaProdutos.add(p1);
		listaProdutos.add(p2);	
		
		
		Venda v1   = new Venda();
		v1.setId(1);
		v1.setCliente(c1);
		v1.setListaProd(listaProdutos);
		
		Venda v2   = new Venda();
		v2.setId(2);
		v2.setCliente(c2);
		v2.setListaProd(listaProdutos);		
		listaVendas.add(v1);
		listaVendas.add(v2);
		
	}
	
	@Test
	public void testGravarCliente() throws SerializadorException {
		assertEquals(true, sCliente.gravar(c1, new File("clientes.dat")));
	}	

	@Test
	public void testGravarProduto() throws SerializadorException {
		assertEquals(true, sProduto.gravar(p1, new File("produtos.dat")));
	}	

	@Test
	public void testGravarVenda() throws SerializadorException {
		assertEquals(true, sVenda.gravar(v1, new File("vendas.dat")));
	}	

	
	@Test
	public void testGravarListaCliente() throws SerializadorException {
		assertEquals(true, sListaCliente.gravar(listaClientes, new File("clienteslista.dat")));
	}
	
	@Test
	public void testGravarListaProduto() throws SerializadorException {
		assertEquals(true, sListaProduto.gravar(listaProdutos, new File("produtoslista.dat")));
	}

	@Test
	public void testGravarListaVenda() throws SerializadorException {
		assertEquals(true, sListaVenda.gravar(listaVendas, new File("vendaslista.dat")));
	}
	
	@Test
	public void testLerCliente() throws SerializadorException {
		assertNotNull(sCliente.ler(new File("clientes.dat")));
	}	
	
	@Test
	public void testLerProduto() throws SerializadorException {		
		assertNotNull(sProduto.ler(new File("produtos.dat")));
	}	

	@Test
	public void testLerVenda() throws SerializadorException {		
		assertNotNull(sVenda.ler(new File("vendas.dat")));
	}		
	
	@Test
	public void testLerListaCliente() throws SerializadorException {
		assertNotNull(sListaCliente.ler(new File("clienteslista.dat")));
	}	
	
	@Test
	public void testLerListaProduto() throws SerializadorException {		
		assertNotNull(sListaProduto.ler(new File("produtoslista.dat")));
	}	

	@Test
	public void testLerListaVenda() throws SerializadorException {		
		assertNotNull(sListaVenda.ler(new File("vendaslista.dat")));
	}	
	
}
