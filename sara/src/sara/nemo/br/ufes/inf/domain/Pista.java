package sara.nemo.br.ufes.inf.domain;

public class Pista extends Recurso {
	int idPista;
	private String nome;
	private String nomeCabeceira;

	public Pista() {}

	public int getIdPista() {
		return idPista;
	}

	public void setIdPista(int idPista) {
		this.idPista = idPista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCabeceira() {
		return nomeCabeceira;
	}

	public void setNomeCabeceira(String nomeCabeceira) {
		this.nomeCabeceira= nomeCabeceira;
	};

	

}
