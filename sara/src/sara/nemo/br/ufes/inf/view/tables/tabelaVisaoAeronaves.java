package sara.nemo.br.ufes.inf.view.tables;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;

public class tabelaVisaoAeronaves extends JFrame {

	/**
	 * 
	 */
	AeronaveDAO aeroDAO= new AeronaveDAO();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CÓDIGO","MATRICULA", "CODIGO_TIPO", "TIPO_ANV"
			}
		);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabelaVisaoAeronaves frame = new tabelaVisaoAeronaves();
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
	public tabelaVisaoAeronaves() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setModel(model);
		contentPane.add(table, BorderLayout.NORTH);
	
		aeroDAO= new AeronaveDAO();
		
		for (Aeronave a: aeroDAO.selecionarList()) {
			model.addRow(new Object[] {
					a.getIdAeronave(),
					a.getMatricula(),
					a.getTipoAsa(),
					a.getIdTipoAeronave()
			});
		}// TODO Auto-generated method stub
	}
}


