package sara.nemo.br.ufes.inf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.factory.ConnectionFactory;
import sara.nemo.br.ufes.inf.item.ItemCategoria;

public class CategoriaDAO {
	public void inserir(Categoria cat)throws SQLException {
		String sql= "INSERT INTO Categoria(classe, passageiroOuCargueiro, especificacao, tipo)"+
					"VALUES(?, ?, ?, ?)";
		Connection con= null;
		PreparedStatement pstm = null;
		
		try {
			con= ConnectionFactory.criarConexao();
			if (con != null) {
				pstm= con.prepareStatement(sql);
				pstm.setString(1, cat.getClasse());
				pstm.setString(2, cat.getPassageiroOuCargueiro());
				pstm.setString(3, cat.getEspecificacao());
				pstm.setString(4, cat.getTipoCategoria());
				pstm.execute();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha no Cadastro: Categoria invalida!");
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
	
	public void selecionar() {
		String sql= "SELECT * FROM Categoria ORDER BY idCategoria ASC";
		
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" +String.format("%-15s", result.getString("classe"))+ String.format("%-12s", result.getString("passageiroOuCargueiro"))+ String.format("%-9s", result.getString("especificacao"))+ "\t"+ result.getString("tipo"));
			}
			con.commit();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem categorias cadastradas!");
			e.printStackTrace();
			
		}
	}
	public List<Categoria> selecionarListaCategoria() {
		String sql= "select * from Categoria order by tipo asc, passageiroOuCargueiro asc, classe asc";
		List<Categoria> listaCategoria= new ArrayList<Categoria>();
		Categoria cat;
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();

			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			while (result.next()) {
				cat= new Categoria();
				cat.setId(result.getInt(1));
				cat.setClasse(result.getString("classe"));
				cat.setEspecificacao(result.getString("especificacao"));
				cat.setPassageiroOuCargueiro(result.getString("passageiroOuCargueiro"));
				cat.setTipoCategoria(result.getString("tipo"));
				listaCategoria.add(cat);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem categorias cadastradas!");
			e.printStackTrace();
		}
		
		return listaCategoria;
	}
	public List<ItemCategoria> selecionarLista() {
		String sql= "SELECT * FROM Categoria ORDER BY especificacao ASC";
		Categoria cat;
		List<ItemCategoria>  lista= new ArrayList<ItemCategoria>();
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			
			con= ConnectionFactory.criarConexao();
			
			pstm= con.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			while (result.next()) {
				cat= new Categoria();
				cat.setId(result.getInt(1));
				cat.setClasse(result.getString("classe"));
				cat.setEspecificacao(result.getString("especificacao"));
				cat.setPassageiroOuCargueiro(result.getString("passageiroOuCargueiro"));
				cat.setTipoCategoria(result.getString("tipo"));
				ItemCategoria item= new ItemCategoria(cat);
				lista.add(item);
				
			}
			return lista;
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não existem categorias cadastradas!");
			e.printStackTrace();
			
		}
		return null;
	}
	
	public Categoria selecionarById(int id) {
		Categoria cat= new Categoria();
		String sql= "SELECT * FROM sara.Categoria WHERE Categoria.idCategoria = ?";
		
		Connection con= null;
		PreparedStatement pstm = null;
		ResultSet result= null;
		
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (con!= null) {
			try {
				pstm= con.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Falha na conexão com o banco de dados");
				e.printStackTrace();
				
			}
			try {
				pstm.setInt(1, id);
				
			} catch (SQLException e) {
				System.out.println("valor de id invalido "+ id);
				e.printStackTrace();
			}
			try {
				result = pstm.executeQuery();
				con.commit();
			} catch (SQLException e) {
				System.out.println("Falha na execução da consulta "+ result);
				e.printStackTrace();
			}
			
			try {
				if (result.next()) {
					cat.setId(result.getInt(1));
					cat.setClasse(result.getString("classe"));
					cat.setPassageiroOuCargueiro(result.getString("passageiroOuCargueiro"));
					cat.setEspecificacao(result.getString("especificacao"));
					cat.setTipoCategoria(result.getString("tipo"));
					System.out.println(" E agora: "+cat.toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (cat);
		}else {
			
			return (null);
		}
	};
	
public void alterar(Categoria cat )throws Exception {
		
		String sql = "UPDATE Categoria SET classe=?, passageiroOuCargueiro=?, especificacao=? , tipo=? WHERE idCategoria=?";
		Connection con= null;
		PreparedStatement pstm = null;
		try {
			con= ConnectionFactory.criarConexao();
			con.setAutoCommit(false);
			
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, cat.getClasse());
			pstm.setString(2, cat.getPassageiroOuCargueiro());
			pstm.setString(3, cat.getEspecificacao());
			pstm.setString(4, cat.getTipoCategoria());
			pstm.setInt(5, cat.getId());
			
			pstm.executeUpdate();
			con.commit();
			
			JOptionPane.showMessageDialog(null, "Alteração bem sucedida!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha na atualização da categoria");
			e.printStackTrace();
			
		}finally {
			con.close();
		}		
	}
public void apagar(int id){
	String sql = "DELETE FROM Categoria WHERE idCategoria=?";
	Connection con= null;
	PreparedStatement pstm = null;
	
	try {
		con= ConnectionFactory.criarConexao();
		con.setAutoCommit(false);
		pstm= con.prepareStatement(sql);
		pstm.setInt(1, id);
		
		pstm.executeUpdate();
		con.commit();
		JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso!");
		
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Código de categoria inválido: "+ id);
		}
	}

}
