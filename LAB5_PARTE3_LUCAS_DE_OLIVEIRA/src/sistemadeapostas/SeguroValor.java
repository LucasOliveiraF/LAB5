package sistemadeapostas;

import java.text.DecimalFormat;

/**
 * Classe que representa um seguro por valor
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class SeguroValor extends Seguro {

	private int valor;
	
	/**
	 * Constroi um seguro por valor
	 * @param valor valor assegurado pelo apostador
	 */
	
	public SeguroValor(int valor) {
		this.valor = valor;
	}

	/**
	 * Representacao textual de um seguro por valor no formato:
	 * TIPO + VALOR DO SEGURO
	 */
	
	@Override
	public String toString() {
		
		double valor = this.valor / 100.0;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String temp = df.format(valor);
		temp = temp.replace(".",",");
		
		return " - ASSEGURADA (VALOR) - R$ " + temp;
	}

	/**
	 * Retorna o valor do seguro
	 */
	
	@Override
	public int getValorSeguro() {
		return this.valor;
	}

}
