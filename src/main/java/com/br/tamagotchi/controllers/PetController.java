package com.br.tamagotchi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetController {
	
	@GetMapping("/pet")
	public ModelAndView exibirFormulario() {
		ModelAndView mv = new ModelAndView("pagina-pet");
		return mv;
	}

}