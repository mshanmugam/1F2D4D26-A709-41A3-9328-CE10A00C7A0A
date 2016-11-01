package com.sample.mongo.utest;

import java.util.Date;

import rx.Observable;
import rx.functions.Action1;

public class RXJavaTester {
	
	
	public static void rx(String... data)
	{
		Observable.from(data).subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				try{
				Thread.sleep(1000);
				}catch(Exception ex)
				{
					
				}
				System.out.println("Test :"+arg0+ " : "+new Date());
				
			}
			
		});
	}

	public static void main(String[] args) {
		
		rx("Maruthi","Deepan","Test","Event");
		System.out.println("Executing");

	}

}
