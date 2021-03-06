package sistemadeapostas;

import easyaccept.EasyAccept;

public class Facade {
	
	private Sistema sistema = new Sistema();
	
	public static void main(String[] args) {
		args = new String[] {"sistemadeapostas.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt", "acceptance_test/us6_test.txt", "acceptance_test/us7_test.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		sistema = new Sistema();
	}
	
	public void inicializa(int caixa, double taxa) {
		sistema.inicializa(caixa, taxa);
	}
	
	public int getCaixa() {
		return sistema.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) throws IllegalArgumentException {
		return sistema.cadastraCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) throws IllegalArgumentException {
		return sistema.cadastraCenario(descricao, bonus);
	}
	
	public String exibirCenario(int cenario) {
		return sistema.exibeCenario(cenario);
	}
	
	public String exibirCenarios() throws Exception {
		return sistema.exibeCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) throws Exception {
		sistema.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) throws Exception {
		return sistema.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorAssegurado, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) throws Exception {
		return sistema.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return sistema.alterarSeguroTaxa(cenario, apostaAssegurada, valor);
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		return sistema.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
	}
	
	public int valorTotalDeApostas(int cenario) throws Exception {
		return sistema.valorTotalDeApostas(cenario);
	}
	
	public int totalDeApostas(int cenario) throws Exception {
		return sistema.totalDeApostas(cenario);
	}
	
	public String exibeApostas(int cenario) throws Exception {
		return sistema.exibeApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) throws Exception {
		sistema.fecharAposta(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) throws Exception {
		return sistema.getCaixaCenario(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) throws Exception {
		return sistema.getTotalRateioCenario(cenario);
	}
	
	public void alterarOrdem(String ordem) throws Exception {
		sistema.alteraOrdem(ordem);
	}
	
	public String exibirCenarioOrdenado(int cenario) throws Exception {
		return sistema.exibirCenarioOrdenado(cenario);
	}

}
