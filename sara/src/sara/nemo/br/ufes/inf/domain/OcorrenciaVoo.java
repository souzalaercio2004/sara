package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class OcorrenciaVoo {
	int idOcorrenciaVoo;
	private int idAeronave;
	private LocalDate data;
	private LocalTime hora;
	private String situacao;
	private int idVoo;
	
	public OcorrenciaVoo() {};
	
	public int getIdOcorrenciaVoo() {
		return idOcorrenciaVoo;
	}

	public void setIdOcorrenciaVoo(int idOcorrenciaVoo) {
		this.idOcorrenciaVoo = idOcorrenciaVoo;
	}

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public int getIdAeronave() {
		return idAeronave;
	}



	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
	}

	public int getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}
	
	
}
