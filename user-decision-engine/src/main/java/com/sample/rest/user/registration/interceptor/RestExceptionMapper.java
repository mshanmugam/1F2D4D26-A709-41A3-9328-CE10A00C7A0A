/**
 * 
 */
package com.sample.rest.user.registration.interceptor;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.codehaus.jackson.map.ObjectMapper;

import com.sample.user.service.exception.BusinessException;
import com.sample.user.service.exception.ServiceNotFoundException;

/**
 * @author maruthishanmugam
 *
 */
public class RestExceptionMapper implements ExceptionMapper<Exception> {

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public Response toResponse(Exception exception) {

		if (exception instanceof ServiceNotFoundException) {
			return Response.status(Response.Status.NOT_FOUND).header("X-Error-Reason-Msg", exception.getMessage())
					.build();
		} else if (exception instanceof BusinessException) {
			try { 
				Response response = Response.status(Response.Status.BAD_REQUEST)
						.entity(mapper.writeValueAsString(((BusinessException) exception).getErrorDetails()))
						.header("X-Error-Reason-Msg", exception.getMessage()).build();

				return response;
			} catch (Exception excep) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.header("X-Error-Reason-Msg", excep.getMessage()).build();
			}
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("X-Error-Reason-Msg", exception.getMessage()).build();
		}

		// TODO Auto-generated method stub

	}

}
