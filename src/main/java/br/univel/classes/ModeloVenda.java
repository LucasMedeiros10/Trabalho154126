package br.univel.classes;

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
		return 2;
	}

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Código";
			case 1:
				return "Cliente";
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
		default:
			return "erro";
		}
	}
}
