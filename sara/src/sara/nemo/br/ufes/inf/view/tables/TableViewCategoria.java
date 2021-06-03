package sara.nemo.br.ufes.inf.view.tables;

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

import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.domain.Categoria;

public class TableViewCategoria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo= new DefaultTableModel();
	
	
	

	/**
	 * Create the frame.
	 */
	public TableViewCategoria() {
		setTitle("Categorias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		table = new JTable(modelo);
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));	
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		contentPane.setLayout(gl_contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);

		
		contentPane.setLayout(gl_contentPane);
		modelo.addColumn("Codigo");// id
		modelo.addColumn("Tipo Categoria");
		modelo.addColumn("Pax ou Cargo");
		modelo.addColumn("Especificacao");
		modelo.addColumn("Classe");
		
		
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		for (Categoria c: categoriaDAO.selecionarListaCategoria()) {
			modelo.addRow(new Object[] {
				c.getId(), c.getTipoCategoria(), c.getPassageiroOuCargueiro(), c.getEspecificacao(),
				c.getClasse()				
			});
		}
		add(scrollPane);
	}
	
	public static void showTableViewCategoria() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewCategoria frame = new TableViewCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
