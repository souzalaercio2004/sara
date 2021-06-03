package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Voo;

public class VooDAO {
	public void inserir(Voo voo)throws SQLException {
		String sql= "INSERT INTO Voo(Aeronave_idAeronave, Categoria_idCategoria, nomeBox ) VALUES(?, ?, ?)";
		
		Connection con= null;
		PreparedStatement pstm = null;
		System.out.println("id Aeronavex: "+ voo.getIdAeronave());
		System.out.println("id Categoriax: "+ voo.getIdCategoria());
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,  voo.getIdAeronave());
			pstm.setInt(2, voo.getIdCategoria());
			pstm.setString(3, voo.getNomeBox());
			pstm.execute();
			con.commit();
			System.out.println(" seu vou foi cadastrado com sucesso!!!!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve falha no Cadastro do voo " + e.getMessage());
			
		}finally {
			con.close();
		}
			
	}
	
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(Voo.idVoo) FROM sara.Voo";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				return (result.getInt(1));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos cadastrados!" + e.getMessage());
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
			
			if (result.next()) {
				voo.setIdVoo(result.getInt("idVoo"));
				voo.setIdAeronave(result.getInt("Aeronave_idAeronave"));
				voo.setIdCategoria(result.getInt("Categoria_idCategoria"));
				voo.setNomeBox(result.getString("nomeBox"));
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
		
		String sql = "UPDATE Voo SET Aeronave_idAeronave=?, Categoria_idCategoria=?, nomeBox=? WHERE idVoo= ?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, voo.getIdAeronave());
			pstm.setInt(2, voo.getIdCategoria());
			pstm.setString(3, voo.getNomeBox());
			pstm.setInt(4,  voo.getIdVoo());
			System.out.println("NOVO NOME BOX: "+voo.getNomeBox());
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
