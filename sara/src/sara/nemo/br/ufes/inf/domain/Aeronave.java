package sara.nemo.br.ufes.inf.domain;
import java.util.Iterator;
import java.util.List;


public class Aeronave {
	private String matricula;
	private String tipoAsa;
	private TipoAeronave tipoAeronave;
	private List<Proprietario> proprietarios;
	
	protected Aeronave() {
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
	public Iterator<Proprietario> getProprietarios() {
		return proprietarios.iterator();
	}
	public void setProprietarios(Proprietario proprietarios) {
		this.proprietarios.add(proprietarios);
	}
	
	public TipoAeronave getTipoAeronave() {
		return tipoAeronave;
	}
	public void setTipoAeronave(TipoAeronave tipoAeronave) {
		this.tipoAeronave = tipoAeronave;
	}
	
	public Aeronave(String matricula, String tipoAsa) {
		this.matricula = matricula;
		this.tipoAsa = tipoAsa;
	}

	
}
