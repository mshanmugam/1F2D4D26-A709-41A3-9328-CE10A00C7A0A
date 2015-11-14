package com.sample.rest.user.registration.dao.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.sample.user.domain.model.TenantConfig;

public class ConfigurationDAOImpl {

	private MongoOperations operations;

	public MongoOperations getOperations() {
		return operations;
	}

	public void setOperations(MongoOperations operations) {
		this.operations = operations;
	}

	public TenantConfig insertTenant(TenantConfig config) throws Exception{
		if(config.getTenantWorkflow() == null)
		{
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("/META-INF/workflow/TENANT_WF.groovy");
			String str = IOUtils.toString(is);
			config.setTenantWorkflow(str);
		}
		operations.save(config);

		return config;
	}

	public TenantConfig updateTenant(TenantConfig config) {
		Query query = new Query();
		query.addCriteria(Criteria.where("tenantName").is(config.getTenantName()));

		operations.updateFirst(query, Update.update("status", config.isStatus()).update("updatedDate", new Date())
				.update("tenantWorkflow", config.getTenantWorkflow()), TenantConfig.class);
		return config;
	}
	
	public TenantConfig readTenant(TenantConfig config)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("tenantName").is(config.getTenantName()));
		
		config = operations.findOne(query, TenantConfig.class);
		
		return config;
	}
	
	public List<TenantConfig> findAllTenants()
	{
		return operations.findAll(TenantConfig.class);
	}
}
