package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.VooGrupoI;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class VooGrupoIDAO {
	public void inserir(VooGrupoI vooGrupoI)throws SQLException {
		String sql= "INSERT INTO VooGrupoI(Voo_idVoo, Hotran_idHotran, numeroDoVoo, horaConfirmadaPouso, horaConfirmadaDecolagem)"+
					"VALUES(?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) System.out.println("Conexão bem sucedida" + con);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooGrupoI.getIdVoo());
			pstm.setInt(2, vooGrupoI.getIdHotran());
			pstm.setInt(3, vooGrupoI.getNumeroVoo());
			pstm.setTime(4, Time.valueOf(vooGrupoI.getHoraConfirmadaPouso()));
			pstm.setTime(5, Time.valueOf(vooGrupoI.getHoraConfirmadaDecolagem()));
			
			pstm.execute();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo do GrupoI invalido!");
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "SELECT * FROM VooGrupoI ORDER BY horaConfirmadaPouso ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("Voo_idVoo")+"  "+ result.getString("Hotran_idHotran")+"  "+result.getString("numeroDoVoo")+"  "+ result.getString("horaConfirmadaPouso")+"  "+ result.getString("horaConfirmadaDecolagem"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoI cadastrados!");
		}
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM VooGrupoI WHERE id= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getString("Voo_idVoo")+"  "+ result.getString("Hotran_idHotran")+"  "+result.getString("numeroDoVoo")+"  "+ result.getString("horaConfirmadaPouso")+"  "+ result.getString("horaConfirmadaDecolagem"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe aeronave cadastrada com esta matricula!");
		}
	}
	public void alterar(VooGrupoI vooGrupoI)throws Exception {
		
		String sql = "UPDATE VooGrupoI SET Voo_idVoo=?, Hotran_idHotran=?, numeroDoVoo=?, horaConfirmadaPouso=?, horaConfirmadaDecolagem=? WHERE id=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,  vooGrupoI.getIdVoo());
			pstm.setInt(2,  vooGrupoI.getIdHotran());
			pstm.setInt(3,  vooGrupoI.getNumeroVoo());
			pstm.setTime(4, Time.valueOf(vooGrupoI.getHoraConfirmadaPouso()));
			pstm.setTime(5, Time.valueOf(vooGrupoI.getHoraConfirmadaDecolagem()));
			
			pstm.executeUpdate();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo do grupo I");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM VooGrupoI WHERE id=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int linhas= pstm.executeUpdate(sql);
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Voo do grupo I excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem voos do grupo I com este id: "+ id);
			}
		}
}
