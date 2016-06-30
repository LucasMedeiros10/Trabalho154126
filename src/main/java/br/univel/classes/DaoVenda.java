package br.univel.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.interfaces.Dao;

public class DaoVenda implements Dao<Venda, Integer> {


	private DaoItemVenda di = new DaoItemVenda();
	private Connection con = null;
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public void salvar(Venda t) {
		SqlGenImpl gerador = new SqlGenImpl();
		
		try {

			PreparedStatement ps = gerador.getSqlInsert(con, t);
			ps.setInt(1, t.getId());
			ps.setInt(2, t.getCliente().getId());			
			ps.setBigDecimal(3, t.getVlrPago());
			ps.executeUpdate();
			ps.close();
			
			di.setCon(con);
			for(ItemVenda iv :  t.getItens()){
				di.salvar(iv);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}			
	}

	@Override
	public Venda buscar(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
		Venda v = new Venda();
		DaoCliente dc = new DaoCliente();
		try {
			dc.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
				
		try {

			PreparedStatement ps = gerador.getSqlSelectById(con, new Venda());
			ps.setInt(1, k);
			ResultSet resultados = ps.executeQuery();
			
			di.setCon(con);
			while (resultados.next()) {
				v.setId(resultados.getInt("id"));				
				v.setCliente(dc.buscar(resultados.getInt("id_cliente")));
				v.setItens(di.listarItensVenda(v.getId()));
				v.setVlrPago(resultados.getBigDecimal("vlrPago"));
			}			
			
			di.setCon(con);
			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return v;
	}

	@Override
	public void atualizar(Venda t) {
		SqlGenImpl gerador = 	new SqlGenImpl();
			
		try {

			PreparedStatement ps = gerador.getSqlUpdateById(con, t);
			ps.setInt(1, t.getCliente().getId());
			ps.setInt(2, t.getId());				
			ps.setBigDecimal(3, t.getVlrPago());
			ps.executeUpdate();
			ps.close();
			
			//atualizar produtos
			di.setCon(con);
			di.excluir(t.getId());
			for(ItemVenda iv :  t.getItens()){
				di.salvar(iv);
			}			

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public void excluir(Integer k) {
		SqlGenImpl gerador = new SqlGenImpl();
				
		try {

			PreparedStatement ps = gerador.getSqlDeleteById(con, new Venda());
			ps.setInt(1, k);
			ps.executeUpdate();
			ps.close();
			
			//excluir produtos
			di.setCon(con);
			di.excluir(k);

		} catch (SQLException e) {
			e.printStackTrace();

		}		
	}

	@Override
	public List<Venda> listarTodos() {
		SqlGenImpl gerador = new SqlGenImpl();
		List<Venda> listaVenda = new ArrayList<Venda>();

		DaoCliente dc = new DaoCliente();
		try {
			dc.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {

			PreparedStatement ps = gerador.getSqlSelectAll(con, new Venda());
			ResultSet resultados = ps.executeQuery();
			di.setCon(con);
			
			while (resultados.next()) {
				
				Venda v = new Venda();
				
				v.setId(resultados.getInt("id"));
				v.setCliente(dc.buscar(resultados.getInt("id_cliente")));
				v.setItens(di.listarItensVenda(v.getId()));
				v.setVlrPago(resultados.getBigDecimal("vlrPago"));
				
				listaVenda.add(v);
			}			
			
			ps.close();
			resultados.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return listaVenda;		
	}

	public void criarTabela(Venda t){
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

			PreparedStatement ps = gerador.getNextId(con, new Venda());
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
