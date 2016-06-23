package br.univel.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private Connection con = null;		

	public Connection abrirConexao() throws SQLException {

		String url = "jdbc:mysql://localhost/trabalho2bim";
		String user = "root";
		String pass = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		con = DriverManager.getConnection(url, user, pass);
	
		return con;
	}	
	
	public void fecharConexao() throws SQLException {
		con.close();
	}
}
