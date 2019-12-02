package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.VooNaoRegularGrupoI;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;

public class VooNaoRegularGrupoIDAO {
	public void inserir(VooNaoRegularGrupoI vooNaoRegularGrupoI)throws SQLException {
		String sql= "INSERT INTO VooNaoRegularGrupoI(Voo_idVoo, numeroDoVooPouso, numeroDoVooDecolagem,"
				+ " horaConfirmadaPouso, horaConfirmadaDecolagem, ProprietarioCiaAerea_idCiaAerea)"+
					"VALUES(?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, vooNaoRegularGrupoI.getIdVoo());
			pstm.setInt(2, vooNaoRegularGrupoI.getNumeroVooPouso());
			pstm.setInt(3, vooNaoRegularGrupoI.getNumeroVooDecolagem());
			pstm.setTime(4, Time.valueOf(vooNaoRegularGrupoI.getHoraConfirmadaPouso()));
			pstm.setTime(5, Time.valueOf(vooNaoRegularGrupoI.getHoraConfirmadaDecolagem()));
			pstm.setInt(6, vooNaoRegularGrupoI.getIdProprietarioCiaAerea());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo Nao Regular GrupoI invalido!");
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	public void selecionar() {
		String sql= "select idVoo, sigla, numeroDoVooPouso, dataPrevistaPouso, horaPrevistaPouso, horaConfirmadaPouso, origem, \n" + 
				"sigla, numeroDoVooDecolagem, dataPrevistaDecolagem, horaPrevistaDecolagem, horaConfirmadaDecolagem,\n" + 
				"destino\n" + 
				"from Voo inner join VooNaoRegularGrupoI inner join Aeronave inner join Proprietario inner join ProprietarioCiaAerea where idVoo= Voo_idVoo and Aeronave_idAeronave= idAeronave and idProprietario= idCiaAerea;";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt("idVoo")+" "+result.getString("Sigla").toUpperCase() +"  "+result.getInt("numeroDoVooPouso")+"  "+ result.getString("dataPrevistaPouso")+
						"  "+result.getString("horaConfirmadaPouso")+"  "+ result.getString("origem").toUpperCase()+
						"  "+ result.getString("Sigla") +" "+result.getInt("numeroDoVooDecolagem")+"  "+ result.getString("dataPrevistaDecolagem")+
						"  "+result.getString("horaPrevistaDecolagem")+"  "+ result.getString("horaConfirmadaDecolagem")+" "+result.getString("destino").toUpperCase());
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem voos do grupoI cadastrados!");
		}
	}
	
	public void selecionarById(int id) {
		String sql= "SELECT * FROM VooNaoRegularGrupoI WHERE id= ?";
		
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
	public void alterar(VooNaoRegularGrupoI vooNaoRegularGrupoI)throws Exception {
		
		String sql = "UPDATE VooNaoRegularGrupoI SET Voo_idVoo=?, Hotran_idHotran=?, numeroDoVooPouso=?, numeroDoVooDecolagem= ?, horaConfirmadaPouso=?, horaConfirmadaDecolagem=?, ProprietarioCiaAerea_idCiaAerea= ? WHERE idVooNaoRegularGrupoI=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,  vooNaoRegularGrupoI.getIdVoo());
			pstm.setInt(3,  vooNaoRegularGrupoI.getNumeroVooPouso());
			pstm.setInt(4,  vooNaoRegularGrupoI.getNumeroVooDecolagem());
			pstm.setTime(5, Time.valueOf(vooNaoRegularGrupoI.getHoraConfirmadaPouso()));
			pstm.setTime(6, Time.valueOf(vooNaoRegularGrupoI.getHoraConfirmadaDecolagem()));
			pstm.setInt(7,  vooNaoRegularGrupoI.getIdProprietarioCiaAerea());
			
			pstm.executeUpdate();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados do voo do grupo I");
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM VooNaoRegularGrupoI WHERE idVooGrupoI=?";
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
