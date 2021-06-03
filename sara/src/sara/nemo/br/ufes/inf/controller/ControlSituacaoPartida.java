package sara.nemo.br.ufes.inf.controller;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import sara.nemo.br.ufes.inf.view.CadastroSituacaoPartidas;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;

public class ControlSituacaoPartida {
	private static String selecao;
	

	public static JPopupMenu menuSituacao(AcessoriosPartida partida, JTable table) {
		
		JPopupMenu menu= new JPopupMenu();
		
	    JMenuItem previsto= new JMenuItem("PREVISTO");
	    JMenuItem confirmado= new JMenuItem("CONFIRMADO");
	    JMenuItem pousado= new JMenuItem("POUSADO");
	    JMenuItem aeronavePatio= new JMenuItem("AERONAVE NO PÁTIO");
	    JMenuItem aeronaveEstadia= new JMenuItem("AERONAVE NA ESTADIA");
	    JMenuItem embarqueProximo= new JMenuItem("EMBARQUE PROXIMO");
	    JMenuItem embarqueImediato= new JMenuItem("EMBARQUE IMEDIATO");
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
	    menu.add(embarqueProximo);
	    menu.add(embarqueImediato);
	    menu.add(etapaConcluida);
	    menu.add(cancelado);
	    menu.add(atrasado);
	    menu.add(alternado);
	    menu.add(decolado);
	    menu.add(emManutencao);
	    
	    
	    previsto.addActionListener((ActionEvent e) -> {
	    	setSelecao("TBC");
	    	ControlSituacaoPartida.ctrlPrevisto(partida, table, menu);
	    	
        });
        
        confirmado.addActionListener((ActionEvent e) -> {
        	setSelecao("CFD");
        	ControlSituacaoPartida.ctrlConfirmado(partida, table, menu);
        	
        });
        
        pousado.addActionListener((ActionEvent e) -> {
        	setSelecao("POU");
        	ControlSituacaoPartida.ctrlPousado(partida, table, menu);
        });
        
        aeronavePatio.addActionListener((ActionEvent e) -> {
        	setSelecao("PTO");
        	ControlSituacaoPartida.ctrlAeronaveNoPatio(partida, table, menu);
        });
        
        aeronaveEstadia.addActionListener((ActionEvent e) -> {
        	setSelecao("EST");
        	ControlSituacaoPartida.ctrlAeronaveNaEstadia(partida, table, menu);
        });
        
        embarqueProximo.addActionListener((ActionEvent e) -> {
        	setSelecao("EMB");
        	ControlSituacaoPartida.ctrlEmbarqueProximo(partida, table, menu);
        });
        
        embarqueImediato.addActionListener((ActionEvent e) -> {
        	setSelecao("EMI");
        	ControlSituacaoPartida.ctrlEmbarqueImediato(partida, table, menu);
        });
        
        etapaConcluida.addActionListener((ActionEvent e) -> {
        	setSelecao("ETC");
        	ControlSituacaoPartida.ctrlEtapaConcluida(partida, table, menu);
        });
        
        cancelado.addActionListener((ActionEvent e) -> {
        	setSelecao("CLD");
        	ControlSituacaoPartida.ctrlCancelado(partida, table, menu);
        });
        
        atrasado.addActionListener((ActionEvent e) -> {
        	setSelecao("ATR");
        	ControlSituacaoPartida.ctrlAtrasado(partida, table, menu);
        });
        
        alternado.addActionListener((ActionEvent e) -> {
        	setSelecao("ALT");
        	ControlSituacaoPartida.ctrlAlternado(partida, table, menu);
        });
        
        
        decolado.addActionListener((ActionEvent e) -> {
        	setSelecao("DEC");
        	ControlSituacaoPartida.ctrlDecolado(partida, table, menu);
        });
        
        emManutencao.addActionListener((ActionEvent e) -> {
        	setSelecao("MNT");
        	ControlSituacaoPartida.ctrlEmManutencao(partida, table, menu);
        });
        
              
	    return(menu);
	}
	
	public static void ctrlPrevisto(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlConfirmado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
		partida= getAcessoriosPartida(table);
	}
	
	public static void ctrlPousado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlAeronaveNoPatio(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		table.setValueAt(getSelecao(), table.getSelectedRow(), 4);
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlAeronaveNaEstadia(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlEmbarqueProximo(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlEmbarqueImediato(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlEtapaConcluida(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	public static void ctrlCancelado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlAtrasado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlAlternado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {	
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlDecolado(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static void ctrlEmManutencao(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		CadastroSituacaoPartidas.showWindow(partida, table, menu);
	}
	
	public static AcessoriosPartida getAcessoriosPartida(JTable table) {
		AcessoriosPartida acessoriosPartida= new AcessoriosPartida();
		
		acessoriosPartida.setDataHoraPrevista(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		acessoriosPartida.setDataHoraAtualizada(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		acessoriosPartida.setVooPartida(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		acessoriosPartida.setMatricula((String)table.getValueAt(table.getSelectedRow(), 3));
		acessoriosPartida.setSituacao((String)table.getValueAt(table.getSelectedRow(), 4));
		acessoriosPartida.setDestino((String)table.getValueAt(table.getSelectedRow(), 5));
		acessoriosPartida.setTipo((String)table.getValueAt(table.getSelectedRow(), 6));
		acessoriosPartida.setVooChegada((String)table.getValueAt(table.getSelectedRow(), 7));
		acessoriosPartida.setProcedencia((String)table.getValueAt(table.getSelectedRow(), 8));
		acessoriosPartida.setEquipamento((String)table.getValueAt(table.getSelectedRow(), 9));
		acessoriosPartida.setNomeBox((String)table.getValueAt(table.getSelectedRow(), 10));
		acessoriosPartida.setPortao((String)table.getValueAt(table.getSelectedRow(), 11));
		acessoriosPartida.setIdHotran((int)((table.getValueAt(table.getSelectedRow(), 12))));		
		
		
		return acessoriosPartida;
	}

	public static void setSelecao(String valor) {
		selecao= valor;
	}
	
	public static String getSelecao() {
		return selecao;
	}
}
