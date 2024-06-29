//package com.example.demo.runners;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.util.EmailUtil;
//
//
//
//@Component
//public class TestEmailRunner implements CommandLineRunner {
//	
//	@Autowired
//	private EmailUtil util;
//
//	@Override
//	public void run(String... args) throws Exception {
//		util.send("vyadav1910@gmail.com", 
//				new String[] {
//					"rajeshmrsc1@gmail.com"	,"panwarramraj4@gmail.com", "skumarpuriya@gmail.com"
//				}, 
//				new String[] {
//						"skumarpuriya@gmail.com"
//				}, "mail test application", "<h1>Hello Students</h1>");
//		
//		
//		
//		util.send("vyadav1910@gmail.com", 
//				 "mail test application", "<h1>Hello Students</h1>");
//		
//	}
//	
//
//
//}
//
