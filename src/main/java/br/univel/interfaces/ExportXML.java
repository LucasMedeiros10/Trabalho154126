package br.univel.interfaces;

import java.io.File;

public interface ExportXML<T> {
	
	/*
	 * Verifica com reflecition a classe T (cliente, produto )
	 * Apos Podemos gerar o xml nescessario .
	 * 
	 */
	
	public void ExportarXml(T t , File file	);
		
	
	public void ImportarXml (T t , File file);
	
	
}
