package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Aeronave_has_Proprietario;

public class Aeronave_has_ProprietarioDAO {
	public void inserir(Aeronave_has_Proprietario ahp)throws SQLException {
		String sql= "INSERT INTO Aeronave_has_Proprietario(Aeronave_idAeronave, Proprietario_idProprietario)"+
					"VALUES(?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) {
				pstm= con.prepareStatement(sql);
				pstm.setInt(1, ahp.getIdAeronave());
				pstm.setInt(2, ahp.getIdProprietario());

				pstm.execute();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro");
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "select * from Aeronave_has_Proprietario order by Aeronave_idAeronave ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				System.out.println(result.getInt(1) +"  "+result.getInt(2));
			}
		}catch (Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Não existem dados na tabela Aeronave_has_Proprietario!"+ e.getMessage());
		}
	}
}
