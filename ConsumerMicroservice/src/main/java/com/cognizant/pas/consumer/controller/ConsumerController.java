package com.cognizant.pas.consumer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pas.consumer.model.Property;
import com.cognizant.pas.consumer.payload.request.BusinessPropertyRequest;
import com.cognizant.pas.consumer.payload.request.ConsumerBusinessRequest;
import com.cognizant.pas.consumer.payload.response.BusinessPropertyDetails;
import com.cognizant.pas.consumer.payload.response.ConsumerBusinessDetails;
import com.cognizant.pas.consumer.payload.response.MessageResponse;
import com.cognizant.pas.consumer.repository.BusinessRepository;
import com.cognizant.pas.consumer.repository.ConsumerRepository;
import com.cognizant.pas.consumer.repository.PropertyRepository;
import com.cognizant.pas.consumer.service.ConsumerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createConsumerBusiness")
	public MessageResponse createConsumerBusiness(@Valid @RequestBody ConsumerBusinessRequest consumerBusinessRequest) throws Exception {
		log.info("Start createConsumerBusiness");
		if (!consumerService.checkBusinessEligibility(consumerBusinessRequest)) {
			throw new Exception("Sorry!!, You are Not Eligibile for Insurance");
		}
		MessageResponse messageResponse = consumerService.createConsumerBusiness(consumerBusinessRequest);
		log.debug("ConsumerBusiness Status: {}", messageResponse);
		log.info("End createConsumerBusiness");
		return (messageResponse);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateConsumerBusiness/{consumerid}") 
	public MessageResponse updateConsumerBusiness(@Valid @PathVariable Long consumerid ,@RequestBody ConsumerBusinessDetails consumerBusinessDetails) throws Exception {
		log.info("Start updateConsumerBusiness");
		if (!consumerRepository.existsById(consumerBusinessDetails.getConsumerId())) {
			throw new Exception("Sorry!!, No Consumer found");
		}
		if (!businessRepository.existsByConsumerId(consumerBusinessDetails.getConsumerId())) {
			throw new Exception("Sorry!!, No Consumer found");
		}
		if (!businessRepository.existsById(consumerBusinessDetails.getBusinessid())) {
			throw new Exception("Sorry!!, No Consumer found");
		}
		MessageResponse messageResponse = consumerService.updateConsumerBusiness(consumerBusinessDetails);
		log.debug("ConsumerBusiness Status: {}", messageResponse);
		log.info("End updateConsumerBusiness");
		return (messageResponse);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/viewConsumerBusiness/{consumerid}")
	public ResponseEntity<?> viewConsumerBusiness(@Valid @PathVariable Long consumerid) throws Exception {
		log.info("Start viewConsumerBusiness");
		if (!consumerRepository.existsById(consumerid)) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
			
			throw new Exception("Sorry!!, No Consumer Found");
		}
		if (!businessRepository.existsByConsumerId(consumerid)) {
			//return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
			throw new Exception("Sorry!!, No Consumer Found");
		}
		ConsumerBusinessDetails consumerBusinessDetails = consumerService.viewConsumerBusiness(consumerid);
		log.debug("ConsumerBusiness Details: {}", consumerBusinessDetails);
		log.info("End viewConsumerBusiness");
		return ResponseEntity.ok(consumerBusinessDetails);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/viewConsumerBusinessByPolicy/{consumerid}")
	public ConsumerBusinessDetails viewConsumerBusinessbypolicy(@Valid @PathVariable Long consumerid) {
		log.info("Start viewConsumerBusinessByPolicy");
		ConsumerBusinessDetails consumerBusinessDetails = consumerService.viewConsumerBusiness(consumerid);
		log.debug("ConsumerBusiness Details: {}", consumerBusinessDetails);
		log.info("End viewConsumerBusinessByPolicy");
		return consumerBusinessDetails;
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createBusinessProperty")
	public MessageResponse createBusinessProperty(@Valid @RequestBody BusinessPropertyRequest businessPropertyRequest) throws Exception {
		log.info("Start createBusinessProperty");
		if (!consumerRepository.existsById(businessPropertyRequest.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(businessPropertyRequest.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(businessPropertyRequest.getBusinessId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!consumerService.checkPropertyEligibility(businessPropertyRequest.getPropertytype(),
				businessPropertyRequest.getInsurancetype(), businessPropertyRequest.getBuildingtype(),businessPropertyRequest.getBuildingage())) {
			return (new MessageResponse("Sorry!!, You are Not Eligibile for Insurance"));
		}
		MessageResponse messageResponse = consumerService.createBusinessProperty(businessPropertyRequest);
		log.debug("BusinessProperty Status: {}", messageResponse);
		log.info("End createBusinessProperty");
		return (messageResponse);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateBusinessProperty/{propertyId}")
	public MessageResponse updateBusinessProperty(@Valid @PathVariable Long propertyId, @RequestBody BusinessPropertyDetails businessPropertyDetails) throws Exception {
		log.info("Start updateBusinessProperty");
		if (!propertyRepository.existsById(businessPropertyDetails.getPropertyId())) {
			throw new Exception("Sorry!!, No Property Found");
		}
		if (!consumerRepository.existsById(businessPropertyDetails.getConsumerId())) {
			throw new Exception("Sorry!!, No Property Found");
		}
		if (!businessRepository.existsByConsumerId(businessPropertyDetails.getConsumerId())) {
			throw new Exception("Sorry!!, No Property Found");
		}
		if (!businessRepository.existsById(businessPropertyDetails.getBusinessId())) {
			throw new Exception("Sorry!!, No Property Found");
		}
		MessageResponse messageResponse = consumerService.updateBusinessProperty(businessPropertyDetails);
		log.debug("BusinessProperty Status: {}", messageResponse);
		log.info("End updateBusinessProperty");
		return (messageResponse);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/viewConsumerProperty/{propertyid}")
	public ResponseEntity<?> viewConsumerProperty(@Valid @PathVariable Long propertyid) throws Exception {
		log.info("Start viewConsumerProperty");
		if (!propertyRepository.existsById(propertyid)) {
			throw new Exception("Sorry!!!, No Property Found");
		}

		BusinessPropertyDetails property = consumerService.viewConsumerProperty(propertyid);
		log.debug("BusinessProperty Details: {}", property);
		log.info("End viewConsumerProperty");
		return ResponseEntity.ok(property);
	}

	public MessageResponse sendPropertyErrorResponse() {
		return (new MessageResponse("(Property Error Response!!"));

	}

	public MessageResponse sendConsumerErrorResponse() {
		return (new MessageResponse("(Consumer Error Response!!"));

	}

}
