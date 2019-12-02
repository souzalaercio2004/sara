package sara.nemo.br.ufes.inf.tables;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sara.nemo.br.ufes.inf.DAO.PartidasDAO;
import sara.nemo.br.ufes.inf.acessorios.AcessoriosPartida;
import sara.nemo.br.ufes.view.Converte;

public class TableViewPartidas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo= new DefaultTableModel();
	
	public TableViewPartidas() {
		setTitle("PARTIDAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		table = new JTable(modelo);
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));	
		
		JScrollPane scrollPane = new JScrollPane(table);
		
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
		modelo.addColumn("DH Normal");
		modelo.addColumn("H_Atual");
		modelo.addColumn("voo");
		modelo.addColumn("Prefixo");
		modelo.addColumn("Sit.");
		modelo.addColumn("Destino");
		modelo.addColumn("Tipo");
		modelo.addColumn("Cheg.");
		modelo.addColumn("Equip.");
		modelo.addColumn("Port.");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		PartidasDAO partidasDAO= new PartidasDAO();
		String data= (JOptionPane.showInputDialog("Digite a data dos Voos no formato dd/mm/aaaa : "));
		LocalDate dataHoje= Converte.converteStringToLocalDateNoFormatoDDMMAAAA(data, "dd/MM/yyyy");
		int diaHoje= dataHoje.getDayOfWeek().getValue();
		String dia= null;
		
		
		switch (diaHoje) {
		case(0):
			dia= "domingo";
			break;
			
		case(1):
			dia= "segundaFeira";
			break;
		case(2):
			dia= "tercaFeira";
			break;
		case(3):
			dia= "quartaFeira";
			break;
		case(4):
			dia= "quintaFeira";
			break;
		case(5):
			dia= "sextaFeira";
			break;
		case(6):
			dia= "sabado";
			break;
			default:
				dia= null;
		}
		
		System.out.println("Dia: "+ dia);
		for (AcessoriosPartida c: partidasDAO.selecionarDadosDePartida(dataHoje, dia)) {
			modelo.addRow(new Object[] {
					c.getDataHoraPrevista().format(formatter),c.getHoraAtualizada(), c.getVooPartida(), c.getPrefixo(), c.getSituacao(),
				c.getDestino(), c.getTipo(), c.getEquipamento(), c.getPortao()
			});
		}
		getContentPane().add(scrollPane);
	}
	
	public static void showTableViewPartidas() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewPartidas frame = new TableViewPartidas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
