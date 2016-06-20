package br.univel.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExportXMLImplTest.class, SerializadorImplTest.class, ArquivoReaderTest.class,
	ProdutoParserTest.class})
public class AllTests {

}
