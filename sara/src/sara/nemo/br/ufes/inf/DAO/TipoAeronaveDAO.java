package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.TipoAeronave;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class TipoAeronaveDAO {
	
	public TipoAeronaveDAO() {};
	
	public void inserir(TipoAeronave tipoAeronave)throws SQLException {
		String sql= "INSERT INTO TipoAeronave(equipamento, comprimento, envergadura, pmd)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, tipoAeronave.getEquipamento());
			pstm.setFloat(2, tipoAeronave.getComprimento());
			pstm.setFloat(3, tipoAeronave.getEnvergadura());
			pstm.setObject(4, tipoAeronave.getPmd());
			pstm.execute();
			con.commit();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Tipo de aeronave inválido!");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM TipoAeronave ORDER BY equipamento ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			while (result.next()) {
				System.out.println(String.format("%-8s", result.getInt(1)) +String.format("%-10s", result.getString("equipamento"))+ result.getString("comprimento") +
						"\t"+result.getString("envergadura")+"\t"+ result.getString("pmd"));
				
			} 
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem tipos de aeronave cadastrados!");
		}
	}
	
	public ArrayList<String> selecionarEquipamento() { //retorna o equipamento
		ArrayList<String> equip= new ArrayList<String>();
		
		String sql= "SELECT * FROM TipoAeronave ORDER BY equipamento ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				equip.add(result.getString("equipamento"));
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem tipos de aeronave cadastrados!");
			return null;
		}
		return (equip);
	}
	
	public String selecionarNomeEquipamento(int idEquipamento) { //retorna o equipamento
			
		
		String sql= "SELECT TipoAeronave.equipamento FROM sara.TipoAeronave where idTipoAeronave= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idEquipamento);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				String nomeEquipamento= result.getString("equipamento");
				return (nomeEquipamento);
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Equipamentos cadastrados!");
			
		}
		return (null);
	}
	
public int selecionarId(String equip) { //retorna o equipamento
			
		
		String sql= "SELECT * FROM sara.TipoAeronave where equipamento= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setString(1, equip);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				return (result.getInt(1));
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Equipamento não cadastrado!");
			e.printStackTrace();
			
		}
		return (0);
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM TipoAeronave WHERE idTipoAeronave= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong("idAeronave") +"  "+result.getString("equipamento")+"  "+result.getString("comprimento")+
						"  "+ result.getString("envergadura")+"  "+result.getString("pmd"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe tipo de aeronave cadastrado com este Id!");
		}
	}
	public void alterar(TipoAeronave tipoAeronave)throws Exception {
		
		String sql = "UPDATE TipoAeronave SET equipamento=?, comprimento=?, envergadura=? , pmd=? WHERE idTipoAeronave=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setString(1, tipoAeronave.getEquipamento());
			pstm.setFloat(2, tipoAeronave.getComprimento());
			pstm.setFloat(3, tipoAeronave.getEnvergadura());
			pstm.setFloat(4, tipoAeronave.getPmd());
			pstm.setInt(5, tipoAeronave.getIdTipoAeronave());
			pstm.executeUpdate();
			
			con.commit();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos tipos de aeronave ");
			e.printStackTrace();
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM TipoAeronave WHERE idTipoAeronave=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, " Tipo de aeronave excluido com sucesso!");

			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe tipo aeronave com este id: "+ id);
				e.printStackTrace();
				
			}
		}
}
