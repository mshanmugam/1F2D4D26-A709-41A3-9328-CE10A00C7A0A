/**
 * 
 */
package com.sample.user.service.orchestration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sample.rest.user.registration.dao.impl.ConfigurationDAOImpl;
import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * @author maruthishanmugam
 *
 */
public class WorkflowEngine {

	private ConfigurationDAOImpl configuration;

	private Map<String, GroovyObject> workflowMap = new HashMap<String, GroovyObject>();

	public ConfigurationDAOImpl getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationDAOImpl configuration) {
		this.configuration = configuration;
	}

	public void runWorkFlow(TenantConfig config, WorkflowDomain domain) {
		try {
			if (config.getTenantName() != null && workflowMap.get(config.getTenantName()) != null) {
				workflowMap.get(config.getTenantName()).invokeMethod("applyWorkflows", new Object[] { domain });
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public void init() {
		try {
			List<TenantConfig> tenants = configuration.findAllTenants();
			GroovyClassLoader gcLoader = new GroovyClassLoader(this.getClass().getClassLoader().getParent());
			for (TenantConfig tenant : tenants) {
				if (tenant.getTenantWorkflow() != null) {
					Class groovyClass = gcLoader.parseClass(tenant.getTenantWorkflow(),
							tenant.getTenantName() + ".groovy");
					GroovyObject object = (GroovyObject) groovyClass.newInstance();
					workflowMap.put(tenant.getTenantName(), object);

				}
			}
			gcLoader.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
