package com.shariq.lc.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.shariq.lc.api.Phone;

public class PhoneNumberFormatter implements Formatter<Phone>{

	@Override
	public String print(Phone phone, Locale locale) {
		// TODO Auto-generated method stub
		
		
		return phone.getCountryCode() + "-" + phone.getUserNumber();
	}

	@Override
	public Phone parse(String completePhoneNumber, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Phone phone=new Phone();

		//split the string received from the user
		String[] phoneNumberArray=completePhoneNumber.split("-");
		
		System.out.println("inside parse method");
		
		//whether the number consists of -
		int index=completePhoneNumber.indexOf('-');
		
		//if the string doesn't have a "-" or start with a "-" add 91 before it 
		if(index == -1 || completePhoneNumber.startsWith("-")) {
			
			//if the '-' is not found, then add 91 as the default country code
			phone.setCountryCode("91");
			
			if(completePhoneNumber.startsWith("-")) {
				phone.setUserNumber(phoneNumberArray[1]);
			
		} else { 
			
			phone.setUserNumber(phoneNumberArray[0]);
			
		}
		}
		
		
		else {
		
		//Extract the countryCode and set it to the phone class countryCodeproperty
		phone.setCountryCode(phoneNumberArray[0]);
		phone.setUserNumber(phoneNumberArray[1]);
		
		}
		return phone;
	}

}
