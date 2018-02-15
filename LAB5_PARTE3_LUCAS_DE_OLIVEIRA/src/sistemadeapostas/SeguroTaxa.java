package sistemadeapostas;

/**
 * Classe que representa um seguro por taxa
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class SeguroTaxa extends Seguro {

	private int valorAposta;
	private double taxa;
	
	/**
	 * Constroi o seguro por taxa
	 * @param valorAposta valor da aposta
	 * @param taxa taxa do seguro
	 */
	
	public SeguroTaxa(int valorAposta, double taxa) {
		this.valorAposta = valorAposta;
		this.taxa = taxa;
	}
	
	/**
	 * Representacao textual de um seguro por taxa no formato:
	 * TIPO + VALOR (com taxa ja aplicada)
	 */
	
	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
	}

	/**
	 * Retorna o valor do seguro
	 */
	
	@Override
	public int getValorSeguro() {
		return (int) (this.valorAposta * this.taxa);
	}

}
