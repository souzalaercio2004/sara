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

import sara.nemo.br.ufes.inf.DAO.RecursosPorProprietarioDAO;
import sara.nemo.br.ufes.inf.acessorios.AcessoriosRecursosPorProprietario;

public class TableViewRecursosPorProprietario extends JFrame {

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
	public static void showTableViewRecursosPorProprietario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewRecursosPorProprietario frame = new TableViewRecursosPorProprietario();
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
	public TableViewRecursosPorProprietario() {
		setTitle("Recursos por Proprietario Cadastrados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable(modelo);	
		JScrollPane scrollPane = new JScrollPane(table);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(47, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(98)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(238, Short.MAX_VALUE))
		);
		
			
		
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		modelo.addColumn("Codigo do Recurso");
		modelo.addColumn("Tipo de Recurso");
		modelo.addColumn("Nome");
		modelo.addColumn("Prioridade");
		modelo.addColumn("Esta em Uso");
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(150);
				table.getColumnModel().getColumn(4).setPreferredWidth(150);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		RecursosPorProprietarioDAO recursosPorProprietarioDAO= new RecursosPorProprietarioDAO();
		
		for (AcessoriosRecursosPorProprietario r: recursosPorProprietarioDAO.selecionarLista()) {
			modelo.addRow(new Object[] {
					r.getIdRecurso(),
					r.getTipoRecurso(),
					r.getNome(),
					r.getPrioridade(),
					r.getEstaEmUso()
			});
		}
		
	}
}
