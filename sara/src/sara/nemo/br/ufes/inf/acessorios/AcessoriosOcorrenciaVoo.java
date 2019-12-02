package sara.nemo.br.ufes.inf.acessorios;

import java.time.LocalDate;
import java.time.LocalTime;

public class AcessoriosOcorrenciaVoo {
	int idOcorrenciaVoo;
	String matricula;
	String equipamento;
	LocalDate data;
	LocalTime hora;
	String situacao;
	
	public AcessoriosOcorrenciaVoo(){
		
	}

	public int getIdOcorrenciaVoo() {
		return idOcorrenciaVoo;
	}

	public void setIdOcorrenciaVoo(int idOcorrenciaVoo) {
		this.idOcorrenciaVoo = idOcorrenciaVoo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
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
	};
	
	
}
