package sara.nemo.br.ufes.inf.domain;

public class PreferenciaUsoRecurso {
	private ProprietarioCiaAerea empresa;
	private Recurso recurso;
	private int prioridade;
	public PreferenciaUsoRecurso(ProprietarioCiaAerea empresa, Recurso recurso, int prioridade) {
		super();
		this.empresa = empresa;
		this.recurso = recurso;
		this.prioridade = prioridade;
	}
	public ProprietarioCiaAerea getEmpresa() {
		return empresa;
	}
	public void setEmpresa(ProprietarioCiaAerea empresa) {
		this.empresa = empresa;
	}
	public Recurso getRecurso() {
		return recurso;
	}
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	



	
	
}
