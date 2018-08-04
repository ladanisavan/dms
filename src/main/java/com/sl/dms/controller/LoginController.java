package com.sl.dms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sl.dms.configuration.handler.LoginSuccessHandler;

@Controller
public class LoginController {

	@Autowired
	LoginSuccessHandler loginSuccessHandler;
	
	@GetMapping("/")
	public void getHome(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		loginSuccessHandler.onAuthenticationSuccess(request, response, authentication);
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/loginfailed")
	public String getLoginWhenAuthFailed(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("authError", true);
		return "redirect:login";
	}
	
	@GetMapping("/admin/home")
	public String getAdminHome(){
		return "/admin/home";
	}
	
	
	@GetMapping("/home")
	public String getUserHome(){
		return "home";
	}
}
