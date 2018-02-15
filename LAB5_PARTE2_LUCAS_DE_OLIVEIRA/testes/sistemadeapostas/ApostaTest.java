package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApostaTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void apostadorVazioTest() throws Exception {
		Aposta aposta = new Aposta("  ", 1099, "N VAI ACONTECER");
	}
	
	@Test(expected=NullPointerException.class)
	public void apostadorNuloTest() throws Exception {
		Aposta aposta = new Aposta(null, 1099, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void valorZeroTest() throws Exception {
		Aposta aposta = new Aposta("Lucas", 0, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void valorNegatico() throws Exception {
		Aposta aposta = new Aposta("Lucas", -1, "N VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void previsaoVazia() throws Exception {
		Aposta aposta = new Aposta("Lucas", 1099, "  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void previsaoNula() throws Exception {
		Aposta aposta = new Aposta("Lucas", 1099, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void previsaoAleatoria() throws Exception {
		Aposta aposta = new Aposta("Lucas", 1099, "Aleatoria");
	}

	@Test
	public void apostaSemSeguroTest() throws Exception {
		Aposta aposta = new Aposta("Lucas", 1099, "N VAI ACONTECER");
		assertEquals("Lucas - R$10,99 - N VAI ACONTECER", aposta.toString());
		assertEquals(1099, aposta.getValor());
		assertFalse(aposta.ocorreu());
	}
	
	@Test
	public void apostaSeguroValor() throws Exception {
		Aposta aposta = new Aposta("Lucas", 1099, "VAI ACONTECER", 100);
		assertEquals(1099, aposta.getValor());
		assertEquals(true, aposta.ocorreu());
		assertEquals("Lucas - R$10,99 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 1,00", aposta.toString());
		aposta.setSeguro(0.1);
		assertEquals("Lucas - R$10,99 - VAI ACONTECER - ASSEGURADA (TAXA) - 10%", aposta.toString());
	}

}
