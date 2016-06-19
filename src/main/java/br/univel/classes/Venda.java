package br.univel.classes;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Venda  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3489894237623305021L;
	
	
	private int id;
	private Cliente cliente;
	private List<Produto> listaProd;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getListaProd() {
		return listaProd;
	}
	public void setListaProd(List<Produto> listaProd) {
		this.listaProd = listaProd;
	}
	
	public Venda(int id, Cliente cliente, List<Produto> listaProd) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.listaProd = listaProd;
	}
	
	
	
	
}
