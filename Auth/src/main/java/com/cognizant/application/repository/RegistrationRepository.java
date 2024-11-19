package com.cognizant.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.application.model.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Integer> {

	public User findByEmailId(String emailId);

	public User findByUsername(String username);

	public User findByUsernameAndPassword(String username, String password);

}
