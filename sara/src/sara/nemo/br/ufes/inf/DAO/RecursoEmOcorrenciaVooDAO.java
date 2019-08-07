package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.RecursoEmOcorrenciaVoo;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class RecursoEmOcorrenciaVooDAO {
	public void inserir(RecursoEmOcorrenciaVoo RecursoEmOcorrenciaVoo)throws SQLException {
		String sql= "INSERT INTO RecursoEmOcorrenciaVoo(Recurso_idRecurso, OcorrenciaVoo_idOcorrenciaVoo)"+
					"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, RecursoEmOcorrenciaVoo.getIdRecurso());
			pstm.setInt(2, RecursoEmOcorrenciaVoo.getIdOcorrenciaVoo());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Dados invalidos!");
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM RecursoEmOcorrenciaVoo ORDER BY Recurso_idRecurso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("Recurso_idRecurso") +"  "+result.getInt("OcorrenciaVoo_idOcorrenciaVoo"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem dados cadastrados!");
		}
	}
	
	public RecursoEmOcorrenciaVoo selecionarById(int id) {
		RecursoEmOcorrenciaVoo RecursoEmOcorrenciaVoo= new RecursoEmOcorrenciaVoo();
		String sql= "SELECT * FROM RecursoEmOcorrenciaVoo WHERE Recurso_idRecurso= ?";
		
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
				RecursoEmOcorrenciaVoo.setIdRecurso(Integer.valueOf(result.getString("Recurso_idRecurso")));
				RecursoEmOcorrenciaVoo.setIdOcorrenciaVoo(Integer.valueOf(result.getString("OcorrenciaVoo_idOcorrenciaVoo")));
				
				
				System.out.println(result.getInt("Recurso_idRecurso") +"  "+result.getInt("OcorrenciaVoo_idOcorrenciaVoo"));
				return (RecursoEmOcorrenciaVoo);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não há dados com este id!");
			e.printStackTrace();
		}
		return (null);
	}
	public void alterar(RecursoEmOcorrenciaVoo RecursoEmOcorrenciaVoo)throws Exception {
		
		String sql = "UPDATE RecursoEmOcorrenciaVoo SET Recurso_idRecurso=?, OcorrenciaVoo_idOcorrenciaVoo=?";		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, RecursoEmOcorrenciaVoo.getIdRecurso());
			pstm.setInt(2, RecursoEmOcorrenciaVoo.getIdOcorrenciaVoo());
			
			pstm.executeUpdate();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int idRecurso, int idOcorrenciaVoo){
		String sql = "DELETE FROM RecursoEmOcorrenciaVoo WHERE Recurso_idRecurso = ? AND OcorrenciaVoo_idOcorrenciaVoo= ? ";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idRecurso);
			pstm.setInt(2, idOcorrenciaVoo);
			pstm.executeUpdate(); 
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
			
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Dados Invalidos ");
			}
		}
	
}
