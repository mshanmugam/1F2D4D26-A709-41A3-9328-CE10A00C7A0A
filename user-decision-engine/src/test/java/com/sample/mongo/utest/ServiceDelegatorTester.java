/**
 * 
 */
package com.sample.mongo.utest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.user.domain.model.TenantConfig;
import com.sample.user.service.orchestration.TenantServiceDelegator;

/**
 * @author maruthishanmugam
 *
 */
public class ServiceDelegatorTester {


	private ApplicationContext context = null;
	@Before
	public void setup()
	{
		context=new ClassPathXmlApplicationContext("/META-INF/configs/ude.spring.base.test.context.xml");
	}
	@org.junit.Test
	public void testDelegate() throws Exception
	{
		TenantConfig config = new TenantConfig();
		config.setTenantName("PNB_METLIFE");
		Map<String,List<String>> tenantSOR = new HashMap<String,List<String>>();
		List<String> sorList = new ArrayList<String>();
		sorList.add("EMAIL_VALIDATE");
		sorList.add("PULL_EMAIL_SOR");
		tenantSOR.put("getEmail",sorList);
		config.setTenantDataSORs(tenantSOR);
		Map<String,Object> request = new HashMap<String,Object>();
		request.put("SSN", "823784098");
		request.put("tenantName", "PNB_METLIFE");
		TenantServiceDelegator delegator =(TenantServiceDelegator) context.getBean("tenantServiceDelegator");
		Object response = delegator.delegateTenantFunctions(null, request, "getAPR");
		System.out.println(new ObjectMapper().writeValueAsString(response));
		
	}
}
