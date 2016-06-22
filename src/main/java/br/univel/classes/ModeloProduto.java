package br.univel.classes;

import java.util.List;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModeloProduto extends AbstractTableModel {

	private List<Produto> lista;
	
	public ModeloProduto(List<Produto> lista) {
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
				return "C�digo";
			case 1:
				return "Descri��o";
			case 2:
				return "Pre�o";
			default:
				return super.getColumnName(column);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Produto p = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescricao();
		case 2:
			return p.getPreco();
		default:
			return "erro";
		}
	}
}
