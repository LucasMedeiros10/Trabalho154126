package br.univel.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesSistema {

	public String ler(String key) {
		Properties p = new Properties();
		try {
			p.load(new FileReader(new File("configs.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return p.getProperty(key);
	}


	public void escrever() {
		Properties p = new Properties();

		p.put("bd", "trabalho2bim");
		p.put("user", "root");
		p.put("password", "root");
		p.put("ip", "localhost");
		p.put("tipoBanco", "relacional");

		try {
			p.store(new FileWriter(new File("configs.properties")), "Configuracoes");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
