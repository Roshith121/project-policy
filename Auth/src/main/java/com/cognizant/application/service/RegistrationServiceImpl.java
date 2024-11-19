package com.cognizant.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.application.model.User;
import com.cognizant.application.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository repository ;
	
	@Override
	public User saveUser(User user) {
		User saveUser = repository.save(user);
		return saveUser;
	}

   
	public User fetchUserByEmailId(String emailId) {
		User user = repository.findByEmailId(emailId);
		return user;
	}


	public User fetchUserByUsername(String username) {
		User user=repository.findByUsername(username);
		return user;
	}


	public User fetchUserByUsernameAndPassword(String username, String password) {
		User user = repository.findByUsernameAndPassword(username,password);
	   return user;
		
	}

}
