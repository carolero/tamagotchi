package com.br.tamagotchi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 4, message = "Nome precisa ter no mínimo 4 letras")
	private String nome;

	private Integer xp = 0;
	private Integer comida = 0;
	private Integer level = 0;
	private Integer pontos = 0;
	
	private boolean administrador = false;
	
	@ElementCollection
	private List<Integer> perguntasRespondidas = new ArrayList<Integer>();

	@OneToOne(mappedBy = "usuario")
	private Login login;

	@OneToOne(mappedBy = "usuario")
	private Pet pet;

	public Usuario() {

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public Integer getComida() {
		return comida;
	}

	public void setComida(Integer comida) {
		this.comida = comida;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public List<Integer> getPerguntasRespondidas() {
		return perguntasRespondidas;
	}

	public void setPerguntasRespondidas(List<Integer> perguntasRespondidas) {
		this.perguntasRespondidas = perguntasRespondidas;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	@Override
	public String toString() {
		String modelo = "OLa";
		modelo += this.nome;
		modelo += this.perguntasRespondidas;
		
		return modelo;
	}
}
