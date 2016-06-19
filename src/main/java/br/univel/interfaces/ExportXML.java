package br.univel.interfaces;

import java.io.File;

public interface ExportXML<T> {
	
	/*
	 * Verifica com reflecition a classe T (cliente, produto )
	 * Apos Podemos gerar o xml nescessario .
	 * 
	 */
	
	public boolean ExportarXml(T t , File file	);
		
	
	public T ImportarXml (T t , File file);
	
	
}
