package sistemadeapostas;

import java.util.ArrayList;

/**
 * Representa um cenario. Todo cenario possui sua descricao (passada na inicializacao), estado (se inicia como nao finalizado) e uma colecao de apostas (posteriormente cadastradas).
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class Cenario {
	
	protected String descricao, estado;
	protected int identificacao;
	protected ArrayList<Aposta> apostas;
	private final String NL = System.lineSeparator();
	private final String ocorreu = "Finalizado (ocorreu)";
	private final String nOcorreu = "Finalizado (n ocorreu)";
	protected boolean pagouCaixa = false;
	
	/**
	 * Constroi um cenario a partir de sua descricao e identificacao. Todo cenario eh inicializado com o estado "Nao finalizado"
	 * 
	 * @param identificacao posicao do cenario na colecao no momento de seu cadastro
	 * @param descricao descricao do cenario
	 * @throws IllegalArgumentException lanca uma excecao caso alguns dos parametros seja invalido
	 */
	
	public Cenario(int identificacao, String descricao) throws IllegalArgumentException {
		
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		
		this.identificacao = identificacao;
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostas = new ArrayList<>();
	}
	
	/**
	 * Representacao textual de um cenario no formato:
	 * DESCRICAO + ESTADO
	 */
	
	@Override
	public String toString() {
		return this.descricao + " - " + this.estado;
	}
	
	/**
	 * Cadastra uma aposta a partir do nome do apostador, seu valor e previsao
	 * @param apostador nome do apostador
	 * @param valor valor em centavos da aposta
	 * @param previsao previsao da aposta
	 * @throws Exception lanca uma excecao caso alguns dos parametros seja invalido
	 */
	
	public void cadastraAposta(String apostador, int valor, String previsao) throws Exception {
		Aposta temp = new Aposta(apostador, valor, previsao);
		this.apostas.add(temp);
	}
	
	/**
	 * Cadastra uma Aposta a partir da posicao do cenario, nome do apostador, valor, previsao, valor assegurado e custo da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos aposta
	 * @param previsao previsao da aposta, pode ser "N VAI ACONTECER" ou "VAI ACONTECER
	 * @param valorAssegurado valor assegurado pelo apostador
	 * @return retorna a posicao da aposta na colecao
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public int cadastrarApostaSeguraValor(String apostador, int valor, String previsao, int valorAssegurado) throws Exception {
		Aposta temp = new Aposta(apostador, valor, previsao, valorAssegurado);
		this.apostas.add(temp);
		
		return apostas.size();
	}
	
	/**
	 * Cadastra uma Aposta a partir da posicao do cenario, nome do apostador, valor, previsao, taxa assegurada e custo da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos aposta
	 * @param previsao previsao da aposta, pode ser "N VAI ACONTECER" ou "VAI ACONTECER
	 * @param taxa taxa assegurada pelo apostador
	 * @return retorna a posicao da aposta na colecao
	 * @throws Exception lanca uma excecao caso algum parametro seja invalido
	 */
	
	public int cadastrarApostaSeguraTaxa(String apostador, int valor, String previsao, double taxa) throws Exception {
		Aposta temp = new Aposta(apostador, valor, previsao, taxa);
		this.apostas.add(temp);
		
		return apostas.size();
	}
	
	/**
	 * Altera o seguro de uma aposta assegurada por taxa para assegurada por valor
	 * @param apostaAssegurada posicao da aposta na colecao
	 * @param valor novo valor assegurado pelo apostador
	 * @return retorna a posicao da aposta na colecao
	 */
	
	public int alterarSeguroValor(int apostaAssegurada, int valor) {
		this.apostas.get(apostaAssegurada-1).setSeguro(valor);
		return apostaAssegurada;
	}
	
	/**
	 * Altera o seguro de uma aposta assegurada por valor para assegurada por taxa
	 * @param apostaAssegurada posicao da aposta na colecao
	 * @param taxa nova taxa assegurada pelo apostador
	 * @return retorna a posicao da aposta na colecao
	 */
	
	public int alterarSeguroTaxa(int apostaAssegurada, double taxa) {
		this.apostas.get(apostaAssegurada-1).setSeguro(taxa);
		return apostaAssegurada;
	}
	
	/**
	 * Retorna a soma dos valores de todas as apostas cadastradas (em centavos)
	 * @return valor inteiro da soma dos valores das apostas
	 */
	
	public int valorTotalDeApostas() {
		
		int valor = 0;
		
		for (Aposta aposta : apostas) {
			valor += aposta.getValor();
		}
		
		return valor;
	}
	
	/**
	 * Retorna o numero de apostas cadastradas
	 * @return numero de apostas cadastradas
	 */
	
	public int totalDeApostas() {
		return this.apostas.size();
	}
	
	/**
	 * Representacao textual das apostas cadastradas no cenario
	 * @return string das apostas cadastradas no cenario
	 * @throws Exception lanca excecao caso nao exista apostas cadastradas
	 */
	
	public String exibeApostas() throws Exception {
		
		if (apostas.size() == 0) {
			throw new Exception("Erro ao consultar apostas: Nenhuma aposta cadastrada");
		}
		
		String retorno = "";
		
		for (Aposta aposta : apostas) {
			retorno += aposta.toString() + NL;
		}
		
		return retorno.trim();
		
	}
	
	/**
	 * Fecha o cenario a partir do valor booleano passado, true se ocorreu, e false se nao ocorreu
	 * 
	 * @param ocorreu valor booleano recebido: true se ocorreu, e false se nao ocorreu
	 * @throws Exception lanca excecao caso o estado ja esteja finalizado
	 */
	
	public void fecharAposta(boolean ocorreu) throws Exception {
		
		if (this.estaFinalizado()) {
			throw new Exception("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		
		if (ocorreu == true) {
			this.estado = this.ocorreu;
		} else {
			this.estado = this.nOcorreu;
		}
	}
	
	/**
	 * Retorna a soma dos valores das apostas que perderam
	 * 
	 * @return soma dos valores das apostas que perderam
	 * @throws Exception lanca excecao no caso do cenario estar aberto
	 */
	
	protected int getValorPerdeu() throws Exception {
		
		int valor = 0;
		
		if (this.estado.equals(this.ocorreu)) {
			for (Aposta aposta : apostas) {
				if (!aposta.ocorreu()) {
					valor += aposta.getValor();
				}
			}
		} else if (this.estado.equals(this.nOcorreu)) {
			for (Aposta aposta : apostas) {
				if (aposta.ocorreu()) {
					valor += aposta.getValor();
				}
			}
		} else {
			throw new Exception("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		
		return valor;
	}

	
	/**
	 * Retorna o valor que devera ser adicionado no caixa do sistema
	 * @param taxa a taxa do caixa do sistema
	 * @return retorna o valor que devera ser adicionado no caixa do sistema
	 * @throws Exception lanca excecao no caso do cenario estar aberto
	 */
	
	public int getCaixaCenario(double taxa) throws Exception {
		
		double valor = Math.floor(this.getValorPerdeu() * taxa);
		return (int) valor;
	}
	
	/**
	 * Retorna o valor que sera distribuido entre os apostadores vencedores
	 * @param taxa a taxa do caixa do sistema
	 * @return valor que sera distribuido entre os apostadores vencedores
	 * @throws Exception lanca excecao no caso do cenario estar aberto
	 */
	
	public int getTotalRateioCenario(double taxa) throws Exception {
		
		int valor = this.getValorPerdeu() - this.getCaixaCenario(taxa);
		
		return valor;
	}
	
	/**
	 * Retorna se o cenario esta finalizado ou nao
	 * 
	 * @return true se esta finalizado, false se nao esta finalizado
	 */
	
	public boolean estaFinalizado() {
		if (this.estado.equals(ocorreu) || this.estado.equals(nOcorreu))
			return true;
		else
			return false;
	}
	
	/**
	 * Atualiza a variavel pagouCaixa 
	 * @param pagou muda a variavel para true
	 */
	
	public void setPagouCaixa() {
		this.pagouCaixa = true;
	}
	
	/**
	 * Retorna valor booleano informando se o cenario pagou para o caixa do sistema o dinheiro das apostas que perderam
	 * @return valor booleano informando se o cenario pagou para o caixa do sistema o dinheiro das apostas que perderam
	 */
	
	public boolean pagouCaixa() {
		return this.pagouCaixa;
	}
	
	/**
	 * Retorna a descricao do cenario
	 * @return descricao do cenario
	 */
	
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Retorna a identificacao do cenario
	 * @return identificacao do cenario
	 */
	
	public int getIdentificacao() {
		return identificacao;
	}

	
}
