/**
 * 
 */
package com.sample.rest.user.registration.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.sample.user.domain.model.BusinessRuleConfig;

/**
 * @author maruthishanmugam
 *
 */
public class BusinessRuleEngineDAOImpl {

	private MongoOperations operations;

	public MongoOperations getOperations() {
		return operations;
	}

	public void setOperations(MongoOperations operations) {
		this.operations = operations;
	}

	public void insertBusinessRules(String name, String content) {
		BusinessRuleConfig config = new BusinessRuleConfig();
		config.setContent(content);
		config.setName(name);
		operations.save(config);
	}

	public void updateBusinessRules(String name, String content) {

		BusinessRuleConfig config = new BusinessRuleConfig();
		config.setContent(content);
		config.setName(name);
		operations.save(config);

		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(config.getName()));
		operations.updateMulti(query, Update.update("content", config.getContent()), BusinessRuleConfig.class);
	}

	public List<BusinessRuleConfig> readAllBusinessRules() {
		return operations.findAll(BusinessRuleConfig.class);
	}

}
