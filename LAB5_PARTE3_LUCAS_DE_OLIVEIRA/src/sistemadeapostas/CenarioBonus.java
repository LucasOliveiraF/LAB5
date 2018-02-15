package sistemadeapostas;
import java.text.DecimalFormat;

/**
 * Representa um cenario com bonus. Todo cenario com bonus possui sua descricao (passada na inicializacao), estado (se inicia como nao finalizado), uma colecao de apostas (posteriormente cadastradas) e o bonus (retirado do caixa do sistema na criacao do cenario).
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class CenarioBonus extends Cenario {
	
	private int bonus;
	
	/**
	 * Constroi um cenario a partir de sua descricao e bonus. Todo cenario eh inicializado com o estado "Nao finalizado"
	 * 
	 * @param descricao descricao do cenario
	 * @param bonus bonus do cenario a ser repassado para os ganhadores
	 * @throws IllegalArgumentException lanca uma excecao caso alguns dos parametros seja invalido
	 */
	
	public CenarioBonus(String descricao, int bonus) throws IllegalArgumentException {
		super(descricao);
		
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		
		this.bonus = bonus;
	}
	
	/**
	 * Representacao textual de um cenario no formato:
	 * DESCRICAO + ESTADO + BONUS (EM REAIS)
	 */
	
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
	
	/**
	 * Retorna o valor do bonus em reais
	 * @return retorna o bonus em reais
	 */
	
	private String getBonusReais() {
		double bonus = this.bonus / 100.0;
		DecimalFormat df = new DecimalFormat("0.00");
		String valor = df.format(bonus);
		valor = valor.replace(".",",");
		return "R$ " + valor;
	}

	
}
