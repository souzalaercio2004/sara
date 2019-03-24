package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;


public class Voo {
	private LocalDate dataPrevistaParaPouso;
	private LocalTime horaPrevistaParaPouso;
	private LocalDate dataPrevistaParaDecolagem;
	private LocalTime horaPrevistaParaDecolagem;
	private String situacao;
	private String origem;
	private String destino;
	private Categoria categoria;
	private OcorrenciaVoo ocorrenciaDoVoo;
	
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public OcorrenciaVoo getOcorrenciaDoVoo() {
		return ocorrenciaDoVoo;
	}
	public void setOcorrenciaDoVoo(OcorrenciaVoo ocorrenciaDoVoo) {
		this.ocorrenciaDoVoo = ocorrenciaDoVoo;
	}
	public Voo(LocalDate dataPrevistaParaPouso, LocalTime horaPrevistaParaPouso, LocalDate dataPrevistaParaDecolagem,
			LocalTime horaPrevistaParaDecolagem, String situacao, String origem, String destino) {
		super();
		this.dataPrevistaParaPouso = dataPrevistaParaPouso;
		this.horaPrevistaParaPouso = horaPrevistaParaPouso;
		this.dataPrevistaParaDecolagem = dataPrevistaParaDecolagem;
		this.horaPrevistaParaDecolagem = horaPrevistaParaDecolagem;
		this.situacao = situacao;
		this.origem = origem;
		this.destino = destino;
	}
	
}
