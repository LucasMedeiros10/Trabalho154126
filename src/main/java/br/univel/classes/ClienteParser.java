package br.univel.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteParser {
	
	public List<Cliente> getCliente(List<String> listaStr){		
		List<Cliente> listacliente = new ArrayList<>();
		
		Pattern p = Pattern.compile("[0-9]+.*");
		
		listaStr.forEach(e -> {
			
			Matcher m = p.matcher(e);
			if(m.matches()){
				listacliente.add(getCliente(e));
			}
			
		});
		
		return listacliente;
	}
	
	private Cliente getCliente(String str){
		
		String[] itens = str.split(";");
		int id             = Integer.parseInt(itens[0]);
		System.out.println(itens[9].toString());
		String nome        = itens[1];
		String endereco    = itens[2];
		int numero         = Integer.parseInt(itens[3]);
		String complemento = itens[4];
		String bairro      = itens[5];
		String cidade      = itens[6];
		String estado      = itens[7];
		String cep         = itens[8];		
		String telefone    = itens[9];
		String celular     = itens[10];
		
		
		Cliente c = new Cliente();
		c.setId(id);
		c.setNome(nome);
		c.setEndereco(endereco);
		c.setNumero(numero);
		c.setComplemento(complemento);
		c.setBairro(bairro);
		c.setCidade(cidade);
		c.setEstado(estado);
		c.setCep(cep);
		c.setTelefone(telefone);
		c.setCelular(celular);
		
		return c;
	}	

}
