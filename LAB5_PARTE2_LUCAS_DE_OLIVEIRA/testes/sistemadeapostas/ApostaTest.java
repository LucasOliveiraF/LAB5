package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApostaTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void apostadorVazioTest() {
		Aposta aposta = new Aposta("  ", 1099, "N VAI ACONTECER");
	}
	
	@Test(expected=NullPointerException.class)
	public void apostadorNuloTest() {
		Aposta aposta = new Aposta(null, 1099, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void valorZeroTest() {
		Aposta aposta = new Aposta("Lucas", 0, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void valorNegatico() {
		Aposta aposta = new Aposta("Lucas", -1, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void previsaoVazia() {
		Aposta aposta = new Aposta("Lucas", 1099, "  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void previsaoNula() {
		Aposta aposta = new Aposta("Lucas", 1099, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void previsaoAleatoria() {
		Aposta aposta = new Aposta("Lucas", 1099, "Aleatoria");
	}

	@Test
	public void apostaTest() {
		Aposta aposta = new Aposta("Lucas", 1099, "N VAI ACONTECER");
		assertEquals("Lucas - R$10.99 - N VAI ACONTECER", aposta.toString());
		assertEquals(1099, aposta.getValor());
		assertFalse(aposta.ocorreu());
	}

}
