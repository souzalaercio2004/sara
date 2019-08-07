package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.Recurso;
import javax.swing.JRadioButton;

public class CadRecurso extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9140603185634829318L;
	private JPanel contentPane;
	private JTextField txtTipoRecurso;
	private JTextField txtLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
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
		Recurso recurso= new Recurso();
		setTitle("CADASTRO DE RECURSO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTipoDeRecurso = new JLabel("Tipo de Recurso");
		lblTipoDeRecurso.setBounds(68, 53, 113, 15);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(317, 155, 96, 25);
		contentPane.setLayout(null);
		contentPane.add(lblTipoDeRecurso);
		
		txtTipoRecurso = new JTextField();
		txtTipoRecurso.setBounds(199, 53, 214, 19);
		txtTipoRecurso.setColumns(10);
		contentPane.add(txtTipoRecurso);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecursoDAO recursoDAO= new RecursoDAO();
				
				recursoDAO.selecionar();
				//if (recurso!= null) System.out.println(recurso.getIdRecurso() +"    "+ recurso.getTipoRecurso());
			}
		});
		
		JRadioButton rdbtnEstaEmUso = new JRadioButton("Esta em uso");
		rdbtnEstaEmUso.setBounds(204, 106, 149, 23);
		contentPane.add(rdbtnEstaEmUso);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecursoDAO recursoDAO= new RecursoDAO();
				
				recurso.setTipoRecurso(txtTipoRecurso.getText().toUpperCase());
				recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				recurso.setEstaEmUso(rdbtnEstaEmUso.isSelected());
				try {
					recursoDAO.inserir(recurso);
					JOptionPane.showMessageDialog(null, "Recurso inserido com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Falha na Inserção do recurso com sucesso!");
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(78, 83, 131, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(204, 83, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		
		
		btnInserir.setBounds(44, 155, 117, 25);
		contentPane.add(btnInserir);
		btnConsultar.setBounds(178, 155, 103, 25);
		contentPane.add(btnConsultar);
		contentPane.add(btnCancelar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recurso recurso= new Recurso();
				RecursoDAO recursoDAO= new RecursoDAO();
				int id= Integer.valueOf(JOptionPane.showInputDialog("Digite o código do recurso: "));
				try {
					recurso= recursoDAO.selecionarById(id);
					recurso.setTipoRecurso(txtTipoRecurso.getText().toUpperCase());
					recursoDAO.alterar(recurso);
					JOptionPane.showMessageDialog(null, "Alteração de recurso bem sucedida!");
				} catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, "Não existe recurso com este código!");
					e2.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(44, 207, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Digite o código do recurso: "));
				RecursoDAO recursoDAO= new RecursoDAO();
				recursoDAO.apagar(id);
			}
		});
		btnDeletar.setBounds(178, 207, 117, 25);
		contentPane.add(btnDeletar);
	}
}
