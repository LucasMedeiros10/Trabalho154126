package br.univel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.univel.classes.ArquivoReader;
import br.univel.classes.Cliente;
import br.univel.classes.Produto;
import br.univel.classes.ProdutoParser;
import br.univel.classes.SerializadorImpl;
import br.univel.classes.Venda;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import com.sun.prism.TextureMap;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Principal extends JFrame{
	
	private static List<Produto> listaPrd = null;
	private static List<Cliente> listaCliente = new ArrayList<Cliente>();
	private static List<Venda> listaVendas = new ArrayList<Venda>();
	
	public static void lerTxtProdutos(){
		
		ArquivoReader reader = new ArquivoReader();
		List<String> lista = reader.lerArquivo("listaprodutos.txt");

		ProdutoParser parser = new ProdutoParser();
		listaPrd = parser.getProduto(lista);		
		
		listaPrd.forEach(e ->{
			System.out.println("ID: " + e.getId());
			System.out.println("Descrição: " + e.getDescricao());
			System.out.println("Preço: " + e.getPreco());
			System.out.println("---------------------------------------------");
		});		
	}
	
	public static void backupProdSerializada(){
		
		SerializadorImpl<List<Produto>> serial = new SerializadorImpl<List<Produto>>();
		try {
			serial.gravar(listaPrd, new File("produtos.dat"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public static void restauraProdSerializada(){
		
		File file = new File("produtos.dat");
		
		try(FileInputStream fis   = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {		
			
			listaPrd = (List<Produto>)ois.readObject();
			
			for (Produto prod : listaPrd) {
				System.out.println(prod.getId());
				System.out.println(prod.getDescricao());				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}				
	}
	
	
	public static void lerTxtClientes(){
		listaCliente.add(new Cliente(1, "Lucas", "Rua ABC", 200, "", "Pq. São Paulo", "Cascavel", "PR", "85803550", "", ""));
		listaCliente.add(new Cliente(2, "Luis", "Rua ABC", 200, "", "Pq. São Paulo", "Cascavel", "PR", "85803550", "", ""));
		
		listaCliente.forEach(e ->{
			System.out.println("ID: " + e.getId());
			System.out.println("Nome: " + e.getNome());
			System.out.println("---------------------------------------------");
		});		
	}
			
	
	public static void backupClienteSerializada(){
		
		SerializadorImpl<List<Cliente>> serial = new SerializadorImpl<List<Cliente>>();
		try {
			serial.gravar(listaCliente, new File("clientes.dat"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void restauraClienteSerializada(){
		
		File file = new File("clientes.dat");
		
		try(FileInputStream fis   = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {		
			
			listaCliente = (List<Cliente>)ois.readObject();
			 
			for (Cliente cli : listaCliente) {
				System.out.println(cli.getId());
				System.out.println(cli.getNome());				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}				
	}
	
	public static void carregarVendas(){
		Cliente c1 = new Cliente(1, "Lucas", "Rua ABC", 200, "", "Pq. São Paulo", "Cascavel", "PR", "85803550", "", "");
		Cliente c2 = new Cliente(2, "Luis", "Rua ABC", 200, "", "Pq. São Paulo", "Cascavel", "PR", "85803550", "", "");	

		listaVendas.add(new Venda(1, c1, listaPrd));
		listaVendas.add(new Venda(2, c2, listaPrd));
		
	}
		
	public static void backupVendaSerializada(){
		
		SerializadorImpl<List<Venda>> serial = new SerializadorImpl<List<Venda>>();
		try {
			serial.gravar(listaVendas, new File("vendas.dat"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public static void restauraVendaSerializada(){
		
		File file = new File("vendas.dat");
		
		try(FileInputStream fis   = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {		
			
			listaVendas = (List<Venda>)ois.readObject();
			 
			for (Venda v : listaVendas) {
				System.out.println(v.getId());
				System.out.println(v.getCliente().getNome());				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}				
	}
	
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
				psqClientes.setSize(701, 413);
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
				psqProdutos.setSize(701, 413);
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
				psqVendas.setSize(701, 413);
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
				
				//tela
				Principal tp = new Principal();
				tp.setExtendedState(MAXIMIZED_BOTH);
				tp.setLocationRelativeTo(null); //centraliza na tela
				tp.setVisible(true);//mostra na tela
			}
		});
	}

	

}
