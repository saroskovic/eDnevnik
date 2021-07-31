package com.iktpreobuka.eDnevnik.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String handleException(MultipartException e, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("message", "file too large");
		return "redirect:/uploadStatus";
	}
}
