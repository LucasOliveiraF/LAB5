package sistemadeapostas;

import java.util.ArrayList;

/**
 * Representa do controlador do sistema de apostas, contem o caixa e a taxa do sistema e os metodos que manipulam cenarios e apostas.
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class Sistema {
	
	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	private final String NL = System.lineSeparator();
	
	/**
	 * Inicializa o sistema apartir do valor em centavos do caixa e da taxa do sistema
	 * @param caixa o valor em centavos do caixa
	 * @param taxa taxa do sistema de apostas
	 * @throws IllegalArgumentException lanca uma excecao caso algum parametro seja invalido
	 */
	
	public void inicializa(int caixa, double taxa) throws IllegalArgumentException {
		if (caixa < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		if (taxa <= 0)
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new ArrayList<>();
	}
	
	/**
	 * Retorna o valor em centavos do caixa
	 * @return valor em centavos do caixa
	 */
	
	public int getCaixa() {
		return this.caixa;
	}
	
	/**
	 * Cadastra um cenario a partir de sua descricao.
	 * @param descricao descricao do cenario
	 * @return retorna a posicao do cenario na colecao
	 * @throws IllegalArgumentException lanca uma excecao caso a descricao do cenario seja invalida
	 */
	
	public int cadastraCenario(String descricao) throws IllegalArgumentException {
		
		Cenario temp = new Cenario(descricao);
		this.cenarios.add(temp);
		
		return this.cenarios.size();
	}
	
	/**
	 * Cadastra um cenario com bonus a apartir de sua descricao e bonus
	 * @param descricao descricao do cenario
	 * @param bonus bonus do cenario
	 * @return retorna a posicao do cenario na colecao
	 * @throws IllegalArgumentException lanca uma excecao caso a descricao do cenario ou bonus sejam invalidos
	 */
	
	public int cadastraCenario(String descricao, int bonus) throws IllegalArgumentException {
		
		CenarioBonus temp = new CenarioBonus(descricao, bonus);
		this.cenarios.add(temp);
		
		this.caixa = this.caixa - bonus;
		
		return this.cenarios.size();
	}
	
	/**
	 * Exibe a representacao textual de um cenario a partir de sua posicao
	 * @param cenario posicao do cenario na colecao
	 * @return retorna a representacao textual do cenario
	 * @throws IndexOutOfBoundsException lanca uma excecao caso a posicao seja invalida
	 */
	
	public String exibeCenario(int cenario) throws IndexOutOfBoundsException {
		validaCenario(cenario, "na consulta de cenario");
		return cenario + " - " + cenarios.get(cenario - 1).toString();
	}
	
	/**
	 * Exibe todos os cenarios cadastrados na colecao
	 * @return retorna a representacao textual de todos os cenarios cadastrados (um por linha)
	 */
	
	public String exibeCenarios() {
		
		String retorno;
		
		if (cenarios.size() == 0)
			return "Nenhum cenario cadastrado!";
		
		retorno = "";
		
		for (int i = 0; i < cenarios.size(); i++) {
			retorno += (i + 1) + " - " + cenarios.get(i).toString() + NL;
		}
		
		return retorno.trim();
	}
	
	/**
	 * Cadastra uma Aposta a partir da posicao do cenario, nome do apostador, valor e previsao da aposta
	 * @param cenario posicao do cenario na colecao
	 * @param apostador nome do apostador
	 * @param valor valor em centavos aposta
	 * @param previsao previsao da aposta, pode ser "N VAI ACONTECER" ou "VAI ACONTECER
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) throws Exception {
		this.validaCenario(cenario, "no cadastro de aposta");
		this.cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return cenarios.get(cenario-1).cadastrarApostaSeguraValor(apostador, valor, previsao, valorAssegurado, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return cenarios.get(cenario-1).cadastrarApostaSeguraTaxa(apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return 0;
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		return 0;
	}
	
	/**
	 * Retorna a soma dos valores das apostas do cenario a partir de sua posicao
	 * @param cenario posicao do cenario na colecao
	 * @return retorna o valor inteiro da soma dos valores das apostas do cenario
	 * @throws IndexOutOfBoundsException lanca uma excecao caso a posicao do cenario seja invalida
	 */
	
	public int valorTotalDeApostas(int cenario) throws IndexOutOfBoundsException {
		this.validaCenario(cenario, "na consulta do valor total de apostas");
		return this.cenarios.get(cenario - 1).valorTotalDeApostas();
	}
	
	/**
	 * Retorna o numero total de apostas feitas no cenario a partir de sua posicao
	 * @param cenario posicao do cenario na colecao
	 * @return numero total de apostas feitas no cenario
	 */
	
	public int totalDeApostas(int cenario) {
		this.validaCenario(cenario, "na consulta do total de apostas");
		return this.cenarios.get(cenario - 1).totalDeApostas();
	}
	
	/**
	 * Retorna a representacao textual das apostas cadastradas no cenario a partir de sua posicao (uma por linha)
	 * @param cenario posicao do cenario na colecao
	 * @return a representacao textual das apostas cadastradas no cenario
	 * @throws Exception lanca uma excecao caso a posicao do cenario seja invalida
	 */
	
	public String exibeApostas(int cenario) throws Exception {
		validaCenario(cenario, "na consulta de aposta");
		return this.cenarios.get(cenario - 1).exibeApostas();
	}
	
	/**
	 * Fecha uma cenario com o valores "Finalizado (ocorreu)" ou "Finalizado (n ocorreu)"
	 * @param cenario posicao do cenario na colecao
	 * @param ocorreu valor booleano: true se ocorreu, false se nao ocorreu
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public void fecharAposta(int cenario, boolean ocorreu) throws Exception {
		validaCenario(cenario, "ao fechar aposta");
		this.cenarios.get(cenario - 1).fecharAposta(ocorreu);
	}
	
	/**
	 * Retorna e adiciona a quantidade que sera adicionada no caixa do sistema
	 * @param cenario posicao do cenario
	 * @return Retorna a quantidade que sera adicionada no caixa do sistema
	 * @throws Exception Exception lanca uma excecao caso a posicao do cenario seja invalida
	 */
	
	public int getCaixaCenario(int cenario) throws Exception {
		this.validaCenario(cenario, "na consulta do caixa do cenario");
		this.validaCenarioFechado(cenario, "na consulta do caixa do cenario");
		
		if (!this.cenarios.get(cenario - 1).pagouCaixa())
			this.caixa += this.cenarios.get(cenario - 1).getCaixaCenario(this.taxa);
			this.cenarios.get(cenario - 1).setPagouCaixa();
		
		return this.cenarios.get(cenario - 1).getCaixaCenario(this.taxa);
	}
	
	/**
	 * Retorna o valor que sera distribuido entre os apostadores vencedores
	 * @param cenario posicao do cenario na colecao
	 * @return valor que sera distribuido entre os apostadores vencedores
	 * @throws Exception lanca uma excecao caso a posicao do cenario seja invalida
	 */
	
	public int getTotalRateioCenario(int cenario) throws Exception {
		this.validaCenario(cenario, "na consulta do total de rateio do cenario");
		this.validaCenarioFechado(cenario, "na consulta do total de rateio do cenario");
		
		return this.cenarios.get(cenario - 1).getTotalRateioCenario(this.taxa);
	}
	
	/**
	 * Testa se a posicao do cenario eh valida
	 * @param cenario posicao do cenario na colecao
	 * @param msg mensagem que contem o metodo que esta sendo testado
	 * @throws IndexOutOfBoundsException lanca uma excecao caso a posicao do cenario seja invalida
	 */
	
	public void validaCenario(int cenario, String msg) throws IndexOutOfBoundsException {
		if (cenario <= 0)
			throw new IndexOutOfBoundsException("Erro " + msg + ": Cenario invalido");
		if (cenario > cenarios.size())
			throw new IndexOutOfBoundsException("Erro " + msg + ": Cenario nao cadastrado");
	}
	
	/**
	 * Testa se o cenario esta aberto ou fechado
	 * @param cenario posicao do cenario na colecao
	 * @param msg mensagem que contem o metodo que esta sento testado
	 * @throws Exception lanca uma excecao caso o cenario esteja aberto
	 */
	
	public void validaCenarioFechado(int cenario, String msg) throws Exception {
		if (!this.cenarios.get(cenario - 1).estaFinalizado())
			throw new Exception("Erro " + msg + ": Cenario ainda esta aberto");
	}
	
}
