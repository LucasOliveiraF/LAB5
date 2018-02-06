package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTest {

	private Cenario cenario;
	private final String NL = System.lineSeparator();
	
	@Before
	public void Before() {
		cenario = new Cenario("Nenhum aluno reprovou P2");
		cenario.cadastraAposta("Lucas", 20000, "N VAI ACONTECER");
		cenario.cadastraAposta("Anonimo", 199, "N VAI ACONTECER");
		cenario.cadastraAposta("Matheus", 10000, "VAI ACONTECER");
		cenario.cadastraAposta("Livia", 30000, "VAI ACONTECER");
		cenario.cadastraAposta("Raquel", 20000, "VAI ACONTECER");
		cenario.cadastraAposta("Matheus", 10000, "VAI ACONTECER");
	}
	
	@Test
	public void cenarioTest() throws Exception {
		assertEquals("Nenhum aluno reprovou P2 - Nao finalizado", cenario.toString());
		assertEquals(90199, cenario.valorTotalDeApostas());
		assertEquals(6, cenario.totalDeApostas());
		assertFalse(cenario.estaFinalizado());
		assertEquals("Lucas - R$200.0 - N VAI ACONTECER" + NL
				+ "Anonimo - R$1.99 - N VAI ACONTECER" + NL
				+ "Matheus - R$100.0 - VAI ACONTECER" + NL
				+ "Livia - R$300.0 - VAI ACONTECER" + NL
				+ "Raquel - R$200.0 - VAI ACONTECER" + NL
				+ "Matheus - R$100.0 - VAI ACONTECER", cenario.exibeApostas());
	}
	
	@Test(expected=Exception.class)
	public void descricaoVazia() throws Exception {
		Cenario cenario = new Cenario("  ");
	}
	
	@Test(expected=Exception.class)
	public void descricaoNula() throws Exception {
		Cenario cenario = new Cenario(null);
	}
	
	@Test(expected=Exception.class)
	public void nenhumaApostaTest() throws Exception {
		Cenario cenario = new Cenario("Cenario");
		cenario.exibeApostas();
	}
	
	@Test
	public void cenarioAconteceuTest() throws Exception {
		cenario.fecharAposta(true);
		assertTrue(cenario.estaFinalizado());
		assertEquals(20199, cenario.getValorPerdeu());
	}
	
	@Test
	public void cenarioNAconteceuTest() throws Exception {
		cenario.fecharAposta(false);
		assertTrue(cenario.estaFinalizado());
		assertEquals(70000, cenario.getValorPerdeu());
	}

}
