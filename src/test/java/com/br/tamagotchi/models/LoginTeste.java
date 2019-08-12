package com.br.tamagotchi.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoginTeste {
	private Login login;

	@Before
	public void testar() {
		login = new Login();

		login.setId(null);
		login.setApelido("luli");
		login.setSenha("12345678");
	}
	@Test
	public void testarGetApelido() {
		assertEquals("luli",login.getApelido());
	}
	@Test
	public void testarGetSenha() {
		assertEquals(12345678,login.getSenha());
	}
}
