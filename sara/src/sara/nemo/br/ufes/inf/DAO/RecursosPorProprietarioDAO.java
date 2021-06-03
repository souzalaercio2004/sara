package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Recurso;
import sara.nemo.br.ufes.inf.domain.RecursosPorProprietario;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosRecursosPorProprietario;

public class RecursosPorProprietarioDAO {
	
	public RecursosPorProprietarioDAO(){};
	
	public void inserir(RecursosPorProprietario recursosPorProprietario)throws SQLException {
		String sql= "INSERT INTO RecursosPorProprietario (Proprietario_idProprietario, Recurso_idRecurso, Prioridade)"+
				"VALUES(?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, recursosPorProprietario.getIdProprietario());
			pstm.setInt(2, recursosPorProprietario.getIdRecurso());
			pstm.setInt(3, recursosPorProprietario.getPrioridade());
			pstm.execute();
			con.commit();
		}catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Recurso por proprietário já esta cadastrado");
			
			
			con.rollback();
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Recurso por proprietario não cadastrado"+e1.getMessage());
			
			con.rollback();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "select idRecurso, nomeDoProprietario, tipoRecurso, nome, Prioridade, estaEmUso from PosicaoPatio inner join RecursosPorProprietario inner join Recurso inner join Proprietario where idPosicaoPatio= Recurso_idRecurso and idPosicaoPatio= idRecurso and Proprietario_idProprietario= idProprietario";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idRecurso")
						+" "+result.getString("nomeDoProprietario")
						+" "+result.getString("tipoRecurso")
						+" "+result.getString("nome") 
						+"  "+result.getInt("Prioridade")+
						"  "+result.getString("estaEmUso"));	
			}

			
		}catch (Exception e){
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
	
	public List<AcessoriosRecursosPorProprietario> selecionarLista() {
		String sql= "select idRecurso, tipoRecurso, nome, Prioridade, estaEmUso from PosicaoPatio"
				+ " inner join RecursosPorProprietario inner join Recurso"
				+ " where idPosicaoPatio= Recurso_idRecurso and idPosicaoPatio= idRecurso";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
			List<AcessoriosRecursosPorProprietario> recprop= new ArrayList<>();
			while (result.next()) {
				AcessoriosRecursosPorProprietario acessorios= new AcessoriosRecursosPorProprietario();
				
				acessorios.setIdRecurso(result.getInt("idRecurso"));
				acessorios.setTipoRecurso(result.getString("tipoRecurso"));
				acessorios.setNome(result.getString("nome"));
				acessorios.setPrioridade(result.getInt("Prioridade"));
				acessorios.setEstaEmUso(result.getString("estaEmUso"));
				
				recprop.add(acessorios);
			}
			con.close();
			return recprop;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public RecursosPorProprietario selecionarById(int idProprietario, int idRecurso) throws Exception {
		String sql= "SELECT * FROM RecursosPorProprietario where Proprietario_idProprietario=? and Recurso_idRecurso=?";
		RecursosPorProprietario recursosPorProprietario= new RecursosPorProprietario();
		Connection con= null;
		PreparedStatement pstm = null;
		
		con= ConnectionFactory.criarConexao();
		con.setAutoCommit(false);
		pstm= con.prepareStatement(sql);
		pstm.setInt(1, idProprietario);
		pstm.setInt(2, idRecurso);
		ResultSet result = pstm.executeQuery();
		//con.commit();
		while (result.next()) {
			recursosPorProprietario.setIdProprietario(result.getInt("Proprietario_idProprietario"));
			recursosPorProprietario.setIdRecurso(result.getInt("Recurso_idRecurso"));
			recursosPorProprietario.setPrioridade(result.getInt("prioridade"));
			con.close();
			return (recursosPorProprietario);
		}
		con.close();
		
		return (null);
	}
	
	public Recurso selecionarByTipoRecurso(String tipoRecurso) throws Exception {
		String sql= "SELECT * FROM RecursosPorProprietario WHERE tipoRecurso= ?";
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
			con.close();
			return (recurso);
		}
		con.close();
		return (null);
	}
	
public void alterar(RecursosPorProprietario recursosPorProprietario)throws Exception {
		
		String sql = "update RecursosPorProprietario set Prioridade= ? where Recurso_idRecurso= ? and Proprietario_idProprietario=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, recursosPorProprietario.getPrioridade());
			pstm.setInt(2, recursosPorProprietario.getIdRecurso());
			pstm.setInt(3, recursosPorProprietario.getIdProprietario());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados" +e.getMessage());
			e.printStackTrace();
			
		}finally {
			con.close();
		}		
	}
	
	public void apagar(int idRecurso, int idProprietario){
		String sql = "DELETE FROM RecursosPorProprietario WHERE Recurso_idRecurso=? and Proprietario_idProprietario=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idRecurso);
			pstm.setInt(2, idProprietario);
			
			pstm.executeUpdate();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe Recurso por proprietario com estes dados");
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
