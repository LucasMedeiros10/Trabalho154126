package br.univel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import br.univel.classes.Conexao;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Principal extends JFrame{
	
	public static Conexao con;	
			
	public Principal(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sistema de Vendas");
		
		JPanel jp = new JPanel();  //cria painel
		jp.setLayout(new BorderLayout()); // seta o layout
		JScrollPane jsc = new JScrollPane();//barra de rolagem	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnCadastros.add(mntmClientes);
		
		//classe anonima
		mntmClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//tela
				PsqClientes psqClientes = new PsqClientes();		
				psqClientes.setSize(740, 460);
				psqClientes.setLocationRelativeTo(null); //centraliza na tela
				psqClientes.setVisible(true);//mostra na tela
			}
		});			
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mnCadastros.add(mntmProdutos);
		
		//classe anonima
		mntmProdutos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//tela
				PsqProdutos psqProdutos = new PsqProdutos();		
				psqProdutos.setSize(740, 460);
				psqProdutos.setLocationRelativeTo(null); //centraliza na tela
				psqProdutos.setVisible(true);//mostra na tela
			}
		});			
		
		JMenu mnLanamentos = new JMenu("Lan\u00E7amentos");
		menuBar.add(mnLanamentos);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mnLanamentos.add(mntmVendas);
		
		//classe anonima
		mntmVendas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//tela
				PsqVendas psqVendas = new PsqVendas();		
				psqVendas.setSize(740, 460);
				psqVendas.setLocationRelativeTo(null); //centraliza na tela
				psqVendas.setVisible(true);//mostra na tela
			}
		});			
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmClientesRel = new JMenuItem("Clientes");
		mnRelatrios.add(mntmClientesRel);
		
		JMenuItem mntmProdutosRel = new JMenuItem("Produtos");
		mnRelatrios.add(mntmProdutosRel);
		
		JMenuItem mntmVendasRel = new JMenuItem("Vendas");
		mnRelatrios.add(mntmVendasRel);
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		
		JMenuItem mntmFecharOSistema = new JMenuItem("Fechar o sistema");
		mnSair.add(mntmFecharOSistema);
		//classe anonima
		mntmFecharOSistema.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});			
		
		
		JLabel lblTitulo = new JLabel("Sistema de Vendas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		jp.add(lblTitulo, BorderLayout.NORTH);			
		
		setContentPane(jp);  //pertence a JFrame		
	}
	
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				con = new Conexao();
				try {
					con.abrirConexao();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//tela
				Principal tp = new Principal();
				tp.setExtendedState(MAXIMIZED_BOTH);
				tp.setLocationRelativeTo(null); //centraliza na tela
				tp.setVisible(true);//mostra na tela
			}
		});
	}

	

}
