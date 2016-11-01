/**
 * 
 */
package com.sample.mongo.utest;

import com.sample.user.service.orchestration.Name;

import groovy.lang.GroovyClassLoader;

/**
 * @author maruthishanmugam
 *
 */
public class GroovyBeanTester {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception{
		
		GroovyClassLoader gcLoader = new GroovyClassLoader(GroovyBeanTester.class.getClassLoader());

		Class clasz = gcLoader.parseClass(GroovyBeanTester.class.getClassLoader().getResourceAsStream("META-INF/workflow/NAME_INT_IMPL.groovy"), "NAME_I.groovy");
		Name name =			(Name)clasz.newInstance();
		System.out.println(name.getName());
		

	}

}
