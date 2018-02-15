package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class CenarioBonusTest {

	private Cenario teste;
	
	@org.junit.Before
	public void Before() throws Exception {
		teste = new CenarioBonus("Teste de cenario", 1000);
		teste.cadastrarApostaSeguraValor("Apostador1", 100000, "VAI ACONTECER", 20000);
		teste.cadastrarApostaSeguraTaxa("Apostador2", 10000, "N VAI ACONTECER", 0.05);
	}
	
	@Test
	public void toSTringTest() {
		assertEquals("Teste de cenario - Nao finalizado - R$ 10,00", teste.toString());
	}
	
	@Test
	public void Test() throws Exception {
		teste.fecharAposta(false);
		System.out.println(teste.getCaixaCenario(0.01));
		System.out.println(teste.getTotalRateioCenario(0.01));
	}

}
