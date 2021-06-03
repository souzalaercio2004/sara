package sara.nemo.br.ufes.inf.view.tables;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosOcorrenciaVoo;

public class TableViewOcorrenciaVoo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo= new DefaultTableModel();
	

	/**
	 * Launch the application.
	 */
	public static void showTableViewOcorrenciaVoo() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewOcorrenciaVoo frame = new TableViewOcorrenciaVoo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableViewOcorrenciaVoo() {
		setTitle("Ocorrencias de voos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(739, Short.MAX_VALUE)
							.addComponent(btnSair)))
					.addGap(189))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(btnSair)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		
		table = new JTable(modelo);
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		modelo.addColumn("idOcorrenciaVoo");
		modelo.addColumn("matricula");
		modelo.addColumn("equipamento");
		modelo.addColumn("data");
		modelo.addColumn("hora");
		modelo.addColumn("situacao");
		
		OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
		
		for (AcessoriosOcorrenciaVoo o: ocorrenciaVooDAO.selecionarLista()) {
			modelo.addRow(new Object[] {
					o.getIdOcorrenciaVoo(),
					o.getMatricula(),
					o.getEquipamento(),
					o.getData(),
					o.getHora(),
					o.getSituacao()
			});
		}
		
	}
}
