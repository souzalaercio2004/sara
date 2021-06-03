package sara.nemo.br.ufes.inf.DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.conexao.ConnectionFactory;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.domain.Hotran;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;
import sara.nemo.br.ufes.inf.domain.TipoAeronave;
import sara.nemo.br.ufes.inf.view.accessorios.Acessorios;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;

public class AcessoriosChegadaDAO {
	
	public AcessoriosChegadaDAO(){};
	
	public void inserir(AcessoriosChegada acessoriosChegada)throws SQLException {
		String sql= "INSERT INTO AcessoriosChegada (idAcessoriosChegada, vooChegada, procedencia, vooPartida, destino, NomeCabeceira, NomeEsteira, idHotran)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			
			pstm.setInt(1, acessoriosChegada.getIdAcessoriosChegada());
			pstm.setString(2, acessoriosChegada.getVooChegada());
			pstm.setString(3, acessoriosChegada.getProcedencia());
			pstm.setString(4, acessoriosChegada.getVooPartida());
			pstm.setString(5, acessoriosChegada.getDestino());
			pstm.setInt(6, Integer.parseInt(acessoriosChegada.getNomeCabeceira()));
			pstm.setString(7, acessoriosChegada.getNomeEsteira());
			pstm.setInt(8, acessoriosChegada.getIdHotran());
			
			pstm.execute();
			con.commit();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro : Dados de acessorios Chegada invalidos ");
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
		String sql= "SELECT * FROM AcessoriosChegada ORDER BY idAcessoriosChegada ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery(sql);
	
			while (result.next()) {
				System.out.println(result.getInt("idAcessoriosChegada") +"  "+result.getString("matricula")+" "+result.getString("situacao") +"  "+result.getString("tipo")+"  "+result.getString("equipamento")+"  "+result.getInt("nomeCabeceira"));
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem Acessorios Chegada cadastrados!" + 52);
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

	public AcessoriosChegada selecionar(Date dataPouso, String vooChegada) {
		AcessoriosChegada dados;
		
		String sql= "select * from Acessorios inner join AcessoriosChegada where idAcessorios=idAcessoriosChegada and  dataPrevista= ? and vooChegada= ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			pstm.setDate(1, dataPouso);
			pstm.setString(2, vooChegada);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				dados= new AcessoriosChegada(); //Sempre que executar um passo do loop devo criar um novo objeto
				
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				String dataHora= result.getDate("dataPrevista").toString()+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);
				dataHora= result.getDate("dataAtualizada").toString()+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);
				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				
				dados.setIdAcessoriosChegada(result.getInt("idAcessoriosChegada"));
				dados.setVooChegada(result.getString("vooChegada"));
				dados.setProcedencia(result.getString("procedencia"));
				dados.setVooPartida(result.getString("vooPartida"));
				dados.setDestino(result.getString("destino"));
				dados.setNomeCabeceira(result.getString("nomeCabeceira"));
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setNomeEsteira(result.getString("nomeEsteira"));
				dados.setIdHotran(result.getInt("idHotran"));

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
		return (null);
	}
	
	
	public List<AcessoriosChegada> obterAcessoriosChegada() {
		List<AcessoriosChegada> lstAcessoriosChegada= new ArrayList<AcessoriosChegada>();
		AcessoriosChegada dados;
		
		String sql= "select * from Acessorios inner join AcessoriosChegada where idAcessorios= idAcessoriosChegada ORDER BY dataPrevista ASC ,horaPrevista ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery();
	
			while (result.next()) {
				dados= new AcessoriosChegada(); //Sempre que executar um passo do loop devo criar um novo objeto
				
				dados.setIdAcessorios(result.getInt("idAcessorios"));
				String dataHora= result.getDate("dataPrevista").toString()+" "+result.getTime("horaPrevista");
				dados.setDataHoraPrevista(dataHora);
				dataHora= result.getDate("dataAtualizada").toString()+" "+result.getTime("horaAtualizada");
				dados.setDataHoraAtualizada(dataHora);
				dados.setMatricula(result.getString("matricula"));
				dados.setSituacao(result.getString("situacao"));
				dados.setTipo(result.getString("tipo"));
				dados.setEquipamento(result.getString("equipamento"));
				
				dados.setIdAcessoriosChegada(result.getInt("idAcessoriosChegada"));
				dados.setVooChegada(result.getString("vooChegada"));
				dados.setProcedencia(result.getString("procedencia"));
				dados.setVooPartida(result.getString("vooPartida"));
				dados.setDestino(result.getString("destino"));
				dados.setNomeCabeceira(result.getString("nomeCabeceira"));
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setNomeEsteira(result.getString("nomeEsteira"));
				dados.setIdHotran(result.getInt("idHotran"));
				
				lstAcessoriosChegada.add(dados);
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
		return (lstAcessoriosChegada);
	}
	
	public List<AcessoriosChegada> obterAcessoriosChegadaDeVoos(){
		List<AcessoriosChegada> lstAcessoriosChegada= new ArrayList<AcessoriosChegada>();
		
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		HotranDAO hotranDAO= new HotranDAO();
		Hotran hotran= null;
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		
		AcessoriosChegada dados;
		
		String sql= "select * from Voo inner join VooChegadaGrupoI where idVoo= idVooChegadaGrupoI "
				+ "and dataPrevistaPouso <>'null' and situacao <> 'ETC'order by idVoo asc";
		
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			pstm= con.prepareStatement(sql);
			
			ResultSet result = pstm.executeQuery();
			String dataHora= null;
			while (result.next()) {
				dados= new AcessoriosChegada(); //Sempre que executar um passo do loop devo criar um novo objeto
				if (result.getDate("dataPrevistaPouso")!= null) {
					dataHora= result.getDate("dataPrevistaPouso").toString()+" "+result.getTime("horaPrevistaPouso").toString();
					dados.setDataHoraPrevista(dataHora);
				}
				
				if(result.getDate("dataConfirmadaPouso")!= null) {
					dataHora= result.getDate("dataConfirmadaPouso").toString()+" "+result.getTime("horaConfirmadaPouso").toString();
				}else dataHora= result.getDate("dataPrevistaPouso").toString()+" "+result.getTime("horaConfirmadaPouso").toString();
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
				dados.setIdAcessoriosChegada(0);
				
				
				id= result.getInt("idVoo");
				
				hotran= hotranDAO.selecionarById(result.getInt("Hotran_idHotran"));
				if (hotran!= null) {
					dados.setVooPartida(String.valueOf(hotran.getNumeroVooDecola()));
					dados.setProcedencia(hotran.getEscalasOrigem());
					dados.setDestino(hotran.getEscalasDestino());
				}
				
				ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
				
				id=result.getInt("idProprietarioCiaAerea");
				ProprietarioCiaAerea proprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarById(id);
				
				if ((proprietarioCiaAerea!= null) && (hotran!= null)){
					String vooChegada= proprietarioCiaAerea.getSiglaCiaAerea()+" "+ hotran.getNumeroVooPousa();
					dados.setVooChegada(vooChegada);
					
				}
				dados.setIdHotran(result.getInt("idHotran"));
				dados.setNomeCabeceira(result.getString("nomeCabeceira"));
				dados.setNomeBox(result.getString("nomeBox"));
				dados.setNomeEsteira(result.getString("nomeEsteira"));
				lstAcessoriosChegada.add(dados);
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
		return (lstAcessoriosChegada);
	}
	
	public int selecionarMaximoID() {
		String sql= "SELECT MAX(idAcessoriosChegada) FROM AcessoriosChegada";
		
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
			JOptionPane.showMessageDialog(null, "Não existem Acessorios Chegada cadastrados!");
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
	
	public AcessoriosChegada selecionarById(int id) throws Exception {
		String sql= "SELECT * FROM AcessoriosChegada WHERE idAcessoriosChegada= ?";
		AcessoriosChegada dados= new AcessoriosChegada();
		Connection con= null;
		PreparedStatement pstm = null;
		
		con= ConnectionFactory.criarConexao();
		con.setAutoCommit(false);
		pstm= con.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet result = pstm.executeQuery();
		con.commit();
		AcessoriosDAO  acessoriosDAO= new AcessoriosDAO();
		try {
		Acessorios acessorios= acessoriosDAO.selecionarById(id);
		while(result.next() && acessorios != null) {
			
			dados.setDataHoraPrevista((acessorios.getDataHoraPrevista()));
			dados.setDataHoraAtualizada(acessorios.getDataHoraAtualizada());
			dados.setMatricula(acessorios.getMatricula());
			dados.setSituacao(acessorios.getSituacao());
			dados.setTipo(acessorios.getTipo());
			dados.setEquipamento(acessorios.getEquipamento());
			dados.setNomeBox(acessorios.getNomeBox());

			dados.setVooChegada(result.getString("vooChegada"));
			dados.setProcedencia(result.getString("procedencia"));
			dados.setVooPartida(result.getString("vooPartida"));
			dados.setDestino(result.getString("destino"));
			dados.setNomeCabeceira(result.getString("NomeCabeceira"));
			dados.setNomeBox(result.getString("NomeBox"));
			dados.setNomeEsteira(result.getString("NomeEsteira"));
			dados.setIdHotran(result.getInt("idHotran"));
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
	
public void alterar(AcessoriosChegada acessoriosChegada ) {
		
		String sql = "UPDATE AcessoriosChegada SET vooChegada=?, procedencia=?, "
				+ "vooPartida=?, destino=?, NomeCabeceira=?, NomeEsteira=?, "
				+ "idHotran=? WHERE idAcessoriosChegada=?";
		Connection con= null;
		PreparedStatement pstm = null;
		Acessorios acessorios=new Acessorios();
		AcessoriosDAO acessoriosDAO= new AcessoriosDAO();
		
		
		acessorios.setDataHoraAtualizada(acessoriosChegada.getDataHoraAtualizada());
		acessorios.setDataHoraPrevista(acessoriosChegada.getDataHoraPrevista());
		acessorios.setEquipamento(acessoriosChegada.getEquipamento());
		acessorios.setIdAcessorios(acessoriosChegada.getIdAcessorios());
		acessorios.setIdHotran(acessoriosChegada.getIdHotran());
		acessorios.setMatricula(acessoriosChegada.getMatricula());
		acessorios.setSituacao(acessoriosChegada.getSituacao());
		acessorios.setTipo(acessoriosChegada.getTipo());
		System.out.println("idAcessorios: "+acessoriosChegada.getIdAcessorios());
		acessorios.setIdAcessorios(acessoriosChegada.getIdAcessorios());
		
		try {
			acessoriosDAO.alterar(acessorios);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha na atualização em Acessorios"+e1.getMessage());
			
		}
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, acessoriosChegada.getVooChegada());
			pstm.setString(2, acessoriosChegada.getProcedencia());
			pstm.setString(3, acessoriosChegada.getVooPartida());
			pstm.setString(4, acessoriosChegada.getDestino());
			pstm.setString(5, acessoriosChegada.getNomeCabeceira());
			pstm.setString(6, acessoriosChegada.getNomeBox());
			pstm.setString(7, acessoriosChegada.getNomeEsteira());
			pstm.setInt(8, acessoriosChegada.getIdHotran());
			pstm.setInt(9, acessoriosChegada.getIdAcessorios());
			pstm.executeUpdate();
			con.commit();
			
			//JOptionPane.showMessageDialog(null, "Alteração bem sucedida em acessorios chegada!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização em Acessorios Chegada "+ e.getMessage());
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
		String sql = "DELETE FROM AcessoriosChegada WHERE idAcessoriosChegada=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			
			con.commit();
			//AcessoriosDAO acessoriosDAO= new AcessoriosDAO();
			//acessoriosDAO.apagar(id);
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Não existem Acessorios Chegada com este id: "+id);
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
