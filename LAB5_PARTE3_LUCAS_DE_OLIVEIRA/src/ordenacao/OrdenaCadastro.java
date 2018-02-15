package ordenacao;

import java.util.Comparator;

import sistemadeapostas.Cenario;

public class OrdenaCadastro implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		return o1.getIdentificacao() - o2.getIdentificacao();
	}

}
