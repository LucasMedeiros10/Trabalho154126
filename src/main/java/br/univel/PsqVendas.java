package br.univel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsqVendas extends PsqPadrao {
	
	public PsqVendas(){
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				LanVendas LanVendas = new LanVendas();		
				LanVendas.setSize(800, 600);
				LanVendas.setLocationRelativeTo(null); //centraliza na tela
				LanVendas.lblTitulo.setText("Alteração de Venda");
				LanVendas.setVisible(true);//mostra na tela				
			
			}
		});
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				LanVendas LanVendas = new LanVendas();		
				LanVendas.setSize(800, 600);
				LanVendas.setLocationRelativeTo(null); //centraliza na tela
				LanVendas.lblTitulo.setText("Lançamento de Venda");
				LanVendas.setVisible(true);//mostra na tela				
			}
		});
		
		setTitle("Vendas");
		lblTitulo.setText("Vendas");
		btnImportarTXT.setVisible(false);
	}
}
