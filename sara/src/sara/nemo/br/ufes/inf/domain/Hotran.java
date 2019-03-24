package sara.nemo.br.ufes.inf.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotran {
		private int numeroVooPousa;
		private int numeroVooDecola;
		private LocalTime horarioPrevistoPouso;
		private LocalTime horarioPrevistoDecolagem;
		private int[] frequencia; //vetor de tamanho 7 representando os dias da semana
		private String escalasOrigem;
		private String escalasDestino;
		private LocalDate inicioVigencia;
		private LocalDate fimVigencia;
		
		public Hotran(int numeroVooPousa, int numeroVooDecola, LocalTime horarioPrevistoPouso,
				LocalTime horarioPrevistoDecolagem, int[] frequencia, String escalasOrigem, String escalasDestino,
				LocalDate inicioVigencia, LocalDate fimVigencia) {
			
			super();
			this.numeroVooPousa = numeroVooPousa;
			this.numeroVooDecola = numeroVooDecola;
			this.horarioPrevistoPouso = horarioPrevistoPouso;
			this.horarioPrevistoDecolagem = horarioPrevistoDecolagem;
			this.frequencia = frequencia;
			this.escalasOrigem = escalasOrigem;
			this.escalasDestino = escalasDestino;
			this.inicioVigencia = inicioVigencia;
			this.fimVigencia = fimVigencia;
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
		public int[] getFrequencia() {
			return frequencia;
		}
		public void setFrequencia(int[] frequencia) {
			this.frequencia = frequencia;
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
}
