package br.univel.relatorios;

import java.sql.SQLException;
import java.util.List;

import br.univel.classes.Cliente;
import br.univel.classes.Conexao;
import br.univel.classes.DaoCliente;
import net.sf.jasperreports.engine.JRDataSource;

public class ClienteDsFactory {
	public static JRDataSource criar(){
		
		DaoCliente dao = new DaoCliente();
		try {
			dao.setCon(new Conexao().abrirConexao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Cliente> lista = dao.listarTodos();
		ClienteJRDataSource ds = new ClienteJRDataSource(lista);
		
		return  ds;
	}
}
