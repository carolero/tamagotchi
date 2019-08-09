package com.br.tamagotchi.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tamagotchi.models.Pet;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.PetRepository;
import com.br.tamagotchi.repositories.UsuarioRepository;

@Service
public class AlimentarPetService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired 
	private PetRepository petRepository;
	
	public String tentarAlimentarPet(Usuario usuario) {
		int pontosParaAlimentar = 6;
		int diminuirFome = 2;
		Pet pet = usuario.getPet();
		if(usuario.getPontos()>= pontosParaAlimentar) {
			usuario.setPontos(usuario.getPontos()-pontosParaAlimentar);
			pet.setFome(pet.getFome()-diminuirFome);
			usuarioRepository.save(usuario);
			petRepository.save(pet);
			return "Seu pet "+ pet.getNomePet()+ " foi alimentado com sucesso!";
		}else {
			return "Voce tem " + usuario.getPontos() + ". Não é suficiente para alimentar o " + pet.getNomePet();
		}
		
	}


}
