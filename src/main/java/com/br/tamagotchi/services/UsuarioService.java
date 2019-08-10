package com.br.tamagotchi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerguntaService perguntaService;
	
	public Usuario buscarPorId(Integer id) {
		return usuarioRepository.findById(id).get();
	}
	
	public void ganharPontosPorAcerto(Usuario usuario) {
		int pontosGanhos = perguntaService.gerarPontuacao();
		usuario.setPontos(usuario.getPontos() + pontosGanhos);
	}

}
