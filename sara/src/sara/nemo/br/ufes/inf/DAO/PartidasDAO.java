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

import sara.nemo.br.ufes.inf.acessorios.AcessoriosPartida;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;
import sara.nemo.br.ufes.view.Converte;

public class PartidasDAO {
	
	public List<AcessoriosPartida> selecionarDadosDePartida(LocalDate dataHoje, String dia) {
		
		String sql= null;
		if (dia== "segundaFeira"){
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino, numVooPousa FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and segundaFeira= '1'";
		}else if (dia== "tercaFeira") {
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and tercaFeira= '1'";
		}else if (dia== "quartaFeira") {
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and quartaFeira= '1'";
		}else if (dia== "quintaFeira") {
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and quintaFeira= '1'";
		}else if (dia== "sextaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and sextaFeira= '1'";
		}else if (dia== "sabado") {
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and sabado= '1'";
		}else if (dia== "domingo") {
			sql= "SELECT sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino FROM sara.Hotran inner join"
					+ " sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and "
					+ "Hotran.idFrequencia= Frequencia.idFrequencia and domingo= '1'";
		}
		
		if (sql== null) return null;
		Connection con= null;
		PreparedStatement pstm = null;
		
		List<AcessoriosPartida> acessoriosPartida= new ArrayList<AcessoriosPartida>();
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			LocalDate dataPrevistaDecolagem= dataHoje;
			LocalTime horaPrevistaDecolagem;
			LocalDateTime dataHoraPrevistaDecolagem;
			while (result.next()) {
				AcessoriosPartida partida= new AcessoriosPartida();
				horaPrevistaDecolagem= Converte.converterJavaSqlTimeToLocalTime(result.getTime("horarioPrevistoDecolagem"));
				dataHoraPrevistaDecolagem= LocalDateTime.of(dataPrevistaDecolagem, horaPrevistaDecolagem);
				
				
				partida.setDataHoraPrevista(dataHoraPrevistaDecolagem);
				partida.setHoraAtualizada(horaPrevistaDecolagem);
				partida.setVooPartida(result.getString("numVooDecola"));
				partida.setPrefixo(" ");
				partida.setSituacao("TBC");
				partida.setDestino(result.getString("escalasDestino"));
				partida.setTipo(" ");
				partida.setVooChegada(result.getString("numVooPousa"));
				partida.setEquipamento("");
				partida.setPortao("00");	
				
				acessoriosPartida.add(partida);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas! "+ e.getMessage());
		}
		return (acessoriosPartida);
	}
	
}
