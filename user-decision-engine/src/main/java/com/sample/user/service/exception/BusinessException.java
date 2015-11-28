/**
 * 
 */
package com.sample.user.service.exception;

import java.util.Map;

/**
 * @author maruthishanmugam
 *
 */
public class BusinessException extends Exception {
	
	private Map<String, String> errorDetails;
	
	public BusinessException()
	{
		super();
	}
	
	public BusinessException(Map<String, String> errorDetails,String errorMessage)
	{
		super(errorMessage);
		this.errorDetails = errorDetails;
		
	}

	/**
	 * @return the errorDetails
	 */
	public Map<String, String> getErrorDetails() {
		return errorDetails;
	}

	/**
	 * @param errorDetails the errorDetails to set
	 */
	public void setErrorDetails(Map<String, String> errorDetails) {
		this.errorDetails = errorDetails;
	}

}
