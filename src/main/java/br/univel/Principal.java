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
import br.univel.classes.DaoCliente;
import br.univel.relatorios.ClienteJRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame{
				
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
		//classe anonima
		mntmClientesRel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					imprimirRelClientes();
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
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
				
				//tela
				Principal tp = new Principal();
				tp.setExtendedState(MAXIMIZED_BOTH);
				tp.setLocationRelativeTo(null); //centraliza na tela
				tp.setVisible(true);//mostra na tela
			}
		});
	}

	private void imprimirRelClientes() throws JRException{
		String arq = "cliente_report.jasper";
		
		DaoCliente dao = new DaoCliente();
		try {
			dao.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClienteJRDataSource ds = new ClienteJRDataSource(dao.listarTodos());


		JasperPrint jp = JasperFillManager.fillReport(arq, null, ds);

		JasperViewer jasperViewer = new JasperViewer(jp);

		jasperViewer.setBounds(50, 50, 320, 240);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);

		jasperViewer.setVisible(true);		
	}
	

}
