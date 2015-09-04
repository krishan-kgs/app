package com.learning.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.learning.app.model.Mail;

@RestController
public class SendMailController {
	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/getMailDetails")
	public ModelAndView getMailDetails() {
		System.out.println("in controller");
		ModelAndView mv = new ModelAndView("sendMail");
		return mv;
	}
	
	@RequestMapping(value ="/checkStatus", method = RequestMethod.POST ,consumes={"application/json"})
	public ResponseEntity<Mail> checkStatus(@RequestBody Mail mail) {
		HttpStatus status = HttpStatus.OK;
		Mail mail2 = new Mail();
		mail2.setComment(mail.getComment());
		mail2.setFromAddr(mail.getFromAddr());
		mail2.setToAddr(mail.getToAddr());
		mail2.setSubject(mail.getSubject());
		mail2.setContent(mail.getContent());
		System.out.println(mail.getComment()+mail.getFromAddr()+mail.getToAddr()+mail.getSubject()+mail.getContent());
		return new ResponseEntity <Mail>(mail2,status);

		
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody String handleException(HttpServletRequest request, Exception ex) {
		System.out.println("Error occurred in serving requested URL[ " + request.getRequestURL() + "?" + request.getQueryString() + " ], error ::: "
				+ ex.getMessage());
		String errorMessage = "Procession failed :: " + ex.getMessage();
		//DBObject dbObject = new BasicDBObject().append("msg", errorMessage).append("ok", 0);
		return errorMessage;
	}
}
