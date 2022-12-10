package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.model.EmailDTO;
import com.masai.model.Emails;
import com.masai.model.User;
import com.masai.repository.EmailsRepository;
import com.masai.repository.UserRepository;

@Service
public class EmailsServiceImpl implements EmailService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailsRepository emailsRepo;
	

	@Override
	public EmailDTO sendMail(EmailDTO emailDto,String starred) throws UsernameNotFoundException {
		List<User> sender =userRepository.findByUsername(emailDto.getSenderMailId());
		
		List<User> receiver =userRepository.findByUsername(emailDto.getRecipientMailId());
		
		if(sender.size() > 0 && receiver.size() >0) {
			Emails email = new Emails();
			
			email.setRecipientMailId(emailDto.getRecipientMailId());
			email.setBody(emailDto.getBody());
			
			// If user wants to star an email then it will changed as starred
			if(starred.toLowerCase().equals("y")) {
				email.setStarred(true);
			}
			
			email.setUser(sender.get(0));
			
			sender.get(0).getEmails().add(email);
			
			userRepository.saveAndFlush(sender.get(0));
			emailsRepo.save(email);
			
			return emailDto;
			
		}else {
			throw new UsernameNotFoundException("Sender Or Receiver Not Found !");
		}
		
	}

	@Override
	public List<EmailDTO> starredMails(String username) throws UsernameNotFoundException {
		
		List<User> user = userRepository.findByUsername(username);
		
		if(user.size() > 0) {
//			new EmailDTO(username, mail.getRecipientMailId(), mail.getBody())
			
			List<EmailDTO> starredMails = new ArrayList<>();
			
			user.get(0).getEmails().forEach(mail ->	{
				if(mail.isStarred()) {
					starredMails.add(
							new EmailDTO(username, mail.getRecipientMailId(), mail.getBody()));
				}	
			});
			
			return starredMails;
		}else {
			throw new UsernameNotFoundException(username+" username Not Found !");
		}
	}

	@Override
	public Emails deleteMail(Integer mailid) throws Exception {
		
		Optional<Emails> mailOpt = emailsRepo.findById(mailid);
		
		if(mailOpt.isPresent()) {
			emailsRepo.delete(mailOpt.get());
			return mailOpt.get();
		}else {
			throw new Exception("Mail Not Found");
		}
		
		
	}

	@Override
	public List<EmailDTO> getAllMails(String username) throws UsernameNotFoundException {
		List<User> user = userRepository.findByUsername(username);
		
		if(user.size() > 0) {
			List<EmailDTO> dto = new ArrayList<>();
			
			List<Emails> mails = user.get(0).getEmails();
			
			mails.forEach((mail)->{
				dto.add(new EmailDTO(mail.getUser().getUsername(), mail.getRecipientMailId(), mail.getBody()));
			});
			
			return dto;
		}else {
			throw new UsernameNotFoundException(username+" username Not Found !");
		}
	}

}
