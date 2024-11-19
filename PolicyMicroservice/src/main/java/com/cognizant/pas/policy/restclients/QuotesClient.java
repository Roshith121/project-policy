package com.cognizant.pas.policy.restclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "quotes-service", path="/quotes")
public interface QuotesClient {
	
	@GetMapping("/getQuotesForPolicy/{businessValue}/{propertyValue}/{propertyType}") 
	public String quotesResponse
	(@Valid @PathVariable Long businessValue,@PathVariable Long propertyValue,@PathVariable String propertyType);

}
