package br.univel.classes;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
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
	
	@XmlElement(name="produto")
	public List<Produto> getListaProd() {
		return listaProd;
	}
	public void setListaProd(List<Produto> listaProd) {
		this.listaProd = listaProd;
	}
	
}
