package br.edu.infinet.sergioantonioapi.model.domain;


public class Usuario extends Pessoa {

	private int pontuacaoCredito ;
	private double rendaMensal;
	private String perfil;
	private boolean ativo;
	private Endereco endereco;

	@Override
	public String obterTipo() {
		return "Usuário";
	}

	public int getPontuacaoCredito() {
		return pontuacaoCredito;
	}

	public void setPontuacaoCredito(int pontuacaoCredito) {
		this.pontuacaoCredito = pontuacaoCredito;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return super.toString() +
				String.format(" - Contas: %d - Renda Mensal: %.2f - Perfil: %s - Ativo: %s - Endereço: %s",
						pontuacaoCredito, rendaMensal, perfil, ativo ? "Sim" : "Não", endereco);
	}
}
