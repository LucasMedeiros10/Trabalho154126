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
	
	static SerializadorImpl<List<Cliente>> sCliente;
	static SerializadorImpl<List<Produto>> sProduto;
	static SerializadorImpl<List<Venda>> sVenda;
	
	static List<Cliente> listaClientes;
	static List<Produto> listaProdutos;
	static List<Venda> listaVendas;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c1            = new Cliente();
		c2            = new Cliente();
		
		listaClientes = new ArrayList<Cliente>();
		listaProdutos = new ArrayList<Produto>();
		listaVendas   = new ArrayList<Venda>();
		
		sCliente      = new SerializadorImpl<List<Cliente>>();
		sProduto      = new SerializadorImpl<List<Produto>>();
		sVenda        = new SerializadorImpl<List<Venda>>();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c1 			  = null;
		c2            = null;
		
		listaClientes = null;
		listaProdutos = null;
		listaVendas   = null;
		
		sCliente      = null;
		sProduto      = null;
		sVenda        = null;
		
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
	
		Produto p1 = new Produto(1, "Produto Teste 1", new BigDecimal(2.5));
		Produto p2 = new Produto(2, "Produto Teste 2", new BigDecimal(1.99));		
		listaProdutos.add(p1);
		listaProdutos.add(p2);	
		
		Venda v1   = new Venda(1, c1, listaProdutos);
		Venda v2   = new Venda(2, c2, listaProdutos);
		listaVendas.add(v1);
		listaVendas.add(v2);
		
	}

	@Test
	public void testGravarCliente() throws SerializadorException {
		assertEquals(true, sCliente.gravar(listaClientes, new File("clientes.dat")));
	}
	
	@Test
	public void testGravarProduto() throws SerializadorException {
		assertEquals(true, sProduto.gravar(listaProdutos, new File("produtos.dat")));
	}

	@Test
	public void testGravarVenda() throws SerializadorException {
		assertEquals(true, sVenda.gravar(listaVendas, new File("vendas.dat")));
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
	
}
