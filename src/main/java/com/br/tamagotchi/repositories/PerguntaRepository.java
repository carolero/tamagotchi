package com.br.tamagotchi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.tamagotchi.models.Pergunta;

@Repository
public interface PerguntaRepository extends CrudRepository<Pergunta, Integer> {

}
