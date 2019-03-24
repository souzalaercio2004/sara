package BancoDeDados;

import java.sql.SQLException;

public class saraMain {

	public static void main(String[] args) throws SQLException {
		Conexao cnx= new Conexao();
		try {
			
			cnx.conectar();
			if (cnx.testarconexao()) {
				cnx.listarAeronaves();
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//
	}

}
