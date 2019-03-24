package sara.nemo.br.ufes.inf.domain;


public abstract class Recurso {
	String tipoRecurso;
	private Boolean estaEmUso;
	
	public Recurso(String tipoRecurso, Boolean estaEmUso) {
		super();
		this.tipoRecurso = tipoRecurso;
		this.estaEmUso = estaEmUso;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public Boolean getEstaEmUso() {
		return estaEmUso;
	}

	public void setEstaEmUso(Boolean estaEmUso) {
		this.estaEmUso = estaEmUso;
	}
	
	
}
