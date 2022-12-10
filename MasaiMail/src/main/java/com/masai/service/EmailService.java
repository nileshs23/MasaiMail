package com.masai.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.masai.model.EmailDTO;
import com.masai.model.Emails;

public interface EmailService {

	public EmailDTO sendMail(EmailDTO emailDto,String starred) throws UsernameNotFoundException;
	
	public List<EmailDTO> getAllMails(String username) throws UsernameNotFoundException; 
	
	public List<EmailDTO> starredMails(String username) throws UsernameNotFoundException;
	
	public Emails deleteMail(Integer mailid) throws Exception;
	
	
	
	
	
}
