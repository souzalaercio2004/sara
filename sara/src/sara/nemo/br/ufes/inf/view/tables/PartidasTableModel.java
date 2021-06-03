package sara.nemo.br.ufes.inf.view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;


public class PartidasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<AcessoriosPartida> linhas;
	private String[]colunas= {
			"DATA E HORA PREVISTA", "DATA_HORA_ATUAL", "VOO", "MATRICULA", "SIT.", "DESTINO", "TIPO", "VOO_CHEG.", "ORIGEM", "EQUIP.",
			"BOX.", "PORTAO", "ID_HOTRAN", "ID_ACESSORIOS"
	};
	
	
	
	public PartidasTableModel() {
		this.linhas= new ArrayList<>(); 
	}
	public void addlinha(AcessoriosPartida acessoriosPartida) {
		linhas.add(acessoriosPartida);
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
		
		AcessoriosPartida acessoriosPartida= (AcessoriosPartida)linhas.get(rowIndex);
		
		switch(columnIndex){
	        case 0: valorObject = acessoriosPartida.getDataHoraPrevista(); break;
	        case 1: valorObject = acessoriosPartida.getDataHoraAtualizada(); break;
	        case 2: valorObject = acessoriosPartida.getVooPartida(); break;
	        case 3: valorObject = acessoriosPartida.getMatricula(); break;
	        case 4: valorObject = acessoriosPartida.getSituacao(); break;
	        case 5: valorObject = acessoriosPartida.getDestino(); break;
	        case 6: valorObject = acessoriosPartida.getTipo(); break;
	        case 7: valorObject = acessoriosPartida.getVooChegada(); break;
	        case 8: valorObject = acessoriosPartida.getProcedencia(); break;
	        case 9: valorObject = acessoriosPartida.getEquipamento(); break;
	        case 10: valorObject = acessoriosPartida.getNomeBox(); break; 
	        case 11: valorObject = acessoriosPartida.getPortao(); break;
	        case 12: valorObject = acessoriosPartida.getIdHotran(); break;
	        case 13: valorObject = acessoriosPartida.getIdAcessorios(); break;
	        default: System.err.println("Índice inválido para tabela de partidas");
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
				linhas.get(linha).setVooPartida((String)valor); break;
			case (3):
				linhas.get(linha).setMatricula((String)valor); break;
			case (4):
				linhas.get(linha).setSituacao((String)valor); break;
			case (5):
				linhas.get(linha).setDestino((String)valor); break;
			case (6):
				linhas.get(linha).setTipo((String)valor); break;
			case (7):
				linhas.get(linha).setVooChegada((String)valor); break;
			case (8):
				linhas.get(linha).setProcedencia((String)valor); break;
			case (9):
				linhas.get(linha).setEquipamento((String)valor); break;
			case (10):
				linhas.get(linha).setNomeBox((String)valor); break;
			case (11):
				linhas.get(linha).setPortao((String)valor); break;
			case (12):
				linhas.get(linha).setIdHotran(((int)valor)); break;
			case (13):
				linhas.get(linha).setIdAcessorios(((int)valor)); break;
		}
	}
	
	public boolean	isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0: 
				return false;
			case 2:
				return true; 
			default:
				return true;
		}
	
	}

}
