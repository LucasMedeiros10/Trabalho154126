package br.univel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.univel.classes.ArquivoReader;
import br.univel.classes.Cliente;
import br.univel.classes.ClienteParser;
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ListaClientes;
import br.univel.classes.ModeloCliente;
import br.univel.classes.SerializadorImpl;
import br.univel.excecoes.SerializadorException;

import java.awt.event.ActionEvent;

public class PsqClientes extends PsqPadrao{
	
	private List<Cliente> lista = new ArrayList<Cliente>();
	private SerializadorImpl<List<Cliente>> serializador = new SerializadorImpl<List<Cliente>>();
	private ExportXMLImp<ListaClientes> exportadorXML = new ExportXMLImp<ListaClientes>();

	public PsqClientes(){
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes lc = new ListaClientes();
				lc = exportadorXML.ImportarXml(lc, new File("listaclientes.xml"));
				
				lista.clear();
				lista = lc.getListaCliente();
				montarConsulta();
			}
		});
		
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListaClientes lc = new ListaClientes();
				lc.setListaCliente(lista);
				exportadorXML.ExportarXml(lc, new File("listaclientes.xml"));
				
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					lista.clear();
					lista = serializador.ler(new File("listaclientes.dat"));
					montarConsulta();
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listaclientes.dat"));
				} catch (SerializadorException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnImportarTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArquivoReader lerTxt = new ArquivoReader();
				ClienteParser cp = new ClienteParser();
				
				lista = cp.getCliente(lerTxt.lerArquivo("listaclientes.csv"));
				montarConsulta();
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				//tela
				CadCliente CadCliente = new CadCliente();		
				CadCliente.setSize(600, 400);
				CadCliente.setLocationRelativeTo(null); //centraliza na tela
				CadCliente.lblTitulo.setText("Alterar Cliente");
				CadCliente.setVisible(true);//mostra na tela					
			
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				CadCliente CadCliente = new CadCliente();		
				CadCliente.setSize(600, 400);
				CadCliente.setLocationRelativeTo(null); //centraliza na tela
				CadCliente.lblTitulo.setText("Inserir Cliente");
				CadCliente.setVisible(true);//mostra na tela					
			}
		});
		
		setTitle("Pesquisa de Clientes");
		lblTitulo.setText("Pesquisa de Clientes");	
		
		// $hide>>$
		montarConsulta();
		// $hide<<$				
	}
	

	public void montarConsulta(){		
		ModeloCliente modelo = new ModeloCliente(lista);//instancia um modelo de tabela
		tblResultados.setModel(modelo);//seta a tabela	
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(20);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(400);		
	}
	
}
