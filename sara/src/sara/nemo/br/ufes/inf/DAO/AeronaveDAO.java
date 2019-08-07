package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class AeronaveDAO {
	
	
	public void inserir(Aeronave aeronave)throws SQLException {
		String sql= "INSERT INTO Aeronave (matricula, tipoAsa, TipoAeronave_idTipoAeronave)"+
					"VALUES(?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setString(1, aeronave.getMatricula());
			pstm.setString(2, aeronave.getTipoAsa());
			pstm.setInt(3, aeronave.getIdTipoAeronave());
			pstm.execute();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Aeronave cadastrada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Aeronave invalida!");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
	}
	
public void alterar(Aeronave aeronave )throws Exception {
		
		String sql = "UPDATE Aeronave SET matricula=?, tipoAsa=?, TipoAeronave_idTipoAeronave=? WHERE idAeronave=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, aeronave.getMatricula());
			pstm.setString(2, aeronave.getTipoAsa());
			pstm.setInt(3, aeronave.getIdTipoAeronave());
			pstm.setInt(4, aeronave.getId());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização da aeronave");
			e.printStackTrace();
			
		}finally {
			con.close();
		}		
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM Aeronave ORDER BY idAeronave ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				String nomeEquipamento=tipoAeronaveDAO.selecionarNomeEquipamento(result.getInt("TipoAeronave_idTipoAeronave"));
				System.out.println(result.getInt(1) +"  "+String.format("%-12s", result.getString("matricula"))+
						" "+ String.format("%-12s", result.getString("tipoAsa"))+
						"\t"+ nomeEquipamento);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!");
		}
	}
	
	public List<Aeronave> selecionarList() {
		String sql= "SELECT * FROM Aeronave ORDER BY idAeronave ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		
		List<Aeronave> aeronave= new ArrayList<>();
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				Aeronave aero= new Aeronave();
				aero.setId(result.getInt("idAeronave"));
				aero.setMatricula(result.getString("matricula"));
				aero.setTipoAsa(result.getString("tipoAsa"));
				aero.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
				
				aeronave.add(aero);
			}
			return aeronave;
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!");
		}
		return null;
	}
	public Aeronave selecionarByMatricula(String matr) {
		Aeronave aeronave= new Aeronave();
		String sql= "SELECT * FROM sara.Aeronave WHERE Aeronave.matricula = ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		ResultSet result= null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados!");
			e1.printStackTrace();
		}
		
		if (con != null) {
			try {
				pstm= con.prepareStatement(sql);
			} catch (SQLException e) {
				System.out.println("Falha na conexão com o banco de dados");
				e.printStackTrace();
			}
			
			try {
				pstm.setString(1, matr);
			} catch (SQLException e) {
				System.out.println("valor de matricula invalido "+ matr);
				e.printStackTrace();
			}
			try {
				result = pstm.executeQuery();
				con.commit();
			} catch (SQLException e) {
				System.out.println("Falha na execução da consulta "+ result);
				e.printStackTrace();
			}
			try {
				if (result.next()) {
					aeronave.setId(result.getInt(1));
					aeronave.setMatricula(result.getString("matricula"));
					aeronave.setTipoAsa(result.getString("tipoAsa"));
					aeronave.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
					return (aeronave);
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return(null);
	}
	
	public String selecionarMatriculaById(int id) {
		String mat= null;
		String sql= "SELECT * FROM sara.Aeronave WHERE Aeronave.idAeronave = ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		ResultSet result= null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			result = pstm.executeQuery();
			con.commit();
			
			mat= result.getString("matricula");
			System.out.println("A matricula da aeronave selecionada e: "+ mat);
			return (mat);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados!");
			e1.printStackTrace();
		}
		return(null);
	}
	
	public Aeronave selecionarAeronaveById(int id) {
		Aeronave aeronave= new Aeronave();
		//String mat= null;
		String sql= "SELECT * FROM sara.Aeronave WHERE Aeronave.idAeronave = ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		ResultSet result= null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados!");
			e1.printStackTrace();
		}
		
		if (con != null) {
			try {
				pstm= con.prepareStatement(sql);
			} catch (SQLException e) {
				System.out.println("Falha na conexão com o banco de dados");
				e.printStackTrace();
			}
			
			try {
				pstm.setInt(1, id);
			} catch (SQLException e) {
				System.out.println("valor de id invalido "+ id);
				e.printStackTrace();
			}
			try {
				result = pstm.executeQuery();
				con.commit();
			} catch (SQLException e) {
				System.out.println("Falha na execução da consulta "+ result);
				e.printStackTrace();
			}
			try {
				if (result.next()) {
					aeronave.setId(result.getInt("idAeronave"));
					aeronave.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
					aeronave.setMatricula(result.getString("matricula"));
					aeronave.setTipoAsa(result.getString("tipoAsa"));
		
					return (aeronave);
				}
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Não existe aeronave com este id: "+ id);
				e.printStackTrace();
			}
			
		}
		return(null);
		
	}
	
	public void apagar(String matricula){
		String sql = "DELETE FROM Aeronave WHERE matricula = ? ";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, matricula);
			pstm.executeUpdate(); 
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Aeronave excluida com sucesso!");
			
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem aeronaves com esta matricula: "+matricula);
			}
		}
}

