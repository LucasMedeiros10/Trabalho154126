package br.univel.classes;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloItemVenda extends AbstractTableModel{

	private List<ItemVenda> lista;
	
	public ModeloItemVenda(List<ItemVenda> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Produto";
			case 1:
				return "Qtde";
			case 2:
				return "Preço";
			case 3:
				return "Total";				
			default:
				return super.getColumnName(column);
		}
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemVenda v = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return v.getP().getDescricao();
		case 1:
			return v.getQtde();
		case 2:
			return v.getP().getPreco();
		case 3:
			BigDecimal vlr = new BigDecimal(v.getP().getPreco().toString());
			return vlr.multiply(v.getQtde());
		default:
			return "erro";
		}
	}

}
