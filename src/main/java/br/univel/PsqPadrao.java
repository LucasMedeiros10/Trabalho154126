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

public class PsqPadrao extends JFrame{
	private JLabel lblTitulo;
	private JTable tblResultados;
	
	public PsqPadrao(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 29, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblTitulo = new JLabel("Pesquisa Padr\u00E3o");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 15;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		getContentPane().add(lblTitulo, gbc_lblTitulo);
		
		tblResultados = new JTable();
		GridBagConstraints gbc_tblResultados = new GridBagConstraints();
		gbc_tblResultados.gridheight = 6;
		gbc_tblResultados.gridwidth = 14;
		gbc_tblResultados.insets = new Insets(0, 0, 5, 5);
		gbc_tblResultados.fill = GridBagConstraints.BOTH;
		gbc_tblResultados.gridx = 0;
		gbc_tblResultados.gridy = 2;
		getContentPane().add(tblResultados, gbc_tblResultados);
		
		JButton btnInserir = new JButton("Inserir");
		GridBagConstraints gbc_btnInserir = new GridBagConstraints();
		gbc_btnInserir.insets = new Insets(0, 0, 5, 0);
		gbc_btnInserir.gridx = 14;
		gbc_btnInserir.gridy = 2;
		gbc_btnInserir.ipadx = 20;  
		gbc_btnInserir.ipady = 5;	
		getContentPane().add(btnInserir, gbc_btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAlterar.gridx = 14;
		gbc_btnAlterar.gridy = 3;
		gbc_btnAlterar.ipadx = 18;  
		gbc_btnAlterar.ipady = 5;	
		getContentPane().add(btnAlterar, gbc_btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 0);
		gbc_btnExcluir.gridx = 14;
		gbc_btnExcluir.gridy = 4;
		gbc_btnExcluir.ipadx = 20;  
		gbc_btnExcluir.ipady = 5;	
		getContentPane().add(btnExcluir, gbc_btnExcluir);
		
		JButton btnSair = new JButton("Sair");
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.insets = new Insets(0, 0, 5, 0);
		gbc_btnSair.gridx = 14;
		gbc_btnSair.gridy = 5;
		gbc_btnSair.ipadx = 32;  
		gbc_btnSair.ipady = 5;			
		getContentPane().add(btnSair, gbc_btnSair);

		JPanel jp = new JPanel();  //cria painel
		jp.setLayout(new BorderLayout()); // seta o layout
		JScrollPane jsc = new JScrollPane();//barra de rolagem	
		
	}
	

}
