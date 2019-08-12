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
	
	public Pet buscarPorId (int id) {
		return petRepository.findById(id).get();
	}
	
	public String cadastrarPet(Usuario usuario, Login login, Pet pet) {
		pet.setUsuario(usuario);
		petRepository.save(pet);
		return "Seu pet foi adicionado";
	}
	
	public String tentarEvoluir(int id) {
		Pet pet = petRepository.findById(id).get();
		String mensagem;
		if(pet.getXpPet() == 100) {
			pet.setXpPet(0);
			pet.setEvoluido(true);
			petRepository.save(pet);
			mensagem = "Pet evoluido";
		} else {
			mensagem = "Sua experiência está baixa demais para evoluir";
		}

		return mensagem;
	}
	
	public void aumentarFomeDoPet(Pet pet) {
		int pontosDeFome = 3;
		pet.setFome(pet.getFome() + pontosDeFome);
		petRepository.save(pet);
	}
	
	public void aumetarXp(Pet pet) {
		int xpGanha = 50;
		pet.setXpPet(pet.getXpPet() + xpGanha);
		petRepository.save(pet);
	}
	
}
