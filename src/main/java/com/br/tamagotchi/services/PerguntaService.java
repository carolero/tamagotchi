package com.br.tamagotchi.services;

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
	
	public Integer sortearPergunta() {
		Random random = new Random();
		try {
			int pergunta = random.nextInt((int) perguntaRepository.count())+1;
			return pergunta;
		}
		catch (Exception e) {
			return null;
		}
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
	
	public boolean verificarIdPergunta(Usuario usuario, Integer idPergunta) {
		Usuario obj = usuarioRepository.findById(usuario.getId()).get();
		return obj.getPerguntasRespondidas().contains(idPergunta);
	}
	
	public String verificarResposta(int idPergunta, String resposta, Usuario usuario) {
		Pergunta pergunta = perguntaRepository.findById(idPergunta).get();
		if(pergunta.getResposta().equalsIgnoreCase(resposta)) {
			int pontosGanhos = gerarPontuacao();
			Usuario user = usuarioRepository.findById(usuario.getId()).get();
			user.getPerguntasRespondidas().add(idPergunta);
			user.setPontos(usuario.getPontos() + pontosGanhos);
			usuarioRepository.save(user);
			return "Parabéns! Está certo :)" + pontosGanhos;
		} else {
			return "Errou! Que pena :(";
		}
	}

}
