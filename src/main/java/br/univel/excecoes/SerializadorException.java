package br.univel.excecoes;

public class SerializadorException extends Exception {

	private static final long serialVersionUID = 3656880596975677139L;

	public SerializadorException(Exception e){
		super(e);		
	}
	
	public SerializadorException(String string){
		super(string);
	}	
	
}
