package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;

public interface UserIService {

	User getUserById(Integer id);
	List<User> getUsers();
	User saveUser(User user, MultipartFile image);
	void deleteById(Integer id);
	List<User> getUserByName(String name);
	List<User> getUserByAddress(String address);
	
	Optional<User> findByEmail(String email);
}
