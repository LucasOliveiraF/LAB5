package sistemadeapostas;

/**
 * Representacao de quando uma aposta nao possui seguro
 * 
 * @author Lucas de Oliveira Ferreira da Silva - 116110084
 * Laboratorio de Programacao II  Matheus Gaudencio
 */

public class SemSeguro extends Seguro {
	
	@Override
	public String toString() {
		return "";
	}

	@Override
	public int getValorSeguro() {
		return 0;
	}

}
