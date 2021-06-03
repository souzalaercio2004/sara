package sara.nemo.br.ufes.inf.view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;


public class ChegadasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<AcessoriosChegada> linhas;
	private String[]colunas= {
			"DATA E HORA PREVISTA", "DATA_HORA_ATUAL", "VOO", "MATRÍCULA", "SIT.", "PROCED.", "TIPO","VOO_PART.", 
			"DEST.", "EQUIP.", "CAB", "BOX","ESTEIRA","IDHOTRAN" ,"IDACESSORIOS"
	};
	
	
	
	public ChegadasTableModel() {
		this.linhas= new ArrayList<AcessoriosChegada>(); 
	}
	public void addlinha(AcessoriosChegada acessoriosChegada) {
		linhas.add(acessoriosChegada);
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
		AcessoriosChegada acessoriosChegada= (AcessoriosChegada)linhas.get(rowIndex);
		
		switch(columnIndex){
	        case 0: valorObject = acessoriosChegada.getDataHoraPrevista(); break;
	        case 1: valorObject = acessoriosChegada.getDataHoraAtualizada(); break;
	        case 2: valorObject = acessoriosChegada.getVooChegada(); break;
	        case 3: valorObject = acessoriosChegada.getMatricula(); break;
	        case 4: valorObject = acessoriosChegada.getSituacao(); break;
	        case 5: valorObject = acessoriosChegada.getProcedencia(); break;
	        case 6: valorObject = acessoriosChegada.getTipo(); break;
	        case 7: valorObject = acessoriosChegada.getVooPartida(); break;
	        case 8: valorObject = acessoriosChegada.getDestino(); break;
	        case 9: valorObject = acessoriosChegada.getEquipamento(); break;
	        case 10: valorObject = acessoriosChegada.getNomeCabeceira(); break;
	        case 11: valorObject = acessoriosChegada.getNomeBox(); break;
	        case 12: valorObject = acessoriosChegada.getNomeEsteira(); break;
	        case 13: valorObject = acessoriosChegada.getIdHotran(); break;
	        case 14: valorObject = acessoriosChegada.getIdAcessoriosChegada(); break;
	        
	        default: System.err.println("Índice inválido para tabela de chegadas");
	    }
		
		// TODO Auto-generated method stub
		return valorObject;
	}
	
	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna){
			case (0):
				linhas.get(linha).setDataHoraPrevista((String)valor); break;
			case (1):
				linhas.get(linha).setDataHoraAtualizada((String)valor); break;
			case (2):
				linhas.get(linha).setVooChegada((String)valor); break;
			case (3):
				linhas.get(linha).setMatricula((String)valor); break;
			case (4):
				linhas.get(linha).setSituacao((String)valor); break;
			case (5):
				linhas.get(linha).setProcedencia((String)valor); break;
			case (6):
				linhas.get(linha).setTipo((String)valor); break;
			case (7):
				linhas.get(linha).setVooPartida((String)valor); break;
			case (8):
				linhas.get(linha).setDestino((String)valor); break;
			case (9):
				linhas.get(linha).setEquipamento((String)valor); break;
			case (10):
				linhas.get(linha).setNomeCabeceira((String)valor); break;
			case (11):
				linhas.get(linha).setNomeBox((String)valor); break;
			case (12):
				linhas.get(linha).setNomeEsteira((String)valor); break;
			case (13):
				linhas.get(linha).setIdHotran((int)(valor)); break;	
			case (14):
				linhas.get(linha).setIdAcessoriosChegada((int)(valor)); break;	
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
	
	public void deleteRow(int linha) {
		linhas.remove(linha);
	}

}
