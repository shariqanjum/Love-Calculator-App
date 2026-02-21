package com.shariq.lc.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shariq.lc.Validator.EmailValidator;
import com.shariq.lc.Validator.UserNameValidator;
import com.shariq.lc.api.CommunicationDTO;
import com.shariq.lc.api.Phone;
import com.shariq.lc.api.UserRegistrationDTO;
import com.shariq.lc.propertyeditor.NamePropertyEditor;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	@Autowired
	private EmailValidator emailvalidator;

    private final LocalValidatorFactoryBean validator;

    RegistrationController(LocalValidatorFactoryBean validator) {
        this.validator = validator;
    }
	
	@RequestMapping("/register")
	public String showRegistrationPage(@ModelAttribute("userReg") UserRegistrationDTO dto) {
		
		System.out.println("inside showRegistrationPage method");
		
		Phone phone= new Phone();
		phone.setCountryCode("91");
		phone.setUserNumber("9333666999");
		
		CommunicationDTO communicationDTO=new CommunicationDTO();
		communicationDTO.setPhone(phone);
		
		dto.setCommunicationDTO(communicationDTO);
		
		return "registration-page";
	}
	@RequestMapping("/registration-success")
	public String processUserReg(@Valid @ModelAttribute("userReg") UserRegistrationDTO dto, BindingResult result)
	{
		System.out.println("inside processUserReg method ");
		
		emailvalidator.validate(dto,result);
		
		
		
		if(result.hasErrors()) {
			
			System.out.println("My page has Errors");
			
			List<ObjectError> allErrors = result.getAllErrors();
			
			for(ObjectError error : allErrors) {
				
				System.out.println(error);
			}
			return "registration-page";
			
		}
		
		
		return "registration-success";
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		
		System.out.println("inside the init binder method..");
		
		StringTrimmerEditor editor = new StringTrimmerEditor(false);
		binder.registerCustomEditor(String.class,"name", editor);
		
		NamePropertyEditor nameEditor = new NamePropertyEditor();
		binder.registerCustomEditor(String.class,"name", nameEditor);
		
		binder.addValidators(new UserNameValidator());
		
		//binder.addValidators(new EmailValidator());
		
	}

}
