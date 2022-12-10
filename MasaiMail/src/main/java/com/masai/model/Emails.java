package com.masai.model;

import com.masai.model.User;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
public class Emails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mailId;
	
	@Email(message = "Enter Valid EMAIL !")
	private String recipientMailId;
	
	@NotNull(message = "Enter Body OF TEXT")
	private String body;
	
	private boolean starred = false;
	
	@ManyToOne(optional = false)
	private User user;
	
	
	
	
	
}
