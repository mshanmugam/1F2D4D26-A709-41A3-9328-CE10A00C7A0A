/**
 * 
 */
package com.sample.mongo.utest;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.sample.user.domain.model.BusinessRuleConfig;
import com.sample.user.domain.model.TenantConfig;

/**
 * @author maruthishanmugam
 *
 */
public class BusinessRuleLoaderTester {

	private ApplicationContext context = null;
	@Before
	public void setup()
	{
		context=new ClassPathXmlApplicationContext("/META-INF/configs/ude.spring.base.test.context.xml");
	}
	
	@org.junit.Test
	public void testInsertData() throws Exception
	{
		MongoOperations operations = (MongoOperations)context.getBean("mongoTemplate");
		BusinessRuleConfig config = new BusinessRuleConfig();
		config.setName("GET_APR_INDIA");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/workflow/GET_APR_INDIA.groovy");
		String str = IOUtils.toString(is);
		config.setContent(str);
		operations.insert(config);
		

		
		
	}
}
