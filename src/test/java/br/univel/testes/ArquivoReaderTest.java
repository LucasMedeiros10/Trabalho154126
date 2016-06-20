package br.univel.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.univel.classes.ArquivoReader;

public class ArquivoReaderTest {

	ArquivoReader reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new ArquivoReader();		
	}
	
	@Test
	public void testLerArquivo() {
		assertNotNull(reader.lerArquivo("listaprodutos.txt"));
	}

}
