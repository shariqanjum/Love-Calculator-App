package com.shariq.lc.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String>{

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		if(phone == null || phone.trim().isEmpty() ) {
			
			return false;
		}
		
		return phone.matches("\\d{10}");
	}

}
