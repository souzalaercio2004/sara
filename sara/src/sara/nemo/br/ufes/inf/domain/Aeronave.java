package sara.nemo.br.ufes.inf.domain;

public class Aeronave {
	int idAeronave;
	private String matricula;
	private String tipoAsa;
	private int idTipoAeronave;
	private int idProprietario;
	

	public Aeronave() {

	}
	public int getIdAeronave() {
		return idAeronave;
	}
	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getTipoAsa() {
		return tipoAsa;
	}
	public void setTipoAsa(String tipoAsa) {
		this.tipoAsa = tipoAsa;
	}
	
	public int getIdTipoAeronave() {
		return idTipoAeronave;
	}
	public void setIdTipoAeronave(int idTipoAeronave) {
		this.idTipoAeronave = idTipoAeronave;
	}
	public Aeronave(String matricula, String tipoAsa) {
		this.matricula = matricula;
		this.tipoAsa = tipoAsa;
	}
	public int getIdProprietario() {
		return idProprietario;
	}
	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
	
	public String toString() {
		String aeronave= idAeronave+" "+matricula+" "+tipoAsa+" "+idTipoAeronave+" "+ idProprietario;
		return aeronave;
	}
}
