package br.univel.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.interfaces.Dao;

public class DaoItemVenda implements Dao<ItemVenda, Integer> {

	private Connection con = null;
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}	
	
	@Override
	public void salvar(ItemVenda t) {
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {

			PreparedStatement ps = gerador.getSqlInsert(con, t);
			ps.setInt(1, t.getId_venda());
			ps.setInt(2, t.getP().getId());
			ps.setBigDecimal(3, t.getQtde());
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}			
	}

	@Override
	public ItemVenda buscar(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
		ItemVenda i = new ItemVenda();
		DaoProduto dp = new DaoProduto();
		try {
			dp.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
				
		try {

			PreparedStatement ps = gerador.getSqlSelectById(con, new Cliente());
			ps.setInt(1, k);
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				i.setId_venda(resultados.getInt("id_venda"));				
				i.setP(dp.buscar(resultados.getInt("id_produto")));
				i.setQtde(resultados.getBigDecimal("qtde"));
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return i;
	}

	@Override
	public void atualizar(ItemVenda t) {
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {

			PreparedStatement ps = gerador.getSqlUpdateById(con, t);
			ps.setBigDecimal(1, t.getQtde());
			ps.setInt(2, t.getId_venda());
			ps.setInt(3, t.getP().getId());
			
			
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

			PreparedStatement ps = con.prepareStatement("DELETE FROM vendas_produtos WHERE id_venda = ?");
			ps.setInt(1, k);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public List<ItemVenda> listarTodos() {
		SqlGenImpl gerador = new SqlGenImpl();
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		DaoProduto dp = new DaoProduto();
		try {
			dp.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
				
		try {

			PreparedStatement ps = gerador.getSqlSelectAll(con, new ItemVenda());
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				ItemVenda i = new ItemVenda();
				
				i.setId_venda(resultados.getInt("id_venda"));				
				i.setP(dp.buscar(resultados.getInt("id_produto")));
				i.setQtde(resultados.getBigDecimal("qtde"));
				
				listaItemVenda.add(i);
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return listaItemVenda;
	}
	
	public void criarTabela(ItemVenda t){
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

			PreparedStatement ps = gerador.getNextId(con, new ItemVenda());
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
	
	public List<ItemVenda> listarItensVenda(int id_venda) {
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		DaoProduto dp = new DaoProduto();
		dp.setCon(con);
				
				
		try {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM vendas_produtos WHERE id_venda = ?");
			ps.setInt(1, id_venda);
			ResultSet resultados = ps.executeQuery();
			
			while (resultados.next()) {
				ItemVenda i = new ItemVenda();
				
				i.setId_venda(resultados.getInt("id_venda"));				
				i.setP(dp.buscar(resultados.getInt("id_produto")));
				i.setQtde(resultados.getBigDecimal("qtde"));
				
				listaItemVenda.add(i);
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return listaItemVenda;
	}


}
