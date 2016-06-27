package br.univel.classes;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Serial;
import br.univel.anotacoes.Tabela;
import br.univel.interfaces.SqlGen;

public class SqlGenImpl extends SqlGen {
	

	@Override
	protected String getCreateTable(Connection con, Object obj) {

		try {

			StringBuilder sb = new StringBuilder();

			// Declaração da tabela.	
			String nomeTabela;
			if (obj.getClass().isAnnotationPresent(Tabela.class)) {

				Tabela anotacaoTabela = obj.getClass().getAnnotation(Tabela.class);
				nomeTabela = anotacaoTabela.value();

			} else {
				nomeTabela = obj.getClass().getSimpleName().toUpperCase();

			}
			sb.append("CREATE TABLE IF NOT EXISTS ").append(nomeTabela).append(" (");
			
			
			Field[] atributos = obj.getClass().getDeclaredFields();
			
			String ChavePrimaria = "";
			boolean temCampo = false;

			// Declaração das colunas
			for (int i = 0; i < atributos.length; i++) {

				Field field = atributos[i];

				String nomeColuna;
				String tipoColuna;
				int    tamanhoColuna = 0;
				
				if(!field.isAnnotationPresent(Serial.class)){


					//nome coluna
					if (field.isAnnotationPresent(Coluna.class)) {
						
						Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
	
						if (anotacaoColuna.nome().isEmpty()) {
							nomeColuna = field.getName().toUpperCase();
							tamanhoColuna = 0;
						} else {
							nomeColuna = anotacaoColuna.nome();
							tamanhoColuna = anotacaoColuna.tamanho();
							
							//verifica se é chave primária
							if(anotacaoColuna.pk()){
								if (ChavePrimaria.equalsIgnoreCase("")){
									ChavePrimaria = anotacaoColuna.nome();
								}else{
									ChavePrimaria = ChavePrimaria + ", " + anotacaoColuna.nome();
								}
								
							}
						}
	
					} else {
						nomeColuna = field.getName().toUpperCase();
					}
	
					//tipo coluna
					Class<?> tipoParametro = field.getType();
	
					if (tipoParametro.equals(String.class)) {
						tipoColuna = "VARCHAR("  + tamanhoColuna + ")";
	
					} else if (tipoParametro.equals(int.class) || tipoParametro.equals(Cliente.class) || tipoParametro.equals(Produto.class)) {
						tipoColuna = "INT";
					} else if (tipoParametro.equals(BigDecimal.class)) {
						tipoColuna = "FLOAT";
					} else {
						tipoColuna = "DESCONHECIDO";
					}							
					
					if (temCampo) {
						sb.append(",");
					}						
	
					sb.append("\n\t").append(nomeColuna).append(' ').append(tipoColuna);
					temCampo = true;
					
				}
			}

			if (ChavePrimaria != ""){
				sb.append(",\n\t PRIMARY KEY(" + ChavePrimaria + ")");
			}
			
			sb.append("\n);");
			
			return sb.toString();

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected PreparedStatement getSqlInsert(Connection con, Object obj) {
		Class<? extends Object> cl = obj.getClass();

		StringBuilder sb = new StringBuilder();
		PreparedStatement ps = null;


		//nome da tabela
		String nomeTabela;
		if (cl.isAnnotationPresent(Tabela.class)) {
			Tabela anotacaoTabela = cl.getAnnotation(Tabela.class);
			nomeTabela = anotacaoTabela.value();

		} else {
			nomeTabela = cl.getSimpleName().toUpperCase();
		}
		sb.append("INSERT INTO ").append(nomeTabela).append(" (");

		Field[] atributos = cl.getDeclaredFields();
		boolean temCampo = false;
		
		// nome dos campos
		for (int i = 0; i < atributos.length; i++) {

			Field field = atributos[i];
			
			String nomeColuna;
			
			if(!field.isAnnotationPresent(Serial.class)) { 
				
				if (field.isAnnotationPresent(Coluna.class)) {
					Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
	
					if (anotacaoColuna.nome().isEmpty()) {
						nomeColuna = field.getName().toUpperCase();
					} else {
						nomeColuna = anotacaoColuna.nome();
					}
	
				} else {
					nomeColuna = field.getName().toUpperCase();
				}
	
				if (temCampo) {
					sb.append(", ");
				}
	
				sb.append(nomeColuna);
				temCampo = true;
			}	
		}

		sb.append(") VALUES (");
		temCampo = false;
		
		for (int i = 0; i < atributos.length; i++) {
			Field field = atributos[i];
			
			if(!field.isAnnotationPresent(Serial.class)) { 
				
				if (temCampo) {
					sb.append(", ");
				}
				sb.append('?');
				temCampo = true;
			}
		}
		sb.append(')');

		try {			
			ps =  con.prepareStatement(sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return ps;

	}

	@Override
	protected PreparedStatement getSqlSelectAll(Connection con, Object obj) {
		PreparedStatement ps = null;
		
		try{
			StringBuilder sb = new StringBuilder();			
			
			// Declaração da tabela.
			String nomeTabela;
			if (obj.getClass().isAnnotationPresent(Tabela.class)) {
	
				Tabela anotacaoTabela = obj.getClass().getAnnotation(Tabela.class);
				nomeTabela = anotacaoTabela.value();
	
			} else {
				nomeTabela = obj.getClass().getSimpleName().toUpperCase();
	
			}
			sb.append("SELECT * FROM ").append(nomeTabela);		

			try {				
				ps =  con.prepareStatement(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			return ps;			
			
		}catch(SecurityException e){
			throw new RuntimeException(e);
		}					
	}

	@Override
	protected PreparedStatement getSqlSelectById(Connection con, Object obj) {
		PreparedStatement ps = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			
			// Declaração da tabela.
			String nomeTabela;
			if (obj.getClass().isAnnotationPresent(Tabela.class)) {
	
				Tabela anotacaoTabela = obj.getClass().getAnnotation(Tabela.class);
				nomeTabela = anotacaoTabela.value();
	
			} else {
				nomeTabela = obj.getClass().getSimpleName().toUpperCase();
	
			}			

			//pega o campo id
			Field[] atributos = obj.getClass().getDeclaredFields();			
			String ChavePrimaria = "";
			for (int i = 0; i < atributos.length; i++) {

				Field field = atributos[i];

				//nome coluna
				if (field.isAnnotationPresent(Coluna.class)) {
					Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
					
					//verifica se é chave primária
					if(anotacaoColuna.pk()){
						if (ChavePrimaria.equalsIgnoreCase("")){
							ChavePrimaria = anotacaoColuna.nome();
						}else{
							ChavePrimaria = ChavePrimaria + ", " + anotacaoColuna.nome();
						}							
					}
				}
			}
			
			sb.append("SELECT * FROM ").append(nomeTabela).append(" WHERE ").append(ChavePrimaria).append(" = ?");
	
			try {				
				ps =  con.prepareStatement(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			return ps;	
		
		}catch(SecurityException e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	protected PreparedStatement getSqlUpdateById(Connection con, Object obj) {
		Class<? extends Object> cl = obj.getClass();

		StringBuilder sb = new StringBuilder();
		PreparedStatement ps = null;

		//nome da tabela
		String nomeTabela;
		if (cl.isAnnotationPresent(Tabela.class)) {
			Tabela anotacaoTabela = cl.getAnnotation(Tabela.class);
			nomeTabela = anotacaoTabela.value();

		} else {
			nomeTabela = cl.getSimpleName().toUpperCase();
		}
		sb.append("UPDATE ").append(nomeTabela).append(" SET \n");

		Field[] atributos = cl.getDeclaredFields();
		String chavePrimaria = "";

		// nome dos campos
		for (int i = 0; i < atributos.length; i++) {

			Field field = atributos[i];
			String nomeColuna;

			if (!field.isAnnotationPresent(Serial.class)) {
				if (field.isAnnotationPresent(Coluna.class)) {
					Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
	
					if (anotacaoColuna.nome().isEmpty()) {
						nomeColuna = field.getName().toUpperCase();
					} else {
						nomeColuna = anotacaoColuna.nome();
					}
	
					if(anotacaoColuna.pk()){
						chavePrimaria = nomeColuna;
					}
					
				} else {
					nomeColuna = field.getName().toUpperCase();
				}
	
	
				//se for chave primária não escreve no update
				if (nomeColuna != chavePrimaria){				
					sb.append("  ").append(nomeColuna).append(" = ?");
					
					if (i < atributos.length -1) {
						sb.append(", \n");
					}				
				}
			}
		}

		sb.append("\n WHERE \n  ").append(chavePrimaria).append(" = ?");

		try {				
			ps =  con.prepareStatement(sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return ps;
	}

	@Override
	protected PreparedStatement getSqlDeleteById(Connection con, Object obj) {
		
		PreparedStatement ps = null;
		try{
			StringBuilder sb = new StringBuilder();
			
			// Declaração da tabela.
			String nomeTabela;
			if (obj.getClass().isAnnotationPresent(Tabela.class)) {
	
				Tabela anotacaoTabela = obj.getClass().getAnnotation(Tabela.class);
				nomeTabela = anotacaoTabela.value();
	
			} else {
				nomeTabela = obj.getClass().getSimpleName().toUpperCase();
			}
			
			
			//pega o campo id
			Field[] atributos = obj.getClass().getDeclaredFields();			
			String ChavePrimaria = "";
			for (int i = 0; i < atributos.length; i++) {

				Field field = atributos[i];

				//nome coluna
				if (field.isAnnotationPresent(Coluna.class)) {
					Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
					
					//verifica se é chave primária
					if(anotacaoColuna.pk()){
						if (ChavePrimaria.equalsIgnoreCase("")){
							ChavePrimaria = anotacaoColuna.nome();
						}else{
							ChavePrimaria = ChavePrimaria + ", " + anotacaoColuna.nome();
						}							
					}
				}
			}
			
			sb.append("DELETE FROM ").append(nomeTabela).append(" WHERE ").append(ChavePrimaria).append(" = ?");
			
			try {				
				ps =  con.prepareStatement(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		
		}catch(SecurityException e){
			throw new RuntimeException(e);
		}			
		
		return ps;	
	}

	@Override
	protected PreparedStatement getNextId(Connection con, Object obj) {
		PreparedStatement ps = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			
			// Declaração da tabela.
			String nomeTabela;
			if (obj.getClass().isAnnotationPresent(Tabela.class)) {
	
				Tabela anotacaoTabela = obj.getClass().getAnnotation(Tabela.class);
				nomeTabela = anotacaoTabela.value();
	
			} else {
				nomeTabela = obj.getClass().getSimpleName().toUpperCase();
	
			}			

			//pega o campo id
			Field[] atributos = obj.getClass().getDeclaredFields();			
			String ChavePrimaria = "";
			for (int i = 0; i < atributos.length; i++) {

				Field field = atributos[i];

				//nome coluna
				if (field.isAnnotationPresent(Coluna.class)) {
					Coluna anotacaoColuna = field.getAnnotation(Coluna.class);
					
					//verifica se é chave primária
					if(anotacaoColuna.pk()){
						if (ChavePrimaria.equalsIgnoreCase("")){
							ChavePrimaria = anotacaoColuna.nome();
						}else{
							ChavePrimaria = ChavePrimaria + ", " + anotacaoColuna.nome();
						}							
					}
				}
			}
			
			sb.append("SELECT COALESCE(MAX(" + ChavePrimaria +"), 0) + 1 AS codigo FROM ").append(nomeTabela);
	
			try {				
				ps =  con.prepareStatement(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		
		}catch(SecurityException e){
			throw new RuntimeException(e);
		}	
		
		return ps;	
	}

}
