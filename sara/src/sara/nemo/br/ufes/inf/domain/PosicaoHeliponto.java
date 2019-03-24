package sara.nemo.br.ufes.inf.domain;

public class PosicaoHeliponto extends Recurso{
	private Integer numeroSpot;


	public PosicaoHeliponto(String tipoRecurso, Boolean estaEmUso, Integer numeroSpot) {
		super(tipoRecurso, estaEmUso);
		this.numeroSpot = numeroSpot;
	}

	public Integer getNumeroSpot() {
		return numeroSpot;
	}

	public void setNumeroSpot(Integer numeroSpot) {
		this.numeroSpot = numeroSpot;
	}
	
	
}
