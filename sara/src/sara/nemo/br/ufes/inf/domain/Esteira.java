package sara.nemo.br.ufes.inf.domain;

public class Esteira extends Recurso {
	private Integer numeroEsteira;

	public Integer getNumeroEsteira() {
		return numeroEsteira;
	}

	public void setNumeroEsteira(Integer numeroEsteira) {
		this.numeroEsteira = numeroEsteira;
	}

	public Esteira(String tipo, Boolean estaEmUso, Integer numeroEsteira) {
		super(tipo, estaEmUso);
		this.numeroEsteira = numeroEsteira;
	}
	
	
}
