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
	
	static SerializadorImpl<List<Cliente>> sCliente;
	static SerializadorImpl<List<Produto>> sProduto;
	static SerializadorImpl<List<Venda>> sVenda;
	
	static List<Cliente> listaClientes;
	static List<Produto> listaProdutos;
	static List<Venda> listaVendas;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		listaClientes = new ArrayList<Cliente>();
		listaProdutos = new ArrayList<Produto>();
		listaVendas   = new ArrayList<Venda>();
		
		sCliente      = new SerializadorImpl<List<Cliente>>();
		sProduto      = new SerializadorImpl<List<Produto>>();
		sVenda        = new SerializadorImpl<List<Venda>>();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		listaClientes = null;
		listaProdutos = null;
		listaVendas   = null;
		
		sCliente      = null;
		sProduto      = null;
		sVenda        = null;
		
	}

	@Before
	public void setUp() throws Exception {

		Cliente c1 = new Cliente(1, "Lucas", "Rua ABC", 200, "", "Pq. São Paulo", "Cascavel", "PR", "85803550", "", "");
		Cliente c2 = new Cliente(1, "Luis", "Rua ABC", 200, "", "Neva", "Cascavel", "PR", "85803550", "", "");		
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
