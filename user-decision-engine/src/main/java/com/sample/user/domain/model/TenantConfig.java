/**
 * 
 */
package com.sample.user.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author maruthishanmugam
 *
 */
@SuppressWarnings("all")
@Document(collection="tenantconfig")
public class TenantConfig {
	@Id
	private String Id;
	
	private String tenantUniqueIdentifier;
	
	private String tenantName;
	
	private String tenantCountry;
	
	private Map tenantWorkflow;
	
	private Map tenantDataSORs;
	
	private Date enrolledDate;
	
	private Date updatedDate;
	
	private boolean status;
	
	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TenantConfig()
	{
		
	}
	public TenantConfig(String tenantUniqueIdentifier,String tenantName,String tenantCountry)
	{
		this.tenantUniqueIdentifier=tenantUniqueIdentifier;
		this.tenantCountry=tenantCountry;
		this.tenantName=tenantName;
		
	}

	public String getTenantUniqueIdentifier() {
		return tenantUniqueIdentifier;
	}

	public void setTenantUniqueIdentifier(String tenantUniqueIdentifier) {
		this.tenantUniqueIdentifier = tenantUniqueIdentifier;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantCountry() {
		return tenantCountry;
	}

	public void setTenantCountry(String tenantCountry) {
		this.tenantCountry = tenantCountry;
	}

	public Map getTenantWorkflow() {
		return tenantWorkflow;
	}

	public void setTenantWorkflow(Map tenantWorkflow) {
		this.tenantWorkflow = tenantWorkflow;
	}

	public Map getTenantDataSORs() {
		return tenantDataSORs;
	}

	public void setTenantDataSORs(Map<String,List<String>> tenantDataSORs) {
		this.tenantDataSORs = tenantDataSORs;
	}


	
	
}
