package br.univel.classes;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Serial;
import br.univel.anotacoes.Tabela;
import br.univel.anotacoes.UmPraMuitos;

@Tabela("vendas")
@XmlRootElement
public class Venda  implements Serializable{
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3489894237623305021L;
	
	@Coluna(nome="id", pk=true)
	private int id;
	
	@Coluna(nome="id_cliente")
	private Cliente cliente;
	
	@UmPraMuitos(nomeTabela="vendas_produtos", nomeCampo="id_produto")
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
