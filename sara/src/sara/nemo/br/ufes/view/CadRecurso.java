package sara.nemo.br.ufes.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class CadRecurso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9140603185634829318L;
	private JPanel contentPane;
	private JTextField tipoRecurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadRecurso frame = new CadRecurso();
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
	public CadRecurso() {
		setTitle("CADASTRO DE RECURSO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTipoDeRecurso = new JLabel("Tipo de Recurso");
		
		tipoRecurso = new JTextField();
		tipoRecurso.setColumns(10);
		
		JRadioButton estaEmUso = new JRadioButton("Esta em uso?");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JButton btnOK = new JButton("OK");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(estaEmUso)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTipoDeRecurso)
									.addGap(50)
									.addComponent(tipoRecurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(btnCancelar)
							.addGap(104)
							.addComponent(btnOK)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tipoRecurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDeRecurso))
					.addGap(36)
					.addComponent(estaEmUso)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOK))
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
