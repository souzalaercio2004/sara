package sara.nemo.br.ufes.inf.domain;

public class Aeronave {
	int id;
	private String matricula;
	private String tipoAsa;
	private int idTipoAeronave;
	
	public Aeronave() {
		//this.proprietarios= new ArrayList<Proprietario>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
