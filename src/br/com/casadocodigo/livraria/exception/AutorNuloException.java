package br.com.casadocodigo.livraria.exception;

public class AutorNuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorNuloException(String mensagem) {
		super(mensagem);
	}

}
