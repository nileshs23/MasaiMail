package com.masai.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
	
	@Email(message = "Enter Valid SENDER email Id")
	private String senderMailId;
	
	@Email(message = "Enter Valid RECIEPIENTs EMAIL !")
	private String recipientMailId;
	
	@NotNull(message = "Enter Body OF TEXT")
	private String body;

}
