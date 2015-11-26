/**
 * 
 */
package com.sample.mongo.utest;

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
	public static void main(String[] args) {
		WorkflowDomain wf = new WorkflowDomain();
		System.out.println("Good God");
		wf.addFacts("tenant", new TenantConfig("Test","123", "IN"));
		System.out.println(wf.getFact("tenant", TenantConfig.class).getTenantCountry());

	}

}
