package com.shariq.lc.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shariq.lc.api.UserRegistrationDTO;

public class UserNameValidator implements Validator {
	
	//check if the userNameObject supports a given object
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserRegistrationDTO.class.equals(clazz);
	}
	//we need to write our custom validation logic
	@Override
	public void validate(Object object, Errors errors) {
		
		//check if the field is null
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.empty", "User Name cannot be empty");
		
		//the string should contain a _
		String userName =((UserRegistrationDTO)object).getUserName();
		if(!userName.contains("_")) {
			
			errors.rejectValue("userName", "userName.invalidString","String must contain a ' _ ' ");
			
			
		}

	}

}
