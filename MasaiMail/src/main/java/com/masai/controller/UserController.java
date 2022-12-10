package com.masai.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.EmailDTO;
import com.masai.model.Emails;
import com.masai.model.User;
import com.masai.service.EmailService;
import com.masai.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/masaimail")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		String msg ="Welcome to Masai App without security" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/welcome2")
	public ResponseEntity<String> welcomeUser(){
		String msg ="Welcome to Masai App with security Authorized" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
		
	@PostMapping("/register")
	public ResponseEntity<UserDetails> signUp(@Valid @RequestBody User user){
		
		UserDetails created = userService.createUser(user);
		return new ResponseEntity<UserDetails>(created,HttpStatus.CREATED);
	}
	
	@GetMapping("/mail")
	public ResponseEntity<List<EmailDTO>> getAllMails(Principal principal){
		List<EmailDTO> mails =  emailService.getAllMails(principal.getName());
		
		return new ResponseEntity<List<EmailDTO>>(mails,HttpStatus.OK);
	}
	
	@GetMapping("/starred")
	public ResponseEntity<List<EmailDTO>> getStarredMails(Principal principal){
		List<EmailDTO> mails =  emailService.starredMails(principal.getName());
		
		return new ResponseEntity<List<EmailDTO>>(mails,HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		
		User updated = userService.updateUser(user);
		return new ResponseEntity<User>(updated,HttpStatus.OK);
	}
	

}
