package email.validate


import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

public class EMAIL_VALIDATE {

	def applyWorkflows={WorkflowDomain workFlow ->

		Map<String, Object> request = workFlow.getFact("request",Map.class);
		if(request != null && request.get("SSN") != null ){
			workFlow.addFacts("shallContinue",true);
		}else{
			Map<String,Object> response = new HashMap<String,Object>();
			response.put("status","failure");
			response.put("reasonMessage","Validation Failed, Empty/Blank SSN");
			workFlow.addFacts("shallContinue",false);
			workFlow.addFacts("response",response);
		}
		System.out.println("Validation_Completed Successfully");
	}
}