package sara.nemo.br.ufes.inf.domain;

public class Proprietario {
	private int idProprietario;
	private String nomeProprietario;
	
	public Proprietario() {};

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public int getIdProprietario() {
		return idProprietario;
	}
	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	
}
