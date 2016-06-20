package br.univel.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.univel.classes.ArquivoReader;
import br.univel.classes.ClienteParser;

public class ClienteParserTest {
	
	ClienteParser cp;

	@Before
	public void setUp() throws Exception {
		cp = new ClienteParser();
	}

	@Test
	public void testGetCliente() {
		assertNotNull(cp.getCliente(new ArquivoReader().lerArquivo("listaclientes.csv")));
	}

}
