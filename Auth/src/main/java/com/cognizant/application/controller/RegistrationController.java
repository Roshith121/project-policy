package com.cognizant.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.application.model.User;

import com.cognizant.application.service.RegistrationServiceImpl;

@RestController 
@CrossOrigin(origins="http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private RegistrationServiceImpl service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmail=user.getEmailId();
		if(tempEmail!=null && !"".equals(tempEmail)) {
			User userObj = service.fetchUserByEmailId(tempEmail);
			if(userObj!=null) {
				throw new Exception("User with "+tempEmail+" already exists!!");
			}
		}
		
		String tempUsername=user.getUsername();
		if(tempUsername!=null && !"".equals(tempUsername)) {
			User userObj = service.fetchUserByUsername(tempUsername);
			if(userObj!=null) {
				throw new Exception("User with "+tempUsername+" already exists!!");
			}
		}
		return service.saveUser(user);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempusername=user.getUsername();
		String temppassword=user.getPassword();
		User userObj=null;
		if(tempusername!=null && temppassword!=null) {
		    userObj = service.fetchUserByUsernameAndPassword(tempusername, temppassword);
		}
		if(userObj==null) {
			throw new Exception("Bad Credentials!!");
		}
		return userObj;
	}

}
