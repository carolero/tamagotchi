package com.br.tamagotchi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tamagotchi.models.Pergunta;
import com.br.tamagotchi.models.Usuario;
import com.br.tamagotchi.repositories.PerguntaRepository;
import com.br.tamagotchi.repositories.UsuarioRepository;

@Service
public class PerguntaService {
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public String cadastrarPergunta(Pergunta pergunta) {
		perguntaRepository.save(pergunta);
		return "Pergunta cadastrada";
	}
	
	public Pergunta mostrarPergunta(int id) {
		if(perguntaRepository.existsById(id)) {
			return perguntaRepository.findById(id).get();
		} else {
			return null;
		}
	}
	
	public int gerarPontuacao() {
		Random random = new Random();
		int pontos = random.nextInt(11);
		return pontos;
	}
	
	public String verificarResposta(int idPergunta, String resposta, Usuario usuario) {
		Pergunta pergunta = perguntaRepository.findById(idPergunta).get();
		if(pergunta.getResposta().equalsIgnoreCase(resposta)) {
			int pontosGanhos = gerarPontuacao();
			usuario.setPontos(usuario.getPontos() + pontosGanhos);
			usuario.getPerguntasRespondidas().add(idPergunta);
			usuarioRepository.save(usuario);
			return "Parabéns! Está certo :)";
		} else {
			return "Errou! Que pena :(";
		}
	}
	
	public void deletarPergunta(int id) {
		perguntaRepository.deleteById(id);
	}
	
	

}
