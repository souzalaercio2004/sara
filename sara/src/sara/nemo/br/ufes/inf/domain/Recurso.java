package sara.nemo.br.ufes.inf.domain;


public class Recurso {
	int idRecurso;
	String tipoRecurso;
	String localizacao;
	Boolean estaEmUso;

	public Recurso() {};
	
	public int getIdRecurso() {
		return idRecurso;
	}


	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}


	public String getTipoRecurso() {
		return tipoRecurso;
	}


	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Boolean getEstaEmUso() {
		return estaEmUso;
	}

	public void setEstaEmUso(Boolean estaEmUso) {
		this.estaEmUso = estaEmUso;
	}


	
	
	
	
}
