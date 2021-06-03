package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.view.Converte;
import sara.nemo.br.ufes.inf.view.accessorios.Acessorios;

public class AcessoriosDAO {

	public AcessoriosDAO(){};

	public void inserir(Acessorios acessorios)throws SQLException {
		String sql= "INSERT INTO Acessorios (dataPrevista, horaPrevista, dataAtualizada, horaAtualizada, matricula, situacao, tipo, equipamento, nomeBox)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;

		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			LocalDateTime localDateTime= Converte.converteStringToLocalDateTime(acessorios.getDataHoraPrevista());


			pstm.setDate(1, Converte.converterLocalDateToJavaSqlDate(localDateTime.toLocalDate()));
			pstm.setTime(2, Converte.converterLocalTimeToJavaSqlTime(localDateTime.toLocalTime()));
			localDateTime= Converte.converteStringToLocalDateTime(acessorios.getDataHoraAtualizada());
			pstm.setDate(3, Converte.converterLocalDateToJavaSqlDate(localDateTime.toLocalDate()));
			pstm.setTime(4, Converte.converterLocalTimeToJavaSqlTime(localDateTime.toLocalTime()));

			pstm.setString(5, acessorios.getMatricula());
			pstm.setString(6, acessorios.getSituacao());
			pstm.setString(7, acessorios.getTipo());
			pstm.setString(8, acessorios.getEquipamento());
			pstm.setString(9, acessorios.getNomeBox());

			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro : Dados de acessorios invalidos"+49);
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
		String sql= "SELECT * FROM Acessorios ORDER BY idAcessorios ASC";

		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				System.out.println(result.getInt("idAcessorios") );
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem acessorios cadastrados!" + 69);
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
	public ArrayList<Acessorios> obterAcessorios(LocalDate dataHoje) throws Exception{
		ArrayList<Acessorios> acessorios= new ArrayList<>();
		Acessorios dados= null;

		String sql= "SELECT * FROM Acessorios where dataPrevista= ? ORDER BY idAcessorios ASC";

		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			Date data= Converte.converterLocalDateToJavaSqlDate(dataHoje);

			pstm.setDate(1, data);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				dados= new Acessorios();
				dados.setIdAcessorios(result.getInt("idAcessorios"));

				String dataHora= result.getDate("dataPrevista")+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);

				dataHora= result.getDate("dataAtualizada")+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);

				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				dados.setNomeBox(result.getString("nomeBox"));
				acessorios.add(dados);
			}
			con.commit();
		}catch (Exception e){
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
		return (acessorios);
	}

	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idAcessorios) FROM Acessorios";

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
			JOptionPane.showMessageDialog(null, "Não existem Acessorios cadastrados! "+126);
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

	public Acessorios selecionarById(int id) throws Exception {
		String sql= "SELECT * FROM Acessorios WHERE idAcessorios= ?";
		Acessorios dados= new Acessorios();
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
				dados.setIdAcessorios(result.getInt("idAcessorios"));

				String dataHora= result.getDate("dataPrevista")+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);

				dataHora= result.getDate("dataAtualizada")+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);

				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				dados.setNomeBox(result.getString("nomeBox"));

				return (dados);
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

	public void alterar(Acessorios acessorios )throws Exception {

		String sql = "UPDATE Acessorios SET dataPrevista=?, horaPrevista=?, "
				+ "dataAtualizada=?, horaAtualizada=?, matricula=?, situacao=?, "
				+ "tipo=?, equipamento=? nomeBox=? WHERE idAcessorios=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);

			pstm= con.prepareStatement(sql);

			LocalDateTime dataHora=Converte.converteStringToLocalDateTime(acessorios.getDataHoraPrevista());
			pstm.setDate(1, Converte.converterLocalDateToJavaSqlDate(dataHora.toLocalDate()));
			pstm.setTime(2, Converte.converterLocalTimeToJavaSqlTime(dataHora.toLocalTime()));

			LocalDateTime dataHoraAtualizada= Converte.converteStringToLocalDateTime(acessorios.getDataHoraAtualizada());
			pstm.setDate(3, Converte.converterLocalDateToJavaSqlDate(dataHoraAtualizada.toLocalDate()));
			pstm.setTime(4, Converte.converterLocalTimeToJavaSqlTime(dataHoraAtualizada.toLocalTime()));

			pstm.setString(5, acessorios.getMatricula());
			pstm.setString(6, acessorios.getSituacao());
			pstm.setString(7, acessorios.getTipo());
			pstm.setString(8, acessorios.getEquipamento());
			pstm.setString(9, acessorios.getNomeBox());
			pstm.setInt(10, acessorios.getIdAcessorios());

			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Alteração de acessorios foi bem sucedida! situacao "+acessorios.getSituacao());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização do acessorio");
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
		String sql = "DELETE FROM Acessorios WHERE idAcessorios=?";
		Connection con= null;
		PreparedStatement pstm = null;

		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);

			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();

			con.commit();
			System.out.println("Acessorio apagado! id= "+id);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios com este id: "+id);
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
