package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Esteira;

public class EsteiraDAO {
	
	public void inserir(Esteira esteira)throws SQLException {
		String sql= "INSERT INTO Esteira(idEsteiraDeBagagem, nome)"+
					"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, esteira.getIdEsteira());
			pstm.setString(2, esteira.getNome());
			pstm.execute();
			con.commit();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Recurso invalido!");
			e.printStackTrace();
			con.rollback();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT idRecurso, tipoRecurso, localizacao, nome, estaEmUso "
				+ "FROM sara.Recurso inner join sara.Esteira where Recurso.idRecurso= Esteira.idEsteiraDeBagagem "
				+ "ORDER BY nome ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			if (result.next()) {
				while (result.next()) {
					System.out.println(result.getInt("idRecurso")+" "+result.getString("tipoRecurso")+
							" "+result.getString("localizacao")+" "+result.getString("nome")+" "+result.getBoolean("estaEmUso"));
				}
			}else JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	//Seleciona a esteira mais prioritaria para o proprietario ainda não alocada a outra aeronave
	public Esteira selecionarEsteira(int idProprietario) {
		Esteira esteira= new Esteira();
		String sql= "select * from Recurso inner join RecursosPorProprietario "
				+ "inner join Esteira inner join Proprietario "
				+ "where Recurso_idRecurso= idRecurso and idRecurso= idEsteiraDeBagagem "
				+ "and Proprietario_idProprietario= idProprietario "
				+ "and idProprietario=?  and estaEmUso = 'false' order by Prioridade asc;";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,idProprietario);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				esteira.setIdEsteira(result.getInt("idEsteiraDeBagagem"));
				esteira.setIdRecurso(result.getInt("idRecurso"));
				esteira.setEstaEmUso(result.getBoolean("estaEmUso"));
				esteira.setLocalizacao(result.getString("localizacao"));
				esteira.setNome(result.getString("nome"));
				esteira.setTipoRecurso(result.getString("tipoRecurso"));
				
				return esteira;
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Posições cadastradas para este proprietario !"+idProprietario );
		}
		return (null);	
		
	}
	
	public ArrayList<String> selecionarNomes() {
		ArrayList<String> nomeEsteira= new ArrayList<String>();
		String sql= "select * from Esteira order by nome ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				nomeEsteira.add(result.getString("nome"));	
			}
			return (nomeEsteira);
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			
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
	public int selecionarIdDadoNome(String dadoNome) {
		String sql= "select * from Esteira where nome= ?";
		
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
				return (result.getInt("idEsteiraDeBagagem"));	
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existem esteiras cadastradas!");
			
		}
		return 0;
	}
	public Esteira selecionarById(int id) {
		Esteira esteira= new Esteira();
		
		String sql= "SELECT * FROM Esteira WHERE idEsteiraDeBagagem= ?";
		
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
				esteira.setIdEsteira(result.getInt("idEsteiraDeBagagem"));
				esteira.setNome(result.getString("nome"));
				System.out.printf("%d    \t %d  \n", result.getInt(1), result.getInt("nome"));
			}
			return (esteira);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Esta esteira não está cadastrada!");
			return (null);
		}
		
	}
	
	public Esteira selecionarEsteiraByIdVoo(int idVoo) {
		Esteira esteira= new Esteira();
		
		String sql= "select * from Voo inner join OcorrenciaVoo inner join RecursoEmOcorrenciaVoo \n" + 
				"inner join Recurso inner join Esteira where idVoo= Voo_idVoo \n" + 
				"and OcorrenciaVoo_idOcorrenciaVoo= idOcorrenciaVoo and Recurso_idRecurso=idRecurso \n" + 
				"and idRecurso= idEsteiraDeBagagem and idVoo= ?";
		
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
				esteira.setIdEsteira(result.getInt("idEsteiraDeBagagem"));
				esteira.setNome(result.getString("nome"));
			}
			return (esteira);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Nenhuma esteira alocada para este Voo "+idVoo);
		}
		return  null;
	}
	
	public Esteira selecionarByNomeDaEsteira(String nome) {
		Esteira esteira= new Esteira();
		String sql= "SELECT * FROM Esteira WHERE nome = ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setString(1, nome);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				esteira.setIdRecurso(result.getInt(1));
				esteira.setNome(result.getString("nome"));
				return (esteira);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Esta esteira não está cadastrada!");
		}
		return (null);
	}
	
	public void alterar(Esteira esteira)throws Exception {
		
		String sql = "UPDATE Esteira SET nome=? WHERE idEsteiraDeBagagem=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, esteira.getNome());
			pstm.setInt(2, esteira.getIdEsteira());
			pstm.executeUpdate();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Esteira atualizada com sucesso "+ esteira.toString());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados da esteira");
			e.printStackTrace();
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Esteira WHERE idEsteiraDeBagagem=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			
			con.commit();
			JOptionPane.showMessageDialog(null, "Esteira excluida com sucesso!");
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe esteira com este codigo: "+id);
			}
		}
}
