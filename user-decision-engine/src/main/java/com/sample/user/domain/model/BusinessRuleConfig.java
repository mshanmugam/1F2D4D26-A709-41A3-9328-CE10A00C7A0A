/**
 * 
 */
package com.sample.user.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author maruthishanmugam
 *
 */
@Document(collection="bus_rules")
public class BusinessRuleConfig {
	
	@Id
	private String Id;
	
	private String name;
	
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
