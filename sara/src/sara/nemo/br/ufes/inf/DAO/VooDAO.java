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
import sara.nemo.br.ufes.view.Converte;

public class VooDAO {
	public void inserir(Voo voo)throws SQLException {
		String sql= "INSERT INTO Voo(Aeronave_idAeronave, dataPrevistaPouso, horaPrevistaPouso, "
				+ "dataPrevistaDecolagem, horaPrevistaDecolagem, situacao, origem, destino, Categoria_idCategoria)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, voo.getIdAeronave());
			pstm.setDate(2, Date.valueOf(voo.getDataPrevistaParaPouso()));
			pstm.setTime(3, Time.valueOf(voo.getHoraPrevistaParaPouso()));
			pstm.setDate(4, Date.valueOf(voo.getDataPrevistaParaDecolagem()));
			pstm.setTime(5, Time.valueOf(voo.getHoraPrevistaParaDecolagem()));
			pstm.setString(6, voo.getSituacao());
			pstm.setString(7, voo.getOrigem());
			pstm.setString(8, voo.getDestino());
			pstm.setInt(9,  voo.getIdCategoria());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo invalido!" + 40);
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
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idVoo) FROM Voo";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				return (result.getInt(1));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos cadastrados!" + 77);
			e.printStackTrace();	
		}
		return (0);
	}
	
	
	public Voo selecionarById(int id) {
		String sql= "SELECT * FROM Voo WHERE idVoo= ?";
		Voo voo= new Voo();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				voo.setIdVoo(result.getInt("idVoo"));
				voo.setIdAeronave(result.getInt("Aeronave_IdAeronave"));
				voo.setDataPrevistaParaPouso(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaPouso")));
				voo.setHoraPrevistaParaPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaPouso")));
				voo.setDataPrevistaParaDecolagem(Converte.converterJavaSqlDateToLocalDate(result.getDate("dataPrevistaDecolagem")));
				voo.setHoraPrevistaParaDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horaPrevistaDecolagem")));
				voo.setSituacao(result.getString("situacao"));
				voo.setOrigem(result.getString("origem"));
				voo.setDestino(result.getString("destino"));
				voo.setIdCategoria(result.getInt("Categoria_idCategoria"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe voo cadastrado com este id! "+ id);
			e.printStackTrace();
			return null;
		}
		return voo;
	}
	
	public void alterar(Voo voo)throws Exception {
		
		String sql = "UPDATE Voo SET Aeronave_idAeronave= ?, dataPrevistaPouso= ?, horaPrevistaPouso= ?, dataPrevistaDecolagem= ?,"
				+ "horaPrevistaDecolagem= ?, situacao= ?, origem= ?, destino= ?, Categoria_idCategoria= ? WHERE idVoo= ?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, voo.getIdAeronave());
			
			pstm.setDate(2, Date.valueOf(voo.getDataPrevistaParaPouso()));
			pstm.setTime(3, Time.valueOf(voo.getHoraPrevistaParaPouso()));
			pstm.setDate(4, Date.valueOf(voo.getDataPrevistaParaDecolagem()));
			pstm.setTime(5, Time.valueOf(voo.getHoraPrevistaParaDecolagem()));
			pstm.setString(6, voo.getSituacao());
			pstm.setString(7, voo.getOrigem());
			pstm.setString(8, voo.getDestino());
			pstm.setInt(9, voo.getIdCategoria());
			pstm.setInt(10,  voo.getIdVoo());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo ");
			e.printStackTrace();
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
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Voo excluido com sucesso!");
			con.commit();
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos com este id: "+ id);
				e.printStackTrace();
			}
		}
}
