package br.edu.infinet.sergioantonioapi.model.domain.exceptions;

public class UsuarioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException(String mensagem) {
		super(mensagem);
	}
}
