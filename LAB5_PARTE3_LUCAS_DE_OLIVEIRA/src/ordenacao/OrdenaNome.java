package ordenacao;
import java.util.Comparator;
import sistemadeapostas.Cenario;

public class OrdenaNome implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		
		return o1.getDescricao().compareTo(o2.getDescricao());
	}

}
