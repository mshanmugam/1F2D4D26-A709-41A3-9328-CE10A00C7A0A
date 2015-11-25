/**
 * 
 */
package com.sample.mongo.utest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.user.service.orchestration.TestAspector;

/**
 * @author maruthishanmugam
 *
 */
public class AspectTester {
	
	private ApplicationContext context = null;
	@Before
	public void setup()
	{
		context=new ClassPathXmlApplicationContext("/META-INF/configs/ude.spring.base.test.context.xml");
	}
	
	@Test
	public void testAspect()
	{
		TestAspector aspect = (TestAspector)context.getBean("testAspect");
		System.out.println(aspect.getName(null));
	}

}

