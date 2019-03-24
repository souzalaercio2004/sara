package sara.nemo.br.ufes.inf.domain;

public class ProprietarioCiaAerea extends Proprietario {
	
	private String sigla;

	public ProprietarioCiaAerea(String proprietario, Aeronave aeronaves2) {
		super(proprietario, aeronaves2);
		// TODO Auto-generated constructor stub
	}

	public ProprietarioCiaAerea(String proprietario, Aeronave aeronaves2, String sigla) {
		super(proprietario, aeronaves2);
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	
	
}
