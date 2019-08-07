package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotran {
		int id;
		private int idCiaAerea;
		int idFrequencia;
		private int numeroVooPousa;
		private int numeroVooDecola;
		private LocalTime horarioPrevistoPouso;
		private LocalTime horarioPrevistoDecolagem;
		private String escalasOrigem;
		private String escalasDestino;
		private LocalDate inicioVigencia;
		private LocalDate fimVigencia;
		//private Frequencia frequencia;
		


		public Hotran() {}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getIdCiaAerea() {
			return idCiaAerea;
		}

		public void setIdCiaAerea(int idCiaAerea) {
			this.idCiaAerea = idCiaAerea;
		}

		public int getNumeroVooPousa() {
			return numeroVooPousa;
		}

		public void setNumeroVooPousa(int numeroVooPousa) {
			this.numeroVooPousa = numeroVooPousa;
		}

		public int getNumeroVooDecola() {
			return numeroVooDecola;
		}

		public void setNumeroVooDecola(int numeroVooDecola) {
			this.numeroVooDecola = numeroVooDecola;
		}

		public LocalTime getHorarioPrevistoPouso() {
			return horarioPrevistoPouso;
		}

		public void setHorarioPrevistoPouso(LocalTime horarioPrevistoPouso) {
			this.horarioPrevistoPouso = horarioPrevistoPouso;
		}

		public LocalTime getHorarioPrevistoDecolagem() {
			return horarioPrevistoDecolagem;
		}

		public void setHorarioPrevistoDecolagem(LocalTime horarioPrevistoDecolagem) {
			this.horarioPrevistoDecolagem = horarioPrevistoDecolagem;
		}

		
		public int getIdFrequencia() {
			return idFrequencia;
		}

		public void setIdFrequencia(int idFrequencia) {
			this.idFrequencia = idFrequencia;
		}

		public String getEscalasOrigem() {
			return escalasOrigem;
		}

		public void setEscalasOrigem(String escalasOrigem) {
			this.escalasOrigem = escalasOrigem;
		}

		public String getEscalasDestino() {
			return escalasDestino;
		}

		public void setEscalasDestino(String escalasDestino) {
			this.escalasDestino = escalasDestino;
		}

		public LocalDate getInicioVigencia() {
			return inicioVigencia;
		}

		public void setInicioVigencia(LocalDate inicioVigencia) {
			this.inicioVigencia = inicioVigencia;
		}

		public LocalDate getFimVigencia() {
			return fimVigencia;
		}

		public void setFimVigencia(LocalDate fimVigencia) {
			this.fimVigencia = fimVigencia;
		}

		

		//implementar toString para colocar frequencia no formato DSTQQSS
}		

