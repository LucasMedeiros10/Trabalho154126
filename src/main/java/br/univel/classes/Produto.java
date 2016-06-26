package br.univel.classes;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Serial;
import br.univel.anotacoes.Tabela;

@Tabela("produtos")
@XmlRootElement
public class Produto  implements Serializable{
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4287610341939625920L;
	
	@Coluna(nome="id", pk=true)
	private int id;
	
	@Coluna(nome="descricao", tamanho=60)
	private String descricao;
	
	@Coluna(nome="preco")
	private BigDecimal preco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
