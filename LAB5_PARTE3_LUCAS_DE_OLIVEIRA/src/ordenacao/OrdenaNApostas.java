package ordenacao;

import java.util.Comparator;

import sistemadeapostas.Cenario;

public class OrdenaNApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		if (o1.totalDeApostas() == o2.totalDeApostas())
			return o1.getIdentificacao() - o2.getIdentificacao();
		else
			return o2.totalDeApostas() - o1.totalDeApostas();
	}

}
