package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.OcorrenciaVoo;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadRecursoEmOcorrenciaVoo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();
	OcorrenciaVoo ocorrenciaVoo= new OcorrenciaVoo();
	OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadRecursoEmOcorrenciaVoo frame = new CadRecursoEmOcorrenciaVoo();
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
	public CadRecursoEmOcorrenciaVoo() {
		setTitle("Ocorrencias de voos e recursos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdRecurso = new JLabel("Recurso");
		lblIdRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdRecurso.setBounds(71, 42, 100, 15);
		contentPane.add(lblIdRecurso);
		
		JComboBox<String> cbxRecurso = new JComboBox<String>();
		ArrayList<String> recurso= recursoDAO.obterRecursos();
		for (String s: recurso) {
			cbxRecurso.addItem(s);
		}
		cbxRecurso.setBounds(177, 37, 153, 24);
		contentPane.add(cbxRecurso);
		
		JLabel lblOcorreNciaDe = new JLabel("Ocorrencia de Voo");
		lblOcorreNciaDe.setBounds(12, 105, 146, 15);
		contentPane.add(lblOcorreNciaDe);
		
		JComboBox<String> cbxOcorrenciaVoo = new JComboBox<String>();
		cbxOcorrenciaVoo.setBounds(177, 100, 146, 24);
		contentPane.add(cbxOcorrenciaVoo);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(12, 175, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(153, 175, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(293, 175, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(12, 212, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(163, 212, 117, 25);
		contentPane.add(btnDeletar);
	}
}
