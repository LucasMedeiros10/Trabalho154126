package br.univel.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Serial;
import br.univel.anotacoes.Tabela;

@Tabela("vendas_produtos")
@XmlRootElement
public class ItemVenda implements Serializable{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 4828102383916007835L;

	@Coluna(nome="id_venda", pk=true)
	private int id_venda;

	@Coluna(nome="id_produto", pk=true)
	private Produto produto;
	
	@Coluna(nome="qtde")
	private BigDecimal qtde;
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	
	public Produto getP() {
		return produto;
	}
	public void setP(Produto p) {
		this.produto = p;
	}
	public BigDecimal getQtde() {
		return qtde;
	}
	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}
}
