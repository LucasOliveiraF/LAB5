package sistemadeapostas;

import java.text.DecimalFormat;

public class SeguroValor extends Seguro {

	private int valor;
	
	public SeguroValor(int valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		
		double valor = this.valor / 100.0;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String temp = df.format(valor);
		temp = temp.replace(".",",");
		
		return " - ASSEGURADA (VALOR) - R$ " + temp;
	}

	@Override
	public int getValorSeguro() {
		return this.valor;
	}

}
