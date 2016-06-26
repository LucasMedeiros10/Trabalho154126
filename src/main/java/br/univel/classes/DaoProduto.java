package br.univel.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.interfaces.Dao;

public class DaoProduto implements Dao<Produto, Integer> {


	private Connection con = null;
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public void salvar(Produto t) {
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {

			PreparedStatement ps = gerador.getSqlInsert(con, t);
			ps.setInt(1, t.getId());
			ps.setString(2, t.getDescricao());
			ps.setBigDecimal(3, t.getPreco());
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}			
	}

	@Override
	public Produto buscar(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
		Produto p = new Produto();
				
		try {

			PreparedStatement ps = gerador.getSqlSelectById(con, new Produto());
			ps.setInt(1, k);
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				p.setId(resultados.getInt("id"));
				p.setDescricao(resultados.getString("descricao"));
				p.setPreco(resultados.getBigDecimal("preco"));
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return p;
	}

	@Override
	public void atualizar(Produto t) {
		SqlGenImpl gerador = new SqlGenImpl();
			
		try {

			PreparedStatement ps = gerador.getSqlUpdateById(con, t);
			ps.setString(1, t.getDescricao());
			ps.setBigDecimal(2, t.getPreco());
			ps.setInt(3, t.getId());
			
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

			PreparedStatement ps = gerador.getSqlDeleteById(con, new Produto());
			ps.setInt(1, k);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public List<Produto> listarTodos() {
		SqlGenImpl gerador = new SqlGenImpl();
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		try {

			PreparedStatement ps = gerador.getSqlSelectAll(con, new Produto());
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				
				Produto p = new Produto();
				p.setId(resultados.getInt("id"));
				p.setDescricao(resultados.getString("descricao"));
				p.setPreco(resultados.getBigDecimal("preco"));
				
				listaProduto.add(p);
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return listaProduto;		
	}

	public void criarTabela(Produto t){
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

			PreparedStatement ps = gerador.getNextId(con, new Produto());
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
