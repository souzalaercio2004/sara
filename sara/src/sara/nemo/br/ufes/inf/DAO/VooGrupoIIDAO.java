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
		String sql= "INSERT INTO VooGrupoII(Voo_idVoo, Aeronave_idAeronave, nomeComandante,telefoneComandante, tempoDeSolo)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) System.out.println("Conexão bem sucedida" + con);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooGrupoII.getIdVoo());
			pstm.setInt(2, vooGrupoII.getIdAeronave());
			pstm.setString(3, vooGrupoII.getNomeComandante());
			pstm.setString(4, vooGrupoII.getTelefoneDoComandante());
			pstm.setTime(5, Time.valueOf(vooGrupoII.getTempoDeSolo()));
			
			pstm.execute();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo do GrupoII invalido!");
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM VooGrupoII ORDER BY matricula ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong(1) +"  "+result.getString("matricula")+"  "+ result.getString("tipoAsa"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoII cadastrados!");
		}
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM VooGrupoII WHERE idVooGrupoII= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idVooGrupoII") +"  "+result.getString("Voo_idVoo")+"  "
						+ result.getString("telefoneComandante") +"  "+ result.getString("tempoDeSolo"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe voo do grupoII cadastrado com este id!");
		}
	}
	public void alterar(VooGrupoII vooGrupoII)throws Exception {
		
		String sql = "UPDATE VooGrupoII SET Voo_idVoo=?, Aeronave_idAeronave=?, nomeComandante=?, telefoneComandante, tempoDeSolo WHERE idVooGrupoII=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooGrupoII.getIdVoo());
			pstm.setInt(2, vooGrupoII.getIdAeronave());
			pstm.setString(3, vooGrupoII.getNomeComandante());
			pstm.setString(4, vooGrupoII.getTelefoneDoComandante());
			pstm.setTime(5, Time.valueOf(vooGrupoII.getTempoDeSolo()));
	
			
			pstm.executeUpdate();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização em dados de voo do grupo II");
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
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo do grupoII excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos do grupoII com este id: "+ id);
			}
		}
}
