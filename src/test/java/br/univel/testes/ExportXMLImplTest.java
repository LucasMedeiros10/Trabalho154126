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
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ItemVenda;
import br.univel.classes.ListaClientes;
import br.univel.classes.ListaProdutos;
import br.univel.classes.ListaVendas;
import br.univel.classes.Produto;
import br.univel.classes.Venda;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExportXMLImplTest {
	static Cliente c1, c2;
	static Produto p1, p2;
	static Venda v1, v2;  
		
	static ExportXMLImp<Cliente> XMLCliente;	
	static ExportXMLImp<Produto> XMLProduto;	
	static ExportXMLImp<Venda> XMLVenda;	
	static ExportXMLImp<ListaClientes> XMLListaCliente;	
	static ExportXMLImp<ListaProdutos> XMLListaProduto;	
	static ExportXMLImp<ListaVendas> XMLListaVenda; 
	
	static List<Cliente> listaClientes;
	static List<Produto> listaProdutos;
	static List<Venda> listaVendas;	
	
	static ListaClientes lc;
	static ListaProdutos lp;
	static ListaVendas lv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c1             = new Cliente();
		c2             = new Cliente();
		
		p1    		   = new Produto();
		p2    		   = new Produto();
		
		v1             = new Venda();
		v2   		   = new Venda();
		
		
		XMLCliente     = new ExportXMLImp<Cliente>();
		XMLListaCliente= new ExportXMLImp<ListaClientes>();
		XMLProduto     = new ExportXMLImp<Produto>();
		XMLListaProduto= new ExportXMLImp<ListaProdutos>();
		XMLVenda       = new ExportXMLImp<Venda>();
		XMLListaVenda  = new ExportXMLImp<ListaVendas>();
		
		listaClientes  = new ArrayList<Cliente>();
		listaProdutos  = new ArrayList<Produto>();
		listaVendas    = new ArrayList<Venda>();	
		
		lc			   = new ListaClientes();
		lp             = new ListaProdutos();
		lv			   = new ListaVendas();	
		
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
		lc.setListaCliente(listaClientes);		
		
		p1.setId(1);
		p1.setDescricao("Produto teste 1");
		p1.setPreco(new BigDecimal(2.5));
		
		p2.setId(2);
		p2.setDescricao("Produto teste 2");
		p2.setPreco(new BigDecimal(1.99));
		
		listaProdutos.add(p1);
		listaProdutos.add(p2);	
		lp.setListaProd(listaProdutos);
		
	
		ItemVenda iv1 = new ItemVenda();
		iv1.setP(p1);
		iv1.setQtde(new BigDecimal(1));
		
		ItemVenda iv2 = new ItemVenda();
		iv2.setP(p2);
		iv2.setQtde(new BigDecimal(1));
		
		List<ItemVenda> listaitens = new ArrayList<ItemVenda>();
		
		Venda v1   = new Venda();
		v1.setId(1);
		v1.setCliente(c1);
		v1.setItens(listaitens);
		
		Venda v2   = new Venda();
		v2.setId(2);
		v2.setCliente(c2);
		v2.setItens(listaitens);		

		listaVendas.add(v1);
		listaVendas.add(v2);	
		lv.setListaVenda(listaVendas);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c1 		        = null;
		c2              = null;
		p1              = null;
		p2              = null;  
		v1              = null;
		v2              = null;  
		
		XMLCliente      = null;
		XMLListaCliente = null;
		XMLProduto      = null;
		XMLListaProduto = null;
		XMLVenda        = null;
		XMLListaVenda   = null;
		
		listaClientes   = null;
		listaProdutos   = null;
		listaVendas     = null;		
		
		lc				= null;
		lp				= null;
		lv				= null;
	}

	
	@Test
	public void testExportarXmlCliente() {
		assertEquals(true, XMLCliente.ExportarXml(c1, new File("clientes.xml")));
	}
	
	@Test
	public void testExportarXmlProduto() {
		assertEquals(true, XMLProduto.ExportarXml(p1, new File("produtos.xml")));
	}
	
	@Test
	public void testExportarXmlVenda() {
		assertEquals(true, XMLVenda.ExportarXml(v1, new File("vendas.xml")));
	}	

	@Test
	public void testExportarXmlListaCliente() {
		assertEquals(true, XMLListaCliente.ExportarXml(lc, new File("clienteslista.xml")));
	}	
	
	@Test
	public void testExportarXmlListaProduto() {
		assertEquals(true, XMLListaProduto.ExportarXml(lp, new File("produtoslista.xml")));
	}
	
	@Test
	public void testExportarXmlListaVenda() {
		assertEquals(true, XMLListaVenda.ExportarXml(lv, new File("vendaslista.xml")));
	}	
	
	@Test
	public void testImportarXmlCliente() {
		assertNotNull(XMLCliente.ImportarXml(c1,  new File("clientes.xml")));
	}
	
	@Test
	public void testImportarXmlProduto() {
		assertNotNull(XMLProduto.ImportarXml(p1,  new File("produtos.xml")));
	}	

	@Test
	public void testImportarXmlVenda() {
		assertNotNull(XMLVenda.ImportarXml(v1,  new File("vendas.xml")));
	}		
	
	@Test
	public void testImportarXmlListaCliente() {
		assertNotNull(XMLListaCliente.ImportarXml(lc,  new File("clienteslista.xml")));
	}
	
	@Test
	public void testImportarXmlListaProduto() {
		assertNotNull(XMLListaProduto.ImportarXml(lp,  new File("produtoslista.xml")));
	}	

	@Test
	public void testImportarXmlListaVenda() {
		assertNotNull(XMLListaVenda.ImportarXml(lv,  new File("vendaslista.xml")));
	}

}
