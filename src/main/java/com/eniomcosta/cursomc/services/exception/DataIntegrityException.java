package com.eniomcosta.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	public DataIntegrityException(Object obj, Throwable cause) {
		super("Não foi possível remover. O registro possui entidades associadas. Entidade: " + obj.getClass().getSimpleName(), cause);
	}

}
