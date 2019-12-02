package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.acessorios.AcessoriosOcorrenciaVoo;
import sara.nemo.br.ufes.inf.domain.OcorrenciaVoo;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;
import sara.nemo.br.ufes.view.Converte;

public class OcorrenciaVooDAO {
	public void inserir(OcorrenciaVoo ocorrenciaVoo)throws SQLException {
		String sql= "INSERT INTO OcorrenciaVoo(Aeronave_idAeronave, data, hora, situacao)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, ocorrenciaVoo.getIdAeronave());
			pstm.setDate(2, Converte.converterLocalDateToJavaSqlDate(ocorrenciaVoo.getData()));
			pstm.setTime(3, Converte.converterLocalTimeToJavaSqlTime(ocorrenciaVoo.getHora()));
			pstm.setString(4, ocorrenciaVoo.getSituacao());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Ocorrência de voo invalida!");
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	public List<AcessoriosOcorrenciaVoo> selecionarLista() {
		String sql= "select idOcorrenciaVoo, matricula, equipamento, data, hora, situacao "
				+ "from OcorrenciaVoo inner join Aeronave inner join TipoAeronave "
				+ "where Aeronave_idAeronave= idAeronave and"
				+ " idTipoAeronave= TipoAeronave_idTipoAeronave ORDER BY idOcorrenciaVoo ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		List<AcessoriosOcorrenciaVoo> lista= new ArrayList<AcessoriosOcorrenciaVoo>();
		AcessoriosOcorrenciaVoo acessorios;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				acessorios= new AcessoriosOcorrenciaVoo();
				acessorios.setIdOcorrenciaVoo(result.getInt("idOcorrenciaVoo"));
				acessorios.setMatricula(result.getString("matricula"));
				acessorios.setEquipamento(result.getString("equipamento"));
				acessorios.setData(Converte.converterJavaSqlDateToLocalDate(result.getDate("data")));
				acessorios.setHora(Converte.converterJavaSqlTimeToLocalTime(result.getTime("hora")));
				acessorios.setSituacao(result.getString("situacao"));
				lista.add(acessorios);
				
			}
			con.commit();
			return lista;
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem este tipo de ocorrência cadastrada!");
			e.printStackTrace();
			
		}
		return null;
	}
	
	public OcorrenciaVoo selecionarById(int id) {
		OcorrenciaVoo ocorrenciaVoo= new OcorrenciaVoo();
		String sql= "SELECT * FROM OcorrenciaVoo WHERE idOcorrenciaVoo= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				ocorrenciaVoo.setIdOcorrenciaVoo(result.getInt("idOcorrenciaVoo"));
				ocorrenciaVoo.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				ocorrenciaVoo.setData(Converte.converterJavaSqlDateToLocalDate(result.getDate("data")));
				ocorrenciaVoo.setHora(Converte.converterJavaSqlTimeToLocalTime(result.getTime("hora")));
				ocorrenciaVoo.setSituacao(result.getString("situacao"));
			}
			return ocorrenciaVoo;
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe ocorrencia cadastrada com este id!");
			e.printStackTrace();
			
		}
		return null;
	}
	public void alterar(OcorrenciaVoo ocorrenciaVoo)throws Exception {
		
		String sql = "UPDATE OcorrenciaVoo SET Aeronave_idAeronave=? , data=? , hora=? , situacao=? WHERE idOcorrenciaVoo=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, ocorrenciaVoo.getIdAeronave());
			pstm.setDate(2, Date.valueOf(ocorrenciaVoo.getData()));
			pstm.setTime(3, Time.valueOf(ocorrenciaVoo.getHora()));
			pstm.setString(4, ocorrenciaVoo.getSituacao());
			pstm.setInt(5,  ocorrenciaVoo.getIdOcorrenciaVoo());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização da ocorrencia");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM OcorrenciaVoo WHERE idOcorrenciaVoo=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			con.commit();
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe ocorrência de voo com este id: "+ id);
			}
		}
}
