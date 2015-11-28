package email.validate


import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

public class GET_APR {

	def applyWorkflows={WorkflowDomain workFlow ->


			Map<String,Object> response = new HashMap<String,Object>();
			response.put("status","Success");
			response.put("data","APR is 10%");
			workFlow.addFacts("shallContinue",true);
			workFlow.addFacts("response",response);

		System.out.println("APR Retrieved Successfully");
	}
}