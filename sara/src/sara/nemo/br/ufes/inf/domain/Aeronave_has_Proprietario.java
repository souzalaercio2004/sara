package sara.nemo.br.ufes.inf.domain;

public class Aeronave_has_Proprietario {
	private int idAeronave;
	private int idProprietario;
	
	public Aeronave_has_Proprietario() {
		idAeronave=0;
		idProprietario=0;
	}
	
	
	public Aeronave_has_Proprietario(int idAeronave, int idProprietario) {
		this.idAeronave = idAeronave;
		this.idProprietario = idProprietario;
	}



	public int getIdAeronave() {
		return idAeronave;
	}

	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
	}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
	
	
}
