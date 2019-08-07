package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.ProprietarioParticular;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class ProprietarioParticularDAO {
	public void inserir(ProprietarioParticular proprietarioParticular)throws SQLException {
		String sql= "INSERT INTO ProprietarioParticular(idProprietarioParticular, querAbastecimento, combustivel)"+
					"VALUES(?, ?, ?)";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			if (con != null) System.out.println("Conexão bem sucedida" + con);
			
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, proprietarioParticular.getId());
			pstm.setBoolean(2, proprietarioParticular.isQuerAbastecimento());
			pstm.setString(3, proprietarioParticular.getTipoCombustivel());
			
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Proprietario particular cadastrado com sucesso!");
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Proprietario particular invalido!");
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM ProprietarioParticular ORDER BY idProprietarioParticular ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			con.commit();
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("nomeDoProprietario")+"  "+ result.getString("querAbastecimento")+"  "+ result.getString("combustivel"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem propietários cadastrados!");
		}
	}
	
	public ProprietarioParticular selecionarById(int id) {
		ProprietarioParticular proprietarioParticular= new ProprietarioParticular();
		String sql= "SELECT * FROM ProprietarioParticular WHERE idProprietarioParticular= ?";
		
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
				proprietarioParticular.setId(result.getInt("idProprietarioParticular"));
				proprietarioParticular.setQuerAbastecimento(result.getBoolean("querAbastecimento"));
				proprietarioParticular.setTipoCombustivel("combustivel");
				
				return (proprietarioParticular);
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe proprietario particular cadastrado com este Id!" + 86);
		}
		return (null);
	}
	public void alterar(ProprietarioParticular proprietarioParticular)throws Exception {
		
		String sql = "UPDATE ProprietarioParticular SET nomeDoProprietario=?, querAbastecimento=?, combustivel=? WHERE idProprietarioParticular=? ";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setBoolean(2, proprietarioParticular.isQuerAbastecimento());
			pstm.setString(3, proprietarioParticular.getTipoCombustivel());
			pstm.setInt(4, proprietarioParticular.getId());
			
			pstm.executeUpdate();
			
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados de proprietário");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM ProprietarioParticular WHERE idProprietarioParticular=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int linhas= pstm.executeUpdate();
			con.commit();
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Proprietario excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem proprietários com este id: "+ id);
			}
		}
}
