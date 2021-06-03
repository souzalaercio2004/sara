package sara.nemo.br.ufes.inf.domain;

public class RecursosPorProprietario {
	int idProprietario;
	int idRecurso;
	int prioridade;
	
	public RecursosPorProprietario() {}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	};
	
	public String toString() {
		return (idProprietario+" "+idRecurso+" "+ prioridade);
	}
	
}
