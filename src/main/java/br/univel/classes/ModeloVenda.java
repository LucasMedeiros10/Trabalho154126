package br.univel.classes;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloVenda extends AbstractTableModel {

	private List<Venda> lista;
	
	public ModeloVenda(List<Venda> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Código";
			case 1:
				return "Cliente";
			case 2:
				return "Valor Total";
			default:
				return super.getColumnName(column);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Venda v = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return v.getId();
		case 1:
			return v.getCliente().getNome();
		case 2:			
			BigDecimal total = new BigDecimal(0);
			
			return total;
		default:
			return "erro";
		}
	}
}
