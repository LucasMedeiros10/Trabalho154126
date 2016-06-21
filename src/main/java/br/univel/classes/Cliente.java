package br.univel.classes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.univel.anotacoes.Coluna;
import br.univel.anotacoes.Tabela;

@Tabela("clientes")
@XmlRootElement
public class Cliente  implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6188080488996261636L;
	
	@Coluna(nome="id", pk=true)	
	private int id;
	
	@Coluna(nome="nome", tamanho=60)	
	private String nome;

	@Coluna(nome="endereco", tamanho=60)	
	private String endereco;
	
	@Coluna(nome="numero")	
	private int numero;
	
	@Coluna(nome="complemento", tamanho=30)	
	private String complemento;
	
	@Coluna(nome="bairro", tamanho=40)	
	private String bairro;
	
	@Coluna(nome="cidade", tamanho=60)	
	private String cidade;
	
	@Coluna(nome="estado", tamanho=2)	
	private String estado;
	
	@Coluna(nome="cep", tamanho=8)	
	private String cep;
	
	@Coluna(nome="telefone", tamanho=15)	
	private String telefone;
	
	@Coluna(nome="celular", tamanho=15)	
	private String celular;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}