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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class CadHotran extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel frequencia;
	private JTextField textnumeroVooPousa;
	private JTextField texthorarioPrevisto;
	private JTextField textEscalas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadHotran frame = new CadHotran();
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
	public CadHotran() {
		setTitle("CADASTRO DE HOTRAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 488);
		frequencia = new JPanel();
		frequencia.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frequencia);
		
		JLabel numeroVoo = new JLabel("Número do voo ");
		
		textnumeroVooPousa = new JTextField();
		textnumeroVooPousa.setColumns(10);
		
		texthorarioPrevisto = new JTextField();
		texthorarioPrevisto.setColumns(10);
		
		JLabel lblhorarioPrevisto = new JLabel("Horário Previsto");
		
		JLabel lblfrequencia = new JLabel("Frequencia");
		
		JRadioButton Segunda = new JRadioButton("Seg");
		
		JRadioButton Terca = new JRadioButton("Ter");
		
		JRadioButton Domingo = new JRadioButton("Dom");
		
		JRadioButton Quarta = new JRadioButton("Qua");
		
		JRadioButton Quinta_1 = new JRadioButton("Qui");
		
		JRadioButton Sexta = new JRadioButton("Sex");
		
		JRadioButton Sabado = new JRadioButton("Sab");
		
		JComboBox<Object> OPCAO = new JComboBox<Object>();
		OPCAO.setModel(new DefaultComboBoxModel<Object>(new String[] {"Pouso", "Decolagem"}));
		OPCAO.setMaximumRowCount(2);
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JButton btnOk = new JButton("OK");
		
		JLabel lblEscalas = new JLabel("Escalas");
		
		textEscalas = new JTextField();
		textEscalas.setColumns(10);
		GroupLayout gl_frequencia = new GroupLayout(frequencia);
		gl_frequencia.setHorizontalGroup(
			gl_frequencia.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_frequencia.createSequentialGroup()
					.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_frequencia.createSequentialGroup()
							.addContainerGap()
							.addComponent(numeroVoo))
						.addGroup(gl_frequencia.createSequentialGroup()
							.addGap(147)
							.addComponent(OPCAO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_frequencia.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_frequencia.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_frequencia.createSequentialGroup()
										.addComponent(lblfrequencia)
										.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
										.addComponent(lblEscalas)
										.addGap(84))
									.addGroup(gl_frequencia.createSequentialGroup()
										.addComponent(Domingo)
										.addGap(18)
										.addComponent(Segunda)
										.addGap(18)
										.addComponent(Terca)
										.addGap(18)
										.addComponent(Quarta))
									.addComponent(lblhorarioPrevisto)
									.addGroup(gl_frequencia.createSequentialGroup()
										.addComponent(Quinta_1)
										.addGap(26)
										.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
											.addComponent(btnCancelar)
											.addGroup(gl_frequencia.createSequentialGroup()
												.addComponent(Sexta)
												.addGap(18)
												.addComponent(Sabado, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
										.addGap(78)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnOk))
							.addGroup(Alignment.LEADING, gl_frequencia.createSequentialGroup()
								.addGap(195)
								.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
									.addComponent(textnumeroVooPousa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(texthorarioPrevisto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(91, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_frequencia.createSequentialGroup()
					.addContainerGap(284, Short.MAX_VALUE)
					.addComponent(textEscalas, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_frequencia.setVerticalGroup(
			gl_frequencia.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_frequencia.createSequentialGroup()
					.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_frequencia.createSequentialGroup()
							.addGap(83)
							.addComponent(OPCAO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addGroup(gl_frequencia.createParallelGroup(Alignment.BASELINE)
								.addComponent(numeroVoo)
								.addComponent(textnumeroVooPousa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_frequencia.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblhorarioPrevisto)
								.addComponent(texthorarioPrevisto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_frequencia.createSequentialGroup()
							.addGap(239)
							.addComponent(lblfrequencia)))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_frequencia.createSequentialGroup()
							.addComponent(lblEscalas)
							.addGap(27)
							.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
								.addComponent(Domingo)
								.addGroup(gl_frequencia.createParallelGroup(Alignment.BASELINE)
									.addComponent(Segunda)
									.addComponent(Terca)
									.addComponent(Quarta)))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_frequencia.createSequentialGroup()
									.addGap(2)
									.addGroup(gl_frequencia.createParallelGroup(Alignment.LEADING)
										.addComponent(Quinta_1)
										.addComponent(Sexta)))
								.addGroup(gl_frequencia.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(Sabado)))
							.addGap(34))
						.addGroup(gl_frequencia.createSequentialGroup()
							.addComponent(textEscalas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_frequencia.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addContainerGap())
		);
		frequencia.setLayout(gl_frequencia);
	}
}
