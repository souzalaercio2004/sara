package sara.nemo.br.ufes.inf.domain;

public class PosicaoHeliponto extends Recurso{
	private int idPosicaoHeliponto;
	private String nome;
	
	public PosicaoHeliponto() {
		super();
	}

	public int getIdPosicaoHeliponto() {
		return idPosicaoHeliponto;
	}

	public void setIdPosicaoHeliponto(int idPosicaoHeliponto) {
		this.idPosicaoHeliponto = idPosicaoHeliponto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
