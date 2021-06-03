package sara.nemo.br.ufes.inf.DAO.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
	private Connection conexao;
	private PreparedStatement stmt;
	private ResultSet resultado;
	
	public void conectar() {
		String servidor= "jdbc:mysql://localhost:3306/sara?useSSL=false";
		String usuario= "root";
		String senha= "root";
		String driver= "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			this.conexao= DriverManager.getConnection(servidor, usuario, senha);
			System.out.println("Conectado ao Banco de Dados Sara ");
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public Connection getConexao() {
		return conexao;
	}


	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}


	public Statement getStmt() {
		return stmt;
	}


	
	public ResultSet getResultado() {
		return resultado;
	}


	public void setResultado(ResultSet resultado) {
		this.resultado = resultado;
	}


	public boolean testarconexao() {
		if(this.conexao != null) {
			return true;
		}else return false;
	}
	
}
