/**
 * 
 */
package com.sample.user.service.orchestration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;

import com.sample.rest.user.registration.dao.impl.BusinessRuleEngineDAOImpl;
import com.sample.rest.user.registration.dao.impl.ConfigurationDAOImpl;
import com.sample.user.domain.model.BusinessRuleConfig;
import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * @author maruthishanmugam
 *
 */
public class WorkflowEngine implements ApplicationContextAware {

	private ApplicationContext context;

	private ConfigurationDAOImpl configuration;

	private BusinessRuleEngineDAOImpl businessRules;

	private Map<String, GroovyObject> workflowMap = new HashMap<String, GroovyObject>();

	public ConfigurationDAOImpl getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationDAOImpl configuration) {
		this.configuration = configuration;
	}

	public void runWorkFlow(TenantConfig config, WorkflowDomain domain, List<String> executors) {
		try {
			domain.addFacts("applicationContext", context);
			domain.addFacts("tenantConfig", config);
			domain.addFacts("shallContinue", true);
			for (String executor : executors) {
				if (domain.getFact("shallContinue", Boolean.class)) {
					workflowMap.get(executor).invokeMethod("applyWorkflows", new Object[] { domain });
				} else {
					break;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public void init() {
		try {

			System.out.println("Loading the Workflows into the Memory");
			List<BusinessRuleConfig> businessRulesConfigs = businessRules.readAllBusinessRules();

			GroovyClassLoader gcLoader = new GroovyClassLoader(this.getClass().getClassLoader());
			for (BusinessRuleConfig businessRuleConfig : businessRulesConfigs) {
				if (businessRuleConfig.getContent() != null) {
					Class groovyClass = gcLoader.parseClass(businessRuleConfig.getContent(),
							businessRuleConfig.getName() + ".groovy");
					GroovyObject object = (GroovyObject) groovyClass.newInstance();
					workflowMap.put(businessRuleConfig.getName(), object);

				}
			}
			gcLoader.close();
			System.out.println("Number of Workflows/Business Rules Loaded " + workflowMap.size());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	@Scheduled(fixedDelay = 5000)
	public void refresh() {
		boolean allswapStatus = true;
		Map<String, GroovyObject> swapMap = new HashMap<String, GroovyObject>();
		try {

			System.out.println("Refreshing the Workflows into the Memory");

			List<BusinessRuleConfig> businessRulesConfigs = businessRules.readAllBusinessRules();

			GroovyClassLoader gcLoader = new GroovyClassLoader(this.getClass().getClassLoader());
			for (BusinessRuleConfig businessRuleConfig : businessRulesConfigs) {
				if (businessRuleConfig.getContent() != null) {
					Class groovyClass = gcLoader.parseClass(businessRuleConfig.getContent(),
							businessRuleConfig.getName() + ".groovy");
					GroovyObject object = (GroovyObject) groovyClass.newInstance();
					swapMap.put(businessRuleConfig.getName(), object);

				}
			}
			gcLoader.close();
			System.out.println("Number of Workflows/Business Rules Loaded " + swapMap.size());
		} catch (Exception exception) {
			exception.printStackTrace();
			allswapStatus = false;
		}
		if (allswapStatus) {
			workflowMap = swapMap;
		} else {
			workflowMap.putAll(swapMap);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;

	}

	public BusinessRuleEngineDAOImpl getBusinessRules() {
		return businessRules;
	}

	public void setBusinessRules(BusinessRuleEngineDAOImpl businessRules) {
		this.businessRules = businessRules;
	}

}
