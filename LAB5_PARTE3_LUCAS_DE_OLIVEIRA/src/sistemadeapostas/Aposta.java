package sistemadeapostas;

import java.text.DecimalFormat;

/**
 * Representao uma aposta. Toda aposta contem o nome do seu apostador, seu valor, previsao. Apostas podem possuir seguro
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 *
 */

public class Aposta {
	
	private final String ocorreu = "VAI ACONTECER";
	private final String nOcorreu = "N VAI ACONTECER";
	private String apostador, previsao;
	private int valor;
	private Seguro seguro;
	
	
	/**
	 * Constroi uma aposta pelo apostador, valor e previsao da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos da aposta
	 * @param previsao previsao da aposta (se vao ou nao acontecer)
	 * @throws Exception 
	 */
	
	public Aposta(String apostador, int valor, String previsao) throws Exception {
		this.validaAposta(apostador, valor, previsao, "no cadastro de aposta");
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SemSeguro();

	}
	
	/**
	 * Cadastra uma Aposta a partir da posicao do cenario, nome do apostador, valor, previsao, valor assegurado e custo da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos aposta
	 * @param previsao previsao da aposta, pode ser "N VAI ACONTECER" ou "VAI ACONTECER
	 * @param valorSeguro valor assegurado pelo apostador
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public Aposta(String apostador, int valor, String previsao, int valorSeguro) throws Exception {
		this.validaAposta(apostador, valor, previsao, "no cadastro de aposta assegurada por valor");
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroValor(valorSeguro);
	}
	
	/**
	 * Cadastra uma Aposta a partir da posicao do cenario, nome do apostador, valor, previsao, taxa assegurada e custo da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos aposta
	 * @param previsao previsao da aposta, pode ser "N VAI ACONTECER" ou "VAI ACONTECER
	 * @param taxaSeguro taxa assegurada pelo apostador
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public Aposta(String apostador, int valor, String previsao, double taxaSeguro) throws Exception {
		this.validaAposta(apostador, valor, previsao, "no cadastro de aposta assegurada por taxa");
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroTaxa(this.valor, taxaSeguro);
	}
	
	/**
	 * Altera o seguro para seguroValor
	 * @param valor novo valor do seguro
	 */
	
	public void setSeguro(int valor) {
		this.seguro = new SeguroValor(valor);
	}
	
	/**
	 * Altera o seguro para seguroTaxa
	 * @param taxa nova taxa do seguro
	 */
	
	public void setSeguro(double taxa) {
		this.seguro = new SeguroTaxa(this.valor, taxa);
	}
	
	/**
	 * Retorna representacao em string do valor em reais da aposta
	 * @return string do valor em reais da aposta
	 */
	
	private String getValorReais() {
		double valor = this.valor / 100.0;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String temp = df.format(valor);
		temp = temp.replace(".",",");
		
		return "R$" + temp;
	}
	
	/**
	 * Retorna o valor da aposta em centavos
	 * @return (inteiro) valor em centavos
	 */
	
	public int getValor() {
		return this.valor;
	}

	/**
	 * Retorna representacao de uma aposta no formato:
	 * APOSTADOR + VALOR EM REAIS + PREVISAO + SEGURO (Se possuir)
	 */

	@Override
	public String toString() {
		return this.apostador + " - " + this.getValorReais() + " - " + this.previsao + this.seguro.toString();
	}
	
	/**
	 * Retorna true se a previsao eh "VAI ACONTECER", e false se eh "N VAI ACONTECER"
	 * @return true se a previsao eh "VAI ACONTECER", e false se eh "N VAI ACONTECER"
	 */
	
	public boolean ocorreu() {
		if (previsao.equals(this.ocorreu)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Valida os parametros de criacao de uma aposta
	 * @param apostador nome do apostador (nao deve ser vazio ou nulo)
	 * @param valor valor da apostra (nao deve ser menor ou igual a zero)
	 * @param previsao previsao da aposta (nao deve ser vazia ou nula)
	 * @param msg mensagem de erro
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	private void validaAposta(String apostador, int valor, String previsao ,String msg) throws Exception {
		if (apostador.trim().isEmpty() || apostador.equals(null))
			throw new IllegalArgumentException("Erro "+ msg + ": Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().isEmpty() || previsao.equals(null))
			throw new IllegalArgumentException("Erro "+ msg + ": Previsao nao pode ser vazia ou nula");
		if (!previsao.equals(ocorreu) && !previsao.equals(nOcorreu))
			throw new IllegalArgumentException("Erro "+ msg + ": Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro "+ msg + ": Valor nao pode ser menor ou igual a zero");
	}
	
	

}
