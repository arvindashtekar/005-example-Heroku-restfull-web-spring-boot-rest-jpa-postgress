package com.mycompany.application.component.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5426747715507830533L;

	public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
