package sara.nemo.br.ufes.inf.view.accessorios;

public class AcessoriosRecursosPorProprietario {
	int idRecurso;
	String tipoRecurso;
	String nome;
	int prioridade;
	String estaEmUso;
	
	public AcessoriosRecursosPorProprietario() {
		
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getEstaEmUso() {
		return estaEmUso;
	}

	public void setEstaEmUso(String estaEmUso) {
		this.estaEmUso = estaEmUso;
	};
	
	
}
