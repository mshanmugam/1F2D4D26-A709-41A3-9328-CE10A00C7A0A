package com.sample.user.service.orchestration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sample.rest.user.registration.dao.impl.ConfigurationDAOImpl;
import com.sample.user.domain.model.TenantConfig;

public class RegistrationDataAdapter {
	
	private ConfigurationDAOImpl configuration;

	public ConfigurationDAOImpl getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationDAOImpl configuration) {
		this.configuration = configuration;
	}
	
	public Map<String, Object> registerTenant(TenantConfig config) throws Exception
	{
		Map<String,Object> result = new HashMap<String,Object>();
		TenantConfig data = null;
		if((data =configuration.readTenant(config)) == null)
		{
			config.setTenantUniqueIdentifier(UUID.randomUUID().toString());
			configuration.insertTenant(config);
			result.put("Registration_Status", "Tenant Registered Successfully");
			result.put("tenantDetail", config);
		}else{
			result.put("Registration_Status", "Tenant Exists - Please use update operation");
			result.put("tenantDetail", data);
		}
		
		return result;
	}
	
	public Map<String,Object> updateTenant(TenantConfig config) throws Exception
	{
		Map<String,Object> result = new HashMap<String,Object>();
		TenantConfig data = null;
		if((data =configuration.readTenant(config)) != null)
		{
			config.setTenantUniqueIdentifier(UUID.randomUUID().toString());
			configuration.updateTenant(config);
			result.put("Registration_Status", "Tenant updated Successfully");
			result.put("tenantDetail", config);
		}else{
			result.put("Registration_Status", "Tenant does not exist - Please use Register operation");
			result.put("tenantDetail", config);
		}
		
		return result;
	}
	

}
