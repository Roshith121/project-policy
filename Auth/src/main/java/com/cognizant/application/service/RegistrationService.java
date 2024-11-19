package com.cognizant.application.service;

import com.cognizant.application.model.User;

public interface RegistrationService {

	public User saveUser(User user);
	
	public User fetchUserByEmailId(String emailId);
	
	public User fetchUserByUsername(String username);
	
	public User fetchUserByUsernameAndPassword(String username, String password);
}
