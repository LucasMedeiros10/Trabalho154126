package br.univel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import br.univel.classes.ArquivoReader;
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ListaProdutos;
import br.univel.classes.ModeloProduto;
import br.univel.classes.Produto;
import br.univel.classes.ProdutoParser;
import br.univel.classes.SerializadorImpl;
import br.univel.excecoes.SerializadorException;

public class PsqProdutos extends PsqPadrao{

	private List<Produto> lista = new ArrayList<Produto>();
	private SerializadorImpl<List<Produto>> serializador = new SerializadorImpl<List<Produto>>();
	private ExportXMLImp<ListaProdutos> exportadorXML = new ExportXMLImp<ListaProdutos>();	
	
	
	public PsqProdutos(){
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProdutos lc = new ListaProdutos();
				lc.setListaProd(lista);
				exportadorXML.ExportarXml(lc, new File("listaprodutos.xml"));				
			}
		});
		
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProdutos lp = new ListaProdutos();
				lp = exportadorXML.ImportarXml(lp, new File("listaprodutos.xml"));
				
				lista.clear();
				lista = lp.getListaProd();
				montarConsulta();				
			}
		});
		
		btnImportarTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArquivoReader lerTxt = new ArquivoReader();
				ProdutoParser pp = new ProdutoParser();
				
				lista = pp.getProduto(lerTxt.lerArquivo("listaprodutos.txt"));
				montarConsulta();				
			}
		});
		
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listaprodutos.dat"));
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
					lista = serializador.ler(new File("listaprodutos.dat"));
					montarConsulta();
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				CadProduto CadProduto = new CadProduto();		
				CadProduto.setSize(470, 280);
				CadProduto.setLocationRelativeTo(null); //centraliza na tela
				CadProduto.lblTitulo.setText("Alterar Produto");
				CadProduto.setVisible(true);//mostra na tela				
			}
		});
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//tela
				CadProduto CadProduto = new CadProduto();		
				CadProduto.setSize(470, 280);
				CadProduto.setLocationRelativeTo(null); //centraliza na tela
				CadProduto.lblTitulo.setText("Inserir Produto");
				CadProduto.setVisible(true);//mostra na tela					
			}
		});
		setTitle("Pesquisa de Produtos");
		lblTitulo.setText("Pesquisa de Produtos");
	}
	
	public void montarConsulta(){		
		ModeloProduto modelo = new ModeloProduto(lista);//instancia um modelo de tabela
		tblResultados.setModel(modelo);//seta a tabela	
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(55);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(380);		
		tblResultados.getColumnModel().getColumn(2).setPreferredWidth(100);			
	}	

}
