package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private Connection conexao= null;
	private Statement stmt= null;
	private ResultSet resultado;
	
	public void conectar() {
		String servidor= "jdbc:mysql://localhost:3306/sara?useSSL=false";
		String usuario= "root";
		String senha= "root";
		String driver= "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			this.conexao= DriverManager.getConnection(servidor, usuario, senha);
			this.stmt= this.conexao.createStatement();
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean testarconexao() {
		if(this.conexao != null) {
			return true;
		}else return false;
	}
	
	public void listarAeronaves() throws SQLException, NullPointerException {
		try {
		String consulta= "SELECT * FROM Aeronave ORDER BY matricula";
		resultado= this.stmt.executeQuery(consulta);
		while (this.resultado.next()) {
			System.out.println("Matricula: "+ resultado.getString(2)+ " Tipo de Asa "+ resultado.getString(3)+ " Tipo da Aeronave "+ resultado.getString(4));
		}
		}catch (SQLException e){
			e.printStackTrace();
		}catch (NullPointerException n) {
			System.out.print("Esta retornando ponteiro nulo\n");
		}
	}
	
}
