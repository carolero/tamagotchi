package com.br.tamagotchi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RespostaCertaController {
	
	@GetMapping("/resposta-correta")
	public ModelAndView mostrarMensagemDeAcerto() {
		ModelAndView modelAndView = new ModelAndView("acertou.html");
		return modelAndView;
	}

}
