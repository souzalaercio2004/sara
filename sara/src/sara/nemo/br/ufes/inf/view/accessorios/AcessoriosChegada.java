package sara.nemo.br.ufes.inf.view.accessorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JTable;

import sara.nemo.br.ufes.inf.view.Converte;

public class AcessoriosChegada extends Acessorios{
	int idAcessoriosChegada;
	String vooChegada;
	String procedencia;
	String vooPartida;
	String destino;
	String nomeCabeceira;
	String nomeEsteira;
	

	
	public AcessoriosChegada() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getIdAcessoriosChegada() {
		return idAcessoriosChegada;
	}



	public void setIdAcessoriosChegada(int idAcessoriosChegada) {
		this.idAcessoriosChegada = idAcessoriosChegada;
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



	

	public String getNomeCabeceira() {
		return nomeCabeceira;
	}



	public void setNomeCabeceira(String nomeCabeceira) {
		this.nomeCabeceira = nomeCabeceira;
	}

	public String getNomeEsteira() {
		return nomeEsteira;
	}



	public void setNomeEsteira(String nomeEsteira) {
		this.nomeEsteira = nomeEsteira;
	}



	public AcessoriosChegada valoresDaLinhaSelecionada(JTable table) {
		AcessoriosChegada chegada= new AcessoriosChegada();
		
		chegada.setDataHoraPrevista((table.getValueAt(table.getSelectedRow(), 0).toString()));
		chegada.setDataHoraAtualizada((table.getValueAt(table.getSelectedRow(), 1).toString()));
		chegada.setVooChegada((table.getValueAt(table.getSelectedRow(), 2).toString()));
		chegada.setMatricula((table.getValueAt(table.getSelectedRow(), 3).toString()));
		chegada.setSituacao((table.getValueAt(table.getSelectedRow(), 4).toString()));
		chegada.setProcedencia((table.getValueAt(table.getSelectedRow(), 5).toString()));
		
		chegada.setTipo((table.getValueAt(table.getSelectedRow(), 6).toString()));
		chegada.setVooPartida((table.getValueAt(table.getSelectedRow(), 7).toString()));
		chegada.setDestino((table.getValueAt(table.getSelectedRow(), 8).toString()));
		chegada.setEquipamento((table.getValueAt(table.getSelectedRow(), 9).toString()));
		chegada.setNomeCabeceira((table.getValueAt(table.getSelectedRow(), 10).toString()));
		chegada.setNomeBox((table.getValueAt(table.getSelectedRow(), 11).toString()));
		chegada.setNomeEsteira((table.getValueAt(table.getSelectedRow(), 12).toString()));
		chegada.setIdHotran(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 13).toString()));
		chegada.setIdAcessoriosChegada(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 14).toString()));
		return (chegada);
	}
	
	public String toString() {
		return(
				super.getDataHoraPrevista()+" "+
				super.getDataHoraAtualizada()+" "+
				vooChegada+" "+
				super.getMatricula()+" "+
				super.getSituacao()+" "+
				procedencia+" "+
				super.getTipo()+" "+
				vooPartida+" "+
				destino+" "+
				super.getEquipamento()+" "+
				nomeCabeceira+" "+
				nomeEsteira+" "+
				super.getIdHotran()+" "+
				idAcessoriosChegada
				);
	}
	public AcessoriosChegada converte(AcessoriosChegada lst) { //Converte a data e hora no padrao usado no Brasil dd/mm/aaaa hh:mm
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
