package BancoDeDados;

import java.sql.SQLException;

public class saraMain {

	public static void main(String[] args) throws SQLException {
		Conexao cnx= new Conexao();
		
			
		cnx.conectar();
		if (cnx.testarconexao()) {
			System.out.println("Teste de conexão bem sucedido");
		}
	}

}
