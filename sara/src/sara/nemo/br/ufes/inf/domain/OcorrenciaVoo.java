package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.lang.String;

public class OcorrenciaVoo {
	private LocalDate data;
	private LocalTime hora;
	private String situacao;
	private Aeronave aeronave;
	
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
	
	public Aeronave getAeronave() {
		return aeronave;
	}
	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}
	public OcorrenciaVoo(LocalDate data, LocalTime hora, String situacao, Aeronave aeronave) {
		super();
		this.data = data;
		this.hora = hora;
		this.situacao = situacao;
		this.aeronave = aeronave;
	}
	
}
