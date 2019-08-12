package com.br.tamagotchi.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PetTeste {
	private Pet pet;
	
	@Before
	public void preparar() {
		pet = new Pet();
		
		pet.setId(null);
		pet.setNomePet("Marrom Bombom");
		pet.setImagemPet("/img/nivel1.png");
		pet.setFome(50);
		pet.setXpPet(100);
		pet.setLevelPet(0);
	}
	
	@Test
	public void testarGetNomePet() {
		assertEquals("Marrom Bombom", pet.getNomePet());
	}
	
	@Test
	public void testarGetFome() {
		assertEquals(50, pet.getFome());
	}
	
	@Test
	public void testarTentarEvoluir() {
		pet.tentarEvoluir();
		assertEquals(true, pet.isEvoluido());
	}
	
	
}
