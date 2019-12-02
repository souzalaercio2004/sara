package sara.nemo.br.ufes.inf.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sara.nemo.br.ufes.inf.acessorios.AcessoriosPartida;

public class PartidasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	List<AcessoriosPartida> linhas;
	String[]colunas= {
			"DATA E HORA PREVISTA", "HORA_ATUAL", "VOO", "PREFIXO", "SIT.", "DESTINO", "TIPO", "CHEG.", "EQUIP.", "PORTAO"
	};
	
	public PartidasTableModel() {
		this.linhas= new ArrayList<AcessoriosPartida>();
	}
	
	public PartidasTableModel(List<AcessoriosPartida>linhas) {
		this.linhas= linhas;
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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valorObject= null;
		AcessoriosPartida acessoriosPartida= linhas.get(rowIndex);
		
		switch(columnIndex){
        case 0: valorObject = acessoriosPartida.getDataHoraPrevista(); break;
        case 1: valorObject = acessoriosPartida.getHoraAtualizada(); break;
        case 2: valorObject = acessoriosPartida.getVooPartida(); break;
        case 3: valorObject = acessoriosPartida.getPrefixo(); break;
        case 4: valorObject = acessoriosPartida.getSituacao(); break;
        case 5: valorObject = acessoriosPartida.getDestino(); break;
        case 6: valorObject = acessoriosPartida.getTipo(); break;
        case 7: valorObject = acessoriosPartida.getVooChegada(); break;
        case 8: valorObject = acessoriosPartida.getEquipamento(); break;
        case 9: valorObject = acessoriosPartida.getPortao(); break;       
        
        default: System.err.println("Índice inválido para tabela de partidas");
    }
		// TODO Auto-generated method stub
		return valorObject;
	}

}
