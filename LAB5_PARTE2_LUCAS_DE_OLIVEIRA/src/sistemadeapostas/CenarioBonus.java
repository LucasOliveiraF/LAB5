package sistemadeapostas;
import java.text.DecimalFormat;

public class CenarioBonus extends Cenario {
	
	private int bonus;
	
	public CenarioBonus(String descricao, int bonus) throws IllegalArgumentException {
		super(descricao);
		
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		
		this.bonus = bonus;
	}
	
	@Override
	public String toString() {
		return super.toString() + " - " + this.getBonusReais();
	}
	
	/**
	 * Retorna o valor que sera distribuido entre os apostadores vencedores
	 * @param taxa a taxa do caixa do sistema
	 * @return valor que sera distribuido entre os apostadores vencedores
	 * @throws Exception lanca excecao no caso do cenario estar aberto
	 */
	
	public int getTotalRateioCenario(double taxa) throws Exception {
		
		int valor = super.getTotalRateioCenario(taxa) + this.bonus;
		
		return valor;
	}
	
	private String getBonusReais() {
		double bonus = this.bonus / 100.0;
		DecimalFormat df = new DecimalFormat("0.00");
		String valor = df.format(bonus);
		valor = valor.replace(".",",");
		return "R$ " + valor;
	}

	
}
