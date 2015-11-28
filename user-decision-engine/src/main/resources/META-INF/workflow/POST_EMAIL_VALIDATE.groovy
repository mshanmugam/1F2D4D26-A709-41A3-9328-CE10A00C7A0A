package email.validate


import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

public class POST_EMAIL_VALIDATE {

	def applyWorkflows={WorkflowDomain workFlow ->

		Map<String, Object> response = new HashMap<String, Object>();
		response = workFlow.getFact("response",Map.class);
		String emailId = (String)response.get("data");
		if(emailId != null && emailId.indexOf("@metlife") != -1)
		{
			response.put("status","business_validation_failure");
			response.put("reasonMessage","Metlife employees are not eligible for this APR");
			response.remove("data");
		}else{
			System.out.println("Post Email Validation completed Successfully");
		}

		workFlow.addFacts("shallContinue",true);
		System.out.println("Email retrieval completed successfully");
	}

	/**
	 * Pass / call the required backends , as needed from the work flow domain and get the email object
	 * @param ssn
	 * @return
	 */
	private String pullEmail(String ssn) {
		return "none@none.com"
	}
}