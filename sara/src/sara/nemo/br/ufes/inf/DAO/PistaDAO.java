package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Pista;

public class PistaDAO {
	public void inserir(Pista pista)throws SQLException {
		String sql= "INSERT INTO Pista(idPista, nome, nomeCabeceira)"+
					"VALUES(?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, pista.getIdPista());
			pstm.setString(2, pista.getNome());
			pstm.setString(3, pista.getNomeCabeceira());
			pstm.execute();
			con.commit();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Pista invalida!");
			e.printStackTrace();
			con.rollback();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "select idPista, tipoRecurso, nome, localizacao, cabeceira, estaEmUso from sara.Recurso inner join sara.Pista where tipoRecurso= 'PISTA' AND idRecurso= idPista";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idPista") +"  "+result.getString("tipoRecurso")
				+"  "+ result.getString("nome")+"  "+ result.getString("localizacao")+"  "+ result.getString("cabeceira")
				+"  "+ result.getString("estaEmUso"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe pista cadastrada!");
		}
	}
	
	public ArrayList<String> selecionarNomeCabeceira() {
		ArrayList<String> nomePista= new ArrayList<String>();
		String sql= "select * from Pista order by cabeceira ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				nomePista.add(result.getString("cabeceira"));	
			}
			return (nomePista);
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			
		}
		return null;
	}
	
	public int selecionarIdDadoNome(String dadoNome) {
		String sql= "select * from Pista where nomeCabeceira= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, dadoNome);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				return (result.getInt(1));	
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			
		}
		return 0;
	}
	
	public Pista selecionarById(int id) {
		Pista pista= new Pista();
		String sql= "SELECT * FROM Pista WHERE idPista= ?";
		
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
				pista.setIdPista(Integer.valueOf(result.getString("idPista")));
				pista.setNome(result.getString("nome"));
				pista.setNomeCabeceira(result.getString("nomeCabeceira"));
				return (pista);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe pista com este id!");
			e.printStackTrace();
		}
		return (null);
	}
	
	public Pista selecionarPistaByIdVoo(int idVoo) {
		Pista pista= new Pista();
		String sql= "select * from Voo inner join OcorrenciaVoo inner join RecursoEmOcorrenciaVoo \n" + 
				"inner join Recurso inner join Pista where idVoo= Voo_idVoo \n" + 
				"and OcorrenciaVoo_idOcorrenciaVoo= idOcorrenciaVoo and Recurso_idRecurso=idRecurso \n" + 
				"and idRecurso= idPista and idVoo= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idVoo);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				pista.setIdPista(Integer.valueOf(result.getString("idPista")));
				pista.setNome(result.getString("nome"));
				pista.setNomeCabeceira(result.getString("nomeCabeceira"));
				return (pista);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe pista com este id!");
			e.printStackTrace();
		}
		return (null);
	}
	
	public Pista selecionarPistaEmUso() {
		Pista pista= new Pista();
		String sql= "select * from Recurso inner join Pista where idRecurso= idPista and estaEmUso = '1'";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				pista.setIdPista(result.getInt("idPista"));
				pista.setNome(result.getString("nome"));
				pista.setNomeCabeceira(result.getString("nomeCabeceira"));
				pista.setIdRecurso(result.getInt("idRecurso"));
				pista.setLocalizacao(result.getString("localizacao"));
				pista.setTipoRecurso(result.getString("tipoRecurso"));
				
				return pista;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe pista em uso definida");
			
		}
		return null;
	}
	
	public void alterar(Pista pista)throws Exception {
		
		String sql = "UPDATE Pista SET nome=?, nomeCabeceira=? WHERE idPista=?";
		Connection con= null;
		
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, pista.getNome());
			pstm.setString(2, pista.getNomeCabeceira());
			pstm.setInt(3, pista.getIdPista());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados da pista");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Pista WHERE idPista=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			con.commit();
			
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe pista com este codigo: "+ id);
				e.printStackTrace();
			}
		}
}
