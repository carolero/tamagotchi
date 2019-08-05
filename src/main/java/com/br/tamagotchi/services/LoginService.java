package com.br.tamagotchi.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tamagotchi.models.Login;
import com.br.tamagotchi.models.Pet;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepo;

	public String cadastrarLogin(Usuario user, Login login, @Valid Pet pet) {
		login.setUsuario(user);
		loginRepo.save(login);
		return "Login cadastrado";

	}

	public Login buscarLogin(Login login) {
		return loginRepo.findByApelidoAndSenha(login.getApelido(), login.getSenha()).get();
	}
}