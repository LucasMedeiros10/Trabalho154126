package br.univel.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Serial;
import br.univel.anotacoes.Tabela;

@Tabela("vendas")
@XmlRootElement
public class Venda  implements Serializable{
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3489894237623305021L;

	@Serial
	private List<ItemVenda> itens;
	
	@Coluna(nome="id", pk=true)
	private int id;
	
	@Coluna(nome="id_cliente")
	private Cliente cliente;	
	
	@Coluna(nome="vlrPago")
	private BigDecimal vlrPago;
	
	
	public BigDecimal getVlrPago() {
		return vlrPago;
	}
	public void setVlrPago(BigDecimal vlrPago) {
		this.vlrPago = vlrPago;
	}
	public List<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}
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

	
}
