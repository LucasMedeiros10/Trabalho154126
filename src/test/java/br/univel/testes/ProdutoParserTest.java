package br.univel.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.univel.classes.ArquivoReader;
import br.univel.classes.ProdutoParser;

public class ProdutoParserTest {

	ProdutoParser pp;
	
	@Before
	public void setUp() throws Exception {
		pp = new ProdutoParser();
	}

	@Test
	public void testGetProduto() {
		assertNotNull(pp.getProduto(new ArquivoReader().lerArquivo("listaprodutos.txt")));
	}

}
