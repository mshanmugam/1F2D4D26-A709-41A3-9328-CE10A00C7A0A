package workflows

import com.sample.user.domain.model.TenantConfig;
import com.sample.user.domain.model.WorkflowDomain;

public class TENANT_WORKFLOW {

	def applyWorkflows={WorkflowDomain workFlow ->
		
		TenantConfig config = workFlow.getFact("tenant",TenantConfig.class);
		System.out.println("Workflow Executed Successfully");
		
		
		
	}
}