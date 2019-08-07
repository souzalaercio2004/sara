package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class VooDAO {
	public void inserir(Voo voo)throws SQLException {
		String sql= "INSERT INTO Voo(Categoria_idCategoria, OcorrenciaVoo_idOcorrenciaVoo, dataPrevistaPouso, "
				+ "horaPrevistaPouso, dataPrevistaDecolagem, horaPrevistaDecolagem, situacao, origem, destino)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) System.out.println("Conexão bem sucedida" + con);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, voo.getCategoria().getId());
			pstm.setInt(2, voo.getOcorrenciaDoVoo().getIdOcorrenciaVoo());
			pstm.setDate(3, Date.valueOf(voo.getDataPrevistaParaPouso()));
			pstm.setTime(4, Time.valueOf(voo.getHoraPrevistaParaPouso()));
			pstm.setDate(5, Date.valueOf(voo.getDataPrevistaParaDecolagem()));
			pstm.setTime(6, Time.valueOf(voo.getHoraPrevistaParaDecolagem()));
			pstm.setString(7, voo.getSituacao());
			pstm.setObject(8, voo.getOrigem());
			pstm.setObject(9, voo.getDestino());
			
			pstm.execute();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo invalido!");
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM Voo ORDER BY dataPrevistaPouso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong(1) +"  "+result.getString("matricula")+"  "+ result.getString("tipoAsa"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!");
		}
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM Voo WHERE idVoo= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong("idVoo") +"  "+result.getString("Categoria_idCategoria")+"  "
						+ result.getString("OcorrenciaVoo_idOcorrenciaVoo")+"  "+result.getString("dataPrevistaPouso")+"  "
						+ result.getString("horaPrevistaPouso")+"  "+result.getString("dataPrevistaDecolagem")+"  "
						+ result.getString("horaPrevistaDecolagem")+"  "+result.getString("situacao")+"  "+result.getString("origem"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe voo cadastrad0 com este id! "+ id);
		}
	}
	public void alterar(Voo voo)throws Exception {
		
		String sql = "UPDATE Voo SET Categoria_idCategoria=?, OcorrenciaVoo_idOcorrenciaVoo=?, dataPrevistaPouso=?"
				+ " horaPrevistaPouso=?, dataPrevistaDecolagem=?, horaPrevistaDecolagem=? situacao=?, origem=? , destino=? WHERE idVoo=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, voo.getCategoria().getId());
			pstm.setInt(2, voo.getOcorrenciaDoVoo().getIdOcorrenciaVoo());
			pstm.setDate(3, Date.valueOf(voo.getDataPrevistaParaPouso()));
			pstm.setTime(4, Time.valueOf(voo.getHoraPrevistaParaPouso()));
			pstm.setDate(5, Date.valueOf(voo.getDataPrevistaParaDecolagem()));
			pstm.setTime(6, Time.valueOf(voo.getHoraPrevistaParaDecolagem()));
			pstm.setString(7, voo.getSituacao());
			pstm.setString(8, voo.getOrigem());
			pstm.setString(9, voo.getDestino());
	
			pstm.executeUpdate();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Voo WHERE idVoo=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos com este id: "+ id);
			}
		}
}
