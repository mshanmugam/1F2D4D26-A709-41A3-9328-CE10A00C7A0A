/**
 * 
 */
package com.sample.mongo.utest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.sample.user.domain.model.TenantConfig;

/**
 * @author maruthishanmugam
 *
 */
public class MongoDBTest {
	
	private ApplicationContext context = null;
	@Before
	public void setup()
	{
		context=new ClassPathXmlApplicationContext("/META-INF/configs/ude.spring.base.test.context.xml");
	}
	
	//@Test
	public void testInsertData()
	{
		MongoOperations operations = (MongoOperations)context.getBean("mongoTemplate");
		
		TenantConfig config = new TenantConfig();
		config.setTenantName("PNB_METLIFE");
		config.setTenantCountry("USA");
		Map tenantSOR = new HashMap();
		List sorList = new ArrayList();
		sorList.add("EMAIL_VALIDATE");
		sorList.add("PULL_EMAIL_SOR");
		tenantSOR.put("getEmail",sorList);
		config.setTenantDataSORs(tenantSOR);
		Map tenantWorkflow = new HashMap();
		List workflowList = new ArrayList();
		workflowList.add("WORKFLOW1");
		workflowList.add("WORKFLOW2");
		tenantWorkflow.put("getEmail", workflowList);
		config.setTenantWorkflow(tenantWorkflow);
		config.setTenantUniqueIdentifier(UUID.randomUUID().toString());
		operations.save(config);
		
	}
	@Test
	public void testReadData()
	{
		MongoOperations operations = (MongoOperations)context.getBean("mongoTemplate");
		
		TenantConfig config = new TenantConfig();
		config.setTenantName("PNB-METLIFE");
		Query search = new Query(Criteria.where("tenantName").is("PNB_METLIFE"));
		TenantConfig output = operations.findOne(search, TenantConfig.class);
		
		System.out.println(output.getTenantCountry());
		
	}

}
