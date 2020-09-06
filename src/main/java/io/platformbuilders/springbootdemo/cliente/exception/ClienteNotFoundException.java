package io.platformbuilders.springbootdemo.cliente.exception;

import lombok.Getter;

@Getter
public class ClienteNotFoundException extends GeneralRunTimeException {

	private static final long serialVersionUID = -429875140464015128L;
	
	public ClienteNotFoundException() {
	    super("Error 002");
	  }
}