package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastroMovimento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField data;
	private JTextField hora;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMovimento frame = new CadastroMovimento();
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
	public CadastroMovimento() {
		setTitle("CADASTRO DE MOVIMENTOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setToolTipText("PO;DE; EE;SE;EP;SP;EI;SI;");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbltipoMovimento = new JLabel("tipoMovimento");
		
		JLabel lbldata = new JLabel("data");
		
		data = new JFormattedTextField("dd/MM/yyyy");
		data.setColumns(10);
		
		JLabel lblhora = new JLabel("hora");
		
		hora = new JFormattedTextField("HH:mm");
		hora.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JButton btnOk = new JButton("OK");
		
		JComboBox<String> cbxTipoMovimento = new JComboBox<String>();
		cbxTipoMovimento.setModel(new DefaultComboBoxModel<String>(new String[] {"PO", "DE", "EP", "SP", "EE", "SE", "EI", "SI"}));
		cbxTipoMovimento.setToolTipText("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(80)
									.addComponent(lbldata))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(71)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblhora)
										.addComponent(lbltipoMovimento))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(hora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(34)
									.addComponent(cbxTipoMovimento, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(101)
							.addComponent(btnCancelar)
							.addGap(54)
							.addComponent(btnOk)))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbltipoMovimento)
						.addComponent(cbxTipoMovimento, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbldata)
						.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblhora)
						.addComponent(hora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
