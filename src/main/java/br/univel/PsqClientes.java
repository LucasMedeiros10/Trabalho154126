package br.univel;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.univel.classes.ArquivoReader;
import br.univel.classes.Cliente;
import br.univel.classes.ClienteParser;
import br.univel.classes.Conexao;
import br.univel.classes.DaoCliente;
import br.univel.classes.ExportXMLImp;
import br.univel.classes.ListaClientes;
import br.univel.classes.ModeloCliente;
import br.univel.classes.SerializadorImpl;
import br.univel.excecoes.SerializadorException;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PsqClientes extends PsqPadrao{
	
	private Conexao conexao;
	private List<Cliente> lista = new ArrayList<Cliente>();
	private SerializadorImpl<List<Cliente>> serializador = new SerializadorImpl<List<Cliente>>();
	private ExportXMLImp<ListaClientes> exportadorXML = new ExportXMLImp<ListaClientes>();
	private DaoCliente dc  = new DaoCliente();
	private LanVendas frameSecundario;
	
	
	
	public PsqClientes(){
		tblResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frameSecundario != null){
					frameSecundario.txtCliente.setText((String) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 1));
					int cod = (int)tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0);
					frameSecundario.setClienteAtual(dc.buscar(cod));
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
							dc.excluir(id);
							montarConsulta();
						}
					}
				}
			}
		});
		
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes lc = new ListaClientes();
				lc = exportadorXML.ImportarXml(lc, new File("listaclientes.xml"));
				
				List<Cliente> listaTemp = new ArrayList<Cliente>();					
				listaTemp = lc.getListaCliente();
				
				for(Cliente c : listaTemp){
					if(dc.buscar(c.getId()).getId() > 0){
						dc.atualizar(c);
					}else{
						dc.salvar(c);
					}
				}
				JOptionPane.showMessageDialog(null, "Importação de XML finalizado com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				montarConsulta();				
				
				
			}
		});
		
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListaClientes lc = new ListaClientes();
				lc.setListaCliente(lista);
				exportadorXML.ExportarXml(lc, new File("listaclientes.xml"));
				JOptionPane.showMessageDialog(null, "Exportação de XML finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);				
				
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					List<Cliente> listaTemp = new ArrayList<Cliente>();					
					listaTemp = serializador.ler(new File("listaclientes.dat"));
					
					for(Cliente c : listaTemp){
						if(dc.buscar(c.getId()).getId() > 0){
							dc.atualizar(c);
						}else{
							dc.salvar(c);
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
		btnSerializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					serializador.gravar(lista, new File("listaclientes.dat"));
					JOptionPane.showMessageDialog(null, "Serialização finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				} catch (SerializadorException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnImportarTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArquivoReader lerTxt = new ArquivoReader();
				ClienteParser cp = new ClienteParser();
				List<Cliente> listaTemp = new ArrayList<Cliente>();
				
				listaTemp = cp.getCliente(lerTxt.lerArquivo("listaclientes.csv"));
				
				for(Cliente c : listaTemp){
					if(dc.buscar(c.getId()).getId() > 0){
						dc.atualizar(c);
					}else{
						dc.salvar(c);
					}
				}
				JOptionPane.showMessageDialog(null, "Importação finalizada com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				montarConsulta();
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
						CadCliente CadCliente = new CadCliente();		
						CadCliente.setSize(600, 400);
						CadCliente.setLocationRelativeTo(null); //centraliza na tela
						CadCliente.lblTitulo.setText("Alterar Cliente");
						CadCliente.setEditando(true);
						CadCliente.carregarDados((int) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0));
						CadCliente.setVisible(true);//mostra na tela
					}
				}
				
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				CadCliente CadCliente = new CadCliente();		
				CadCliente.setSize(600, 400);
				CadCliente.setLocationRelativeTo(null); //centraliza na tela
				CadCliente.lblTitulo.setText("Inserir Cliente");
				CadCliente.txtid.setText(Integer.toString(dc.proximoID()));
				CadCliente.setEditando(false);
				CadCliente.setVisible(true);//mostra na tela			
				montarConsulta();
			}
		});
		
		setTitle("Pesquisa de Clientes");
		lblTitulo.setText("Pesquisa de Clientes");	
		
		
		
		// $hide>>$
		executarScripts();
		montarConsulta();
		// $hide<<$				
	}

	public void montarConsulta(){	
		txtPesquisa.setText("");
		lista.clear();
		lista = dc.listarTodos();
		ModeloCliente modelo = new ModeloCliente(lista);//instancia um modelo de tabela
		
		tblResultados.setRowSorter(null);
		tblResultados.setModel(modelo);//seta a tabela		
		tblResultados.getColumnModel().getColumn(0).setPreferredWidth(20);		
		tblResultados.getColumnModel().getColumn(1).setPreferredWidth(400);		
	}
	
	private void executarScripts(){			
		
		conexao = new Conexao();
		
		try {
			dc.setCon(conexao.abrirConexao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		dc.criarTabela(new Cliente());
	}
	
	private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt){                                         
        ModeloCliente tabela_clientes =  (ModeloCliente) tblResultados.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela_clientes);
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
