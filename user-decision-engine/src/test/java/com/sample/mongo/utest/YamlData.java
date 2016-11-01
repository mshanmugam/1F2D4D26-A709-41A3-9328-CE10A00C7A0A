package com.sample.mongo.utest;

import java.io.Serializable;

public class YamlData implements Serializable {
	
	private String name;
	
	private String id;

	/**
	 * @return the name 
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
