package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.Proprietario;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class ProprietarioDAO {
	
	public ProprietarioDAO() {};
	
	public void inserir(Proprietario proprietario)throws SQLException {
		String sql= "INSERT INTO Proprietario(nomeDoProprietario)"+
					"VALUES(?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setString(1, proprietario.getNomeProprietario());
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Proprietario cadastrado com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Proprietario invalido!");
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM Proprietario ORDER BY idProprietario ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" +String.format("%-15s", result.getString("nomeDoProprietario")));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem proprietarios cadastrados!");
			e.printStackTrace();
			
		}
	}
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idProprietario) AS maxId FROM Proprietario";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				return (Integer.valueOf(result.getInt(1)));
			}else return (0);
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario cadastrado!");
			e.printStackTrace();	
		}
		return (0);
	}
	public Proprietario selecionarById(int id) {
		Proprietario proprietario= new Proprietario();
		String sql= "SELECT * FROM Proprietario WHERE idProprietario= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			if (result.next()) {
				proprietario.setIdProprietario(result.getInt(1));
				proprietario.setNomeProprietario(result.getString("nomeDoProprietario"));
				
				return(proprietario);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario cadastrado com este id!"+ id);
		}
		return (null);
	}
	
	public int selecionarIdByName(String nomeProp) {
		String sql= "SELECT * FROM Proprietario WHERE nomeDoProprietario= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, nomeProp);
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			if (result.next()) {
				
				return(result.getInt(1));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario cadastrado com este nome!");
		}
		return (0);
	}
	
	public ArrayList<String> selecionarProprietario() { //retorna o equipamento
		ArrayList<String> prop= new ArrayList<String>();
		
		String sql= "SELECT * FROM Proprietario ORDER BY nomeDoProprietario ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				prop.add(result.getString("nomeDoProprietario"));
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem proprietários cadastrados!");
			return null;
		}
		return (prop);
	}
	
	public void alterar (Proprietario proprietario) throws Exception {
		
		String sql = "UPDATE Proprietario SET nomeDoProprietario=? WHERE idProprietario=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, proprietario.getNomeProprietario());
			pstm.setInt(2, proprietario.getIdProprietario());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Falha na atualização do proprietario");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Proprietario WHERE idProprietario=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			int linhas= pstm.executeUpdate();
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Proprietario excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem proprietarios com este id: "+ id);
			}
		}
}
