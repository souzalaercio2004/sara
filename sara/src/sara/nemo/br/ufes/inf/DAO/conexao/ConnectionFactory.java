package sara.nemo.br.ufes.inf.DAO.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String usuario= "root";
	private static final String senha= "root";
	private static String dataBase= "jdbc:mysql://localhost:3306/sara?useSSL=false";
	private static final String driver= "com.mysql.cj.jdbc.Driver";
	
	public static Connection criarConexao() throws Exception{
		Class.forName(driver);
		Connection conexao= DriverManager.getConnection(dataBase, usuario, senha);
		return conexao;
	}
	/*
	public static void main(String[]args) throws Exception{
		Connection con= criarConexao();
		if (con != null) {
			System.out.println("Conexão bem sucedida! " + con);
			con.close();
		}
	} */
}
