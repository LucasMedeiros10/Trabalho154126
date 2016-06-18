package br.univel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsqClientes extends PsqPadrao{

	public PsqClientes(){
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				//tela
				CadCliente CadCliente = new CadCliente();		
				CadCliente.setSize(600, 600);
				CadCliente.setLocationRelativeTo(null); //centraliza na tela
				CadCliente.lblTitulo.setText("Alterar Cliente");
				CadCliente.setVisible(true);//mostra na tela					
			
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tela
				CadCliente CadCliente = new CadCliente();		
				CadCliente.setSize(600, 600);
				CadCliente.setLocationRelativeTo(null); //centraliza na tela
				CadCliente.lblTitulo.setText("Inserir Cliente");
				CadCliente.setVisible(true);//mostra na tela					
			}
		});
		
		setTitle("Pesquisa de Clientes");
		lblTitulo.setText("Pesquisa de Clientes");
	}
}
