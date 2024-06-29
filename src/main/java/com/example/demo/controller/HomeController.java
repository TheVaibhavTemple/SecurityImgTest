package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.service.UserIService;

@Controller
public class HomeController {
	
	@Autowired
	private UserIService userIService;

	@GetMapping("/home")
	public String showHome() {
		return "Home";
	}
	
	@GetMapping("/welcome")
	public String showWelcome() {
		return "Welcome";
	}
	 
	@GetMapping("/admin")
	public String showAdmin() {
		return "Admin";
	}
	
	@GetMapping("/emp")
	public String showEmp() {
		return "Employee";
	}
	
	@GetMapping("/std")
	public String showStd() {
		return "Student";
	}
	
	@GetMapping("/denied")
	public String showDenied() {
		return "Denied";
	}
	
	@GetMapping("/error")
	public String showError() {
		return "Error";
	}
	
	@GetMapping("/std1")
	public String showStd1() {
		return "Student1";
	}
	
	@GetMapping("/emp/emp1") 
	public String showEmp1() {
		return "Employee1";
	}
	
	@GetMapping("/emp/emp2")
	public String showEmp2() {
		return "Employee2"; 
	}
	
	@GetMapping("/emp/emp3")
	public String showEmp3() {
		return "Employee3";
	}
	
	@GetMapping("/reg")
	public String showReg() {
		return "Register";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user,@RequestParam("image") MultipartFile image, Model model) throws IOException {
		
		User userReturn = userIService.saveUser(user, image);
		 model.addAttribute("img", Base64.getEncoder().encodeToString(userReturn.getImageData()));
		model.addAttribute("msg", "User has been create and user id is : " + userReturn.getId());
		return "Register";
	}
}