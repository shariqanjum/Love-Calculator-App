package com.shariq.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shariq.lc.api.UserInfoDTO;
import com.shariq.lc.service.LCAppService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("userInfo")
public class LCAppController {
	
	@Autowired
	private LCAppService lcappService;
	
	@RequestMapping("/")
	public String showHomepage(Model model)
	{
		model.addAttribute("userInfo", new UserInfoDTO());
		
		return"home-page";
	}
	
	
	@RequestMapping("/process-homepage")
	public String showRequestPage(Model model, @Valid UserInfoDTO userInfoDTO, BindingResult result) {
		
		model.addAttribute("userInfo", userInfoDTO);
		
		model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "userInfo", result);
		
		System.out.println(userInfoDTO.isTermAndCondition());
		
		if(result.hasErrors())
		{
			System.out.println("my form has errors..");
			
			return "home-page";
		}
		
		String appResult = lcappService.calculateLove(userInfoDTO.getUserName(), userInfoDTO.getCrushName());
		
		userInfoDTO.setResult(appResult);
		
		
		

		//write a service which will calculate the love % between the userName and crushName
		
		return "result-page";	
		
		}
	
		
	}


