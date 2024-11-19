package com.cognizant.pas.policy.restclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.pas.policy.payload.response.ConsumerBusinessDetails;


@FeignClient(name = "consumer-service", path="/consumer")
public interface ConsumerClient {
	
	@GetMapping("/viewConsumerBusinessByPolicy/{consumerid}")
	public ConsumerBusinessDetails viewConsumerBusiness(@Valid @PathVariable Long consumerid);

}
