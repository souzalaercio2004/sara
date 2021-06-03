																																																																																																																																																																				package sara.nemo.br.ufes.inf.view.accessorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JTable;

import sara.nemo.br.ufes.inf.view.Converte;

public class AcessoriosPartida extends Acessorios{
	int idAcessoriosPartida;
	String vooPartida;
	String destino;
	String vooChegada;
	String procedencia;
	String nomeBox;
	String portao;
	int idVoo;
	
	public AcessoriosPartida() {}

	
	public int getIdAcessoriosPartida() {
		return idAcessoriosPartida;
	}



	public void setIdAcessoriosPartida(int idAcessoriosPartida) {
		this.idAcessoriosPartida = idAcessoriosPartida;
	}



	public String getVooPartida() {
		return vooPartida;
	}



	public void setVooPartida(String vooPartida) {
		this.vooPartida = vooPartida;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public String getVooChegada() {
		return vooChegada;
	}



	public void setVooChegada(String vooChegada) {
		this.vooChegada = vooChegada;
	}



	public String getProcedencia() {
		return procedencia;
	}



	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}



	public String getPortao() {
		return portao;
	}



	public void setPortao(String portao) {
		this.portao = portao;
	}



	public String getNomeBox() {
		return nomeBox;
	}


	public void setNomeBox(String nomeBox) {
		this.nomeBox = nomeBox;
	}

	

	public int getIdVoo() {
		return idVoo;
	}


	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}


	public AcessoriosPartida valoresDaLinhaSelecionada(JTable table) {
		AcessoriosPartida partida= new AcessoriosPartida();
		
		partida.setDataHoraPrevista((table.getValueAt(table.getSelectedRow(), 0).toString()));
		partida.setDataHoraAtualizada((table.getValueAt(table.getSelectedRow(), 1).toString()));
		partida.setVooPartida((table.getValueAt(table.getSelectedRow(), 2).toString()));
		partida.setMatricula((table.getValueAt(table.getSelectedRow(), 3).toString()));
		partida.setSituacao((table.getValueAt(table.getSelectedRow(), 4).toString()));
		partida.setDestino((table.getValueAt(table.getSelectedRow(), 5).toString()));
		partida.setTipo(table.getValueAt(table.getSelectedRow(), 6).toString());
		partida.setVooChegada((table.getValueAt(table.getSelectedRow(), 7).toString()));
		partida.setProcedencia((table.getValueAt(table.getSelectedRow(), 8).toString()));
		partida.setEquipamento((table.getValueAt(table.getSelectedRow(), 9).toString()));
		partida.setNomeBox((table.getValueAt(table.getSelectedRow(), 10).toString()));
		partida.setPortao((table.getValueAt(table.getSelectedRow(), 10).toString()));
		partida.setIdHotran(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 11).toString()));
		
		
		return (partida);
	}
	
	public String toString() {
		return(
				super.getDataHoraPrevista()+" "+
				super.getDataHoraAtualizada()+" "+
				vooPartida+" "+
				super.getMatricula()+" "+
				super.getSituacao()+" "+
				destino+" "+
				super.getTipo()+" "+
				vooChegada+" "+
				procedencia+" "+
				super.getEquipamento()+" "+
				nomeBox+" "+
				portao+" "+
				super.getIdHotran()+" "+
				idVoo
				);
	}
	
	public AcessoriosPartida converte(AcessoriosPartida lst) { //Converte a data e hora no padrao usado no Brasil dd/mm/aaaa hh:mm
		LocalDate date= null;
		LocalTime time= null;
		String []dateTime= lst.getDataHoraPrevista().split(" ");
		LocalDateTime localDateTime;
		if (dateTime != null) {
			if ((dateTime[0] != null)&& (dateTime[1]!= null)) {
				date= LocalDate.parse(dateTime[0]);
				time = LocalTime.parse(dateTime[1]);
				localDateTime= LocalDateTime.of(date, time);
				lst.setDataHoraPrevista(Converte.converteLocalDateTimeToString(localDateTime));
			}
			
			dateTime= lst.getDataHoraAtualizada().split(" ");
			try {
				if (dateTime[0] != null) date= LocalDate.parse(dateTime[0]);
				if (dateTime[1]!= null)time = LocalTime.parse(dateTime[1]);
				localDateTime= LocalDateTime.of(date, time);

				lst.setDataHoraAtualizada(Converte.converteLocalDateTimeToString(localDateTime));
			}catch(DateTimeParseException dte) {
				return lst;
			}
		}
		return lst;
	}
}
