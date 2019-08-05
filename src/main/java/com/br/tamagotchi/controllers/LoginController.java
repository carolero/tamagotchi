package com.br.tamagotchi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.tamagotchi.models.Login;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public ModelAndView exibirLoginForm() {
		ModelAndView modelAndView = new ModelAndView("login.html");
		return modelAndView;
	}
	
	@PostMapping("/login")
	public String logar(Login login) {
		ModelAndView modelAndView = new ModelAndView();
		Login objetoLogin = loginService.buscarLogin(login);
		return "redirect:/home";
	}
}
