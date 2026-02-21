package com.shariq.lc.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shariq.lc.api.UserRegistrationDTO;

@Component
public class EmailValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserRegistrationDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "communicationDTO.email", "email.empty");
		
		//whether the email is ending with ace.com
		
		String email =((UserRegistrationDTO)target).getCommunicationDTO().getEmail();
		if(!email.endsWith("ace.com")) {
			
			errors.rejectValue("communicationDTO.email","email.invalidDomain");
		}

	}

}
