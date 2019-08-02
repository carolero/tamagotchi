package com.br.tamagotchi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.tamagotchi.models.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

}
