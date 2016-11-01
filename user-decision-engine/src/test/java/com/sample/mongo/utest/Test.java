/**
 * 
 */
package com.sample.mongo.utest;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPConstants;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

/**
 * @author maruthishanmugam
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		WorkflowDomain wf = new WorkflowDomain();
		System.out.println("Good God");
		wf.addFacts("tenant", new TenantConfig("Test","123", "IN"));
		System.out.println(wf.getFact("tenant", TenantConfig.class).getTenantCountry());
		String Deepan = "Deepan";
		String expression = "test.getName(Deepan)";
		JexlExpression jex = new JexlBuilder().create().createExpression(expression);
		
		JexlContext ctx = new MapContext();
		ctx.set("test", new Test());
		ctx.set("Deepan", Deepan);
		System.out.println(jex.evaluate(ctx));
		HttpURLConnection connection = (HttpURLConnection)new URL("m").openConnection();
		HttpServletRequest request ;
		
		
		
		
	}
	
	public String getName(String str)
	{
		System.out.println(str);
		return "Maruthi";
	}

}
