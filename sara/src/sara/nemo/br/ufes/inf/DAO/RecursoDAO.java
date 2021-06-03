package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Esteira;
import sara.nemo.br.ufes.inf.domain.Pista;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;
import sara.nemo.br.ufes.inf.domain.PosicaoHeliponto;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.Recurso;

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

	public Recurso selecionarById(int id) throws Exception {
		String sql= "SELECT * FROM Recurso WHERE idRecurso= ?";
		Recurso recurso= new Recurso();
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
				recurso.setEstaEmUso(result.getBoolean("estaEmUso"));
				recurso.setIdRecurso(result.getInt("idRecurso"));
				recurso.setLocalizacao(result.getString("localizacao"));
				recurso.setTipoRecurso(result.getString("tipoRecurso"));
				return (recurso);
			}
		}catch (SQLException e) {
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
		return (null);
	}

	public Recurso selecionarByTipoRecurso(String tipoRecurso) throws Exception {
		String sql= "SELECT * FROM Recurso WHERE tipoRecurso= ?";
		Recurso recurso= new Recurso();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setString(1, tipoRecurso);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				recurso.setIdRecurso(Integer.valueOf(result.getInt("idRecurso")));
				recurso.setTipoRecurso(result.getString("tipoRecurso"));
				recurso.setLocalizacao(result.getString(("localizacao")));
				recurso.setEstaEmUso(Boolean.valueOf(result.getBoolean("estaEmUso")));
				return (recurso);
			}
		}catch(SQLException e) {
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
		return (null);
	}

	public Esteira selecionarEsteiraDadoIdRecurso(int idRec) throws Exception {
		String sql= "select * from Recurso inner join Esteira where idRecurso= idEsteiraDeBagagem and idRecurso= ?";
		Esteira esteira= new Esteira();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, idRec);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				esteira.setEstaEmUso(result.getBoolean("estaEmUso"));
				esteira.setIdEsteira(result.getInt("idEsteiraDeBagagem"));
				esteira.setLocalizacao(result.getString("localizacao"));
				esteira.setNome(result.getString("nome"));
				esteira.setTipoRecurso(result.getString("tipoRecurso"));
				return (esteira);
			}
		}catch (SQLException e) {
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
		return (null);
	}

	public PosicaoHeliponto selecionarPosicaoHelipontoDadoIdRecurso(int idRec) throws Exception {
		String sql= "select * from Recurso inner join PosicaoHeliponto where idRecurso= idPosicaoHeliponto and idRecurso= ?";
		PosicaoHeliponto posicaoHeliponto= new PosicaoHeliponto();
		Connection con= null;
		PreparedStatement pstm = null;

		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, idRec);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				posicaoHeliponto.setEstaEmUso(result.getBoolean("estaEmUso"));
				posicaoHeliponto.setIdPosicaoHeliponto(result.getInt("idPosicaoHeliponto"));
				posicaoHeliponto.setLocalizacao(result.getString("localizacao"));
				posicaoHeliponto.setNome(result.getString("nome"));
				posicaoHeliponto.setTipoRecurso(result.getString("tipoRecurso"));
				return (posicaoHeliponto);
			}
		}catch (SQLException e) {
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
		return (null);
	}

	public Pista selecionarPistaDadoIdRecurso(int idRec) throws Exception {
		String sql= "select * from Recurso inner join Pista where idRecurso= idPista and idRecurso= ?";
		Pista pista= new Pista();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, idRec);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				pista.setEstaEmUso(result.getBoolean("estaEmUso"));
				pista.setIdPista(result.getInt("idPosicaoHeliponto"));
				pista.setLocalizacao(result.getString("localizacao"));
				pista.setNome(result.getString("nome"));
				pista.setTipoRecurso(result.getString("tipoRecurso"));
				return (pista);
			}
		}catch (SQLException e) {
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
		return (null);
	}

	public PortaoDeEmbarque selecionarPortaoDeEmbarqueDadoIdRecurso(int idRec) throws Exception {
		String sql= "select * from Recurso inner join PortaoDeEmbarque where idRecurso= idPortaoEmbarque and idRecurso= ?";
		PortaoDeEmbarque portaoDeEmbarque= new PortaoDeEmbarque();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, idRec);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				portaoDeEmbarque.setEstaEmUso(result.getBoolean("estaEmUso"));
				portaoDeEmbarque.setIdPortaoDeEmbarque(result.getInt("idPortaoEmbarque"));
				portaoDeEmbarque.setLocalizacao(result.getString("localizacao"));
				portaoDeEmbarque.setNome(result.getString("nome"));
				portaoDeEmbarque.setTipoRecurso(result.getString("tipoRecurso"));
				return (portaoDeEmbarque);
			}
		}catch(SQLException e) {
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
		return (null);
	}

	public PosicaoPatio selecionarPosicaoPatioDadoIdRecurso(int idRec) throws Exception {
		String sql= "select * from Recurso inner join PosicaoPatio where idRecurso= idPortaoEmbarque and idRecurso= ?";
		PosicaoPatio posicaoPatio= new PosicaoPatio();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setInt(1, idRec);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {

				posicaoPatio.setEstaEmUso(result.getBoolean("estaEmUso"));
				posicaoPatio.setIdPosicaoPatio(result.getInt("idPosicaoPatio"));
				posicaoPatio.setLocalizacao(result.getString("localizacao"));
				posicaoPatio.setNome(result.getString("nome"));
				posicaoPatio.setTipoRecurso(result.getString("tipoRecurso"));
				return (posicaoPatio);
			}
		}catch (SQLException e) {
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

	public void alterarEstaEmUso(Boolean estaEmUso, int idRecurso )throws Exception {

		String sql = "UPDATE Recurso SET estaEmUso=? WHERE idRecurso=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);

			pstm= con.prepareStatement(sql);

			pstm.setBoolean(1, estaEmUso);
			pstm.setInt(2, idRecurso);

			pstm.executeUpdate();
			con.commit();

		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização do recurso");
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
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Recursos com este id: "+id);
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


}
