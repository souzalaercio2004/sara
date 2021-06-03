package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;

public class ProprietarioCiaAereaDAO {
	
	public ProprietarioCiaAereaDAO() {};
	
	public void inserir(ProprietarioCiaAerea proprietarioCiaAerea)throws SQLException {
		String sql= "INSERT INTO ProprietarioCiaAerea(idCiaAerea, sigla)"+
					"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, proprietarioCiaAerea.getIdCiaAerea());
			pstm.setString(2, proprietarioCiaAerea.getSiglaCiaAerea());
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Proprietario de cia aérea cadastrado com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Proprietario de cia aérea invalido! "+ 35);
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM ProprietarioCiaAerea ORDER BY idCiaAerea ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idCiaAerea") + "\t" +String.format("%-15s", result.getString("sigla")));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem proprietarios de companhias aéreas cadastrados!");
			e.printStackTrace();
			
		}
	}
	
	public ProprietarioCiaAerea selecionarBySigla(String sigla) {
		ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
		String sql= "SELECT * FROM ProprietarioCiaAerea WHERE sigla=? ";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, sigla);
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			while (result.next()) {
				proprietarioCiaAerea.setIdCiaAerea(result.getInt(1));
				proprietarioCiaAerea.setSiglaCiaAerea(result.getString("sigla"));
				return (proprietarioCiaAerea);
				//System.out.println(result.getInt(1) + "\t" +String.format("%-15s", result.getString("sigla"))+ "\t" +String.format("%-15s", result.getString("nomeDoProprietario")));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Sigla invalida!");
			e.printStackTrace();
		}
		return (null);
	}
	
	public ProprietarioCiaAerea selecionarById(int id) {
		ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
		String sql= "SELECT * FROM ProprietarioCiaAerea inner join Proprietario"
				+ " WHERE idCiaAerea= idProprietario and idCiaAerea= ?";
		
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
				proprietarioCiaAerea.setIdCiaAerea(result.getInt(1));
				//proprietarioCiaAerea.setIdProprietario(result.getInt("Proprietario_idProprietario"));
				proprietarioCiaAerea.setSiglaCiaAerea(result.getString("sigla"));
				
				
				//System.out.println(result.getInt("idCiaAerea")+"  "+ result.getString("sigla")+"  "+ result.getString("nomeDoProprietario"));
				return(proprietarioCiaAerea);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario de cia aérea cadastrado com este id!"+ id);
		}
		return (null);
	}
	
	public ArrayList<String> selecionarSigla() {
		String sql= "SELECT * FROM ProprietarioCiaAerea ORDER BY sigla";
		ArrayList<String> siglas= new ArrayList<String>();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			//pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				siglas.add(result.getString("sigla"));
				//System.out.println(result.getInt(1) +"  "+result.getInt("idCiaAerea")+"  "+ result.getInt("sigla")+"  "+ result.getInt("idAeronave")+"  "+ result.getInt("idProprietario"));
			}
			return (siglas);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario de cia aérea cadastrado com este id!");
		}
		return (null);
	}
	
	public void alterar (ProprietarioCiaAerea proprietarioCiaAerea) throws Exception {
		
		String sql = "UPDATE ProprietarioCiaAerea SET sigla=? WHERE idCiaAerea=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, proprietarioCiaAerea.getIdCiaAerea());
			pstm.setString(2, proprietarioCiaAerea.getSiglaCiaAerea());
			
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização do proprietario de cia aérea");
			System.out.println(e.getMessage());
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(long id){
		String sql = "DELETE FROM ProprietarioCiaAerea WHERE idCiaAerea=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			int linhas= pstm.executeUpdate();
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Aeronave excluida com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem aeronaves com esta matricula: "+ id);
			}
		}
}
