package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;

public class PortaoDeEmbarqueDAO {

	public void inserir(PortaoDeEmbarque portaoDeEmbarque)throws SQLException {
		String sql= "INSERT INTO PortaoDeEmbarque(idPortaoEmbarque, nome)"+
				"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;

		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, portaoDeEmbarque.getIdPortaoDeEmbarque());
			pstm.setString(2, portaoDeEmbarque.getNome());
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Portão de embarque cadastrado com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Portao de embarque inválido!");
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
	public void selecionar() {
		String sql= "SELECT * FROM sara.PortaoDeEmbarque inner join sara.Recurso where Recurso.idRecurso= PortaoDeEmbarque.idPortaoEmbarque ORDER BY idPortaoEmbarque ASC";

		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+ result.getString("tipoRecurso")+"  "+ result.getString("nome")+"  "+ result.getString("localizacao")+"  "+ result.getBoolean("estaEmUso"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe portão de embarque cadastrado!");
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

	public ArrayList<String> selecionarNomes() {
		ArrayList<String> nomePortaoDeEmbarque= new ArrayList<String>();
		String sql= "select * from PortaoDeEmbarque order by nome ASC";

		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				nomePortaoDeEmbarque.add(result.getString("nome"));	
			}
			return (nomePortaoDeEmbarque);

		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existe portao de embarque cadastrado!");

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

	public int selecionarIdDadoNome(String dadoNome) {
		String sql= "select * from PortaoDeEmbarque where nome= ?";

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
			JOptionPane.showMessageDialog(null, "Dados Invalidos!");

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
		return 0;
	}

	public PortaoDeEmbarque selecionarPortaoDeEmbarque(int idProprietario) {
		PortaoDeEmbarque portaoDeEmbarque= new PortaoDeEmbarque();
		String sql= "select * from Recurso inner join RecursosPorProprietario "
				+ "inner join PortaoDeEmbarque inner join Proprietario "
				+ "where Recurso_idRecurso= idRecurso and idRecurso= idPortaoEmbarque "
				+ "and Proprietario_idProprietario= idProprietario "
				+ "and idProprietario=?  and estaEmUso = '0' order by Prioridade asc;";

		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,idProprietario);
			ResultSet result = pstm.executeQuery();

			if (result.next()) {
				portaoDeEmbarque.setIdPortaoDeEmbarque(result.getInt("idPortaoEmbarque"));
				portaoDeEmbarque.setNome(result.getString("nome"));
				return portaoDeEmbarque;
			}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Portao de embarque cadastrado para este proprietario !"+idProprietario );
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

	public PortaoDeEmbarque selecionarPortaoDeEmbarqueByNome(String nome) {
		if (nome!= "00") {
			PortaoDeEmbarque portaoDeEmbarque= null;
			String sql= "select * from Voo inner join OcorrenciaVoo "
					+ "inner join RecursoEmOcorrenciaVoo inner join Recurso "
					+ "inner join PortaoDeEmbarque where idVoo= Voo_idVoo "
					+ "and OcorrenciaVoo_idOcorrenciaVoo= idOcorrenciaVoo "
					+ "and Recurso_idRecurso=idRecurso "
					+ "and idRecurso= idPortaoEmbarque and nome= ?";

			Connection con= null;
			PreparedStatement pstm = null;
			try {
				con= ConnectionFactory.criarConexao();
				con.setAutoCommit(false);
				pstm= con.prepareStatement(sql);
				pstm.setString(1, nome);
				ResultSet result = pstm.executeQuery();
				//con.commit();
				portaoDeEmbarque=new PortaoDeEmbarque();
				while (result.next()) {
					portaoDeEmbarque.setIdPortaoDeEmbarque(result.getInt("idPortaoEmbarque"));
					portaoDeEmbarque.setNome(result.getString("nome"));
					return(portaoDeEmbarque);
				}

			}catch (Exception e){

				JOptionPane.showMessageDialog(null, "Não existe portão de embarque cadastrado com este nome! "+nome);
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
		return (null);
	}


	public PortaoDeEmbarque selecionarPortaoDeEmbarqueById(int idPortaoEmbarque) {
		PortaoDeEmbarque portaoDeEmbarque= null;
		String sql= "select * from PortaoDeEmbarque where idPortaoEmbarque=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, idPortaoEmbarque);
			ResultSet result = pstm.executeQuery();
			//con.commit();
			portaoDeEmbarque=new PortaoDeEmbarque();
			while (result.next()) {
				portaoDeEmbarque.setIdPortaoDeEmbarque(result.getInt("idPortaoEmbarque"));
				portaoDeEmbarque.setNome(result.getString("nome"));
				return(portaoDeEmbarque);
			}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe portão de embarque cadastrado com este codigo! "+idPortaoEmbarque);
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

	public void alterar(PortaoDeEmbarque portaoDeEmbarque)throws Exception {

		String sql = "UPDATE PortaoDeEmbarque SET nome=? WHERE idPortaoEmbarque=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();

			pstm= con.prepareStatement(sql);
			pstm.setString(1, portaoDeEmbarque.getNome());
			pstm.setInt(2, portaoDeEmbarque.getIdPortaoDeEmbarque());

			pstm.executeUpdate();

		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do portão de embarque");
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
		String sql = "DELETE FROM PortaoDeEmbarque WHERE idPortaoEmbarque=?";
		Connection con= null;
		PreparedStatement pstm = null;

		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.executeUpdate();
			con.commit();

			JOptionPane.showMessageDialog(null, "Portao de embarque excluido com sucesso!");
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe portão de embarque este: "+id);

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
