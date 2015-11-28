/**
 * 
 */
package com.sample.user.service.exception;

/**
 * @author maruthishanmugam
 *
 */
public class ServiceNotFoundException extends Exception {
	
	public ServiceNotFoundException()
	{
		super();
	}

	public ServiceNotFoundException(String message)
	{
		super(message);
	}
}
