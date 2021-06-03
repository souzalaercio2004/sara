package sara.nemo.br.ufes.inf.view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sara.nemo.br.ufes.inf.domain.Aeronave;


public class AeronavesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Aeronave> linhas;
	private String[]colunas= {
			"CÓDIGO", "MATRÍCULA", "TIPO_ASA", "COD_TIPO_ANV"
	};
	
	
	
	public AeronavesTableModel() {
		this.linhas= new ArrayList<>(); 
	}
	public void addlinha(Aeronave aeronave) {
		linhas.add(aeronave);
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return linhas.size();
	}		

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}
	public String getColumName(int columnIndex) {
		return colunas[columnIndex];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valorObject= null;
		
		Aeronave aeronave= (Aeronave)linhas.get(rowIndex);
		
		switch(columnIndex){
	        case 0: valorObject = aeronave.getIdAeronave(); break;
	        case 1: valorObject = aeronave.getMatricula(); break;
	        case 2: valorObject = aeronave.getTipoAsa(); break;
	        case 3: valorObject = aeronave.getIdTipoAeronave(); break;
	        
	        
	        default: System.err.println("Índice inválido para tabela de aeronaves");
	    }
		
		// TODO Auto-generated method stub
		return valorObject;
	}
	
	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna){
			case (0):
				linhas.get(linha).setIdAeronave((int)valor); break;
			case (1):
				linhas.get(linha).setMatricula((String)valor); break;
			case (2):
				linhas.get(linha).setTipoAsa((String)valor); break;
			case (3):
				linhas.get(linha).setIdTipoAeronave((int)valor); break;			
		}
	}
	
	public boolean	isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0: 
				return false;
			default:
				return true;
		}
	
	}

}
