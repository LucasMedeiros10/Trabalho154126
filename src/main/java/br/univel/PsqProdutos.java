package br.univel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsqProdutos extends PsqPadrao{
	
	public PsqProdutos(){
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				CadProduto CadProduto = new CadProduto();		
				CadProduto.setSize(600, 400);
				CadProduto.setLocationRelativeTo(null); //centraliza na tela
				CadProduto.lblTitulo.setText("Alterar Produto");
				CadProduto.setVisible(true);//mostra na tela				
			}
		});
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//tela
				CadProduto CadProduto = new CadProduto();		
				CadProduto.setSize(600, 400);
				CadProduto.setLocationRelativeTo(null); //centraliza na tela
				CadProduto.lblTitulo.setText("Inserir Produto");
				CadProduto.setVisible(true);//mostra na tela					
			}
		});
		setTitle("Pesquisa de Produtos");
		lblTitulo.setText("Pesquisa de Produtos");
	}

}
