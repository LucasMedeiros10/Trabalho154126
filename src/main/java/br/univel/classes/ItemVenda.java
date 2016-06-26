package br.univel.classes;

import java.math.BigDecimal;

public class ItemVenda {

	private Produto produto;
	private BigDecimal qtde;
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
