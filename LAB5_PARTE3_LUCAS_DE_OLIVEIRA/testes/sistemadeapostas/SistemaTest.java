package sistemadeapostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class SistemaTest {

	private Sistema sistema;
	private final String NL = System.lineSeparator();
	
	@org.junit.Before
	public void Before() {
		sistema = new Sistema();
		sistema.inicializa(0, 0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void inicializaCaixaNegativa() {
		sistema.inicializa(-1, 0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void inicializaTaxaZero() {
		sistema.inicializa(0, 0.0);
	}
	
	@Test
	public void getCaixaTest() {
		sistema.inicializa(1000, 0.1);
		assertEquals(1000, sistema.getCaixa());
	}
	
	@org.junit.Before
	public void cadastraCenarioTest() {
		assertEquals(1, sistema.cadastraCenario("Cenario1"));
		assertEquals(2, sistema.cadastraCenario("Cenario2"));
		assertEquals(3, sistema.cadastraCenario("Cenario3"));
		assertEquals(4, sistema.cadastraCenario("Cenario4", 1000));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraCenarioVazio() {
		sistema.cadastraCenario("  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraCenarioNulo() {
		sistema.cadastraCenario(null);
	}
	
	@Test
	public void exibeCenarioTest() {
		assertEquals("1 - Cenario1 - Nao finalizado", sistema.exibeCenario(1));
		assertEquals("2 - Cenario2 - Nao finalizado", sistema.exibeCenario(2));
		assertEquals("3 - Cenario3 - Nao finalizado", sistema.exibeCenario(3));
		assertEquals("4 - Cenario4 - Nao finalizado - R$ 10,00", sistema.exibeCenario(4));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void exibeCenarioZero() {
		sistema.exibeCenario(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void exibeCenarioNaoCadastrado() {
		sistema.exibeCenario(5);
	}
	
	@Test
	public void exibeCenarios() {
		assertEquals("1 - Cenario1 - Nao finalizado" + NL
				+ "2 - Cenario2 - Nao finalizado" + NL
				+ "3 - Cenario3 - Nao finalizado" + NL
				+ "4 - Cenario4 - Nao finalizado - R$ 10,00", sistema.exibeCenarios());
		
		Sistema sistemavazio = new Sistema();
		sistemavazio.inicializa(0, 0.1);
		assertEquals("Nenhum cenario cadastrado!", sistemavazio.exibeCenarios());
	}
	
	@Test
	public void cadastraApostaTest() throws Exception {
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Carla", 20000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Paula", 30000, "N VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "N VAI ACONTECER");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void cadastraApostaCenarioZero() throws Exception {
		sistema.cadastrarAposta(0, "Lucas", 10000, "VAI ACONTECER");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void cadastraApostaCenarioNaoCadastrado() throws Exception {
		sistema.cadastrarAposta(5, "Lucas", 10000, "VAI ACONTECER");
	}
	
	@Test
	public void valorTotalDeApostas() throws Exception {
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Carla", 20000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Paula", 30000, "N VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "N VAI ACONTECER");
		assertEquals(80000, sistema.valorTotalDeApostas(1));
		assertEquals(0, sistema.valorTotalDeApostas(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void valorTotalDeApostasCenarioZero() {
		sistema.valorTotalDeApostas(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void valorTotalDeApostasCenarioNaoCadastrado() {
		sistema.valorTotalDeApostas(5);
	}
	
	@Test
	public void totalDeApostas() throws Exception {
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Carla", 20000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Paula", 30000, "N VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Lucas", 10000, "N VAI ACONTECER");
		assertEquals(5, sistema.totalDeApostas(1));
		assertEquals(0, sistema.totalDeApostas(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void totalDeApostasCenarioZero() {
		sistema.totalDeApostas(0);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void totalDeApostasCenarioNaoCadastrado() {
		sistema.totalDeApostas(5);
	}
	
	@Test
	public void exibeApostasTest() throws Exception {
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Carla", 20000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Paula", 30000, "N VAI ACONTECER");
		assertEquals("Lucas - R$100,00 - VAI ACONTECER" + NL
				+ "Carla - R$200,00 - VAI ACONTECER" + NL
				+ "Paula - R$300,00 - N VAI ACONTECER", sistema.exibeApostas(1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void exibeApostasCenarioZero() throws Exception {
		sistema.exibeApostas(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void exibeApostasCenarioNaoCadastrado() throws Exception {
		sistema.exibeApostas(5);
	}
	
	@Test(expected=Exception.class)
	public void exibeApostasNenhumaApostaCadastrada() throws Exception {
		sistema.exibeApostas(2);
	}
	
	@Test
	public void fecharAposta() throws Exception {
		sistema.cadastrarAposta(1, "Lucas", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Carla", 20000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Paula", 30000, "N VAI ACONTECER");
		sistema.fecharAposta(1, true);
		assertEquals(3000, sistema.getCaixaCenario(1));
		assertEquals(2000, sistema.getCaixa());
		assertEquals(3000, sistema.getCaixaCenario(1));
		assertEquals(2000, sistema.getCaixa());
		assertEquals(27000, sistema.getTotalRateioCenario(1));
	}
	
	@Test(expected=Exception.class)
	public void fecharApostaCenarioZero() throws Exception {
		sistema.fecharAposta(0, true);
	}
	
	@Test(expected=Exception.class)
	public void fecharApostaCenarioNaoCadastrado() throws Exception {
		sistema.fecharAposta(5, true);
	}
	
	@Test(expected=Exception.class)
	public void getCaixaCenarioZero() throws Exception {
		sistema.getCaixaCenario(0);
	}
	
	@Test(expected=Exception.class)
	public void getCaixaCenarioNaoCadastrado() throws Exception {
		sistema.getCaixaCenario(4);
	}
	
	@Test(expected=Exception.class)
	public void getCaixaCenarioAberto() throws Exception {
		sistema.getCaixaCenario(2);
	}
	
	@Test(expected=Exception.class)
	public void getTotalRateioCenarioZero() throws Exception {
		sistema.getTotalRateioCenario(0);
	}
	
	@Test(expected=Exception.class)
	public void getTotalRateioCenarioNaoCadastrado() throws Exception {
		sistema.getTotalRateioCenario(4);
	}
	
	@Test(expected=Exception.class)
	public void getTotalRateioCenarioAberto() throws Exception {
		sistema.getTotalRateioCenario(2);
	}
	
}
