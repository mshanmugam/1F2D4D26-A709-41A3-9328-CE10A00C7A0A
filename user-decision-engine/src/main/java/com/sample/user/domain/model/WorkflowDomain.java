package com.sample.user.domain.model;

import java.util.HashMap;
import java.util.Map;

public class WorkflowDomain {
	
	
	private Map<String,Object> facts;
	
	public void addFacts(String factName,Object object)
	{
		if(facts == null)
		{
			facts = new HashMap<String,Object>();
		}
		facts.put(factName, object);
	}
	
	public <T extends Object> T getFact(String factName,Class<T> type)
	{
		return (T)facts.get(factName);
	}

}
