package com.br.tamagotchi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.tamagotchi.services.AlimentarPetService;
import com.br.tamagotchi.models.Pet;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.PetRepository;
import com.br.tamagotchi.services.PerguntaService;
import com.br.tamagotchi.services.PetService;
import com.br.tamagotchi.services.UsuarioService;

@Controller
public class PetController {
	@Autowired
	private AlimentarPetService AlimentarPetService;
	@Autowired
	private PerguntaService perguntaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PetService petService; 
	@Autowired
	private PetRepository petRepository;
	
	@GetMapping("/")
	public ModelAndView exibirPaginaPet(HttpSession session) {
		if(session.getAttribute("usuario") != null) {
			ModelAndView modelAndView = new ModelAndView("pagina-pet.html");
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			String mensagem = "Olá " + usuario.getNome();
			modelAndView.addObject("mensagem", mensagem);
			modelAndView.addObject("usuario", usuarioService.buscarPorId(usuario.getId()));
			modelAndView.addObject("pet", petService.buscarPorId(usuario.getPet().getId()));
			modelAndView.addObject("idPergunta", perguntaService.sortearPergunta());
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			session.setAttribute("url", "/");
			return modelAndView;
		}
	}
	
	@GetMapping("/alimentar")
	public ModelAndView tentarAlimentarPet(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		AlimentarPetService.tentarAlimentarPet(usuario);
		return modelAndView;
	}

	@GetMapping("/evoluir")
	public ModelAndView evoluir(HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Pet pet = (Pet) usuario.getPet();
		petService.tentarEvoluir(pet.getId());
		pet.setImagemPet("https://i.imgur.com/9ftAP58.png");
		pet.setLevelPet(1);
		petRepository.save(pet);
		return model;
		
	}
}
