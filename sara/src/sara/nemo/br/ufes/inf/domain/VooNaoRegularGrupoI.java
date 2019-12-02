package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class VooNaoRegularGrupoI extends Voo{
	int idVooGrupoI;
	int idVoo;
	int numeroVooPouso;
	int numeroVooDecolagem;
	LocalDate dataConfirmadaPouso;
	LocalDate dataConfirmadaDecolagem;
	LocalTime horaConfirmadaPouso;
	LocalTime horaConfirmadaDecolagem;
	int idProprietarioCiaAerea;
	
	public VooNaoRegularGrupoI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdVooGrupoI() {
		return idVooGrupoI;
	}

	public void setIdVooGrupoI(int idVooGrupoI) {
		this.idVooGrupoI = idVooGrupoI;
	}

	public int getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}

	public int getNumeroVooPouso() {
		return numeroVooPouso;
	}

	public void setNumeroVooPouso(int numeroVooPouso) {
		this.numeroVooPouso = numeroVooPouso;
	}

	public int getNumeroVooDecolagem() {
		return numeroVooDecolagem;
	}

	public void setNumeroVooDecolagem(int numeroVooDecolagem) {
		this.numeroVooDecolagem = numeroVooDecolagem;
	}

	public LocalDate getDataConfirmadaPouso() {
		return dataConfirmadaPouso;
	}

	public void setDataConfirmadaPouso(LocalDate dataConfirmadaPouso) {
		this.dataConfirmadaPouso = dataConfirmadaPouso;
	}

	public LocalDate getDataConfirmadaDecolagem() {
		return dataConfirmadaDecolagem;
	}

	public void setDataConfirmadaDecolagem(LocalDate dataConfirmadaDecolagem) {
		this.dataConfirmadaDecolagem = dataConfirmadaDecolagem;
	}

	public LocalTime getHoraConfirmadaPouso() {
		return horaConfirmadaPouso;
	}

	public void setHoraConfirmadaPouso(LocalTime horaConfirmadaPouso) {
		this.horaConfirmadaPouso = horaConfirmadaPouso;
	}

	public LocalTime getHoraConfirmadaDecolagem() {
		return horaConfirmadaDecolagem;
	}

	public void setHoraConfirmadaDecolagem(LocalTime horaConfirmadaDecolagem) {
		this.horaConfirmadaDecolagem = horaConfirmadaDecolagem;
	}

	public int getIdProprietarioCiaAerea() {
		return idProprietarioCiaAerea;
	}

	public void setIdProprietarioCiaAerea(int idProprietarioCiaAerea) {
		this.idProprietarioCiaAerea = idProprietarioCiaAerea;
	}
	
	
	
}
