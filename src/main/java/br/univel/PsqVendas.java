package br.univel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.univel.classes.Cliente;
import br.univel.classes.Conexao;
import br.univel.classes.DaoItemVenda;
import br.univel.classes.DaoVenda;
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ItemVenda;
import br.univel.classes.ListaClientes;
import br.univel.classes.ListaVendas;
import br.univel.classes.ModeloVenda;
import br.univel.classes.Produto;
import br.univel.classes.SerializadorImpl;
import br.univel.classes.Venda;
import br.univel.excecoes.SerializadorException;

import java.awt.event.ActionEvent;

public class PsqVendas extends PsqPadrao {
	
	private Conexao conexao;	
	private List<Venda> lista = new ArrayList<Venda>();
	private SerializadorImpl<List<Venda>> serializador = new SerializadorImpl<List<Venda>>();
	private ExportXMLImp<ListaVendas> exportadorXML = new ExportXMLImp<ListaVendas>();	
	private DaoVenda dv = new DaoVenda();
	
	public PsqVendas(){
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
							dv.excluir(id);
							montarConsulta();
						}
					}
				}				
			}
		});
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarConsulta();
			}
		});
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtPesquisaKeyPressed(arg0);
			}
		});			
		
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listavendas.dat"));
				} catch (SerializadorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				JOptionPane.showMessageDialog(null, "Serialização finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
					List<Venda> listaTemp = new ArrayList<Venda>();					
					listaTemp = serializador.ler(new File("listavendas.dat"));
					
					for(Venda v : listaTemp){
						if(dv.buscar(v.getId()).getId() > 0){
							dv.atualizar(v);
						}else{
							dv.salvar(v);
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
		
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVendas lv = new ListaVendas();
				lv = exportadorXML.ImportarXml(lv, new File("listavendas.xml"));		
				
				
				List<Venda> listaTemp = new ArrayList<Venda>();					
				listaTemp = lv.getListaVenda();
				
				for(Venda v : listaTemp){
					if(dv.buscar(v.getId()).getId() > 0){
						dv.atualizar(v);
					}else{
						dv.salvar(v);
					}
				}
				JOptionPane.showMessageDialog(null, "Importação de XML finalizado com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				montarConsulta();					
				
			}
		});
		
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVendas lv = new ListaVendas();
				lv.setListaVenda(lista);
				exportadorXML.ExportarXml(lv, new File("listavendas.xml"));			
				JOptionPane.showMessageDialog(null, "Exportação de XML finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
			
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
						//tela
						LanVendas LanVendas = new LanVendas();		
						LanVendas.setSize(700, 530);
						LanVendas.setLocationRelativeTo(null); //centraliza na tela
						LanVendas.lblTitulo.setText("Alteração de Venda");
						LanVendas.setEditando(true);
						LanVendas.carregarDados((int) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0));
						LanVendas.setVisible(true);//mostra na tela				
					}
				}				
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				LanVendas LanVendas = new LanVendas();		
				LanVendas.setSize(700, 530);
				LanVendas.setLocationRelativeTo(null); //centraliza na tela
				LanVendas.lblTitulo.setText("Lançamento de Venda");
				LanVendas.setEditando(false);
				LanVendas.setId_venda(dv.proximoID());		
				LanVendas.setVisible(true);//mostra na tela				
			}
		});
		
		setTitle("Vendas");
		lblTitulo.setText("Vendas");
		btnImportarTXT.setVisible(false);
		
		// $hide>>$
		executarScripts();
		montarConsulta();
		// $hide<<$				
	}
	
	public void montarConsulta(){	
		txtPesquisa.setText("");
		lista.clear();
		lista = dv.listarTodos();
		
		ModeloVenda modelo = new ModeloVenda(lista);//instancia um modelo de tabela
		tblResultados.setModel(modelo);//seta a tabela	
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(55);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(380);			
	}	
	
	private void executarScripts(){			
		
		conexao = new Conexao();
		
		try {
			dv.setCon(conexao.abrirConexao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		dv.criarTabela(new Venda());
		
		//cria tabela de produtos
		DaoItemVenda di = new DaoItemVenda();

		try {
			di.setCon(conexao.abrirConexao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		di.criarTabela(new ItemVenda());
		
	}	
	
	private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt){                                         
		ModeloVenda model =  (ModeloVenda) tblResultados.getModel();
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
}
