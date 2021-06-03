package sara.nemo.br.ufes.inf.domain;

public class Esteira extends Recurso {
	private int idEsteira;
	private String nome;
	
	public Esteira() {
		super();
	}
	
	public int getIdEsteira() {
		return idEsteira;
	}
	public void setIdEsteira(int idEsteira) {
		this.idEsteira = idEsteira;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return(idEsteira+ " "+nome);
	}
}
