package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movimento {
	int id;
	private String tipoMovimento;
	private LocalDate data;
	private LocalTime hora;
	private int idOcorrenciaVoo;
	
	public String getTipoMovimento() {
		return tipoMovimento;
	}
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
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
	public Movimento(String tipoMovimento, LocalDate data, LocalTime hora) {
		this.tipoMovimento = tipoMovimento;
		this.data = data;
		this.hora = hora;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdOcorrenciaVoo() {
		return idOcorrenciaVoo;
	}
	public void setIdOcorrenciaVoo(int idOcorrenciaVoo) {
		this.idOcorrenciaVoo = idOcorrenciaVoo;
	}
	
	
}
