package br.univel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.univel.classes.Cliente;
import br.univel.classes.Conexao;
import br.univel.classes.DaoCliente;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class CadCliente extends JFrame{
	public JButton btnSalvar;
	public JButton btnCancelar;
	public JLabel lblTitulo;
	public JTextField txtid;
	private JTextField txtnome;
	private JTextField txtendereco;
	private JTextField txtnumero;
	private JTextField txtcomplemento;
	private JTextField txtbairro;
	private JTextField txtcep;
	private JTextField txttelefone;
	private JTextField txtcelular;
	private boolean editando = false;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JTextField txtUF;
	private JLabel lblUf;
	
	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public CadCliente(){
		setTitle("Cadastro de Cliente");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				
				c.setId(Integer.parseInt(txtid.getText()));
				c.setNome(txtnome.getText());
				c.setEndereco(txtendereco.getText());
				c.setBairro(txtbairro.getText());
				c.setCelular(txtcelular.getText());
				c.setCep(txtcep.getText());
				c.setCidade(txtCidade.getText());
				c.setEstado(txtUF.getText());
				c.setComplemento(txtcomplemento.getText());
				if(txtnumero.getText().trim().isEmpty()){
					c.setNumero(0);
				}else{
					c.setNumero(Integer.parseInt(txtnumero.getText()));					
				}	
				c.setTelefone(txttelefone.getText());
				
				DaoCliente dc = new DaoCliente();
				try {
					dc.setCon(new Conexao().abrirConexao());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				if(isEditando()){
					dc.atualizar(c);
				}else{
					dc.salvar(c);					
				}
				dispose();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		lblTitulo = new JLabel("Cadastro de Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblId = new JLabel("ID");
		
		txtid = new JTextField();
		txtid.setEnabled(false);
		txtid.setEditable(false);
		txtid.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		
		txtnome = new JTextField();
		txtnome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Endere\u00E7o");
		
		txtendereco = new JTextField();
		txtendereco.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero");
		
		txtnumero = new JTextField();
		txtnumero.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Complemento");
		
		txtcomplemento = new JTextField();
		txtcomplemento.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		
		txtbairro = new JTextField();
		txtbairro.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		
		txtcep = new JTextField();
		txtcep.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		
		txttelefone = new JTextField();
		txttelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		
		txtcelular = new JTextField();
		txtcelular.setColumns(10);
		
		lblCidade = new JLabel("Cidade");
		
		lblUf = new JLabel("UF");
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		
		txtUF = new JTextField();
		txtUF.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblNome)
								.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addGap(392)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBairro)
							.addGap(214)
							.addComponent(lblNewLabel_2))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtbairro, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(txtcomplemento, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(133)
							.addComponent(lblUf, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(txtUF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtcep, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(txttelefone, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCep)
									.addGap(100)
									.addComponent(lblTelefone)))
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCelular)
								.addComponent(txtcelular, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtendereco, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(txtnumero, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblTitulo)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblId)
							.addGap(3)
							.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNome))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(btnSalvar)
							.addGap(6)
							.addComponent(btnCancelar)))
					.addGap(5)
					.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtendereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtnumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBairro)
						.addComponent(lblNewLabel_2))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtbairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcomplemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCep)
						.addComponent(lblTelefone)
						.addComponent(lblCelular))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtcep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txttelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCidade)
						.addComponent(lblUf))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	public void carregarDados(int codigo){
		
		DaoCliente dc = new DaoCliente();
		try {
			dc.setCon(new Conexao().abrirConexao());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		Cliente c = dc.buscar(codigo);
		
		txtid.setText(Integer.toString(c.getId()));
		txtnome.setText(c.getNome());
		txtendereco.setText(c.getEndereco());
		txtnumero.setText(Integer.toString(c.getNumero()));
		txtbairro.setText(c.getBairro());
		txtcelular.setText(c.getCelular());
		txttelefone.setText(c.getTelefone());
		txtcep.setText(c.getCep());
		txtCidade.setText(c.getCidade());
		txtcomplemento.setText(c.getComplemento());
		txtUF.setText(c.getEstado());
	}
}