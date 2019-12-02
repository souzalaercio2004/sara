package sara.nemo.br.ufes.inf.tables;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;

public class TableViewAeronaves extends JFrame {

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
	public static void showTableViewAeronaves() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewAeronaves frame = new TableViewAeronaves();
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
	public TableViewAeronaves() {
		setTitle("Aeronaves Cadastradas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		
		table = new JTable(modelo);
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		modelo.addColumn("idAeronave");
		modelo.addColumn("matricula");
		modelo.addColumn("tipoAsa");
		modelo.addColumn("TipoAeronave_idTipoAeronave");
		
		AeronaveDAO aeroDAO= new AeronaveDAO();
		
		for (Aeronave a: aeroDAO.selecionarList()) {
			modelo.addRow(new Object[] {
					a.getId(),
					a.getMatricula(),
					a.getTipoAsa(),
					a.getIdTipoAeronave()
			});
		}
		
	}
}
