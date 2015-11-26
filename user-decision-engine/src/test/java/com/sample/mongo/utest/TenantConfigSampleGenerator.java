/**
 * 
 */
package com.sample.mongo.utest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

import com.sample.user.domain.model.TenantConfig;

/**
 * @author maruthishanmugam
 *
 */
public class TenantConfigSampleGenerator {
	
	public static void main(String args[]) throws Exception
	{
		TenantConfig config = new TenantConfig();
		config.setTenantName("PNB_METLIFE");
		config.setTenantCountry("USA");
		Map<String,List<String>> tenantSOR = new HashMap<String,List<String>>();
		List<String> sorList = new ArrayList<String>();
		sorList.add("EMAIL_VALIDATE");
		sorList.add("PULL_EMAIL_SOR");
		tenantSOR.put("getEmail",sorList);
		config.setTenantDataSORs(tenantSOR);
		Map<String,List<String>> tenantWorkflow = new HashMap<String,List<String>>();
		List<String> workflowList = new ArrayList<String>();
		workflowList.add("WORKFLOW1");
		workflowList.add("WORKFLOW2");
		tenantWorkflow.put("getEmail", workflowList);
		config.setTenantWorkflow(tenantWorkflow);
		config.setTenantUniqueIdentifier(UUID.randomUUID().toString());
		System.out.println(new ObjectMapper().writeValueAsString(config));
		
	}

}
