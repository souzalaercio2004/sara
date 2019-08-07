package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.Recurso;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class RecursoDAO {
	
	public RecursoDAO(){};
	
	public void inserir(Recurso recurso)throws SQLException {
		String sql= "INSERT INTO Recurso (tipoRecurso, localizacao, estaEmUso)"+
				"VALUES(?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, recurso.getTipoRecurso());
			pstm.setString(2, recurso.getLocalizacao());
			pstm.setBoolean(3, recurso.getEstaEmUso());
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Recurso não foi cadastrado! 34");
			e.printStackTrace();
			con.rollback();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM Recurso ORDER BY tipoRecurso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
	
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("tipoRecurso")+"  "+result.getString("localizacao")+"  "+result.getInt("estaEmUso"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem recursos cadastrados!" + 52);
		}
	}
	public ArrayList<String> obterRecursos() {
		ArrayList<String> tipoDeRecurso= new ArrayList<String>();
		String sql= "SELECT * FROM Recurso ORDER BY tipoRecurso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
	
			while (result.next()) {
				tipoDeRecurso.add(result.getString("tipoRecurso"));
				//System.out.println(result.getInt(1) +"  "+result.getString("tipoRecurso"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem recursos cadastrados!" + 52);
		}
		return (tipoDeRecurso);
	}
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idRecurso) FROM Recurso";
		
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
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			e.printStackTrace();	
		}
		return (0);
	}
	
	public Recurso selecionarById(int id) throws Exception {
		String sql= "SELECT * FROM Recurso WHERE idRecurso= ?";
		Recurso recurso= new Recurso();
		Connection con= null;
		PreparedStatement pstm = null;
		
		con= ConnectionFactory.criarConexao();
		con.setAutoCommit(false);
		pstm= con.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet result = pstm.executeQuery();
		con.commit();
		while (result.next()) {
			recurso.setIdRecurso(result.getInt("idRecurso"));
			recurso.setTipoRecurso(result.getString("tipoRecurso"));
			return (recurso);
		}
		return (null);
	}
	
	public Recurso selecionarByTipoRecurso(String tipoRecurso) throws Exception {
		String sql= "SELECT * FROM Recurso WHERE tipoRecurso= ?";
		Recurso recurso= new Recurso();
		Connection con= null;
		PreparedStatement pstm = null;
		
		con= ConnectionFactory.criarConexao();
		con.setAutoCommit(false);
		pstm= con.prepareStatement(sql);
		pstm.setString(1, tipoRecurso);
		ResultSet result = pstm.executeQuery();
		con.commit();
		if (result.next()) {
			recurso.setIdRecurso(Integer.valueOf(result.getInt("idRecurso")));
			recurso.setTipoRecurso(result.getString("tipoRecurso"));
			recurso.setLocalizacao(result.getString(("localizacao")));
			recurso.setEstaEmUso(Boolean.valueOf(result.getBoolean("estaEmUso")));
			return (recurso);
		}
		return (null);
	}
	
public void alterar(Recurso recurso )throws Exception {
		
		String sql = "UPDATE Recurso SET tipoRecurso=?, localizacao=?, estaEmUso=? WHERE idRecurso=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, recurso.getTipoRecurso());
			pstm.setString(2, recurso.getLocalizacao());
			pstm.setBoolean(3, recurso.getEstaEmUso());
			pstm.setInt(4, recurso.getIdRecurso());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização do recurso");
			e.printStackTrace();
			
		}finally {
			con.close();
		}		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Recurso WHERE idRecurso=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Recurso excluido com sucesso!");
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem Recursos com este id: "+id);
				e.printStackTrace();
			}
		}
}
