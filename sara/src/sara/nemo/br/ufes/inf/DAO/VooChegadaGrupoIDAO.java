package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.VooChegadaGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;

public class VooChegadaGrupoIDAO {
	public void inserir(VooChegadaGrupoI vooChegadaGrupoI)throws SQLException {
		String sql= "INSERT INTO VooChegadaGrupoI(idVooChegadaGrupoI, idHotran, "
				+ "idProprietarioCiaAerea, numeroVooPouso, dataPrevistaPouso, "
				+ "horaPrevistaPouso, dataConfirmadaPouso, horaConfirmadaPouso, "
				+ "situacao, origem, nomeCabeceira, nomeEsteira, Hotran_idHotran)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooChegadaGrupoI.getIdVooChegadaGrupoI());
			pstm.setInt(2, vooChegadaGrupoI.getIdHotran());
			pstm.setInt(3, vooChegadaGrupoI.getIdProprietarioCiaAerea());
			pstm.setInt(4, vooChegadaGrupoI.getNumeroVooPouso());
			pstm.setDate(5, Converte.converterLocalDateToJavaSqlDate(vooChegadaGrupoI.getDataPrevistaParaPouso()));
			pstm.setTime(6, Converte.converterLocalTimeToJavaSqlTime(vooChegadaGrupoI.getHoraPrevistaParaPouso()));
			pstm.setDate(7, Converte.converterLocalDateToJavaSqlDate(vooChegadaGrupoI.getDataConfirmadaPouso()));
			pstm.setTime(8, Converte.converterLocalTimeToJavaSqlTime(vooChegadaGrupoI.getHoraConfirmadaPouso()));
			pstm.setString(9, vooChegadaGrupoI.getSituacao());
			pstm.setString(10, vooChegadaGrupoI.getOrigem());
			pstm.setInt(11, vooChegadaGrupoI.getNomeCabeceira());
			pstm.setString(12, vooChegadaGrupoI.getNomeEsteira());
			pstm.setInt(13, vooChegadaGrupoI.getIdHotran());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo do GrupoI invalido! "+ e.getMessage());
			e.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	public void selecionar() {
		String sql= "SELECT sigla, numeroVooPouso, dataPrevistaPouso, horaPrevistaPouso, "
				+ "matricula, situacao, equipamento, nomeCabeceira, "
				+ "nomeEsteira"
				+ "FROM Voo inner join VooChegadaGrupoI inner join Aeronave inner join TipoAeronave "
				+ "inner join ProprietarioCiaAerea where idVoo= idVooChegadaGrupoI "
				+ "and idAeronave= Aeronave_idAeronave and idProprietario= idCiaAerea "
				+ "and idTipoAeronave=TipoAeronave_idTipoAeronave order by idVooChegadaGrupoI ASC";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(
					result.getString("sigla")+ " "+
					result.getInt("numeroVooPouso")+" "+
					result.getString("dataPrevistaPouso")+" "+
					result.getString("horaPrevistaPouso")+" "+
					result.getString("matricula")+" "+ 
					result.getString("situacao")+" "+
					result.getString("equipamento")+" "+
					result.getInt("numeroDoVooDecolagem")+" "+
					result.getInt("nomeCabeceira")+" "+
					result.getString("nomeBox")+" "+
					result.getString("nomeEsteira"));
				
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoI cadastrados!"+ e.getMessage());
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public VooChegadaGrupoI selecionarVooGrupoIPouso(Date dataPrevistaParaPouso, int numeroDoVooPouso) {
		VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
		
		String sql= "select* from Voo inner join VooChegadaGrupoI "
				+ "where idVoo= idVooChegadaGrupoI and dataPrevistaPouso= ? "
				+ "and numeroVooPouso= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setDate(1, dataPrevistaParaPouso);
			pstm.setInt(2, numeroDoVooPouso);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				
				vooChegadaGrupoI.setIdVooChegadaGrupoI(result.getInt("idvooChegadaGrupoI"));
				vooChegadaGrupoI.setIdHotran(result.getInt("idHotran"));
				vooChegadaGrupoI.setIdProprietarioCiaAerea(result.getInt("idProprietarioCiaAerea"));
				vooChegadaGrupoI.setNumeroVooPouso(result.getInt("numeroVooPouso"));
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaPouso")));
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooChegadaGrupoI.setSituacao(result.getString("situacao"));
				vooChegadaGrupoI.setOrigem(result.getString("origem"));
				vooChegadaGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooChegadaGrupoI.setNomeBox(result.getString("nomeBox"));
				vooChegadaGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooChegadaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				System.out.println("RETORNANDO: "+vooChegadaGrupoI);
				return vooChegadaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public VooChegadaGrupoI selecionarVooChegadaGrupoIByDataPrevistaParaPousoAndIdAeronave(int idAeronave, Date dataPrevistaParaPouso) {
		VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
		
		String sql= "select * from Voo inner join VooChegadaGrupoI "
				+ "where idVoo=idVooChegadaGrupoI and Aeronave_idAeronave=? "
				+ "and dataPrevistaPouso <= ? order by idVoo desc";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idAeronave);
			pstm.setDate(2, dataPrevistaParaPouso);
			
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				vooChegadaGrupoI= new VooChegadaGrupoI();
				vooChegadaGrupoI.setIdVooChegadaGrupoI(result.getInt("idVoo"));
				vooChegadaGrupoI.setIdHotran(result.getInt("idHotran"));
				vooChegadaGrupoI.setIdProprietarioCiaAerea(result.getInt("idProprietarioCiaAerea"));
				vooChegadaGrupoI.setNumeroVooPouso(result.getInt("numeroVooPouso"));
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaPouso")));
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooChegadaGrupoI.setSituacao(result.getString("situacao"));
				vooChegadaGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooChegadaGrupoI.setNomeBox(result.getString("nomeBox"));
				vooChegadaGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooChegadaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				
				return vooChegadaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public VooChegadaGrupoI selecionarVooChegadaGrupoI(int vooPouso, Date dataPrevista) {
		
		
		String sql= "select * from Voo inner join VooChegadaGrupoI "
				+ "where idVoo= idVooChegadaGrupoI and numeroVooPouso=? "
				+ "and dataPrevistaPouso=? ";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, vooPouso);
			pstm.setDate(2, dataPrevista);
			
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			if (result.next()) {
				
				VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
				
				vooChegadaGrupoI.setIdVoo(result.getInt("idVooChegadaGrupoI"));
				vooChegadaGrupoI.setIdVooChegadaGrupoI(result.getInt("idVoo"));
				vooChegadaGrupoI.setIdHotran(result.getInt("idHotran"));
				vooChegadaGrupoI.setIdProprietarioCiaAerea(result.getInt("idProprietarioCiaAerea"));
				vooChegadaGrupoI.setNumeroVooPouso(result.getInt("numeroVooPouso"));
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaPouso")));
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooChegadaGrupoI.setSituacao(result.getString("situacao"));
				vooChegadaGrupoI.setOrigem("origem");
				vooChegadaGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooChegadaGrupoI.setNomeBox(result.getString("nomeBox"));
				vooChegadaGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooChegadaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				
				return vooChegadaGrupoI;
			}
		}catch (Exception e){
			System.out.println("Falha na execucao da Consulta "+e.getMessage());
			e.printStackTrace();			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public VooChegadaGrupoI selecionarById(int idVooChegadaGrupoI) {
		VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
		
		String sql= "SELECT * FROM Voo inner join VooChegadaGrupoI WHERE idVooChegadaGrupoI= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idVooChegadaGrupoI);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				vooChegadaGrupoI.setIdVooChegadaGrupoI(result.getInt("idvooChegadaGrupoI"));
				vooChegadaGrupoI.setIdHotran(result.getInt("idHotran"));
				vooChegadaGrupoI.setIdProprietarioCiaAerea(result.getInt("idProprietarioCiaAerea"));
				vooChegadaGrupoI.setNumeroVooPouso(result.getInt("numeroVooPouso"));
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaPouso")));
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooChegadaGrupoI.setSituacao(result.getString("situacao"));
				vooChegadaGrupoI.setOrigem(result.getString("origem"));
				vooChegadaGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooChegadaGrupoI.setNomeBox(result.getString("nomeBox"));
				vooChegadaGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooChegadaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				
				return vooChegadaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public VooChegadaGrupoI selecionarByDataPrevistaPouso(Date dataPrevistaParaPouso) {
		VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
		
		String sql= "select * from Voo inner join VooChegadaGrupoI where idVoo= idVooChegadaGrupoI and dataPrevistaPouso= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setDate(1, dataPrevistaParaPouso);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				vooChegadaGrupoI.setIdVooChegadaGrupoI(result.getInt("idvooChegadaGrupoI"));
				vooChegadaGrupoI.setIdHotran(result.getInt("idHotran"));
				vooChegadaGrupoI.setIdProprietarioCiaAerea(result.getInt("idProprietarioCiaAerea"));
				vooChegadaGrupoI.setNumeroVooPouso(result.getInt("numeroDoVooPouso"));
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaParaPouso")));
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooChegadaGrupoI.setSituacao(result.getString("situacao"));
				vooChegadaGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooChegadaGrupoI.setNomeBox(result.getString("nomeBox"));
				vooChegadaGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooChegadaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				
				return vooChegadaGrupoI;
			}
		}catch (Exception e){
			return null;
			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public void alterar(VooChegadaGrupoI vooChegadaGrupoI) {
		String sql = "UPDATE VooChegadaGrupoI SET idHotran=?, idProprietarioCiaAerea= ?, "
				+ "numeroVooPouso= ?, dataPrevistaPouso= ?, horaPrevistaPouso= ?,"
				+ " dataConfirmadaPouso= ?, horaConfirmadaPouso= ?, situacao= ?,origem= ?,"
				+ "nomeCabeceira= ?, nomeEsteira=?, Hotran_idHotran=? WHERE idVooChegadaGrupoI=?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			
			pstm.setInt(1, vooChegadaGrupoI.getIdHotran());
			pstm.setInt(2, vooChegadaGrupoI.getIdProprietarioCiaAerea());
			pstm.setInt(3, vooChegadaGrupoI.getNumeroVooPouso());
			pstm.setDate(4, Converte.converterLocalDateToJavaSqlDate(vooChegadaGrupoI.getDataPrevistaParaPouso()));
			pstm.setTime(5, Converte.converterLocalTimeToJavaSqlTime(vooChegadaGrupoI.getHoraPrevistaParaPouso()));
			pstm.setDate(6, Converte.converterLocalDateToJavaSqlDate(vooChegadaGrupoI.getDataConfirmadaPouso()));
			pstm.setTime(7, Converte.converterLocalTimeToJavaSqlTime(vooChegadaGrupoI.getHoraConfirmadaPouso()));
			pstm.setString(8, vooChegadaGrupoI.getSituacao());
			pstm.setString(9, vooChegadaGrupoI.getOrigem());
			pstm.setInt(10, vooChegadaGrupoI.getNomeCabeceira());
			pstm.setString(11, vooChegadaGrupoI.getNomeEsteira());
			pstm.setInt(12, vooChegadaGrupoI.getIdHotran());
			pstm.setInt(13, vooChegadaGrupoI.getIdVooChegadaGrupoI());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo do grupo I "+e.getMessage());
			e.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	public void apagar(int idVooChegadaGrupoI){
		String sql = "DELETE FROM VooChegadaGrupoI WHERE idVooChegadaGrupoI=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idVooChegadaGrupoI);
			
			int linhas= pstm.executeUpdate();
			
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo do grupo I excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos do grupo I com este id: "+ idVooChegadaGrupoI);
			}finally {			
				try {
					if(pstm != null){
						pstm.close();
					}
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
}
