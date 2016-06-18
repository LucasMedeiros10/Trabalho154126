package br.univel;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadPadrao extends JFrame{
	public JButton btnSalvar;
	public JButton btnCancelar;
	public JLabel lblTitulo;
	
	public CadPadrao(){
		setTitle("Cadastro Padr\u00E3o");
		
		btnSalvar = new JButton("Salvar");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		lblTitulo = new JLabel("Cadastro Padr\u00E3o");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnCancelar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSalvar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addGap(24)
					.addComponent(btnSalvar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar)
					.addContainerGap(193, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
}
