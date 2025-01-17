package com.cognizant.pas.quotes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pas.quotes.models.QuotesMaster;
import com.cognizant.pas.quotes.repository.QuotesMasterRepository;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("quotes")
public class QuotesController {
	
	@Autowired
	QuotesMasterRepository quotesMasterRepository;
	
	@GetMapping("/getQuotesForPolicy/{businessValue}/{propertyValue}/{propertyType}")
	public String getQuotesForPolicy
	(@Valid @PathVariable Long businessValue,@PathVariable Long propertyValue,@PathVariable String propertyType){
		log.info("Start");
		String quotes;
		try
        {
		QuotesMaster quotesMaster=quotesMasterRepository
				.findByBusinessValueAndPropertyValueAndPropertyType(businessValue, propertyValue, propertyType);
		log.debug("QuotesMaster: {}", quotesMaster);
		quotes=quotesMaster.getQuotes();
        }catch(NullPointerException e) 
        { 
        	quotes= "No Quotes, Contact Insurance Provider";
        	return quotes;  
        } 
		log.info("End");
		return quotes; 
	}
	

}
