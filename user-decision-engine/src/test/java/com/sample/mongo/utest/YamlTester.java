package com.sample.mongo.utest;

import java.util.UUID;

import org.yaml.snakeyaml.Yaml;

public class YamlTester {
	
	public static void main(String args[]) throws Exception
	{
		YamlData data = new YamlData();
		data.setId(UUID.randomUUID().toString());
		data.setName("Maruthi");
		Yaml yaml = new Yaml();
		System.out.println(yaml.dump(data));
	}

}
