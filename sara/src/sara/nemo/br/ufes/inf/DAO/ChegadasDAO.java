package sara.nemo.br.ufes.inf.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.acessorios.AcessoriosChegada;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;
import sara.nemo.br.ufes.view.Converte;

public class ChegadasDAO {
	
	public List<AcessoriosChegada> selecionarDadosDeChegada(LocalDate dataHoje, String dia) {
		
		String sql= null;
		if (dia== "segundaFeira"){
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
				+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
				+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
				+ " Hotran.idFrequencia= Frequencia.idFrequencia and segundaFeira= '1'";
		}else if (dia== "tercaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and tercaFeira= '1'";
		}else if (dia== "quartaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and quartaFeira= '1'";
		}else if (dia== "quintaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and quintaFeira= '1'";
		}else if (dia== "sextaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and sextaFeira= '1'";
		}else if (dia== "sabado") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and sabado= '1'";
		}else if (dia== "domingo") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and domingo= '1'";
		}
		
		if (sql== null) return null;
		Connection con= null;
		PreparedStatement pstm = null;
		
		List<AcessoriosChegada> acessoriosChegada= new ArrayList<AcessoriosChegada>();
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			LocalDate dataPrevistaPouso= dataHoje;
			LocalTime horaPrevistaPouso;
			LocalDateTime dataHoraPrevistaPouso;
			while (result.next()) {
				AcessoriosChegada chegada= new AcessoriosChegada();
				horaPrevistaPouso= Converte.converterJavaSqlTimeToLocalTime(result.getTime("horarioPrevistoPouso"));
				dataHoraPrevistaPouso= LocalDateTime.of(dataPrevistaPouso, horaPrevistaPouso);
				
				chegada.setBox("00");
				chegada.setDataHoraPrevista(dataHoraPrevistaPouso);
				chegada.setEquipamento("");
				chegada.setEsteira("00");
				chegada.setHoraAtualizada(horaPrevistaPouso);
				chegada.setPortao("00");
				chegada.setPrefixo("");
				chegada.setProcedencia(result.getString("escalasOrigem"));
				chegada.setSituacao("PRV");
				chegada.setTipo(" ");
				chegada.setVooChegada(result.getString("sigla")+" "+ result.getString("numVooPousa"));
				chegada.setVooPartida(result.getString("numVooDecola"));
				
				acessoriosChegada.add(chegada);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!"+ e.getMessage());
		}
		return (acessoriosChegada);
	}
	
}
