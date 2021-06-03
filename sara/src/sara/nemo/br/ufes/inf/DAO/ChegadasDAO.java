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

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.view.Converte;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;

public class ChegadasDAO {
	
	public List<AcessoriosChegada> selecionarDadosDeChegada(LocalDate dataHoje, String dia) {
		
		String sql= null;
		if (dia== "segundaFeira"){
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
				+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
				+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
				+ " Hotran.idFrequencia= Frequencia.idFrequencia and segundaFeira= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "tercaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and tercaFeira= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "quartaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and quartaFeira= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "quintaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and quintaFeira= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "sextaFeira") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and sextaFeira= '1' and '" +dataHoje+"'  BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "sabado") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and sabado= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
		}else if (dia== "domingo") {
			sql= "SELECT sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, numVooDecola, escalasDestino, equipamento, idHotran FROM"
					+ " sara.Hotran inner join sara.ProprietarioCiaAerea inner join sara.Frequencia "
					+ "where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and"
					+ " Hotran.idFrequencia= Frequencia.idFrequencia and domingo= '1' and '" +dataHoje+"' BETWEEN date(Hotran.inicioVigencia)and date(Hotran.fimVigencia) ORDER BY horarioPrevistoPouso";
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
			LocalDateTime dataHoraAtualizada;
			
			while (result.next()) {
				AcessoriosChegada chegada= new AcessoriosChegada();
				
				horaPrevistaPouso= Converte.converterJavaSqlTimeToLocalTime(result.getTime("horarioPrevistoPouso"));
				dataHoraPrevistaPouso= LocalDateTime.of(dataPrevistaPouso, horaPrevistaPouso);
				
				chegada.setNomeBox("00");
				
				chegada.setDataHoraPrevista(Converte.converteLocalDateTimeToString(dataHoraPrevistaPouso));
				chegada.setNomeCabeceira("00");
				chegada.setEquipamento("");
				chegada.setNomeEsteira("00");
				
				dataHoraAtualizada=LocalDateTime.of(dataPrevistaPouso, horaPrevistaPouso);
				
				chegada.setDataHoraAtualizada(Converte.converteLocalDateTimeToString(dataHoraAtualizada));
				chegada.setMatricula("");
				chegada.setProcedencia(result.getString("escalasOrigem"));
				chegada.setSituacao("TBC");
				chegada.setTipo(" ");
				chegada.setVooChegada(result.getString("sigla")+" "+ result.getString("numVooPousa"));
				chegada.setVooPartida(result.getString("numVooDecola"));
				chegada.setDestino(result.getString("escalasDestino"));
				chegada.setEquipamento(result.getString("equipamento"));
				chegada.setIdHotran(result.getInt("idHotran"));
				 
				acessoriosChegada.add(chegada);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!");
		}
		return (acessoriosChegada);
	}
	
}
