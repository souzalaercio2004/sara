package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.VooGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;

public class VooGrupoIDAO {
	public void inserir(VooGrupoI vooGrupoI)throws SQLException {
		String sql= "INSERT INTO VooGrupoI(idVooGrupoI, Hotran_idHotran, numeroDoVooPouso, numeroDoVooDecolagem,"
				+ " horaConfirmadaPouso, horaConfirmadaDecolagem, ProprietarioCiaAerea_idCiaAerea, nomeCabeceira, nomeBox, nomeEsteira)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooGrupoI.getIdVooGrupoI());
			pstm.setInt(2, vooGrupoI.getIdHotran());
			pstm.setInt(3, vooGrupoI.getNumeroVooPouso());
			pstm.setInt(4, vooGrupoI.getNumeroVooDecolagem());
			
			if (vooGrupoI.getHoraConfirmadaPouso() == null) {
				pstm.setTime(5, null);
			}else pstm.setTime(5, Time.valueOf(vooGrupoI.getHoraConfirmadaPouso())); // No inicio é igual ao horarioPevisto
			
			if (vooGrupoI.getHoraConfirmadaDecolagem()== null) {
				pstm.setTime(6, null);
			}else pstm.setTime(6, Time.valueOf(vooGrupoI.getHoraConfirmadaDecolagem()));
			
			pstm.setInt(7, vooGrupoI.getIdProprietarioCiaAerea());
			pstm.setInt(8, vooGrupoI.getNomeCabeceira());
			pstm.setString(9, vooGrupoI.getNomeBox());
			pstm.setString(10, vooGrupoI.getNomeEsteira());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo do GrupoI invalido! "+ e.getMessage());
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		//String sql= "SELECT * FROM VooGrupoI ORDER BY idVooGrupoI ASC";
		String sql= "SELECT sigla, numeroDoVooPouso, dataPrevistaPouso, horaPrevistaPouso, "
				+ "matricula, situacao, equipamento, numeroDoVooDecolagem , nomeCabeceira, "
				+ "nomeBox, nomeEsteira"
				+ "FROM Voo inner join VooGrupoI inner join Aeronave inner join TipoAeronave "
				+ "inner join ProprietarioCiaAerea where idVoo= idVooGrupoI "
				+ "and idAeronave= Aeronave_idAeronave and idProprietario= idCiaAerea "
				+ "and idTipoAeronave=TipoAeronave_idTipoAeronave order by idVooGrupoI ASC";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(
					result.getString("sigla")+ " "+
					result.getInt("numeroDoVooPouso")+" "+
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
		}
	}
	
	public VooGrupoI selecionarVooGrupoIPouso(Date dataPrevistaPouso, int numeroVooPouso) {
		VooGrupoI vooGrupoI= new VooGrupoI();
		
		String sql= "select* from Voo inner join VooGrupoI where idVoo= idVooGrupoI and dataPrevistaPouso= ? and numeroDoVooPouso= ?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setDate(1, dataPrevistaPouso);
			pstm.setInt(2, numeroVooPouso);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				vooGrupoI.setIdHotran(result.getInt("idHotran"));
				vooGrupoI.setIdVooGrupoI(result.getInt("idVooGrupoI"));
				vooGrupoI.setNumeroVooPouso(result.getInt("numeroDoVooPouso"));
				vooGrupoI.setNumeroVooDecolagem(result.getInt("numeroDoVooDecolagem"));
				vooGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooGrupoI.setIdProprietarioCiaAerea(result.getInt("ProprietarioCiaAerea_idCiaAerea"));
				vooGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooGrupoI.setNomeBox(result.getString("nomeBox"));
				vooGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				vooGrupoI.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				return vooGrupoI;
			}
		}catch (Exception e){
			return null;
			
		}
		return null;
	}
	public VooGrupoI selecionarById(int id) {
		VooGrupoI vooGrupoI= new VooGrupoI();
		
		String sql= "SELECT * FROM VooGrupoI WHERE idVooGrupoI= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				vooGrupoI.setIdHotran(result.getInt("idHotran"));
				vooGrupoI.setIdVooGrupoI(result.getInt("idVooGrupoI"));
				vooGrupoI.setNumeroVooPouso(result.getInt("numeroDoVooPouso"));
				vooGrupoI.setNumeroVooDecolagem(result.getInt("numeroDoVooDecolagem"));
				vooGrupoI.setDataConfirmadaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataConfirmadaPouso")));
				vooGrupoI.setHoraConfirmadaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaConfirmadaPouso")));
				vooGrupoI.setIdProprietarioCiaAerea(result.getInt("ProprietarioCiaAerea_idCiaAerea"));
				vooGrupoI.setNomeCabeceira(result.getInt("nomeCabeceira"));
				vooGrupoI.setNomeBox(result.getString("nomeBox"));
				vooGrupoI.setNomeEsteira(result.getString("nomeEsteira"));
				
				return vooGrupoI;
			}
		}catch (Exception e){
			return null;
			
		}
		return null;
	}
	public void alterar(VooGrupoI vooGrupoI)throws Exception {
		System.out.println("Alterando voo");
		String sql = "UPDATE VooGrupoI SET Voo_idVoo=?, Hotran_idHotran=?, numeroDoVooPouso=?, numeroDoVooDecolagem= ?, horaConfirmadaPouso=?, horaConfirmadaDecolagem=?, ProprietarioCiaAerea_idCiaAerea= ?, nomeCabeceira=?, nomeBox=?, nomeEsteira=? WHERE idVooGrupoI=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,  vooGrupoI.getIdVoo());
			pstm.setInt(2,  vooGrupoI.getIdHotran());
			pstm.setInt(3,  vooGrupoI.getNumeroVooPouso());
			pstm.setInt(4,  vooGrupoI.getNumeroVooDecolagem());
			pstm.setTime(5, Time.valueOf(vooGrupoI.getHoraConfirmadaPouso()));
			pstm.setTime(6, Time.valueOf(vooGrupoI.getHoraConfirmadaDecolagem()));
			pstm.setInt(7,  vooGrupoI.getIdProprietarioCiaAerea());
			
			pstm.setInt(8,  vooGrupoI.getNomeCabeceira());
			pstm.setString(9,  vooGrupoI.getNomeBox());
			pstm.setString(10,  vooGrupoI.getNomeEsteira());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo do grupo I");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM VooGrupoI WHERE id=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo do grupo I excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos do grupo I com este id: "+ id);
			}
		}
}
