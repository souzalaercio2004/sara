package sara.nemo.br.ufes.view;


import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.RecursoDAO;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CadPreferenciaUsoRecursos extends JFrame {
	RecursoDAO recursoDAO= new RecursoDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtprioridade;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
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
		setBounds(100, 100, 450, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(106, 53, 70, 15);
		contentPane.add(lblEmpresa);
		
		JComboBox<String> txtempresa = new JComboBox<String>();
		txtempresa.setModel(new DefaultComboBoxModel<String>(new String[] {"AVIANCA", "AZUL", "GLO", "LATAM"}));
		txtempresa.setBounds(209, 56, 187, 25);
		
		txtempresa.addItem("Esteira");
		txtempresa.addItem("Posicao");
		
		contentPane.add(txtempresa);
		
		JLabel lblRecurso = new JLabel("Recurso");
		lblRecurso.setBounds(106, 93, 70, 15);
		contentPane.add(lblRecurso);
		
		JComboBox<String> txtrecurso = new JComboBox<String>();
		txtrecurso.setBounds(209, 93, 187, 25);
		contentPane.add(txtrecurso);
		
		JLabel lblPrioridade = new JLabel("Prioridade");
		lblPrioridade.setBounds(106, 134, 86, 15);
		contentPane.add(lblPrioridade);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtprioridade = new JTextField();
		txtprioridade.setBounds(219, 132, 61, 19);
		contentPane.add(txtprioridade);
		txtprioridade.setColumns(10);
		btnOk.setBounds(67, 200, 117, 25);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(230, 200, 117, 25);
		contentPane.add(btnCancelar);
		ArrayList<String> tipoRecurso= recursoDAO.obterRecursos();
		for (String s: tipoRecurso) {
			txtrecurso.addItem(s);
		}
	}
}
