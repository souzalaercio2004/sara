package sara.nemo.br.ufes.view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadPreferenciaUsoRecursos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtprioridade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPreferenciaUsoRecursos frame = new CadPreferenciaUsoRecursos();
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
	public CadPreferenciaUsoRecursos() {
		setTitle("Prioridade no uso de Recursos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(106, 53, 70, 15);
		contentPane.add(lblEmpresa);
		
		JLabel lblRecurso = new JLabel("Recurso");
		lblRecurso.setBounds(106, 93, 70, 15);
		contentPane.add(lblRecurso);
		
		JLabel lblPrioridade = new JLabel("Prioridade");
		lblPrioridade.setBounds(106, 134, 86, 15);
		contentPane.add(lblPrioridade);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(67, 200, 117, 25);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 200, 117, 25);
		contentPane.add(btnCancelar);
		
		JComboBox<String> txtempresa = new JComboBox<String>();
		txtempresa.setModel((new DefaultComboBoxModel<String>(new String[] {"AVIANCA", "AZUL", "GLO", "LATAM"})));
		txtempresa.setBounds(209, 56, 187, 25);
		txtempresa.addItem("Esteira");
		txtempresa.addItem("Posicao");
		
		contentPane.add(txtempresa);
		
		JComboBox<String> txtrecurso = new JComboBox<String>();
		txtrecurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Cabeceira", "Esteira", "Hangar", "Posicao Heliponto", "Posicao Patio", "Portao de Embarque"}));
		txtrecurso.setBounds(209, 93, 187, 25);
		contentPane.add(txtrecurso);
		
		txtprioridade = new JTextField();
		txtprioridade.setBounds(219, 132, 61, 19);
		contentPane.add(txtprioridade);
		txtprioridade.setColumns(10);
	}
}
