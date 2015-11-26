/**
 * 
 */
package com.sample.user.service.orchestration;

import java.util.List;
import java.util.Map;

import com.sample.rest.user.registration.dao.impl.ConfigurationDAOImpl;
import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

/**
 * @author maruthishanmugam
 *
 */
public class TenantServiceDelegator {
	
	private WorkflowEngine engine;
	
	private ConfigurationDAOImpl configuration;
	
	
	public Object delegateTenantFunctions(TenantConfig config,Map<String,Object> request,String uri) throws Exception
	{
		if(config == null)
		{
			String tenantName = (String)request.get("tenantName");
			TenantConfig query = new TenantConfig();
			query.setTenantName(tenantName);
			config = configuration.readTenant(query);
			if(config == null)
			{
				throw new Exception("No Tenant Available for the Tenant Name : "+tenantName);
			}
		}
		Map<String,List<String>> dataSORs = config.getTenantDataSORs();
		List<String> sors = (List<String>)dataSORs.get(uri);
		WorkflowDomain domain = new WorkflowDomain();
		domain.addFacts("request", request);
		domain.addFacts("tenantConfig", config);
		if(sors != null && !sors.isEmpty())
		{
			System.out.println("SORs workflow being executed");
			engine.runWorkFlow(config, domain, sors);
		}else{
			System.out.println("No SORs available for execution");
		}
		Object response = domain.getFact("response", Object.class);
		return response;
	}


	public WorkflowEngine getEngine() {
		return engine;
	}


	public void setEngine(WorkflowEngine engine) {
		this.engine = engine;
	}

	/**
	 * @return the configuration
	 */
	public ConfigurationDAOImpl getConfiguration() {
		return configuration;
	}


	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(ConfigurationDAOImpl configuration) {
		this.configuration = configuration;
	}

}
