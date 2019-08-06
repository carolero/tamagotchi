package com.br.tamagotchi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.tamagotchi.models.Login;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.services.PetService;

@Controller
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@GetMapping("/pet")
	public ModelAndView exibirFormulario(HttpSession session) {
		if(session.getAttribute("usuario") != null) {
			ModelAndView modelAndView = new ModelAndView("pagina-pet.html");
			Login login = (Login) session.getAttribute("login");
			String mensagem = "Olá " + login.getUsuario().getNome();
			modelAndView.addObject("mensagem", mensagem);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			session.setAttribute("url", "/pet");
			return modelAndView;
		}
	}

}
