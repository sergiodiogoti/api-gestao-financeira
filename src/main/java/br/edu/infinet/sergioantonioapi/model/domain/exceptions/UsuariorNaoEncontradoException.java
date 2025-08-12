package br.edu.infinet.sergioantonioapi.model.domain.exceptions;

public class UsuariorNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuariorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
