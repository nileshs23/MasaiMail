package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.EmailDTO;
import com.masai.model.Emails;
import com.masai.service.EmailService;

@RestController
@RequestMapping("/masaimail")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/mail")
	public ResponseEntity<EmailDTO> sendMail(@RequestBody EmailDTO dto ,@RequestParam(name="starred",required = false) String star){
		EmailDTO mail =  emailService.sendMail(dto, star);
		
		return new ResponseEntity<EmailDTO>(mail,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/mail/delete")
	public ResponseEntity<Emails> deleteMail(@RequestParam Integer mailId) throws Exception{
		Emails mail = emailService.deleteMail(mailId);
		
		return new ResponseEntity<Emails>(mail,HttpStatus.OK);
	}
	
	
}
