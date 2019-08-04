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
	@PostMapping("/cadastro/login")
	public ModelAndView cadastrarLogin(@Valid Usuario user, BindingResult bindingUser, @Valid Login login,
			BindingResult bindingLogin) {
		ModelAndView modelAndView = new ModelAndView("cadastro.html");
		if (bindingUser.hasErrors() || bindingLogin.hasErrors()) {
			List<String>mensagens = new ArrayList<String>();
			for (ObjectError objerro : bindingUser.getAllErrors()) {
				mensagens.add(objerro.getDefaultMessage());
			}
			for (ObjectError objerro : bindingLogin.getAllErrors()) {
				mensagens.add(objerro.getDefaultMessage());
			}
			modelAndView.addObject("mensagens", mensagens);
		} else {
			modelAndView.addObject("mensagens", loginService.cadastrarLogin(user, login, null));

		}

		return modelAndView;
	}
}