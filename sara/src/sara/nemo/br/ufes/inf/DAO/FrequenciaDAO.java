package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Frequencia;

public class FrequenciaDAO {
	public void inserir(Frequencia frequencia)throws SQLException {
		String sql= "INSERT INTO Frequencia(domingo, segundaFeira, tercaFeira, quartaFeira,"
				+ "quintaFeira, sextaFeira, sabado)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setBoolean(1, frequencia.isDomingo());
			pstm.setBoolean(2, frequencia.isSegunda());
			pstm.setBoolean(3, frequencia.isTerca());
			pstm.setBoolean(4, frequencia.isQuarta());
			pstm.setBoolean(5, frequencia.isQuinta());
			pstm.setBoolean(6, frequencia.isSexta());
			pstm.setBoolean(7, frequencia.isSabado());
			
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Frequencia cadastrada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Frequencia invalida!");
			e.printStackTrace();
			con.rollback();
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM Frequencia ORDER BY idFrequencia";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			con.commit();
			
			System.out.println("id  D  S  T  Q  Q  S  S ");
			while (result.next()) {
				System.out.print(result.getInt("idFrequencia")+"  ");
				for (int i=2; i<=8; i++) {
					if (result.getBoolean(i) == true) {
						System.out.print(" X ");
					}else System.out.print(" - ");
				}
				System.out.println();
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem frequencias cadastradas!");
			e.printStackTrace();
			
		}
	}
	
	public ArrayList<Frequencia> obterIdFrequencia() {
	String sql= "SELECT * FROM Frequencia ORDER BY idFrequencia";
			ArrayList<Frequencia> freq= new ArrayList<Frequencia>();
			Frequencia frequencia;
			Connection con= null;
			PreparedStatement pstm = null;
			try {
				con= ConnectionFactory.criarConexao();
				con.setAutoCommit(false);
				pstm= con.prepareStatement(sql);
				ResultSet result = pstm.executeQuery(sql);
				con.commit();
				
				while (result.next()) {
					frequencia= new Frequencia();
					frequencia.setIdFrequencia(result.getInt("idFrequencia"));
					frequencia.setDomingo(result.getBoolean("domingo"));
					frequencia.setSegunda(result.getBoolean("segundaFeira"));
					frequencia.setTerca(result.getBoolean("tercaFeira"));
					frequencia.setQuarta(result.getBoolean("quartaFeira"));
					frequencia.setQuinta(result.getBoolean("quintaFeira"));
					frequencia.setSexta(result.getBoolean("sextaFeira"));
					frequencia.setSabado(result.getBoolean("sabado"));
					
					freq.add(frequencia);
				}
				return (freq);
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem frequencias cadastradas!");
				e.printStackTrace();
				return (null);
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public Frequencia selecionarById(int id) {
		Frequencia frequencia= new Frequencia();
		
		String sql= "SELECT * FROM Frequencia WHERE idFrequencia= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				frequencia.setIdFrequencia(id);
				frequencia.setDomingo(result.getBoolean("domingo"));
				frequencia.setSegunda(result.getBoolean("segundaFeira"));
				frequencia.setTerca(result.getBoolean("tercaFeira"));
				frequencia.setQuarta(result.getBoolean("quartaFeira"));
				frequencia.setQuinta(result.getBoolean("quintaFeira"));
				frequencia.setSexta(result.getBoolean("sextaFeira"));
				frequencia.setSabado(result.getBoolean("sabado"));
				
				return (frequencia);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Esta frequencia não está cadastrada! "+ 102);
			e.printStackTrace();
			
		}
		return (null);
	}
	
public void alterar(Frequencia frequencia)throws Exception {
		
		String sql = "UPDATE Frequencia SET  domingo=?, segundaFeira=?, tercaFeira=?, quartaFeira=?, quintaFeira=?, sextaFeira=?, sabado=? WHERE idFrequencia=? ";
				
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
						
			pstm.setBoolean(1, frequencia.isDomingo());
			pstm.setBoolean(2, frequencia.isSegunda());
			pstm.setBoolean(3, frequencia.isTerca());
			pstm.setBoolean(4, frequencia.isQuarta());
			pstm.setBoolean(5, frequencia.isQuinta());
			pstm.setBoolean(6, frequencia.isSexta());
			pstm.setBoolean(7, frequencia.isSabado());
			pstm.setInt(8, frequencia.getIdFrequencia());
			
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Frequencia atualizada com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados de frequencia");
			e.printStackTrace();
			
		}finally {
			con.close();
		}
		
	}

public void apagar(int id){
	String sql = "DELETE FROM Frequencia WHERE idFrequencia= ?";
	Connection con= null;
	PreparedStatement pstm = null;
	
	try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Frequencia excluida com sucesso!");
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existe frequencia com este id: "+ id);
			e.printStackTrace();
		}
	}
}
