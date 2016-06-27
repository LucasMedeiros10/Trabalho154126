package br.univel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import br.univel.classes.ArquivoReader;
import br.univel.classes.Conexao;
import br.univel.classes.DaoProduto;
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ListaProdutos;
import br.univel.classes.ModeloProduto;
import br.univel.classes.Produto;
import br.univel.classes.ProdutoParser;
import br.univel.classes.SerializadorImpl;
import br.univel.excecoes.SerializadorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PsqProdutos extends PsqPadrao{

	private Conexao conexao;	
	private List<Produto> lista = new ArrayList<Produto>();
	private SerializadorImpl<List<Produto>> serializador = new SerializadorImpl<List<Produto>>();
	private ExportXMLImp<ListaProdutos> exportadorXML = new ExportXMLImp<ListaProdutos>();	
	private DaoProduto dp = new DaoProduto();
	private LanVendas frameSecundario;
	
	
	public PsqProdutos(){
		tblResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frameSecundario != null){
					frameSecundario.txtNomeProduto.setText((String) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 1));
					int cod = (int)tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0);
					frameSecundario.setProdutoAtual(dp.buscar(cod));
					dispose();
				}
			}
		
		});
		
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtPesquisaKeyPressed(arg0);
			}
		});		
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarConsulta();
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista.isEmpty()){
					JOptionPane.showMessageDialog(null, "Nenhum registro a ser excluído.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}else{	
					if(tblResultados.getSelectedRow() == -1){
						JOptionPane.showMessageDialog(null, "Selecione um registro.", "Informação", JOptionPane.INFORMATION_MESSAGE);
					}else{
					
						int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?", "Exclusão", JOptionPane.YES_NO_OPTION);
						
						if(opcao == 0){
							int id = (int) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0);
							dp.excluir(id);
							montarConsulta();
						}
					}
				}
			}
		});		
		
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProdutos lc = new ListaProdutos();
				lc.setListaProd(lista);
				exportadorXML.ExportarXml(lc, new File("listaprodutos.xml"));	
				JOptionPane.showMessageDialog(null, "Exportação de XML finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProdutos lp = new ListaProdutos();
				lp = exportadorXML.ImportarXml(lp, new File("listaprodutos.xml"));
				
				List<Produto> listaTemp = new ArrayList<Produto>();					
				listaTemp = lp.getListaProd();
				
				for(Produto p : listaTemp){
					if(dp.buscar(p.getId()).getId() > 0){
						dp.atualizar(p);
					}else{
						dp.salvar(p);
					}
				}
				JOptionPane.showMessageDialog(null, "Importação de XML finalizado com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				montarConsulta();				
			}
		});
		
		btnImportarTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArquivoReader lerTxt = new ArquivoReader();
				ProdutoParser pp = new ProdutoParser();
				List<Produto> listaTemp = new ArrayList<Produto>();
				
				listaTemp = pp.getProduto(lerTxt.lerArquivo("listaprodutos.txt"));
				
				for(Produto p : listaTemp){
					if(dp.buscar(p.getId()).getId() > 0){
						dp.atualizar(p);
					}else{
						dp.salvar(p);
					}
				}
				JOptionPane.showMessageDialog(null, "Importação finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				montarConsulta();			
			}
		});
		
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listaprodutos.dat"));
					JOptionPane.showMessageDialog(null, "Serialização finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					List<Produto> listaTemp = new ArrayList<Produto>();					
					listaTemp = serializador.ler(new File("listaprodutos.dat"));
					for(Produto p : listaTemp){
						if(dp.buscar(p.getId()).getId() > 0){
							dp.atualizar(p);
						}else{
							dp.salvar(p);
						}
					}
					JOptionPane.showMessageDialog(null, "Restauração finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
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
				if(lista.isEmpty()){
					JOptionPane.showMessageDialog(null, "Nenhum registro a ser alterado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}else{					
					if(tblResultados.getSelectedRow() == -1){
						JOptionPane.showMessageDialog(null, "Selecione um registro.", "Informação", JOptionPane.INFORMATION_MESSAGE);
					}else{		
						CadProduto CadProduto = new CadProduto();		
						CadProduto.setSize(470, 280);
						CadProduto.setLocationRelativeTo(null); //centraliza na tela
						CadProduto.lblTitulo.setText("Alterar Produto");
						CadProduto.setEditando(true);
						CadProduto.carregarDados((int) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0));
						CadProduto.setVisible(true);//mostra na tela	
					}
				}				
			}
		});
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//tela
				CadProduto CadProduto = new CadProduto();		
				CadProduto.setSize(470, 280);
				CadProduto.setLocationRelativeTo(null); //centraliza na tela
				CadProduto.lblTitulo.setText("Inserir Produto");
				CadProduto.txtID.setText(Integer.toString(dp.proximoID()));				
				CadProduto.setEditando(false);
				CadProduto.setVisible(true);//mostra na tela					
			}
		});
		setTitle("Pesquisa de Produtos");
		lblTitulo.setText("Pesquisa de Produtos");
		
		// $hide>>$
		executarScripts();
		montarConsulta();
		// $hide<<$		
	}
	
	public void montarConsulta(){		
		txtPesquisa.setText("");
		lista.clear();
		lista = dp.listarTodos();
		ModeloProduto modelo = new ModeloProduto(lista);//instancia um modelo de tabela
		
		tblResultados.setRowSorter(null);
		tblResultados.setModel(modelo);//seta a tabela	
		
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(55);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(380);		
		tblResultados.getColumnModel().getColumn(2).setPreferredWidth(100);			
	}	
	
	private void executarScripts(){			
		
		conexao = new Conexao();
		
		try {
			dp.setCon(conexao.abrirConexao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		dp.criarTabela(new Produto());
	}
	
	private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt){                                         
        ModeloProduto model =  (ModeloProduto) tblResultados.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tblResultados.setRowSorter(sorter);
        String text = txtPesquisa.getText().toUpperCase();
        if (text.length() == 0){
             sorter.setRowFilter(null);
        } else{
             try{
                sorter.setRowFilter(
                RowFilter.regexFilter(text));
             } catch (PatternSyntaxException pse) {
                System.err.println("Erro");
             }
        }
	}

	public LanVendas getFrameSecundario() {
		return frameSecundario;
	}

	public void setFrameSecundario(LanVendas frameSecundario) {
		this.frameSecundario = frameSecundario;
	}  	

}
