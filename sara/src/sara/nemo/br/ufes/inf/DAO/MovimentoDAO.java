package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Movimento;

public class MovimentoDAO {
	public void inserir(Movimento movimento)throws SQLException {
		String sql= "INSERT INTO Movimento(tipoMovimento, dataMovimento, horaMovimento, OcorrenciaVoo_idOcorrenciaVoo)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) System.out.println("Conexão bem sucedida" + con);
			pstm= con.prepareStatement(sql);
			pstm.setString(1, movimento.getTipoMovimento());
			pstm.setDate(2, Date.valueOf(movimento.getData()));
			pstm.setTime(3, Time.valueOf(movimento.getHora()));
			pstm.setInt(4, movimento.getIdOcorrenciaVoo());
			pstm.execute();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Movimento invalido!");
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM Movimento ORDER BY idMovimento ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong(1) +"  "+result.getString("tipoMovimento")+"  "+result.getString("dataMovimento")+
						"  "+result.getString("horaMovimento")+"  "+result.getString("OcorrenciaVoo_idOcorrenciaVoo"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe movimento cadastrado!");
		}
	}
	
	public void selecionarById(Long id) {
		String sql= "SELECT * FROM Movimento WHERE idMovimento= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getLong(1) +"  "+result.getString("tipoMovimento")+"  "+result.getString("dataMovimento")+"  "+result.getString("horaMovimento"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Este Movimento não está cadastrado!");
		}
	}
	
public void alterar(Movimento movimento)throws Exception {
		
		String sql = "UPDATE Movimento SET tipoMovimento=?, dataMovimento=?, horaMovimento=?, OcorrenciaVoo_idOcorrenciaVoo=? WHERE idHotran=?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, movimento.getTipoMovimento());
			pstm.setDate(2, Date.valueOf(movimento.getData()));
			pstm.setTime(3, Time.valueOf(movimento.getHora()));
			pstm.setLong(4, movimento.getIdOcorrenciaVoo());
			pstm.executeUpdate();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização do movimento");
		}finally {
			con.close();
		}
		
	}
	public void apagar(long id){
		String sql = "DELETE FROM Movimento WHERE idMovimento=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Movimento excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existe movimento com este id: "+ id);
			}
		}
}
