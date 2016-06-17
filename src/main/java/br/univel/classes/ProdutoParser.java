package br.univel.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProdutoParser {

	public List<Produto> getProduto(List<String> listaStr){
		
		List<Produto> listaProd = new ArrayList<>();
		
		Pattern p = Pattern.compile("[0-9]+.*");
		
		listaStr.forEach(e -> {
			
			if(!e.startsWith("----")){
				Matcher m = p.matcher(e);
				if(m.matches()){
					listaProd.add(getProduto(e));
				}
			}
			
		});
		
		return listaProd;
	}
	
	private Produto getProduto(String str){

		int id  = Integer.parseInt(str.substring(0, str.indexOf(" ")));
		String descricao = str.substring(12, str.indexOf("  US$"));			
		BigDecimal preco = new BigDecimal(str.substring(str.indexOf("US$") + 4, str.length()).replace(".", "").replace(",", "."));
		Produto p = new Produto(id, descricao, preco);
		return p;
	}
}
