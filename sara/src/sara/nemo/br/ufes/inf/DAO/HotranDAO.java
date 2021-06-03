package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Hotran;
import sara.nemo.br.ufes.inf.view.Converte;

public class HotranDAO {
	public void inserir(Hotran hotran)throws SQLException {
		
		String sql= "INSERT INTO Hotran(idCiaAerea, idFrequencia, numVooPousa, numVooDecola, horarioPrevistoPouso, horarioPrevistoDecolagem,"
				+ "escalasOrigem, escalasDestino, equipamento, inicioVigencia, fimVigencia)"+
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, hotran.getIdCiaAerea());
			pstm.setInt(2, hotran.getIdFrequencia());
			pstm.setInt(3, hotran.getNumeroVooPousa());
			pstm.setInt(4, hotran.getNumeroVooDecola());
			pstm.setTime(5, Converte.converterLocalTimeToJavaSqlTime(hotran.getHorarioPrevistoPouso()));
			pstm.setTime(6, Converte.converterLocalTimeToJavaSqlTime(hotran.getHorarioPrevistoDecolagem()));
			pstm.setString(7, hotran.getEscalasOrigem());
			pstm.setString(8, hotran.getEscalasDestino());
			pstm.setString(9, hotran.getEquipamento());
			pstm.setDate(10, Converte.converterLocalDateToJavaSqlDate(hotran.getInicioVigencia()));
			pstm.setDate(11, Converte.converterLocalDateToJavaSqlDate(hotran.getFimVigencia()));
			pstm.execute();
			con.commit();
			JOptionPane.showMessageDialog(null, "Hotran cadastrado com sucesso");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro :Hotran invalido!");
		}finally {
			con.close();
		}
	}
	public void selecionar() throws SQLException {
		String sql= "select idHotran, sigla, numVooPousa, horarioPrevistoPouso, escalasOrigem, domingo, segundaFeira, tercaFeira, quartaFeira, quintaFeira, sextaFeira, sabado, "
				+ "	sigla, numVooDecola, horarioPrevistoDecolagem, escalasDestino, equipamento, domingo, segundaFeira, tercaFeira, quartaFeira, quintaFeira, sextaFeira, sabado, inicioVigencia, fimVigencia"
				+ " from Hotran inner join ProprietarioCiaAerea inner join Frequencia where Hotran.idCiaAerea= ProprietarioCiaAerea.idCiaAerea and Hotran.idFrequencia= Frequencia.idFrequencia ";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				
				System.out.println(result.getString("idHotran") +"  "+result.getString("sigla") +"  "+result.getInt("numVooPousa")+"  "+result.getTime("horarioPrevistoPouso")+"  "+result.getString("escalasOrigem").toUpperCase()
				+" "+result.getInt("Domingo")+" "+result.getInt("SegundaFeira")+" "+result.getInt("TercaFeira")+" "+result.getInt("QuartaFeira")+" "+result.getInt("QuintaFeira")+" "+result.getInt("SextaFeira")
				+" "+result.getInt("Sabado")
				+" "+result.getInt("numVooDecola") 
				+"  "+result.getTime("horarioPrevistoDecolagem")+" \t"+result.getString("escalasDestino").toUpperCase()
				+" "+result.getString("equipamento")
				+" "+result.getInt("Domingo")+" "+result.getInt("SegundaFeira")+" "+result.getInt("TercaFeira")
				+" "+result.getInt("QuartaFeira")+" "+result.getInt("QuintaFeira")+" "+result.getInt("SextaFeira")
				+" "+result.getInt("Sabado")+"  "+result.getDate("inicioVigencia")+"  "+result.getDate("fimVigencia"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Hotrans cadastradas!");
			e.printStackTrace();	
		}finally {
			con.close();
		}
	}
	//ACERTAR DAQUI EM DIANTE OS METODOS DE TRALHAR COM HOTRAM
	public Hotran selecionarById(int id) {
		Hotran hotran= new Hotran();
		String sql= "SELECT * FROM Hotran WHERE idHotran= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet result = pstm.executeQuery();
			
			if (result.next()) {
				hotran.setId(result.getInt(1));
				hotran.setIdCiaAerea(result.getInt("idCiaAerea"));
				hotran.setIdFrequencia(result.getInt("idFrequencia"));
				hotran.setNumeroVooPousa(result.getInt("numVooPousa"));
				hotran.setNumeroVooDecola(result.getInt("numVooDecola"));
				hotran.setHorarioPrevistoPouso(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horarioPrevistoPouso")));
				hotran.setHorarioPrevistoDecolagem(Converte.converterJavaSqlTimeToLocalTime(result.getTime("horarioPrevistoDecolagem")));
				hotran.setEscalasOrigem(result.getString("escalasOrigem"));
				hotran.setEscalasDestino(result.getString("escalasDestino"));
				hotran.setEquipamento(result.getString("equipamento"));
				hotran.setInicioVigencia(Converte.converterJavaSqlDateToLocalDate(result.getDate("inicioVigencia")));
				hotran.setFimVigencia(Converte.converterJavaSqlDateToLocalDate(result.getDate("fimVigencia")));
				
				return (hotran);
				
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Este Hotran não está cadastrado! "+ e.getMessage());
		}
		return (null);
	}
	public void alterar(Hotran hotran)throws Exception {
		
		String sql = "UPDATE Hotran SET idCiaAerea=?, idFrequencia=?, numVooPousa=?, "
				+ "numVooDecola=?, horarioPrevistoPouso=?, horarioPrevistoDecolagem=? , "
				+ "escalasOrigem=?, escalasDestino=?, equipamento=?, inicioVigencia=?, "
				+ "fimVigencia=? WHERE idHotran=?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);

			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, hotran.getIdCiaAerea());
			pstm.setInt(2, hotran.getIdFrequencia());
			pstm.setInt(3, hotran.getNumeroVooPousa());
			pstm.setInt(4, hotran.getNumeroVooDecola());
			pstm.setTime(5, Converte.converterLocalTimeToJavaSqlTime(hotran.getHorarioPrevistoPouso()));
			pstm.setTime(6, Converte.converterLocalTimeToJavaSqlTime(hotran.getHorarioPrevistoDecolagem()));
			pstm.setString(7, hotran.getEscalasOrigem());
			pstm.setString(8, hotran.getEscalasDestino());
			pstm.setString(9, hotran.getEquipamento());
			pstm.setDate(10, Converte.converterLocalDateToJavaSqlDate(hotran.getInicioVigencia()));
			pstm.setDate(11, Converte.converterLocalDateToJavaSqlDate(hotran.getFimVigencia()));
			pstm.setInt(12, hotran.getId());
			
			pstm.executeUpdate();
			con.commit();
			JOptionPane.showMessageDialog(null, "Hotran atualizado com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização dos dados da Hotran");
			e.printStackTrace();
		}finally {
			con.close();
		}
		
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Hotran WHERE idHotran=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			int linhas= pstm.executeUpdate();
			if (linhas> 0) {
				JOptionPane.showMessageDialog(null, "Hotran excluido com sucesso!");
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem Hotran com este id: "+ id);
			}
		}
}
