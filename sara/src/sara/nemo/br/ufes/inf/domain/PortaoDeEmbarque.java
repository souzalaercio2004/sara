package sara.nemo.br.ufes.inf.domain;

public class PortaoDeEmbarque extends Recurso{
	int idPortaoDeEmbarque;
	private String nome;
	
	public PortaoDeEmbarque() {
		super();
	}

	public int getIdPortaoDeEmbarque() {
		return idPortaoDeEmbarque;
	}

	public void setIdPortaoDeEmbarque(int idPortaoDeEmbarque) {
		this.idPortaoDeEmbarque = idPortaoDeEmbarque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	};
	
	
}
