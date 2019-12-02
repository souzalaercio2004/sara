package sara.nemo.br.ufes.inf.domain;

import java.time.LocalTime;

public class VooGrupoII extends Voo{
	int idVooGrupoII;
	int idAeronave;
	String nomeComandante;
	String telefoneDoComandante;
	LocalTime tempoDeSolo;
	int idProprietarioParticular;
	
	public VooGrupoII() {};

	public int getIdVooGrupoII() {
		return idVooGrupoII;
	}
	public void setIdVooGrupoII(int idVooGrupoII) {
		this.idVooGrupoII = idVooGrupoII;
	}
	
	public int getIdAeronave() {
		return idAeronave;
	}
	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
	}
	public String getNomeComandante() {
		return nomeComandante;
	}
	public void setNomeComandante(String nomeComandante) {
		this.nomeComandante = nomeComandante;
	}
	public String getTelefoneDoComandante() {
		return telefoneDoComandante;
	}
	public void setTelefoneDoComandante(String telefoneDoComandante) {
		this.telefoneDoComandante = telefoneDoComandante;
	}
	public LocalTime getTempoDeSolo() {
		return tempoDeSolo;
	}
	public void setTempoDeSolo(LocalTime tempoDeSolo) {
		this.tempoDeSolo = tempoDeSolo;
	}


	
	public int getIdProprietarioParticular() {
		return idProprietarioParticular;
	}

	public void setIdProprietarioParticular(int idProprietarioParticular) {
		this.idProprietarioParticular = idProprietarioParticular;
	}
	
}
