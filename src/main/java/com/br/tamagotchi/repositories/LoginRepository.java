package com.br.tamagotchi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.tamagotchi.models.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

	public Optional<Login> findByApelidoAndSenha(String apelido, String senha);
}


