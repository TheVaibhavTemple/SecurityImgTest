package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.service.UserIService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController //Https + java to json + @Component
@RequestMapping("/api/user")
@ConfigurationProperties(prefix="my.app")
public class UserController {
	
	private Integer id;
	private String code;

	@Autowired
	private UserIService userIService;
	
	@Value("${app.name}")
	private String appName;
	
	@GetMapping(value = "/{id}")
	User getUserById(@PathVariable Integer id){
//		log.info("request received into controller getUserById");
		return userIService.getUserById(id);
	}
	
	@GetMapping(value = "/getall")
	List<User> getUsers(){
//		System.out.println(appName + id + code);
//		log.info("INFO");  //info	WARN	error
//		log.debug("debug");//info  	debug	WARN	ERROR
//		log.warn("warn");  //WARN	ERROR
//		log.error("error");//ERROR
//		log.trace("trace");//info  	debug	WARN	ERROR	TRACE
		
		return userIService.getUsers();
	}
	
	
	
	@PostMapping("/")			//create
	User saveUser(@RequestBody User user, MultipartFile image){
//		return userIService.saveUser(user);
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	void deleteById(@PathVariable Integer id){
		userIService.deleteById(id);
	}
	
	@PutMapping(value = "/")
	User update(@RequestBody User user){
//		return userIService.saveUser(user);
		return null;
	}
	
	@GetMapping(value = "/getuserbyname/{name}")
	List<User> getUserByName(@PathVariable String name){
		return userIService.getUserByName(name);
	}
	
	@GetMapping(value = "/getuserbyaddress/{address}")
	List<User> getUserByAddress(@PathVariable String address){
		return userIService.getUserByAddress(address);
	}
}
