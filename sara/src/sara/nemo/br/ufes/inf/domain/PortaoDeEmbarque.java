package sara.nemo.br.ufes.inf.domain;

public class PortaoDeEmbarque extends Recurso{
	private Integer numeroDoPortao;

	

	public PortaoDeEmbarque(String tipoRecurso, Boolean estaEmUso, Integer numeroDoPortao) {
		super(tipoRecurso, estaEmUso);
		this.numeroDoPortao = numeroDoPortao;
	}

	public PortaoDeEmbarque(String tipoRecurso, Boolean estaEmUso) {
		super(tipoRecurso, estaEmUso);
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroDoPortao() {
		return numeroDoPortao;
	}

	public void setNumeroDoPortao(Integer numeroDoPortao) {
		this.numeroDoPortao = numeroDoPortao;
	}

	
}
