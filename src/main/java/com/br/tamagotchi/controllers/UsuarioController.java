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
import com.br.tamagotchi.models.Pet;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.services.LoginService;
import com.br.tamagotchi.services.PetService;
import com.br.tamagotchi.services.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PetService petService;
	
	@GetMapping("/cadastrar")
	public ModelAndView exibirFormCadastro() {
		ModelAndView modelAndView = new ModelAndView("cadastro.html");
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarUsuario(@Valid Usuario usuario, BindingResult binUsuario
			,@Valid Login login, BindingResult binLogin
			,@Valid Pet pet, BindingResult binPet) {
		ModelAndView modelAndView = new ModelAndView("cadastro.html");
		if(binUsuario.hasErrors() || binLogin.hasErrors() || binPet.hasErrors()) {
			List<String> mensagens = new ArrayList<String>();
			for (ObjectError objectError : binUsuario.getAllErrors()) {
				mensagens.add(objectError.getDefaultMessage());
			}
			for (ObjectError objectError : binLogin.getAllErrors()) {
				mensagens.add(objectError.getDefaultMessage());
			}
			for (ObjectError objectError : binPet.getAllErrors()) {
				mensagens.add(objectError.getDefaultMessage());
			}
			modelAndView.addObject("mensagens", mensagens);
			return modelAndView;
		} else {
			modelAndView.addObject("mensagens", petService.cadastrarPet(usuario, login, pet));
			modelAndView.addObject("mensagens", loginService.cadastrarLogin(usuario, login));
		}
		return modelAndView;
	}

}
