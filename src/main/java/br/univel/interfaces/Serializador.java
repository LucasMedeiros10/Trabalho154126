package br.univel.interfaces;

import java.io.File;

import br.univel.excecoes.SerializadorException;

public interface Serializador<T> {
	
	/**
	 * 
	 * Verifica com reflection se T implementa serializable. Serializa o objeto no arquivo especificado
	 * 
	 * SerializadorException deixa encapsulado os erros originais
	 * 
	 * @param t
	 * @param file
	 * @throws SerializadorException
	 */
	
	public boolean gravar(T t, File file) throws SerializadorException;
	
	
	/**
	 * Le o arquivo especificado e retorna o objeto de classe utilizada na instanciacao 
	 * do Serializador(T);
	 * 
	 * Antes de retornar verifica com reflection se a classe é correta.
	 * 
	 * @param file
	 * @return
	 * @throws SerializadorException
	 */
	public T ler(File file) throws SerializadorException;	

}
