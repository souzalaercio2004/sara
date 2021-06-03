package sara.nemo.br.ufes.inf.view.tables;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import sara.nemo.br.ufes.inf.DAO.AcessoriosPartidaDAO;
import sara.nemo.br.ufes.inf.DAO.HotranDAO;
import sara.nemo.br.ufes.inf.DAO.VooChegadaGrupoIDAO;
import sara.nemo.br.ufes.inf.controller.ControlSituacaoPartida;
import sara.nemo.br.ufes.inf.domain.Hotran;
import sara.nemo.br.ufes.inf.domain.VooChegadaGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;

public class TableViewPartidas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static boolean flag= true;

	PartidasTableModel modelo= new PartidasTableModel();

	public TableViewPartidas() {
		AcessoriosPartida partida= new AcessoriosPartida();
		setTitle("VOOS PARTIDAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		table = new JTable(modelo);
		table.setName("");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));	

		JScrollPane scrollPane = new JScrollPane(table);

		//TRATAMENTO DO CLICK DO MOUSE
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()== 3) {
					JPopupMenu situacao= ControlSituacaoPartida.menuSituacao(partida, table); // partidas -> tela que aciona o JPopupMenu

					situacao.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(30, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1260, GroupLayout.PREFERRED_SIZE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(49, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);


		contentPane.setLayout(gl_contentPane);

		//DAR NOME AS COLUNAS
		table.getColumnModel().getColumn(0).setHeaderValue(modelo.getColumName(0));
		table.getColumnModel().getColumn(1).setHeaderValue(modelo.getColumName(1));
		table.getColumnModel().getColumn(2).setHeaderValue(modelo.getColumName(2));
		table.getColumnModel().getColumn(3).setHeaderValue(modelo.getColumName(3));
		table.getColumnModel().getColumn(4).setHeaderValue(modelo.getColumName(4));
		table.getColumnModel().getColumn(5).setHeaderValue(modelo.getColumName(5));
		table.getColumnModel().getColumn(6).setHeaderValue(modelo.getColumName(6));
		table.getColumnModel().getColumn(7).setHeaderValue(modelo.getColumName(7));
		table.getColumnModel().getColumn(8).setHeaderValue(modelo.getColumName(8));
		table.getColumnModel().getColumn(9).setHeaderValue(modelo.getColumName(9));
		table.getColumnModel().getColumn(10).setHeaderValue(modelo.getColumName(10));
		table.getColumnModel().getColumn(11).setHeaderValue(modelo.getColumName(11));
		table.getColumnModel().getColumn(12).setHeaderValue(modelo.getColumName(12));
		table.getColumnModel().getColumn(13).setHeaderValue(modelo.getColumName(13));
		
		//DIMENSIONA AS COLUNAS 
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		table.getColumnModel().getColumn(10).setPreferredWidth(40);
		table.getColumnModel().getColumn(11).setPreferredWidth(40);
		table.getColumnModel().getColumn(12).setPreferredWidth(0);
		table.getColumnModel().getColumn(13).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(12).setMinWidth(0);
		table.getColumnModel().getColumn(12).setMaxWidth(0);
		
		table.getColumnModel().getColumn(13).setMinWidth(0);
		table.getColumnModel().getColumn(13).setMaxWidth(0);

		// CENTRALISAR OS VALORES NAS COLUNAS		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(9).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(10).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(11).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(12).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(13).setCellRenderer( centerRenderer );
		//

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		AcessoriosPartidaDAO acessoriosPartidaDAO= new AcessoriosPartidaDAO();
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
		VooChegadaGrupoI vooChegadaGrupoI= null;
		
		for (AcessoriosPartida lst: acessoriosPartidaDAO.obterAcessoriosPartidaDeVoos()) {
			
			lst.converte(lst);// Retorna data e hora no padrao dd/mm/aaaa hh:mm
			vooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarById(lst.getIdVoo());
			if(vooChegadaGrupoI!= null) {
				lst.setVooChegada(String.valueOf(vooChegadaGrupoI.getNumeroVooPouso()));
				//lst.setNomeBox(vooChegadaGrupoI.getNomeBox());
			}else System.out.println("A variavel vooChegada esta retornando null");
			
			if (!lst.getSituacao().equals("ETC")) modelo.addlinha(lst);
		
		}
		
		try {
			for (AcessoriosPartida lst: acessoriosPartidaDAO.obterAcessoriosPartida()) {
				String []dateTime= lst.getDataHoraPrevista().split(" ");
				LocalDate date= LocalDate.parse(dateTime[0]);
				LocalTime time = LocalTime.parse(dateTime[1]);
				LocalDateTime localDateTime= LocalDateTime.of(date, time);
				lst.setDataHoraPrevista(Converte.converteLocalDateTimeToString(localDateTime));

				dateTime= lst.getDataHoraAtualizada().split(" ");
				date= LocalDate.parse(dateTime[0]);
				time = LocalTime.parse(dateTime[1]);
				localDateTime= LocalDateTime.of(date, time);

				lst.setDataHoraAtualizada(Converte.converteLocalDateTimeToString(localDateTime));
				HotranDAO hotranDAO= new HotranDAO();
				Hotran hotran= hotranDAO.selecionarById(lst.getIdHotran());
				if (hotran!= null) lst.setEquipamento(hotran.getEquipamento());
				
				
				modelo.addlinha(lst);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void showTableViewPartidas() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewPartidas frame = new TableViewPartidas();
					frame.setVisible(flag);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}




