package com.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="INSURANCE_PLANS")
public class InsurancePlanEntity {
	
	@Id
	@Column(name="PLAN_ID")
	private Integer planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="HOLDER_NAME")
	private String holderName;
	
	@Column(name="BENEFIT_AMT")
	private String benefitAmt;

}
