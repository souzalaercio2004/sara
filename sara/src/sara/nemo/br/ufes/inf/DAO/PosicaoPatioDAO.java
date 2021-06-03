package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;

public class PosicaoPatioDAO {
	public void inserir(PosicaoPatio posicaoPatio)throws SQLException {
		String sql= "INSERT INTO PosicaoPatio(idPosicaoPatio, nome, comprimentoToleravel, envergaduraToleravel, "
				+ "aeronaveCritica)"+
				"VALUES(?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, posicaoPatio.getIdPosicaoPatio());
			pstm.setString(2, posicaoPatio.getNome());
			pstm.setFloat(3, posicaoPatio.getComprimentoToleravel());
			pstm.setFloat(4, posicaoPatio.getEnvergaduratoleravel());
			pstm.setString	(5, posicaoPatio.getAeronaveCritica());
			pstm.execute();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Posicão no patio cadastrada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Posicão invalida!");
			e.printStackTrace();
			
		}finally {
		     con.close();
		  }
	}
	
	public void selecionar() {
		String sql= "select idPosicaoPatio, tipoRecurso, localizacao, nome, comprimentoToleravel, envergaduraToleravel,"
				+ "aeronaveCritica, estaEmUso from sara.PosicaoPatio inner join sara.Recurso "
				+ "where PosicaoPatio.idPosicaoPatio= Recurso.idRecurso order by idPosicaoPatio ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idPosicaoPatio") +"  "+result.getString("tipoRecurso")+"  "+result.getString("localizacao")+"  "
					+ result.getString("nome")+"  "+result.getString("comprimentoToleravel")+"  "
					+ result.getString("envergaduraToleravel")+"  "+result.getString("aeronaveCritica")+"  "
					+ result.getString("estaEmUso"));
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas!");
		}
		
	}
	
	//Seleciona a posicao mais prioritaria para o proprietario ainda não alocada a outra aeronave
	public PosicaoPatio selecionarPosicaoPatio(int idProprietario) {
		PosicaoPatio posicao= new PosicaoPatio();
		String sql= "select * from Recurso inner join RecursosPorProprietario "
				+ "inner join PosicaoPatio inner join Proprietario "
				+ "where Recurso_idRecurso= idRecurso and idPosicaoPatio= idRecurso "
				+ "and idProprietario= Proprietario_idProprietario and estaEmUso= 'false' "
				+ "and idProprietario= ? order by Prioridade asc";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,idProprietario);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				posicao.setIdPosicaoPatio(result.getInt("idPosicaoPatio"));
				posicao.setNome(result.getString("nome"));
				posicao.setComprimentoToleravel(result.getFloat("comprimentoToleravel"));
				posicao.setEnvergaduratoleravel(result.getFloat("envergaduraToleravel"));
				posicao.setAeronaveCritica(result.getString("aeronaveCritica"));
				return posicao;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas para este proprietario !"+idProprietario );
		}
		return (null);	
		
	}
	
	public PosicaoPatio selecionarPosicaoPatioById(int idPosicaoPatio) {
		PosicaoPatio posicao= new PosicaoPatio();
		String sql= "select * from PosicaoPatio where idPosicaoPatio= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,idPosicaoPatio);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				posicao.setIdPosicaoPatio(result.getInt("idPosicaoPatio"));
				posicao.setNome(result.getString("nome"));
				posicao.setComprimentoToleravel(result.getFloat("comprimentoToleravel"));
				posicao.setEnvergaduratoleravel(result.getFloat("envergaduraToleravel"));
				posicao.setAeronaveCritica(result.getString("aeronaveCritica"));
				return posicao;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas com este codigo "+idPosicaoPatio);
		} 
		return (null);
		
	}
	
	public PosicaoPatio selecionarPosicaoPatioByNome(String nome) {
		PosicaoPatio posicao= new PosicaoPatio();
		String sql= "select * from PosicaoPatio where nome= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setString(1,nome);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				posicao.setIdPosicaoPatio(result.getInt("idPosicaoPatio"));
				posicao.setNome(result.getString("nome"));
				posicao.setComprimentoToleravel(result.getFloat("comprimentoToleravel"));
				posicao.setEnvergaduratoleravel(result.getFloat("envergaduraToleravel"));
				posicao.setAeronaveCritica(result.getString("aeronaveCritica"));
				return posicao;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas com este nome "+nome);
		} 
		return (null);
		
	}
	
	public PosicaoPatio selecionarPosicaoPatioByIdVoo(int idVoo) {
		PosicaoPatio posicao= new PosicaoPatio();
		String sql= "select * from Voo inner join OcorrenciaVoo inner join RecursoEmOcorrenciaVoo \n" + 
				"inner join Recurso inner join PosicaoPatio where idVoo= Voo_idVoo \n" + 
				"and OcorrenciaVoo_idOcorrenciaVoo= idOcorrenciaVoo and Recurso_idRecurso=idRecurso \n" + 
				"and idRecurso= idPosicaoPatio and idVoo= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,idVoo);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				posicao.setIdPosicaoPatio(result.getInt("idPosicaoPatio"));
				posicao.setNome(result.getString("nome"));
				posicao.setComprimentoToleravel(result.getFloat("comprimentoToleravel"));
				posicao.setEnvergaduratoleravel(result.getFloat("envergaduraToleravel"));
				posicao.setAeronaveCritica(result.getString("aeronaveCritica"));
				return posicao;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas para este Código de voo "+idVoo);
		} 
		return (null);
		
	}
	
	public ArrayList<String> selecionarNomes() {
		ArrayList<String> nomePosicaoPatio= new ArrayList<String>();
		String sql= "select * from PosicaoPatio order by nome ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				nomePosicaoPatio.add(result.getString("nome"));	
			}
			return (nomePosicaoPatio);
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existe posicao do pátio cadastrada!");
			
		}
		return null;
	}
	
	public ArrayList<String> selecionarNomeDaPosicao() {
		ArrayList<String> posicoes= new ArrayList<String>();
		String sql= "SELECT * FROM PosicaoPatio ORDER BY idPosicaoPatio ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
		
		pstm= con.prepareStatement(sql);
		ResultSet result = pstm.executeQuery(sql);
		
			while (result.next()) {
				posicoes.add(result.getString("nome"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (posicoes);
	}
	
	public int selecionarIdDadoNome(String dadoNome) {
		String sql= "select * from PosicaoPatio where nome= ?";
		
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
	
	public PosicaoPatio selecionarById(int id) throws SQLException {
		PosicaoPatio posicaoPatio= new PosicaoPatio();
		String sql= "SELECT * FROM sara.PosicaoPatio WHERE idPosicaoPatio= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				posicaoPatio.setIdPosicaoPatio(Integer.valueOf(result.getInt("idPosicaoPatio")));
				//posicaoPatio.setIdRecurso(Integer.valueOf(result.getString("idRecurso")));
				posicaoPatio.setNome(result.getString("nome"));
				posicaoPatio.setComprimentoToleravel(Float.valueOf(result.getString("comprimentoToleravel")));
				posicaoPatio.setEnvergaduratoleravel(Float.valueOf(result.getString("envergaduraToleravel")));
				posicaoPatio.setAeronaveCritica(result.getString("aeronaveCritica"));
				return (posicaoPatio);
				//System.out.println(result.getInt(1) +"  "+result.getString("idRecurso")+"  "+ result.getString("nome")+result.getString("comprimentoToleravel")+"  "+ result.getString("envergaduraToleravel")+result.getString("aeronaveCritica")+"  "+ result.getString("estaEmUso"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe posicao cadastrada com este codigo!");
			e.printStackTrace();
		}
		return (null);
	}
	public void alterar(PosicaoPatio posicaoPatio)throws Exception {
		
		String sql = "UPDATE sara.PosicaoPatio SET nome=?, comprimentoToleravel=?, "
				+ "envergaduraToleravel=?, aeronaveCritica=? WHERE idPosicaoPatio=?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, posicaoPatio.getNome());
			pstm.setFloat(2, posicaoPatio.getComprimentoToleravel());
			pstm.setFloat(3, posicaoPatio.getEnvergaduratoleravel());
			pstm.setString(4, posicaoPatio.getAeronaveCritica());
			pstm.setInt(5, posicaoPatio.getIdPosicaoPatio());
			
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Posicão no patio atualizada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados da posição");
			e.printStackTrace();
			
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM sara.PosicaoPatio WHERE idPosicaoPatio=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Posição excluida com sucesso!");
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem posições com este id: "+ id);
			}
		}
}
