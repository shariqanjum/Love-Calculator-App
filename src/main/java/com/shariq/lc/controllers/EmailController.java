package com.shariq.lc.controllers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shariq.lc.api.EmailDTO;
import com.shariq.lc.api.UserInfoDTO;
import com.shariq.lc.service.LCAppEmailServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {
	
	Logger logger= Logger.getLogger(EmailController.class.getName());
	
	@Autowired
	private LCAppEmailServiceimpl lcAppEmailService;
	
	@RequestMapping("/sendEmail")
	public String sendEmail( Model model) {
		
		
		model.addAttribute("emailDTO", new EmailDTO());
		
		return "send-email-page";
		
	}
	

	@RequestMapping("/process-email")
	public String processEmail(HttpSession session,
			@ModelAttribute("emailDTO") EmailDTO emailDTO) {
		
		   UserInfoDTO userInfoDTO =
		            (UserInfoDTO) session.getAttribute("userInfo");
		   if (userInfoDTO == null) {
		        return "redirect:/";
		    }
		   
		   
			try {
				lcAppEmailService.sendEmail(userInfoDTO.getUserName(), emailDTO.getUserEmail(), userInfoDTO.getResult());
			} catch (Exception e) {

				logger.info("logging the exception" + e);
			}

			return "process-email-page";

}
}
