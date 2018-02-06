package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class CenarioBonusTest {

	private Cenario teste = new CenarioBonus("Teste de cenario", 1000);
	
	@Test
	public void test() {
		System.out.println(teste.toString());
	}

}
