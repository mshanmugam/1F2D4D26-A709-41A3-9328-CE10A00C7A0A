/**
 * 
 */
package com.sample.mongo.utest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;

/**
 * @author maruthishanmugam
 *
 */
public class JBossSer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		
		Logger logger = Logger.getRootLogger();
		
		
		NonSerializableObj obj = new NonSerializableObj();
		obj.setCity(UUID.randomUUID().toString());
		obj.setName(UUID.randomUUID().toString());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JBossObjectOutputStream joos = new JBossObjectOutputStream(baos);
		joos.writeObject(obj);
		System.out.println(baos.toByteArray().length);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		JBossObjectInputStream jiis = new JBossObjectInputStream(bais);
		NonSerializableObj obj1 = (NonSerializableObj)jiis.readObject();
		logger.info(obj1.getName());
		
	}

}
