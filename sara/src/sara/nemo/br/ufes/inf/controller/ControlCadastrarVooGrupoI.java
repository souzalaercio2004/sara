package sara.nemo.br.ufes.inf.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import sara.nemo.br.ufes.inf.DAO.VooGrupoIDAO;
import sara.nemo.br.ufes.inf.domain.VooGrupoI;

public class ControlCadastrarVooGrupoI {
	
	public static void inserir(VooGrupoI vooGrupoI) {
		VooGrupoIDAO vooGrupoIDAO= new VooGrupoIDAO();
		try {
			vooGrupoIDAO.inserir(vooGrupoI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dados invalidos para cadastro de voos do GrupoI"+ e.getMessage());
			
		}
	}
}
