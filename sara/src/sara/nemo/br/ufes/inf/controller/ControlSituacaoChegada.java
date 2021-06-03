package sara.nemo.br.ufes.inf.controller;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import sara.nemo.br.ufes.inf.DAO.AcessoriosChegadaDAO;
import sara.nemo.br.ufes.inf.view.CadastroSituacaoChegadas;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;

public class ControlSituacaoChegada {
	private static String selecao;
	
	public static JPopupMenu menuSituacao(AcessoriosChegada chegada, JTable table) {
		
		JPopupMenu menu= new JPopupMenu();
		
	    JMenuItem previsto= new JMenuItem("PREVISTO");
	    JMenuItem confirmado= new JMenuItem("CONFIRMADO");
	    JMenuItem pousado= new JMenuItem("POUSADO");
	    JMenuItem aeronavePatio= new JMenuItem("AERONAVE NO PÁTIO");
	    JMenuItem aeronaveEstadia= new JMenuItem("AERONAVE NA ESTADIA");
	    JMenuItem etapaConcluida= new JMenuItem("ETAPA CONCLUIDA");
	    JMenuItem cancelado= new JMenuItem("CANCELADO");
	    JMenuItem atrasado= new JMenuItem("ATRASADO");
	    JMenuItem alternado= new JMenuItem("ALTERNADO");
	    JMenuItem decolado= new JMenuItem("DECOLADO");
	    JMenuItem emManutencao= new JMenuItem("EM MANUTENÇÃO");
	   
	    menu.add(previsto);
	    menu.add(confirmado);
	    menu.add(pousado);
	    menu.add(aeronavePatio);
	    menu.add(aeronaveEstadia);
	    menu.add(etapaConcluida);
	    menu.add(cancelado);
	    menu.add(atrasado);
	    menu.add(alternado);
	    menu.add(decolado);
	    menu.add(emManutencao);
	    
	    
	    previsto.addActionListener((ActionEvent e) -> {
	    	setSelecao("TBC");
	    	ControlSituacaoChegada.ctrlPrevisto(chegada, table, menu);
        });
        
        confirmado.addActionListener((ActionEvent e) -> {
        	setSelecao("CFD");
        	ControlSituacaoChegada.ctrlConfirmado(chegada, table, menu);
        });
        
        pousado.addActionListener((ActionEvent e) -> {
        	setSelecao("POU");
        	ControlSituacaoChegada.ctrlPousado(chegada, table, menu);
        });
        
        aeronavePatio.addActionListener((ActionEvent e) -> {
        	setSelecao("PTO");
        	ControlSituacaoChegada.ctrlAeronaveNoPatio(chegada, table, menu);
        });
        
        aeronaveEstadia.addActionListener((ActionEvent e) -> {
        	setSelecao("EST");
        	ControlSituacaoChegada.ctrlAeronaveNaEstadia(chegada, table, menu);
        });
        
        etapaConcluida.addActionListener((ActionEvent e) -> {
        	setSelecao("ETC");
        	ControlSituacaoChegada.ctrlEtapaConcluida(chegada, table, menu);
        });
        
        cancelado.addActionListener((ActionEvent e) -> {
        	setSelecao("CLD");
        	ControlSituacaoChegada.ctrlCancelado(chegada, table, menu);
        });
        
        atrasado.addActionListener((ActionEvent e) -> {
        	setSelecao("ATR");
        	ControlSituacaoChegada.ctrlAtrasado(chegada, table, menu);
        });
        
        alternado.addActionListener((ActionEvent e) -> {
        	setSelecao("ALT");
        	ControlSituacaoChegada.ctrlAlternado(chegada, table, menu);
        });
        
        
        decolado.addActionListener((ActionEvent e) -> {
        	setSelecao("DEC");
        	ControlSituacaoChegada.ctrlDecolado(chegada, table, menu);
        });
        
        emManutencao.addActionListener((ActionEvent e) -> {
        	setSelecao("MNT");
        	ControlSituacaoChegada.ctrlEmManutencao(chegada, table, menu);
        });
             
	    return(menu);
	}
	
	
	public static void ctrlPrevisto(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlConfirmado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		AcessoriosChegadaDAO acessoriosChegadaDAO= new AcessoriosChegadaDAO();
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
		chegada= getAcessoriosChegada(table);
		acessoriosChegadaDAO.apagar(chegada.getIdAcessoriosChegada()); 	// Ao confirmar o voo ele sai da tabela 
																		//AcessoriosChegada e vai para a tabela Voo grupo I
	}
	
	public static void ctrlPousado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		chegada= getAcessoriosChegada(table);
		chegada.setSituacao(selecao);
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
		
	}
	
	public static void ctrlAeronaveNoPatio(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlAeronaveNaEstadia(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlEtapaConcluida(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
		
	}
	public static void ctrlCancelado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlAtrasado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlAlternado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlDecolado(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static void ctrlEmManutencao(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		CadastroSituacaoChegadas.showWindow(chegada, table, menu);
	}
	
	public static AcessoriosChegada getAcessoriosChegada(JTable table) {
		AcessoriosChegada acessoriosChegada= new AcessoriosChegada();
		
		acessoriosChegada.setDataHoraPrevista((String)table.getValueAt(table.getSelectedRow(), 0));
		acessoriosChegada.setDataHoraAtualizada((String)table.getValueAt(table.getSelectedRow(), 1));
		acessoriosChegada.setVooChegada((String)table.getValueAt(table.getSelectedRow(), 2));
		acessoriosChegada.setMatricula((String)table.getValueAt(table.getSelectedRow(), 3));
		acessoriosChegada.setSituacao((String)table.getValueAt(table.getSelectedRow(), 4));
		acessoriosChegada.setProcedencia((String)table.getValueAt(table.getSelectedRow(), 5));
		acessoriosChegada.setTipo((String)table.getValueAt(table.getSelectedRow(), 6));
		acessoriosChegada.setVooPartida((String)table.getValueAt(table.getSelectedRow(), 7));
		acessoriosChegada.setDestino((String)table.getValueAt(table.getSelectedRow(), 8));
		acessoriosChegada.setEquipamento((String)table.getValueAt(table.getSelectedRow(), 9));
		acessoriosChegada.setNomeCabeceira((String)table.getValueAt(table.getSelectedRow(), 10));
		acessoriosChegada.setNomeBox((String)table.getValueAt(table.getSelectedRow(), 11));
		acessoriosChegada.setNomeEsteira((String)table.getValueAt(table.getSelectedRow(), 12));
		acessoriosChegada.setIdHotran(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 13).toString()));
		acessoriosChegada.setIdAcessoriosChegada(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 14).toString()));
		return acessoriosChegada;
	}

	
	
	public static void setSelecao(String valor) {
		selecao= valor;
	}
	
	public static String getSelecao() {
		return selecao;
	}
}
