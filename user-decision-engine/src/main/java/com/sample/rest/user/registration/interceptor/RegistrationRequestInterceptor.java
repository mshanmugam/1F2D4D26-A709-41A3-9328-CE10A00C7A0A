/**
 * 
 */
package com.sample.rest.user.registration.interceptor;

import java.io.InputStream;
import java.util.Map;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * @author maruthishanmugam
 *
 */
public class RegistrationRequestInterceptor extends AbstractPhaseInterceptor {

	public RegistrationRequestInterceptor() {
		super(Phase.POST_UNMARSHAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault {
		try{
		Object request = message.get(Message.PROTOCOL_HEADERS);
		
		System.out.println("Request : "+request.toString() );
		}catch(Exception exception)
		{
			exception.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
