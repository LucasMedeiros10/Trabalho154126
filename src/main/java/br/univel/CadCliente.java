package br.univel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class CadCliente extends JFrame{
	public GroupLayout groupLayout;
	public JButton btnSalvar;
	public JButton btnCancelar;
	public JLabel lblTitulo;
	private JTextField txtid;
	private JTextField txtnome;
	private JTextField txtendereco;
	private JTextField txtnumero;
	private JTextField txtcomplemento;
	private JTextField txtbairro;
	private JTextField txtcep;
	private JTextField txttelefone;
	private JTextField txtcelular;
	
	public CadCliente(){
		setTitle("Cadastro de Cliente");
		
		btnSalvar = new JButton("Salvar");
		
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
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitulo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome)
								.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnCancelar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSalvar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtendereco, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_1)
									.addComponent(txtnumero, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblBairro)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtcep, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(txttelefone, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
											.addComponent(lblTelefone)))
									.addComponent(txtbairro, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
								.addGap(19)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_2, Alignment.LEADING)
									.addComponent(txtcomplemento, Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtcelular, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCelular)))))
						.addComponent(lblCep))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitulo)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addComponent(btnSalvar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblId)
									.addGap(3)
									.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNome)))
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(114, Short.MAX_VALUE)
							.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtendereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtnumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtbairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcomplemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtcep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txttelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCelular)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtcelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(122))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
}