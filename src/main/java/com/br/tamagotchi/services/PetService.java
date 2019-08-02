package com.br.tamagotchi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tamagotchi.models.Login;
import com.br.tamagotchi.models.Pet;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	public String cadastrarPet(Usuario usuario, Login login, Pet pet) {
		pet.setUsuario(usuario);
		petRepository.save(pet);
		return "Seu pet foi adicionado";
	}

}
