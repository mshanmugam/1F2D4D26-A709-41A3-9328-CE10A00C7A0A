package com.sample.rest.user.registration.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.data.repository.query.RepositoryQuery;

import com.sample.user.domain.model.TenantConfig;
import com.sample.user.service.orchestration.RegistrationDataAdapter;
import com.sample.user.service.orchestration.TenantServiceDelegator;

@Path("/registration")
public class UserRegistrationService {

	private RegistrationDataAdapter dataAdapter;

	private TenantServiceDelegator serviceDelegator;

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object registerUser(Map<String, Object> request) throws Exception {

		TenantConfig config = new TenantConfig();
		config.setTenantName((String) request.get("tenantName"));
		config.setTenantCountry((String) request.get("country"));
		config.setStatus(true);
		config.setEnrolledDate(new Date());
		config.setUpdatedDate(new Date());
		return dataAdapter.registerTenant(config);

	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object updateUser(Map<String, Object> request) throws Exception{

		TenantConfig config = new TenantConfig();
		config.setTenantName((String) request.get("tenantName"));
		config.setTenantCountry((String) request.get("country"));

		config.setStatus(false);
		config.setUpdatedDate(new Date());
		return dataAdapter.updateTenant(config);

	}

	@POST
	@Path("/service/{query:.*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Object dynamicTenantServices(@PathParam("query") String query, Map<String, Object> request) throws Exception{
		Object result = serviceDelegator.delegateTenantFunctions(null, request, query);

		return result;
	}

	public RegistrationDataAdapter getDataAdapter() {
		return dataAdapter;
	}

	public void setDataAdapter(RegistrationDataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
	}

	/**
	 * @return the serviceDelegator
	 */
	public TenantServiceDelegator getServiceDelegator() {
		return serviceDelegator;
	}

	/**
	 * @param serviceDelegator
	 *            the serviceDelegator to set
	 */
	public void setServiceDelegator(TenantServiceDelegator serviceDelegator) {
		this.serviceDelegator = serviceDelegator;
	}

}
