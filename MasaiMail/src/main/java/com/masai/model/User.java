package com.masai.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	
	@Pattern(regexp = "/^([^0-9]*)$/",message = "Name Should Not Contain Numeric Character !")
	private String firstName;
	
	@Pattern(regexp = "/^([^0-9]*)$/",message = "Name Should Not Contain Numeric Character !")
	private String lastName;
	
	@Email(message = "Enter Valid EMAIL !")
	@Column(unique = true)
	private String username;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{6,12}$",
	message = "Password must be of 6 characters and must include "
			+ " at least one upper case English letter, one lower case "
			+ "English letter, one number and one special character.")
	private String password;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dob;
	
	private String role;
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "user",fetch = FetchType.LAZY)
	private List<Emails> emails = new ArrayList<>();
	
}
