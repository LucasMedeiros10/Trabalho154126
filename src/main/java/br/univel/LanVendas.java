package br.univel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import br.univel.classes.Cliente;
import br.univel.classes.Conexao;
import br.univel.classes.DaoVenda;
import br.univel.classes.ItemVenda;
import br.univel.classes.ModeloItemVenda;
import br.univel.classes.Produto;
import br.univel.classes.Venda;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LanVendas extends JFrame{
	public JButton btnSalvar;
	public JButton btnCancelar;
	public JLabel lblTitulo;
	public JTextField txtCliente;
	private JLabel lblCliente;
	private JTable tblProdutos;
	public JTextField txtNomeProduto;
	private JTextField txtQtde;
	private Produto produtoAtual;
	private Cliente clienteAtual;
	private LanVendas formAtual;
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	private JLabel lblTotal;
	private boolean editando = false;
	private int id_venda;
	
	public LanVendas(){
		setTitle("Lan\u00E7amento de Venda");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(itens.isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe pelo menos um produto.", "Informação", JOptionPane.WARNING_MESSAGE);
				}else{	
					Venda v = new Venda();
					
					v.setId(getId_venda());
					v.setCliente(clienteAtual);
					v.setItens(itens);
					
					DaoVenda dv = new DaoVenda();
					try {
						dv.setCon(new Conexao().abrirConexao());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
					
					if(isEditando()){
						dv.atualizar(v);	
						dispose();	
						
					}else{
						dv.salvar(v);		
						dispose();	
					}
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		lblTitulo = new JLabel("Lan\u00E7amento de Venda");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("Valor Total:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTotal = new JLabel("0,00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);
		
		JButton btnProcurarCliente = new JButton("Procurar");
		btnProcurarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				PsqClientes psqClientes = new PsqClientes();
				psqClientes.setSize(740, 460);
				psqClientes.setLocationRelativeTo(null); //centraliza na tela
				psqClientes.btnInserir.setEnabled(false);
				psqClientes.btnAlterar.setEnabled(false);
				psqClientes.btnExcluir.setEnabled(false);
				psqClientes.btnExportarXML.setEnabled(false);
				psqClientes.btnImportarXML.setEnabled(false);
				psqClientes.btnImportarTXT.setEnabled(false);
				psqClientes.btnRestaurar.setEnabled(false);
				psqClientes.btnSerializar.setEnabled(false);
				psqClientes.setFrameSecundario(getFormAtual());
				psqClientes.setVisible(true);//mostra na tela				
			}
		});
		
		lblCliente = new JLabel("Cliente");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblProduto = new JLabel("Produto");
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setEditable(false);
		txtNomeProduto.setColumns(10);
		
		JButton btnProcurarProd = new JButton("Procurar");
		btnProcurarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				PsqProdutos psqProdutos = new PsqProdutos();		
				psqProdutos.setSize(740, 460);
				psqProdutos.setLocationRelativeTo(null); //centraliza na tela
				psqProdutos.btnInserir.setEnabled(false);
				psqProdutos.btnAlterar.setEnabled(false);
				psqProdutos.btnExcluir.setEnabled(false);
				psqProdutos.btnExportarXML.setEnabled(false);
				psqProdutos.btnImportarXML.setEnabled(false);
				psqProdutos.btnImportarTXT.setEnabled(false);
				psqProdutos.btnRestaurar.setEnabled(false);
				psqProdutos.btnSerializar.setEnabled(false);
				psqProdutos.setFrameSecundario(getFormAtual());				
				psqProdutos.setVisible(true);//mostra na tela				
			}
		});
		
		txtQtde = new JTextField();
		txtQtde.setText("1");
		txtQtde.setColumns(10);
		
		JLabel lblQtde = new JLabel("Qtde");
		
		JButton btnInserirProd = new JButton("+");
		btnInserirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getProdutoAtual() != null){
					if(txtQtde.getText().isEmpty() || txtQtde.getText().trim() == "0"){
						JOptionPane.showMessageDialog(null, "Informe a quantidade.", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else{
						//adiciona produto na lista
						ItemVenda iv = new ItemVenda();
						iv.setP(produtoAtual);
						iv.setQtde(new BigDecimal(txtQtde.getText()));
						iv.setId_venda(id_venda);
						itens.add(iv);
						
						
						montarConsulta();
						calcularTotal();
						limparProduto();						
					}
				}else{
					JOptionPane.showMessageDialog(null, "Escolha um produto para lançamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInserirProd.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnExcluirProd = new JButton("-");
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(itens.isEmpty()){
					JOptionPane.showMessageDialog(null, "Nenhum registro a ser excluído.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}else{	
					if(tblProdutos.getSelectedRow() == -1){
						JOptionPane.showMessageDialog(null, "Selecione um registro.", "Informação", JOptionPane.INFORMATION_MESSAGE);
					}else{
					
						int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?", "Exclusão", JOptionPane.YES_NO_OPTION);
						
						if(opcao == 0){
							itens.remove(tblProdutos.getSelectedRow());
							montarConsulta();
							calcularTotal();
							limparProduto();
						}
					}
				}				

			}
		});
		btnExcluirProd.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCliente)
										.addComponent(txtCliente, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
										.addComponent(lblProduto)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblQtde)
												.addComponent(txtQtde, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnInserirProd)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnExcluirProd))
										.addComponent(txtNomeProduto, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnProcurarCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnProcurarProd, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProcurarCliente)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProduto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNomeProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProcurarProd))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblQtde)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInserirProd)
						.addComponent(btnExcluirProd))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		
		tblProdutos = new JTable();
		scrollPane.setViewportView(tblProdutos);
		getContentPane().setLayout(groupLayout);
		
		setFormAtual(this);
		// $hide>>$
		montarConsulta();
		calcularTotal();
		// $hide<<$			
	}

	public Produto getProdutoAtual() {
		return produtoAtual;
	}

	public void setProdutoAtual(Produto produtoAtual) {
		this.produtoAtual = produtoAtual;
	}

	public LanVendas getFormAtual() {
		return formAtual;
	}

	public void setFormAtual(LanVendas formAtual) {
		this.formAtual = formAtual;
	}
	
	private void montarConsulta(){
		ModeloItemVenda modelo = new ModeloItemVenda(itens);//instancia um modelo de tabela
		tblProdutos.setModel(modelo);//seta a tabela		
	}
	
	private void limparProduto(){
		setProdutoAtual(null);
		txtQtde.setText("1");
		txtNomeProduto.setText("");
	}
	
	private void calcularTotal(){
		BigDecimal total = new BigDecimal("0");

		for(ItemVenda iv : itens){
			total = iv.getP().getPreco().multiply(iv.getQtde()).add(total);
		}
		
		lblTotal.setText(total.toString());
	}

	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	public void setClienteAtual(Cliente clienteAtual) {
		this.clienteAtual = clienteAtual;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	
	public void carregarDados(int codigo){
		
		DaoVenda dv = new DaoVenda();
		try {
			dv.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		Venda v = dv.buscar(codigo);
		itens.clear();
		itens = v.getItens();

		
		setClienteAtual(v.getCliente());
		txtCliente.setText(clienteAtual.getNome());		
		setId_venda(v.getId());
		montarConsulta();
		calcularTotal();
	}		
}
