package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Aeronave;

public class AeronaveDAO {
	
	
	public void inserir(Aeronave aeronave)throws SQLException {
		String sql= "INSERT INTO Aeronave (matricula, tipoAsa, TipoAeronave_idTipoAeronave, idProprietario)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			String matricula= aeronave.getMatricula().trim();
			
			pstm= con.prepareStatement(sql);
			pstm.setString(1, matricula);
			pstm.setString(2, aeronave.getTipoAsa());
			pstm.setInt(3, aeronave.getIdTipoAeronave());
			pstm.setInt(4, aeronave.getIdProprietario());
			pstm.execute();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Aeronave cadastrada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Aeronave invalida!");
			e.printStackTrace();
			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterar(Aeronave aeronave )throws Exception {
		
		String sql = "UPDATE Aeronave SET matricula=?, tipoAsa=?, TipoAeronave_idTipoAeronave=?, idProprietario=?"
				+ " WHERE idAeronave=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			String matricula= aeronave.getMatricula().trim();
			
			pstm.setString(1, matricula);
			pstm.setString(2, aeronave.getTipoAsa());
			pstm.setInt(3, aeronave.getIdTipoAeronave());
			pstm.setInt(4, aeronave.getIdProprietario());
			pstm.setInt(5, aeronave.getIdAeronave());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização da aeronave");
			e.printStackTrace();
			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				aero.setIdAeronave(result.getInt(1));
				
				String matricula= result.getString("matricula").trim();
				aero.setMatricula(matricula);
				aero.setTipoAsa(result.getString("tipoAsa"));
				aero.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
				aero.setIdProprietario(result.getInt("idProprietario"));
				
				aeronave.add(aero);
			}
			return aeronave;
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas!");
			e.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Aeronave selecionarAeronaveByMatricula(String matr){
		
		Aeronave aero= new Aeronave();
		String sql= "SELECT * FROM Aeronave WHERE matricula= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			String matricula= matr.trim();
			pstm.setString(1, matricula);
			
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			if (result.next()) {
				aero.setIdAeronave(result.getInt("idAeronave"));
				aero.setMatricula(result.getString("matricula"));
				aero.setTipoAsa(result.getString("tipoAsa"));
				aero.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
				aero.setIdProprietario(result.getInt("idProprietario"));
				
				con.close();
				return (aero);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Nenhuma aeronave cadastrada com matricula: "+ matr);
			
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	};
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idAeronave) FROM Aeronave";
		
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
			JOptionPane.showMessageDialog(null, "Não existem aeronaves cadastradas");
			e.printStackTrace();	
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (0);
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
			return (mat);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados!");
			e1.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return(null);
	}
	
	public Aeronave selecionarAeronaveById(int id) {
		Aeronave aeronave= new Aeronave();
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
			
			if (result.next()) {
				aeronave.setIdAeronave(result.getInt("idAeronave"));
				aeronave.setIdTipoAeronave(result.getInt("TipoAeronave_idTipoAeronave"));
				aeronave.setMatricula(result.getString("matricula"));
				aeronave.setTipoAsa(result.getString("tipoAsa"));
				aeronave.setIdProprietario(result.getInt("idProprietario"));
	
				return (aeronave);
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados!");
			e1.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return(null);
		
	}
	
	public String selecionarEquipamento(String matricula) {
		String sql= "select * from Aeronave inner join TipoAeronave where TipoAeronave_idTipoAeronave= idTipoAeronave  and matricula= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			matricula= matricula.trim();
			pstm.setString(1, matricula);
			ResultSet result = pstm.executeQuery();
			con.commit();
			
			if (result.next()) {
				String equip= result.getString("equipamento");
				con.close();
				return (equip);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados! 298 ");
			e1.printStackTrace();
		}finally {			
			try {
				if(pstm != null){
					pstm.close();
				}
				con.close();
			} catch (SQLException e) {
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
			}finally {			
				try {
					if(pstm != null){
						pstm.close();
					}
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}

