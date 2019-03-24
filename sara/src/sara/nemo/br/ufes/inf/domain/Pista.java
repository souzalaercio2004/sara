package sara.nemo.br.ufes.inf.domain;

public class Pista extends Recurso {
	private String nomeDaPista;
	private String Cabeceira;
	
	public Pista(String tipoRecurso, Boolean estaEmUso, String nomeDaPista, String cabeceira) {
		super(tipoRecurso, estaEmUso);
		this.nomeDaPista = nomeDaPista;
		Cabeceira = cabeceira;
	}


	public String getNomeDaPista() {
		return nomeDaPista;
	}


	public void setNomeDaPista(String nomeDaPista) {
		this.nomeDaPista = nomeDaPista;
	}


	public String getCabeceira() {
		return Cabeceira;
	}


	public void setCabeceira(String cabeceira) {
		Cabeceira = cabeceira;
	}

	
	
}
