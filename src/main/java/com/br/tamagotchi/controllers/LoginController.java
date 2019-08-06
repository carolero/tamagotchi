package com.br.tamagotchi.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.br.tamagotchi.models.Login;
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
	public String logar(Login login, HttpSession session) {
		ModelAndView modelAndView = null;
		if(session.getAttribute("login") != null) {
			modelAndView = new ModelAndView("redirect" + session.getAttribute("login"));
		}else {
			modelAndView = new ModelAndView("login.html");
		}

		Login objetoLogin = loginService.buscarLogin(login);
		if ( objetoLogin != null) {
			session.setAttribute("usuario", loginService.buscarLogin(login));
			String mensagemDeSaudacao = "Olá, seja bem vindo " + objetoLogin.getApelido(); 
			modelAndView.addObject("mensagem", mensagemDeSaudacao);
		}else {
			String naoEncontradoLogin =  " Não foi encontrado o seu login. Tente novamente ";
			modelAndView.addObject("mensagem", naoEncontradoLogin);
		}

		return "redirect:/pet";
	}
}
