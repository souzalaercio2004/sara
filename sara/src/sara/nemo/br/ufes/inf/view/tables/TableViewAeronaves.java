package sara.nemo.br.ufes.inf.view.tables;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.view.CadastroAeronave;


public class TableViewAeronaves extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	public static boolean flag= true;
	AeronavesTableModel modelo= new AeronavesTableModel();
	
	public TableViewAeronaves(CadastroAeronave f) {
		setTitle("AERONAVES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 656, 188);
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(105))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		addWindowListener(new WindowAdapter(){ //Retorna ao menu inicial ao clicar no botao X do JFrame 
			public void windowClosing(WindowEvent event) {
				setVisible(false);
				//f.setVisible(true);
			}
		});
		
		
		//DAR NOME AS COLUNAS
		table.getColumnModel().getColumn(0).setHeaderValue(modelo.getColumName(0));
		table.getColumnModel().getColumn(1).setHeaderValue(modelo.getColumName(1));
		table.getColumnModel().getColumn(2).setHeaderValue(modelo.getColumName(2));
		table.getColumnModel().getColumn(3).setHeaderValue(modelo.getColumName(3));		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);

		
		// CENTRALISAR OS VALORES NAS COLUNAS		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
			
		for(Aeronave c: aeronaveDAO.selecionarList()) {
			
			modelo.addlinha(c);
				
		}
	
		getContentPane().add(scrollPane);
		
	}
	
	public static void showTableViewAeronaves(CadastroAeronave f){
		EventQueue.invokeLater(new Runnable() {
			public void run() throws DateTimeParseException {
				f.setVisible(false);
				TableViewAeronaves frame;
				frame = new TableViewAeronaves(f);
				frame.setVisible(flag);
				
			}
		});
	}
	
}
