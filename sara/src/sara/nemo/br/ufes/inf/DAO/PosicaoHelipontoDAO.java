package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.PosicaoHeliponto;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class PosicaoHelipontoDAO {
	public void inserir(PosicaoHeliponto posicaoHeliponto)throws SQLException {
		String sql= "INSERT INTO PosicaoHeliponto(idPosicao, nome)"+
					"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, posicaoHeliponto.getIdPosicaoHeliponto());
			pstm.setString(2, posicaoHeliponto.getNome());
			
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Posicao do Heliponto cadastrada com sucesso");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Posicao invalida!");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT idPosicao, nome, tipoRecurso, localizacao, estaEmUso "
				+ "from sara.PosicaoHeliponto inner join sara.Recurso "
				+ "where PosicaoHeliponto.idPosicao= Recurso.idRecurso order by idRecurso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("nome")+"  "+ result.getString("tipoRecurso")
						+"  "+ result.getString("localizacao")+"  "+ result.getInt("estaEmUso"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe recurso cadastrado!");
		}
	}
	
	public ArrayList<String> selecionarNomes() {
		ArrayList<String> nomePosicaoHeliponto= new ArrayList<String>();
		String sql= "select * from PosicaoHeliponto order by nome ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				nomePosicaoHeliponto.add(result.getString("nome"));	
			}
			return (nomePosicaoHeliponto);
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existe posição do Heliponto cadastrada!");
			
		}
		return null;
	}
	
	public int selecionarIdDadoNome(String dadoNome) {
		String sql= "select * from PosicaoHeliponto where nome= dadoNome";
		
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
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			
		}
		return 0;
	}
	
	public PosicaoHeliponto selecionarById(int id) {
		PosicaoHeliponto posicaoHeliponto= new PosicaoHeliponto();
		String sql= "SELECT * FROM PosicaoHeliponto WHERE idPosicao= ?";
		
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
				posicaoHeliponto.setIdPosicaoHeliponto((result.getInt("idPosicao")));
				posicaoHeliponto.setNome((result.getString(("nome").toUpperCase())));
				return (posicaoHeliponto);
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe posicao no heliponto com este codigo!");
			e.printStackTrace();
			
		}
		return (null);
	}
	
	
	public void alterar(PosicaoHeliponto posicaoHeliponto)throws Exception {
		
		String sql = "UPDATE PosicaoHeliponto SET nome=? WHERE idPosicao=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, posicaoHeliponto.getNome());
			pstm.setInt(2, posicaoHeliponto.getIdPosicaoHeliponto());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados de posicao");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM PosicaoHeliponto WHERE idPosicao=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Posicão de heliponto excluida com sucesso!");
		}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem posicão de heliponto com este id: "+ id);
			}
		}
}
