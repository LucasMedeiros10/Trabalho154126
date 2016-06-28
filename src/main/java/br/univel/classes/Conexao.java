
package br.univel.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private Connection con = null;		

	public Connection abrirConexao() throws SQLException {
		PropertiesSistema ps = new PropertiesSistema();
		
		
		String url = "jdbc:mysql://" + ps.ler("ip") +"/" + ps.ler("bd");
		String user = ps.ler("user");
		String pass = ps.ler("password");
		
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