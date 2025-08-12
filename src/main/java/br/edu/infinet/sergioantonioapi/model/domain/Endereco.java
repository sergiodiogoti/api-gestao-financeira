package br.edu.infinet.sergioantonioapi.model.domain;

public class Endereco {

	//TODO atualizar com os demais campos do viaCep
	private String cep;
	private String localidade;
		
	//TODO construtor do endereço
	
	@Override
	public String toString() {
		return cep + " - " + localidade;
	}

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}	
}