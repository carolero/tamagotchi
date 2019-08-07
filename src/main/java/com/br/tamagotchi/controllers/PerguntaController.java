package com.br.tamagotchi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.tamagotchi.models.Pergunta;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.services.PerguntaService;
import com.br.tamagotchi.services.UsuarioService;

@Controller
public class PerguntaController {

	@Autowired
	private PerguntaService perguntaService;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/cadastrar/pergunta")
	public ModelAndView exibirFormularioCadastroPergunta(HttpSession session) {
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		if(usuario != null &&  usuario.isAdministrador()) {
			ModelAndView modelAndView = new ModelAndView("cadastrar-pergunta.html");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			session.setAttribute("url", "/");
			return modelAndView;
		}
	}

	@GetMapping("/pergunta/{idPergunta}")
	public ModelAndView mostrarPergunta(@PathVariable int idPergunta, HttpSession session) {
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			ModelAndView modelAndView = new ModelAndView("pergunta.html");
			if (perguntaService.verificarIdPergunta(usuario, idPergunta)) {
				ModelAndView modelAndViewRespondido = new ModelAndView("pergunta-respondida.html");
				modelAndViewRespondido.addObject("erro", "Essa pergunta já foi respondida!");
				return modelAndViewRespondido;
			}else {
				modelAndView.addObject("pergunta", perguntaService.mostrarPergunta(idPergunta));
				return modelAndView;
			}
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			session.setAttribute("url", "/");
			return modelAndView;
		}

	}

	@PostMapping("/cadastrar/pergunta")
	public ModelAndView cadastrarPergunta(@Valid Pergunta pergunta, BindingResult binPergunta, HttpSession session) {
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		if(usuario != null &&  usuario.isAdministrador()) {
			ModelAndView modelAndView = new ModelAndView("cadastrar-pergunta.html");
			if (binPergunta.hasErrors()) {
				List<String> mensagens = new ArrayList<String>();
				for (ObjectError objectError : binPergunta.getAllErrors()) {
					mensagens.add(objectError.getDefaultMessage());
				}
				modelAndView.addObject("mensagem", mensagens);
				return modelAndView;
			} else {
				modelAndView.addObject("mensagem", perguntaService.cadastrarPergunta(pergunta));
			}
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			session.setAttribute("url", "/");
			return modelAndView;
		}

	}

	@PostMapping("/pergunta/{idPergunta}")
	public ModelAndView verificarResposta(@PathVariable int idPergunta, String resposta, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("pergunta.html");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		modelAndView.addObject("pergunta", perguntaService.mostrarPergunta(idPergunta));
		modelAndView.addObject("mensagem", perguntaService.verificarResposta(idPergunta, resposta, usuario));
		return modelAndView;
	}
}
