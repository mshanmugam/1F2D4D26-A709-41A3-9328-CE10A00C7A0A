package com.sample.user.service.orchestration;

import java.util.Date;

import com.sample.rest.user.registration.annotation.LogParam;
import com.sample.rest.user.registration.annotation.LogType;
import com.sample.rest.user.registration.annotation.UDETrace;

public class TestAspector {
	
	@UDETrace(informationGroupTag="[REGISTRATION]",information="Invoking the getName for Registration",logTypes={LogType.TIMER,LogType.ENTRY_EXIT,LogType.EXCEPTION})
	public String getName(String name)
	{
		
			return name.toString();
		
		//return "Name_Success";
	}

}
