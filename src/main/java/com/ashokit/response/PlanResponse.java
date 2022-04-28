package com.ashokit.response;

import lombok.Data;

@Data
public class PlanResponse {
	
	private Integer planId;
	private String planName;
	private String planStatus;
	private String holderName;
	private String benefitAmt;
	

}
