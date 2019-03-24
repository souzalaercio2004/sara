package sara.nemo.br.ufes.inf.domain;

import java.util.List;


public class Proprietario {
	String nomeProprietario;
	List<Aeronave> aeronaves;// Lista de aeronaves de um proprietário
	
	protected Proprietario(String proprietario, Aeronave aeronaves2) {
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public List<Aeronave> getAeronaves() {
		return aeronaves;
	}

	public void setAeronaves(List<Aeronave> aeronaves) {
		this.aeronaves = aeronaves;
	}
	
	
}
