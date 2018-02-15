package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTest {

	private Cenario cenario;
	private final String NL = System.lineSeparator();
	
	@Before
	public void Before() throws Exception {
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
		assertEquals("Lucas - R$200,00 - N VAI ACONTECER" + NL
				+ "Anonimo - R$1,99 - N VAI ACONTECER" + NL
				+ "Matheus - R$100,00 - VAI ACONTECER" + NL
				+ "Livia - R$300,00 - VAI ACONTECER" + NL
				+ "Raquel - R$200,00 - VAI ACONTECER" + NL
				+ "Matheus - R$100,00 - VAI ACONTECER", cenario.exibeApostas());
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
	
	@Test
	public void cenarioBonusTest() throws Exception {
		Cenario cenario = new CenarioBonus("Cenario Teste", 100);
		cenario.cadastraAposta("Apostador1", 1000, "VAI ACONTECER");
		cenario.cadastrarApostaSeguraTaxa("Apostador2", 900, "N VAI ACONTECER", 0.1);
		cenario.cadastrarApostaSeguraValor("Apostador3", 2000, "VAI ACONTECER", 300);
		assertEquals(false, cenario.estaFinalizado());
		assertEquals("Cenario Teste - Nao finalizado - R$ 1,00", cenario.toString());
		assertEquals("Apostador1 - R$10,00 - VAI ACONTECER" + NL
				+ "Apostador2 - R$9,00 - N VAI ACONTECER - ASSEGURADA (TAXA) - 10%" + NL
				+ "Apostador3 - R$20,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 3,00", cenario.exibeApostas());
		assertEquals(false, cenario.pagouCaixa());
		assertEquals(false, cenario.estaFinalizado());
		cenario.fecharAposta(true);
		assertEquals(true, cenario.estaFinalizado());
		assertEquals("Cenario Teste - Finalizado (ocorreu) - R$ 1,00", cenario.toString());
		assertEquals(9, cenario.getCaixaCenario(0.01));
		assertEquals(991, cenario.getTotalRateioCenario(0.01));
		//System.out.println(cenario.getValorSeguros());
	}

}
