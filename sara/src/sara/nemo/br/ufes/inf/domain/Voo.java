package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;


public class Voo {
	int idVoo;
	int idAeronave;
	private int idCategoria;
	private LocalDate dataPrevistaParaPouso;
	private LocalTime horaPrevistaParaPouso;
	private LocalDate dataPrevistaParaDecolagem;
	private LocalTime horaPrevistaParaDecolagem;
	private String situacao;
	private String origem;
	private String destino;
	private OcorrenciaVoo ocorrenciaDoVoo;

	public Voo() {}
	
	public int getIdVoo() {
		return idVoo;
	}
	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}
	
	public int getIdAeronave() {
		return idAeronave;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
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
	public LocalDate getDataPrevistaParaDecolagem() {
		return dataPrevistaParaDecolagem;
	}
	public void setDataPrevistaParaDecolagem(LocalDate dataPrevistaParaDecolagem) {
		this.dataPrevistaParaDecolagem = dataPrevistaParaDecolagem;
	}
	public LocalTime getHoraPrevistaParaDecolagem() {
		return horaPrevistaParaDecolagem;
	}
	public void setHoraPrevistaParaDecolagem(LocalTime horaPrevistaParaDecolagem) {
		this.horaPrevistaParaDecolagem = horaPrevistaParaDecolagem;
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
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public OcorrenciaVoo getOcorrenciaDoVoo() {
		return ocorrenciaDoVoo;
	}
	public void setOcorrenciaDoVoo(OcorrenciaVoo ocorrenciaDoVoo) {
		this.ocorrenciaDoVoo = ocorrenciaDoVoo;
	}
	
	
}
