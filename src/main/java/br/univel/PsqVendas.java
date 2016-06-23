package br.univel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.univel.classes.ExportXMLImp;
import br.univel.classes.ListaVendas;
import br.univel.classes.ModeloVenda;
import br.univel.classes.SerializadorImpl;
import br.univel.classes.Venda;
import br.univel.excecoes.SerializadorException;

import java.awt.event.ActionEvent;

public class PsqVendas extends PsqPadrao {
	
	private List<Venda> lista = new ArrayList<Venda>();
	private SerializadorImpl<List<Venda>> serializador = new SerializadorImpl<List<Venda>>();
	private ExportXMLImp<ListaVendas> exportadorXML = new ExportXMLImp<ListaVendas>();	
	
	
	public PsqVendas(){
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listavendas.dat"));
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}						
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
					lista.clear();
					lista = serializador.ler(new File("listavendas.dat"));
					montarConsulta();
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVendas lv = new ListaVendas();
				lv = exportadorXML.ImportarXml(lv, new File("listavendas.xml"));
				
				lista.clear();
				lista = lv.getListaVenda();
				montarConsulta();				
			}
		});
		
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVendas lv = new ListaVendas();
				lv.setListaVenda(lista);
				exportadorXML.ExportarXml(lv, new File("listavendas.xml"));			
			
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				LanVendas LanVendas = new LanVendas();		
				LanVendas.setSize(800, 600);
				LanVendas.setLocationRelativeTo(null); //centraliza na tela
				LanVendas.lblTitulo.setText("Alteração de Venda");
				LanVendas.setVisible(true);//mostra na tela				
			
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				LanVendas LanVendas = new LanVendas();		
				LanVendas.setSize(800, 600);
				LanVendas.setLocationRelativeTo(null); //centraliza na tela
				LanVendas.lblTitulo.setText("Lançamento de Venda");
				LanVendas.setVisible(true);//mostra na tela				
			}
		});
		
		setTitle("Vendas");
		lblTitulo.setText("Vendas");
		btnImportarTXT.setVisible(false);
		
		// $hide>>$
		montarConsulta();
		// $hide<<$				
	}
	
	public void montarConsulta(){		
		ModeloVenda modelo = new ModeloVenda(lista);//instancia um modelo de tabela
		tblResultados.setModel(modelo);//seta a tabela	
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(55);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(380);		
		tblResultados.getColumnModel().getColumn(2).setPreferredWidth(200);			
	}	
}
