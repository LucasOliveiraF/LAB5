package sistemadeapostas;

public class CenarioBonus extends Cenario {
	
	private int bonus;
	
	public CenarioBonus(String descricao, int bonus) throws IllegalArgumentException {
		super(descricao);
		
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		
		this.bonus = bonus;
	}
	
	/*@Override
	public int getValorPerdeu() throws Exception {
		return super.getValorPerdeu() + this.bonus;
	}*/
	
	@Override
	public String toString() {
		return super.toString() + " - " + this.getBonusReais();
	}
	
	public int getBonus() {
		return bonus;
	}
	
	private String getBonusReais() {
		double bonus = this.bonus / 100.0;
		return "R$" + Double.toString(bonus);
	}

}
