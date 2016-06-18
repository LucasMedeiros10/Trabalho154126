package br.univel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsqPadrao extends JFrame{
	public JLabel lblTitulo;
	public JTable tblResultados;
	public JTextField textField;
	public JButton btnInserir;
	public JButton btnAlterar;
	public JButton btnExcluir;
	public JButton btnImportarXML;
	public JButton btnExportarXML;
	public JButton btnImportarTXT;
	public JButton btnSair;
	
	public PsqPadrao(){
		setTitle("Pesquisa Padr\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		lblTitulo = new JLabel("Pesquisa Padr\u00E3o");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		tblResultados = new JTable();
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		btnAlterar = new JButton("Alterar");
		
		btnExcluir = new JButton("Excluir");
		
		btnImportarXML = new JButton("Importar XML");
		
		btnExportarXML = new JButton("Exportar XML");
		
		btnImportarTXT = new JButton("Importar TXT");
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPesquisa = new JLabel("Pesquisa");
		
		JButton btnNewButton = new JButton("Serializar");
		
		JButton btnNewButton_1 = new JButton("Restaurar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPesquisa)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addComponent(tblResultados, GroupLayout.PREFERRED_SIZE, 523, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExportarXML)
						.addComponent(btnImportarXML)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInserir, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1)
						.addComponent(btnImportarTXT, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(49))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
					.addGap(5))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPesquisa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(btnInserir, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnImportarXML)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExportarXML)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnImportarTXT)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair))
						.addComponent(tblResultados, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnInserir, btnAlterar, btnExcluir, btnImportarXML, btnExportarXML, btnImportarTXT, btnSair, btnNewButton, btnNewButton_1});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnInserir, btnAlterar, btnExcluir, btnImportarXML, btnExportarXML, btnImportarTXT, btnSair, btnNewButton, btnNewButton_1});
		getContentPane().setLayout(groupLayout);

		JPanel jp = new JPanel();  //cria painel
		jp.setLayout(new BorderLayout()); // seta o layout
		jp.setSize(800, 600);
		JScrollPane jsc = new JScrollPane();//barra de rolagem	
		
	}
}
