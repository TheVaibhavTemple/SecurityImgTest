package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//AUTHENTICATION
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("A").password("{noop}B").authorities("ADMIN");
//		auth.inMemoryAuthentication().withUser("M").password("{noop}N").authorities("EMPLOYEE");
//		auth.inMemoryAuthentication().withUser("X").password("{noop}Y").authorities("STUDENT");
		
		auth.userDetailsService(service)
		.passwordEncoder(encoder);
	}
	
	
	//AUTHORIZATION
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		
		.antMatchers("/home", "/reg").permitAll()
		
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/emp","/emp/**").hasAuthority("EMPLOYEE")
		.antMatchers("/std","/std1").hasAuthority("STUDENT")
		
		
		
//		.anyRequest().authenticated()
		
		.and()
		
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
		
		.and()
		
		.logout()
		
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
		
		.exceptionHandling()
		
		.accessDeniedPage("/denied");
		
	}
}
