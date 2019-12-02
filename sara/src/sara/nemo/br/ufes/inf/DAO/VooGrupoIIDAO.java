package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.VooGrupoII;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class VooGrupoIIDAO {
	public void inserir(VooGrupoII vooGrupoII)throws SQLException {
		String sql= "INSERT INTO VooGrupoII(idVooGrupoII, idProprietarioParticular, nomeComandante, telefoneComandante, tempoDeSolo)"+
					"VALUES(?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			System.out.println(vooGrupoII.getIdVooGrupoII());
			System.out.println(vooGrupoII.getIdProprietarioParticular());
			System.out.println(vooGrupoII.getNomeComandante());
			System.out.println(vooGrupoII.getTelefoneDoComandante());
			System.out.println(vooGrupoII.getTempoDeSolo());
			
			pstm.setInt(1, vooGrupoII.getIdVooGrupoII());
			pstm.setInt(2, vooGrupoII.getIdProprietarioParticular());
			pstm.setString(3, vooGrupoII.getNomeComandante());
			pstm.setString(4, vooGrupoII.getTelefoneDoComandante());
			pstm.setTime(5, Time.valueOf(vooGrupoII.getTempoDeSolo()));
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo do GrupoII invalido!");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "select * from Voo inner join VooGrupoII inner join Aeronave where idVoo= idVooGrupoII and Aeronave_idAeronave= idAeronave ORDER BY matricula ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("matricula")+"  "+ result.getString("tipoAsa")+"  "
						+ result.getString("dataPrevistaPouso")+"  "+ result.getString("horaPrevistaPouso")+"  "
						+ result.getString("dataPrevistaDecolagem")+"  "+ result.getString("horaPrevistaDecolagem"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoII cadastrados!");
			e.printStackTrace();
		}
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM VooGrupoII WHERE idVooGrupoII= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(result.getInt("idVooGrupoII") +"  "+result.getString("Voo_idVoo")+"  "
						+ result.getString("telefoneComandante") +"  "+ result.getString("tempoDeSolo"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe voo do grupoII cadastrado com este id!");
		}
	}
	public void alterar(VooGrupoII vooGrupoII)throws Exception {
		
		String sql = "UPDATE VooGrupoII SET idProprietarioParticular=?, nomeComandante=?, telefoneComandante= ?, tempoDeSolo= ? WHERE idVooGrupoII=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooGrupoII.getIdProprietarioParticular());
			pstm.setString(2, vooGrupoII.getNomeComandante());
			pstm.setString(3, vooGrupoII.getTelefoneDoComandante());
			pstm.setTime(4, Time.valueOf(vooGrupoII.getTempoDeSolo()));
			pstm.setInt(5, vooGrupoII.getIdVooGrupoII());
	
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Dados de voo do grupo II atualizados com sucesso");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização em dados de voo do grupo II");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM VooGrupoII WHERE idVooGrupoII=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "Voo do grupoII excluido com sucesso!");
			con.commit();
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe voo do grupoII com este id: "+ id);
				e.printStackTrace();
			}
		}
}
