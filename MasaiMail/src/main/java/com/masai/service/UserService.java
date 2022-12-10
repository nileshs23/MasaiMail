package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.config.MySecurityForUser;
import com.masai.model.User;
import com.masai.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<User> users = userRepository.findByUsername(username);
		
		if(users.size() >0) {
			return new MySecurityForUser(users.get(0));
		}else {
			throw new UsernameNotFoundException(username+" user Not Found !");
		}
	}
	
	public UserDetails createUser(User user) {
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return new MySecurityForUser(userRepository.save(user));
	}
	
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);	
	}


}
