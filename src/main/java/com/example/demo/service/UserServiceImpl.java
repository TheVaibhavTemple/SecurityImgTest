package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.UserIRepo;
import com.example.demo.model.User;
import com.example.demo.util.EmailUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImpl implements UserIService {

	@Autowired
	UserIRepo userIRepo;
	
	@Autowired
	EmailUtil emailUtil;

	@Override
	public User getUserById(Integer id) {
		Optional<User> user =  userIRepo.findById(id);
		
//		log.info("req entered in service impl getUserById");
//		
//		log.debug(String.valueOf(user.isEmpty()));
//		log.debug(String.valueOf(user.isPresent()));
//		log.debug(user.get().toString());
//		log.debug(user.get().getId().toString());
//		
//		log.warn("don not call empty object");
		
		return user.isPresent() ? user.get() : new User();
	}

	@Override
	public List<User> getUsers() {
		log.info("request received into service impl");
		return userIRepo.findAll();
	}

	@Override
	public User saveUser(User user, MultipartFile image) {
		
		try {
			user.setImageData(image.getBytes());
//			user.setImageData(ImageUtil.compressImage(image.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		user.setPassword(enc.encode(user.getPassword()));
		User savedUser = userIRepo.save(user);//will get id here
		

		log.info("user saved");
		
		new Thread(() -> {
			//send a email
			log.info("email process started");
			emailUtil
			.send(user.getEmail(), 
					"User registration has been completed", 
					"<h1>User "+user.getName()+" has been registred and user id is "+savedUser.getId()+"</h1>");
			
			
			log.info("email process end");
		}).start();
		
		
		log.info("user return to controller");
		return savedUser;
	}

	@Override
	public void deleteById(Integer id) {
		userIRepo.deleteById(id);
	}

	@Override
	public List<User> getUserByName(String name) {	
		return userIRepo.findUserByName(name);
	}

	@Override
	public List<User> getUserByAddress(String address) {
		return userIRepo.findUserByAddress(address);
	}

//	@Scheduled(cron = "* * * ? * *")
	public void testScheduling() {
		System.out.println("Hello");
	}
	
	
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userIRepo.findUserByEmail(email);
	}
	
	//-----------------------------------//
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
	{
		System.out.println(username);
		//fetch obj by email
		Optional<User> opt =  userIRepo.findUserByEmail(username);
		System.out.println(opt.get());
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("Not exist");
		} else {
			User user = opt.get();
			return new org.springframework.security.core.userdetails
					.User(
							username, 
							user.getPassword(),
							user.getUsrRole()
							.stream()
							.map(role->new SimpleGrantedAuthority(role))
							.collect(Collectors.toSet())
							);
		}
	}
	
}
