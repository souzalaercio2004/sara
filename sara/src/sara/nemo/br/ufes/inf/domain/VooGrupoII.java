package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class VooGrupoII extends Voo{
	int idVooGrupoII;
	int idAeronave;
	String nomeComandante;
	String telefoneDoComandante;
	LocalTime tempoDeSolo;
	ProprietarioParticular proprietarioParticular;
	
	

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
	public ProprietarioParticular getProprietarioParticular() {
		return proprietarioParticular;
	}
	public void setProprietarioParticular(ProprietarioParticular proprietarioParticular) {
		this.proprietarioParticular = proprietarioParticular;
	}
	
	public VooGrupoII(LocalDate dataPrevistaParaPouso, LocalTime horaPrevistaParaPouso,
			LocalDate dataPrevistaParaDecolagem, LocalTime horaPrevistaParaDecolagem, String situacao, String origem,
			String destino, String nomeComandante, String telefoneDoComandante, LocalTime tempoDeSolo,
			ProprietarioParticular proprietarioParticular) {
		
		super.setDataPrevistaParaPouso(dataPrevistaParaPouso);
		super.setHoraPrevistaParaPouso(horaPrevistaParaPouso);
		super.setDataPrevistaParaDecolagem(dataPrevistaParaDecolagem);
		super.setHoraPrevistaParaDecolagem(horaPrevistaParaDecolagem);
		super.setSituacao(situacao);
		super.setOrigem(origem);
		super.setDestino(destino);
		
		this.nomeComandante = nomeComandante;
		this.telefoneDoComandante = telefoneDoComandante;
		this.tempoDeSolo = tempoDeSolo;
		this.proprietarioParticular = proprietarioParticular;
	}	
}
