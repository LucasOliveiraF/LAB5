package sistemadeapostas;

public class SeguroTaxa extends Seguro {

	private int valorAposta;
	private double taxa;
	
	public SeguroTaxa(int valorAposta, double taxa) {
		this.valorAposta = valorAposta;
		this.taxa = taxa;
	}
	
	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
	}

	@Override
	public int getValorSeguro() {
		return (int) (this.valorAposta * this.taxa);
	}

}
