package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class VooChegadaGrupoII extends Voo{
	int idVooGrupoII;
	int idAeronave;
	int idProprietarioParticular;
	private LocalDate dataPrevistaParaPouso;
	private LocalTime horaPrevistaParaPouso;
	private LocalDate dataConfirmadaPouso;
	private LocalTime horaConfirmadaPouso;
	private String situacao;
	private String origem;
	private int nomeCabeceira;
	String nomeComandante;
	String telefoneDoComandante;
	LocalTime tempoDeSolo;
	
	
	
	public VooChegadaGrupoII() {}



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



	public int getIdProprietarioParticular() {
		return idProprietarioParticular;
	}



	public void setIdProprietarioParticular(int idProprietarioParticular) {
		this.idProprietarioParticular = idProprietarioParticular;
	}



	public LocalDate getDataPrevistaParaPouso() {
		return dataPrevistaParaPouso;
	}



	public void setDataPrevistaParaPouso(LocalDate dataPrevistaParaPouso) {
		this.dataPrevistaParaPouso = dataPrevistaParaPouso;
	}



	public LocalTime getHoraPrevistaParaPouso() {
		return horaPrevistaParaPouso;
	}



	public void setHoraPrevistaParaPouso(LocalTime horaPrevistaParaPouso) {
		this.horaPrevistaParaPouso = horaPrevistaParaPouso;
	}



	public LocalDate getDataConfirmadaPouso() {
		return dataConfirmadaPouso;
	}



	public void setDataConfirmadaPouso(LocalDate dataConfirmadaPouso) {
		this.dataConfirmadaPouso = dataConfirmadaPouso;
	}



	public LocalTime getHoraConfirmadaPouso() {
		return horaConfirmadaPouso;
	}



	public void setHoraConfirmadaPouso(LocalTime horaConfirmadaPouso) {
		this.horaConfirmadaPouso = horaConfirmadaPouso;
	}



	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public String getOrigem() {
		return origem;
	}



	public void setOrigem(String origem) {
		this.origem = origem;
	}



	public int getNomeCabeceira() {
		return nomeCabeceira;
	}



	public void setNomeCabeceira(int nomeCabeceira) {
		this.nomeCabeceira = nomeCabeceira;
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
	};

	
	
}
