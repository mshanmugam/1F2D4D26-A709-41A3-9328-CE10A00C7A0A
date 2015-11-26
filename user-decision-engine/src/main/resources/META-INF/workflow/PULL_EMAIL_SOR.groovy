package email.validate


import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

public class PULL_EMAIL_SOR {

	def applyWorkflows={WorkflowDomain workFlow ->

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status","success");
		response.put("data",pullEmail(null));
		workFlow.addFacts("response",response);
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