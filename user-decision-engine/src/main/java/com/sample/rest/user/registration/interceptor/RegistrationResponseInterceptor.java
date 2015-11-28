/**
 * 
 */
package com.sample.rest.user.registration.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * @author maruthishanmugam
 *
 */
public class RegistrationResponseInterceptor extends AbstractPhaseInterceptor {

	
	public RegistrationResponseInterceptor() {
		super(Phase.MARSHAL);
	}
	/* (non-Javadoc)
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault {
		// TODO Auto-generated method stub

	}

}
