package br.univel.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.interfaces.Dao;

public class DaoCliente implements Dao<Cliente, Integer> {

	private Connection con = null;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	@Override
	public void salvar(Cliente t) {
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {

			PreparedStatement ps = gerador.getSqlInsert(con, t);
			ps.setInt(1, t.getId());
			ps.setString(2, t.getNome());
			ps.setString(3, t.getEndereco());
			ps.setInt(4, t.getNumero());
			ps.setString(5, t.getComplemento());
			ps.setString(6, t.getBairro());
			ps.setString(7, t.getCidade());
			ps.setString(8, t.getEstado());
			ps.setString(9, t.getCep());
			ps.setString(10, t.getTelefone());
			ps.setString(11, t.getCelular());
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}			
	}

	@Override
	public Cliente buscar(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
		Cliente c = new Cliente();
		c.setId(0);
				
		try {

			PreparedStatement ps = gerador.getSqlSelectById(con, new Cliente());
			ps.setInt(1, k);
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				c.setId(resultados.getInt("id"));
				c.setNome(resultados.getString("nome"));
				c.setEndereco(resultados.getString("endereco"));
				c.setNumero(resultados.getInt("numero"));
				c.setComplemento(resultados.getString("complemento"));
				c.setBairro(resultados.getString("bairro"));
				c.setCidade(resultados.getString("cidade"));
				c.setEstado(resultados.getString("estado"));
				c.setCep(resultados.getString("cep"));
				c.setTelefone(resultados.getString("telefone"));
				c.setCelular(resultados.getString("celular"));
				
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return c;
	}

	@Override
	public void atualizar(Cliente t) {
		SqlGenImpl gerador = new SqlGenImpl();
			
		try {

			PreparedStatement ps = gerador.getSqlUpdateById(con, t);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getEndereco());
			ps.setInt(3, t.getNumero());
			ps.setString(4, t.getComplemento());
			ps.setString(5, t.getBairro());
			ps.setString(6, t.getCidade());
			ps.setString(7, t.getEstado());
			ps.setString(8, t.getCep());
			ps.setString(9, t.getTelefone());
			ps.setString(10, t.getCelular());
			ps.setInt(11, t.getId());
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public void excluir(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
				
		try {

			PreparedStatement ps = gerador.getSqlDeleteById(con, new Cliente());
			ps.setInt(1, k);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public List<Cliente> listarTodos() {
		SqlGenImpl gerador = new SqlGenImpl();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		try {

			PreparedStatement ps = gerador.getSqlSelectAll(con, new Cliente());
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				
				Cliente c = new Cliente();
				c.setId(resultados.getInt("id"));
				c.setNome(resultados.getString("nome"));
				c.setEndereco(resultados.getString("endereco"));
				c.setNumero(resultados.getInt("numero"));
				c.setComplemento(resultados.getString("complemento"));
				c.setBairro(resultados.getString("bairro"));
				c.setCidade(resultados.getString("cidade"));
				c.setEstado(resultados.getString("estado"));
				c.setCep(resultados.getString("cep"));
				c.setTelefone(resultados.getString("telefone"));
				c.setCelular(resultados.getString("celular"));
				
				
				listaCliente.add(c);
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return listaCliente;		
	}

	public void criarTabela(Cliente t){
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {
			String sql = gerador.getCreateTable(con, t);	
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}			
		
	}

	@Override
	public int proximoID() {
		SqlGenImpl gerador = new SqlGenImpl();
		int cod = 0;
				
		try {

			PreparedStatement ps = gerador.getNextId(con, new Cliente());
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				cod = resultados.getInt("codigo");
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return cod;
	}	
	
}
