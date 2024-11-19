package com.cognizant.pas.policy.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IssuePolicyRequest {
	
	@NotBlank
	private String pid;
	@NotNull
	private Long consumerid;
	@NotNull
	private Long businessid;
	@NotBlank
	private String paymentdetails;
	@NotBlank
	private String acceptancestatus;

}
