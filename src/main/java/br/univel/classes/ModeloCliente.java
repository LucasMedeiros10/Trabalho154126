package br.univel.classes;

import java.util.List;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModeloCliente extends AbstractTableModel {

	private List<Cliente> lista;
	
	public ModeloCliente(List<Cliente> lista) {
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
				return "Nome";
			default:
				return super.getColumnName(column);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Cliente c = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		default:
			return "erro";
		}
	}
}
