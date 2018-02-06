package sistemadeapostas;

/**
 * Representao uma aposta. Toda aposta contem o nome do seu apostador, seu valor e previsao
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
	//private boolean previsaoBooleana;
	
	/**
	 * Constroi uma aposta pelo apostador, valor e previsao da aposta
	 * @param apostador nome do apostador
	 * @param valor valor em centavos da aposta
	 * @param previsao previsao da aposta (se vao ou nao acontecer)
	 * @throws IllegalArgumentException lanca uma excecao caso alguns dos parametros seja invalido
	 */
	
	public Aposta(String apostador, int valor, String previsao) throws IllegalArgumentException {
		
		if (apostador.trim().isEmpty() || apostador.equals(null))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().isEmpty() || previsao.equals(null))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		if (!previsao.equals(ocorreu) && !previsao.equals(nOcorreu))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		//this.previsaoBooleana = this.previsaoBooleana(previsao);
	}
	
	/**
	 * Retorna representacao em string do valor em reais da aposta
	 * @return string do valor em reais da aposta
	 */
	
	private String getValorReais() {
		double valor = this.valor / 100.0;
		return "R$" + Double.toString(valor);
	}
	
	/**
	 * Retorna o valor da aposta em centavos
	 * @return (inteiro) valor em centavos
	 */
	
	public int getValor() {
		return valor;
	}
	
	/**
	 * Retorna representacao de uma aposta no formato:
	 * APOSTADOR + VALOR EM REAIS + PREVISAO
	 */

	@Override
	public String toString() {
		return this.apostador + " - " + this.getValorReais() + " - " + this.previsao;
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

}
