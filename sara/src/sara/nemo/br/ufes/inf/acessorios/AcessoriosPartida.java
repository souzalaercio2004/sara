package sara.nemo.br.ufes.inf.acessorios;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AcessoriosPartida {
	LocalDateTime dataHoraPrevista;
	LocalTime horaAtualizada;
	String vooPartida;
	String situacao;
	String destino;
	String prefixo;
	String tipo;
	String vooChegada;
	String Equipamento;
	String portao;
	
	public AcessoriosPartida() {}

	public LocalDateTime getDataHoraPrevista() {
		return dataHoraPrevista;
	}

	public void setDataHoraPrevista(LocalDateTime dataHoraPrevista) {
		this.dataHoraPrevista = dataHoraPrevista;
	}

	public LocalTime getHoraAtualizada() {
		return horaAtualizada;
	}

	public void setHoraAtualizada(LocalTime horaAtualizada) {
		this.horaAtualizada = horaAtualizada;
	}

	public String getVooPartida() {
		return vooPartida;
	}

	public void setVooPartida(String vooPartida) {
		this.vooPartida = vooPartida;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVooChegada() {
		return vooChegada;
	}

	public void setVooChegada(String vooChegada) {
		this.vooChegada = vooChegada;
	}

	public String getEquipamento() {
		return Equipamento;
	}

	public void setEquipamento(String equipamento) {
		Equipamento = equipamento;
	}

	public String getPortao() {
		return portao;
	}

	public void setPortao(String portao) {
		this.portao = portao;
	}
	
	
}
