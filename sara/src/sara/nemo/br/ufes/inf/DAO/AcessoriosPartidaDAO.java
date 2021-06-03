package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.domain.Hotran;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;
import sara.nemo.br.ufes.inf.domain.TipoAeronave;
import sara.nemo.br.ufes.inf.domain.VooPartidaGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;

public class AcessoriosPartidaDAO {
	
	public AcessoriosPartidaDAO(){};
	
	public void inserir(AcessoriosPartida acessoriosPartida) {
		String sql= "INSERT INTO AcessoriosPartida (idAcessoriosPartida, vooPartida, destino, vooChegada, procedencia, portao, idHotran)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, acessoriosPartida.getIdAcessoriosPartida());
			pstm.setString(2, acessoriosPartida.getVooPartida());
			pstm.setString(3, acessoriosPartida.getDestino());
			pstm.setString(4, acessoriosPartida.getVooChegada());
			pstm.setString(5, acessoriosPartida.getProcedencia());
			pstm.setString(6, acessoriosPartida.getPortao());
			pstm.setInt(7, acessoriosPartida.getIdHotran());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro : Dados de acessorios invalidos");
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		String sql= "SELECT * FROM AcessoriosPartida ORDER BY idAcessoriosPartida ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
	
			while (result.next()) {
				System.out.println(result.getInt("idAcessoriosPartida") +"  "+result.getString("vooPartida")+" "+result.getString("destino") +"  "+result.getString("vooChegada")+"  "+result.getString("procedencia")+"  "+result.getString("portao")+"  "+result.getInt("idHotran"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios Partida cadastrados!" + 52);
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
	
	public ArrayList<AcessoriosPartida> obterAcessoriosPartida() throws SQLException {
		ArrayList<AcessoriosPartida> acessoriosPartida= new ArrayList<AcessoriosPartida>();
		AcessoriosPartida dados;
		
		String sql= "SELECT * FROM Acessorios inner join AcessoriosPartida "
				+ "where idAcessorios= idAcessoriosPartida order BY dataPrevista ASC ,horaPrevista ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery();
	
			while (result.next()) {
				dados= new AcessoriosPartida();
				
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				String dataHora= result.getDate("dataPrevista").toString()+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);
				dataHora= result.getDate("dataAtualizada")+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);
				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				//dados.setCabeceira(result.getString("cabeceira"));
				
				dados.setIdAcessoriosPartida(result.getInt("idAcessorios"));
				dados.setVooPartida(result.getString("vooPartida"));
				dados.setDestino(result.getString("destino"));
				dados.setVooChegada(result.getString("vooChegada"));
				dados.setProcedencia(result.getString("procedencia"));
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setPortao(result.getString("portao"));
				dados.setIdHotran(result.getInt("idHotran"));
				dados.setIdVoo(result.getInt("Voo_idVoo"));
				acessoriosPartida.add(dados);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios cadastrados---------->!"+ e.getMessage());
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
		return (acessoriosPartida);
	}
	
	public List<AcessoriosPartida> obterAcessoriosPartidaDeVoos(){
		List<AcessoriosPartida> lstAcessoriosPartida= new ArrayList<AcessoriosPartida>();
		
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		HotranDAO hotranDAO= new HotranDAO();
		Hotran hotran= new Hotran();
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		
		AcessoriosPartida dados;
		
		String sql= "select * from Voo inner join VooPartidaGrupoI where idVoo= idVooPartidaGrupoI "
				+ "and dataPrevistaParaDecolagem <>'null' and situacao <> 'ETC'order by idVoo asc";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery();
			String dataHora= null;
			while (result.next()) {
				dados= new AcessoriosPartida(); //Sempre que executar um passo do loop devo criar um novo objeto
				if (result.getDate("dataPrevistaParaDecolagem")!= null) {
					dataHora= result.getDate("dataPrevistaParaDecolagem").toString()+" "+result.getTime("horaPrevistaParaDecolagem").toString();
					dados.setDataHoraPrevista(dataHora);
				}
				
				if(result.getDate("dataConfirmadaDecolagem")!= null) {
					dataHora= result.getDate("dataConfirmadaDecolagem").toString()+" "+result.getTime("horaConfirmadaDecolagem").toString();
				}else dataHora= result.getDate("dataPrevistaParaDecolagem").toString()+" "+result.getTime("horaPrevistaParaDecolagem").toString();
				dados.setDataHoraAtualizada(dataHora);
				int id= result.getInt("Aeronave_idAeronave");
				
				Aeronave aeronave= aeronaveDAO.selecionarAeronaveById(id);

				if(aeronave !=null) {
					dados.setMatricula(aeronave.getMatricula());
				}
				
				dados.setSituacao(result.getString("situacao"));
				
				Categoria categoria= categoriaDAO.selecionarById(result.getInt("Categoria_IdCategoria"));

				String tipo= categoria.abreviarCategoria();
				dados.setTipo(tipo);

				id=result.getInt("Aeronave_idAeronave");
				aeronaveDAO= new AeronaveDAO();
				aeronave= aeronaveDAO.selecionarAeronaveById(id);
				TipoAeronave tipoAeronave= null;
				
				if(aeronave!= null) {
					tipoAeronave= tipoAeronaveDAO.selecionarTipoAeronave(aeronave.getIdTipoAeronave());
				}
				
				if(tipoAeronave != null) dados.setEquipamento(tipoAeronave.getEquipamento());
				//dados.setIdAcessoriosPartida(0);
				
				
				id= result.getInt("idVoo");
				
				hotran= hotranDAO.selecionarById(result.getInt("Hotran_idHotran"));
				
				ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
				
				id=aeronave.getIdProprietario();
				ProprietarioCiaAerea proprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarById(id);
				String vooPartida= proprietarioCiaAerea.getSiglaCiaAerea()+" "+ hotran.getNumeroVooDecola();
				dados.setVooPartida(vooPartida);
				
				dados.setProcedencia(hotran.getEscalasOrigem());
				
				VooPartidaGrupoIDAO vooPartidaGrupoIDAO= new VooPartidaGrupoIDAO();
				VooPartidaGrupoI vooPartidaGrupoI= vooPartidaGrupoIDAO.selecionarById(result.getInt("idVoo"));
				
				if(vooPartidaGrupoI!=null) {
					System.out.println("id do voo vale: "+ result.getInt("idVoo"));
					dados.setDestino(vooPartidaGrupoI.getDestino());
				}else {
					dados.setDestino(hotran.getEscalasDestino());
				}
				
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setPortao(result.getString("portaoDeEmbarque"));
				dados.setIdVoo(result.getInt("Voo_idVoo"));
				
				lstAcessoriosPartida.add(dados);
			}
		}catch (NullPointerException npe){
			JOptionPane.showMessageDialog(null, "Gerando a Tabela de Voos! ");
			npe.printStackTrace();
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Dados fornecidos invalidos! "+ e.getMessage());
			
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
		return (lstAcessoriosPartida);
	}
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idAcessoriosPartida) FROM AcessoriosPartida";
		
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
			JOptionPane.showMessageDialog(null, "Não existem Acessorios Partida cadastrados!");
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
	
	public AcessoriosPartida selecionar(LocalDate  dataPrevista) {
		AcessoriosPartida dados= null;
		
		String sql= "select * from Acessorios inner join AcessoriosPartida where idAcessorios=idAcessoriosPartida and  dataPrevista= ? ";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			Date data= Converte.converterLocalDateToJavaSqlDate(dataPrevista);
			System.out.println("Buscando Acessorio partida para o dia: "+data);
			pstm.setDate(1, data);
			
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				dados= new AcessoriosPartida(); //Sempre que executar um passo do loop devo criar um novo objeto
				
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				String dataHora= result.getDate("dataPrevista").toString()+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);
				dataHora= result.getDate("dataAtualizada").toString()+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);
				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				
				dados.setIdAcessoriosPartida(result.getInt("idAcessoriosPartida"));
				dados.setVooChegada(result.getString("vooChegada"));
				dados.setProcedencia(result.getString("procedencia"));
				dados.setVooPartida(result.getString("vooPartida"));
				dados.setDestino(result.getString("destino"));
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setPortao("portao");
				dados.setIdHotran(result.getInt("idHotran"));
				dados.setIdVoo(result.getInt("Voo_idVoo"));
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				System.out.println("dados: "+dados.toString());
				return dados;
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios cadastrados! ");
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
		return (dados);
	}
	
	public AcessoriosPartida selecionar(Date dataPouso, String vooChegada) {
		AcessoriosPartida dados= null;
		
		String sql= "select * from Acessorios inner join AcessoriosPartida where idAcessorios=idAcessoriosPartida and  dataPrevista= ? and vooChegada= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);

			pstm.setDate(1, dataPouso);
			pstm.setString(2, vooChegada);
			ResultSet result = pstm.executeQuery();
			con.commit();
			while (result.next()) {
				dados= new AcessoriosPartida(); //Sempre que executar um passo do loop devo criar um novo objeto
				
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				String dataHora= result.getDate("dataPrevista").toString()+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);
				dataHora= result.getDate("dataAtualizada").toString()+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);
				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				
				dados.setIdAcessoriosPartida(result.getInt("idAcessoriosPartida"));
				dados.setVooChegada(result.getString("vooChegada"));
				dados.setProcedencia(result.getString("procedencia"));
				dados.setVooPartida(result.getString("vooPartida"));
				dados.setDestino(result.getString("destino"));
				
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setPortao("portao");
				dados.setIdHotran(result.getInt("idHotran"));
				dados.setIdVoo(result.getInt("Voo_idVoo"));
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				System.out.println("dados: "+dados.toString());
				return dados;
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios cadastrados! ");
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
		return (dados);
	}
	
	public AcessoriosPartida selecionarById(int id) throws Exception {
		String sql= "SELECT * FROM Acessorios inner join AcessoriosPartida WHERE idAcessorios= idAcessoriosPartida and idAcessoriosPartida= ?";
		AcessoriosPartida dados= new AcessoriosPartida();
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
			
			
			dados.setIdAcessoriosPartida(Integer.parseInt("idAcessoriosPartida"));
			dados.setVooPartida(result.getString("vooPartida"));
			dados.setDestino(result.getString("destino"));
			dados.setVooChegada(result.getString("vooChegada"));
			dados.setProcedencia(result.getString("procedencia"));
			dados.setNomeBox(result.getString("nomeBox"));
			dados.setPortao(result.getString("portao"));
			
			dados.setIdHotran(result.getInt("idHotran"));
			dados.setIdAcessorios(result.getInt("idAcessorios"));
			return (dados);
		}
		}catch (Exception e) {
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
	
public void alterar(AcessoriosPartida acessoriosPartida )throws Exception {
		
		String sql = "UPDATE AcessoriosPartida SET vooPartida=?, destino=?, vooChegada=?,"
				+ " procedencia=?, portao=?, idHotran=? "
				+ "WHERE idAcessoriosPartida=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, acessoriosPartida.getVooPartida());
			pstm.setString(2, acessoriosPartida.getDestino());
			pstm.setString(3, acessoriosPartida.getVooChegada());
			pstm.setString(4, acessoriosPartida.getProcedencia());
			//pstm.setString(5, acessoriosPartida.getNomeBox());
			System.out.println("Exibindo portao: "+acessoriosPartida.getPortao());
			pstm.setString(5, acessoriosPartida.getPortao());
			pstm.setInt(6, acessoriosPartida.getIdHotran());
			pstm.setInt(7, acessoriosPartida.getIdAcessoriosPartida());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização em Acessorios Partida");
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
		String sql = "DELETE FROM AcessoriosPartida WHERE idAcessoriosPartida=?";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			
			con.commit();
			System.out.println("Apagado o AcessorioPartida......... id= "+id);
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem Acessorios Partida com este id: "+id);
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
