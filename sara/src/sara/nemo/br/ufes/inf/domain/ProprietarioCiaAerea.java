package sara.nemo.br.ufes.inf.domain;

public class ProprietarioCiaAerea extends Proprietario{
	int idCiaAerea;
	private String siglaCiaAerea;
	
	public ProprietarioCiaAerea(){
		super();
	}
	
	
	
	public int getIdCiaAerea() {
		return idCiaAerea;
	}

	public void setIdCiaAerea(int idCiaAerea) {
		this.idCiaAerea = idCiaAerea;
	}

	public String getSiglaCiaAerea() {
		return siglaCiaAerea;
	}

	public void setSiglaCiaAerea(String siglaCiaAerea) {
		this.siglaCiaAerea = siglaCiaAerea;
	}
	
}
