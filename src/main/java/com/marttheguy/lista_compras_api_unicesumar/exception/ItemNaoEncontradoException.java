package com.marttheguy.lista_compras_api_unicesumar.exception;

public class ItemNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * envia a mensagem para sua classe mae "Exception"
	 * @param String mensagem
	 */
	public ItemNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
