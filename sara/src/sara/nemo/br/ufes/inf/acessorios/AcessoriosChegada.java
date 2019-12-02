package sara.nemo.br.ufes.inf.acessorios;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AcessoriosChegada {
	LocalDateTime dataHoraPrevista;
	LocalTime horaAtualizada;
	String vooChegada;
	String situacao;
	String procedencia;
	String prefixo;
	String Esteira;
	String tipo;
	String vooPartida;
	String box;
	String Equipamento;
	String portao;
	
	public AcessoriosChegada() {}

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

	public String getVooChegada() {
		return vooChegada;
	}

	public void setVooChegada(String vooChegada) {
		this.vooChegada = vooChegada;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getEsteira() {
		return Esteira;
	}

	public void setEsteira(String esteira) {
		Esteira = esteira;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVooPartida() {
		return vooPartida;
	}

	public void setVooPartida(String vooPartida) {
		this.vooPartida = vooPartida;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
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
