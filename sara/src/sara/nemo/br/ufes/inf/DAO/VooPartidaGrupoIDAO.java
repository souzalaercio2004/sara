package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.VooPartidaGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;

public class VooPartidaGrupoIDAO {
	public void inserir(VooPartidaGrupoI vooPartidaGrupoI)throws SQLException {
		String sql= "INSERT INTO VooPartidaGrupoI(idVooPartidaGrupoI, numeroVooDecolagem, "
				+ "dataPrevistaParaDecolagem, horaPrevistaParaDecolagem, dataConfirmadaDecolagem, "
				+ "horaConfirmadaDecolagem, destino, situacao, "
				+ "portaoDeEmbarque, Hotran_idHotran, Voo_idVoo)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooPartidaGrupoI.getIdVooPartidaGrupoI());
			pstm.setInt(2, vooPartidaGrupoI.getNumeroVooDecolagem());
			pstm.setDate(3, Converte.converterLocalDateToJavaSqlDate(vooPartidaGrupoI.getDataPrevistaParaDecolagem()));
			pstm.setTime(4, Converte.converterLocalTimeToJavaSqlTime(vooPartidaGrupoI.getHoraPrevistaParaDecolagem()));
			System.out.println("Data Confirmada de decolagem: "+vooPartidaGrupoI.getDataConfirmadaDecolagem());
		
			pstm.setDate(5, Converte.converterLocalDateToJavaSqlDate(vooPartidaGrupoI.getDataConfirmadaDecolagem()));
			pstm.setTime(6, Converte.converterLocalTimeToJavaSqlTime(vooPartidaGrupoI.getHoraConfirmadaDecolagem()));
			pstm.setString(7,vooPartidaGrupoI.getDestino());
			System.out.println("Situacao Partida: "+ vooPartidaGrupoI.getSituacaoPartida());
			pstm.setString(8, vooPartidaGrupoI.getSituacaoPartida());
			pstm.setString(9, vooPartidaGrupoI.getPortaoDeEmbarque());
			pstm.setInt(10, vooPartidaGrupoI.getHotran_idHotran());
			System.out.println("Voo_idVoo: "+vooPartidaGrupoI.getIdVooPartidaGrupoI());
			pstm.setInt(11, vooPartidaGrupoI.getIdVooPartidaGrupoI());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo Partida GrupoI invalido! "+ e.getMessage());
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT idVooPartidaGrupoI,sigla, numeroVooDecolagem, dataPrevistaParaDecolagem, "
				+ "horaPrevistaParaDecolagem, situacao, dataConfirmadaDecolagem, "
				+ "horaConfirmadaDecolagem, matricula, equipamento, destino, portaoDeEmbarque, "
				+ "Hotran_idHotran FROM Voo inner join VooPartidaGrupoI inner join Aeronave "
				+ "inner join TipoAeronave inner join ProprietarioCiaAerea "
				+ "where idVoo= idVooPartidaGrupoI and idAeronave= Aeronave_idAeronave "
				+ "and idProprietario= idCiaAerea and idTipoAeronave=TipoAeronave_idTipoAeronave "
				+ "order by idVooPartidaGrupoI ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(
					result.getInt("idVooPartidaGrupoI")+ " "+
					result.getString("sigla")+ " "+
					result.getInt("numeroVooDecolagem")+" "+
					result.getString("dataPrevistaParaDecolagem")+" "+
					result.getString("horaPrevistaParaDecolagem")+" "+
					result.getString("situacao")+" "+
					result.getString("dataConfirmadaParaDecolagem")+" "+
					result.getString("horaConfirmadaParaDecolagem")+" "+
					result.getString("matricula")+" "+ 
					result.getString("equipamento")+" "+
					result.getString("destino")+" "+
					result.getString("portaoDeEmbarque")+" "+
					result.getInt("nomeCabeceira")+" "+
					result.getString("nomeBox"));
					
				
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoI cadastrados!"+ e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public VooPartidaGrupoI selecionarVooGrupoIPartida(Date dataPrevistaDecolagem, int numeroVooDecolagem) {
		VooPartidaGrupoI vooPartidaGrupoI= new VooPartidaGrupoI();
		
		String sql= "select* from Voo inner join VooPartidaGrupoI where idVoo= idVooPartidaGrupoI and dataPrevistaParaDecolagem= ? and numeroVooDecolagem= ?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setDate(1, dataPrevistaDecolagem);
			pstm.setInt(2, numeroVooDecolagem);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				
				vooPartidaGrupoI.setIdVooPartidaGrupoI(result.getInt("idvooPartidaGrupoI"));
				vooPartidaGrupoI.setHotran_idHotran(result.getInt("Hotran_idHotran"));
				vooPartidaGrupoI.setDataConfirmadaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaDecolagem")));
				vooPartidaGrupoI.setDataPrevistaParaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaParaDecolagem")));
				vooPartidaGrupoI.setDestino(result.getString("destino"));
				vooPartidaGrupoI.setHoraConfirmadaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaDecolagem")));
				vooPartidaGrupoI.setHoraPrevistaParaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaParaDecolagem")));
				vooPartidaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				vooPartidaGrupoI.setIdCategoria(result.getInt("Categoria_idCategoria"));
				vooPartidaGrupoI.setIdVoo(result.getInt("idVoo"));;
				vooPartidaGrupoI.setNumeroVooDecolagem(result.getInt("numeroVooDecolagem"));
				vooPartidaGrupoI.setPortaoDeEmbarque(result.getString("portaoDeEmbarque"));
				vooPartidaGrupoI.setSituacaoPartida(result.getString("situacao"));
				
				return vooPartidaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public VooPartidaGrupoI selecionarVooGrupoIPartidabyDate(Date dataPrevistaDecolagem) {
		VooPartidaGrupoI vooPartidaGrupoI= new VooPartidaGrupoI();
		
		String sql= "select* from Voo inner join VooPartidaGrupoI where idVoo= idVooPartidaGrupoI and dataPrevistaParaDecolagem= ?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setDate(1, dataPrevistaDecolagem);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				
				vooPartidaGrupoI.setIdVooPartidaGrupoI(result.getInt("idvooPartidaGrupoI"));
				vooPartidaGrupoI.setHotran_idHotran(result.getInt("Hotran_idHotran"));
				vooPartidaGrupoI.setDataConfirmadaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaDecolagem")));
				vooPartidaGrupoI.setDataPrevistaParaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaParaDecolagem")));
				vooPartidaGrupoI.setDestino(result.getString("destino"));
				vooPartidaGrupoI.setHoraConfirmadaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaDecolagem")));
				vooPartidaGrupoI.setHoraPrevistaParaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaParaDecolagem")));
				vooPartidaGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				vooPartidaGrupoI.setIdCategoria(result.getInt("Categoria_idCategoria"));
				vooPartidaGrupoI.setIdVoo(result.getInt("idVoo"));;
				vooPartidaGrupoI.setNumeroVooDecolagem(result.getInt("numeroVooDecolagem"));
				vooPartidaGrupoI.setPortaoDeEmbarque(result.getString("portaoDeEmbarque"));
				vooPartidaGrupoI.setSituacaoPartida(result.getString("situacao"));
				
				return vooPartidaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public VooPartidaGrupoI selecionarById(int idVooPartidaGrupoI) {
		VooPartidaGrupoI vooPartidaGrupoI= new VooPartidaGrupoI();
		
		String sql= "SELECT * FROM Voo inner join VooPartidaGrupoI WHERE idVoo= idVooPartidaGrupoI and idVooPartidaGrupoI= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idVooPartidaGrupoI);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				vooPartidaGrupoI.setIdVooPartidaGrupoI(result.getInt("idvooPartidaGrupoI"));
				vooPartidaGrupoI.setHotran_idHotran(result.getInt("Hotran_idHotran"));
				vooPartidaGrupoI.setDataConfirmadaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaDecolagem")));
				vooPartidaGrupoI.setDataPrevistaParaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaParaDecolagem")));
				vooPartidaGrupoI.setDestino(result.getString("destino"));
				vooPartidaGrupoI.setHoraConfirmadaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaDecolagem")));
				vooPartidaGrupoI.setHoraPrevistaParaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaParaDecolagem")));
				vooPartidaGrupoI.setNumeroVooDecolagem(result.getInt("numeroVooDecolagem"));
				vooPartidaGrupoI.setPortaoDeEmbarque(result.getString("portaoDeEmbarque"));
				vooPartidaGrupoI.setSituacaoPartida(result.getString("situacao"));
				
				
				return vooPartidaGrupoI;
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public void alterar(VooPartidaGrupoI vooPartidaGrupoI) {
		String sql = "UPDATE VooPartidaGrupoI SET numeroVooDecolagem=?, "
				+ "dataPrevistaParaDecolagem= ?, horaPrevistaParaDecolagem=?, "
				+ "dataConfirmadaDecolagem=?, horaConfirmadaDecolagem=?, "
				+ "destino=?, situacao= ?, portaoDeEmbarque=?, Hotran_idHotran=? "
				+ "WHERE idVooPartidaGrupoI=?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			
			
			pstm.setInt(1, vooPartidaGrupoI.getNumeroVooDecolagem());
			pstm.setDate(2, Converte.converterLocalDateToJavaSqlDate(vooPartidaGrupoI.getDataPrevistaParaDecolagem()));
			pstm.setTime(3, Converte.converterLocalTimeToJavaSqlTime(vooPartidaGrupoI.getHoraPrevistaParaDecolagem()));
			pstm.setDate(4, Converte.converterLocalDateToJavaSqlDate(vooPartidaGrupoI.getDataConfirmadaDecolagem()));
			pstm.setTime(5, Converte.converterLocalTimeToJavaSqlTime(vooPartidaGrupoI.getHoraConfirmadaDecolagem()));
			pstm.setString(6,vooPartidaGrupoI.getDestino());
			pstm.setString(7, vooPartidaGrupoI.getSituacaoPartida());
			pstm.setString(8, vooPartidaGrupoI.getPortaoDeEmbarque());
			pstm.setInt(9, vooPartidaGrupoI.getHotran_idHotran());
			pstm.setInt(10, vooPartidaGrupoI.getIdVooPartidaGrupoI());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo partida grupo I");
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void apagar(int idVooPartidaGrupoI){
		String sql = "DELETE FROM VooPartidaGrupoI WHERE idVooPartidaGrupoI=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idVooPartidaGrupoI);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo Partida do grupo I excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe voos Partida do grupo I com este id: "+ idVooPartidaGrupoI);
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
