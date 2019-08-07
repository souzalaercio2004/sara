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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.Pista;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadPista extends JFrame {
	Recurso recurso= new Recurso();
	Pista pista= new Pista();
	PistaDAO pistaDAO= new PistaDAO();
	RecursoDAO recursoDAO= new RecursoDAO();
	String tipoRecurso= "PISTA";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeDaPista;
	private JTextField txtCabeceira;
	private JTextField txtLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPista frame = new CadPista();
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
	public CadPista() {
		setTitle("CADASTRO DE PISTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCabeceira = new JLabel("Cabeceira");
		lblCabeceira.setBounds(121, 165, 71, 15);
		
		txtCabeceira = new JTextField();
		txtCabeceira.setBounds(210, 163, 114, 19);
		txtCabeceira.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localização");
		lblLocalizacao.setBounds(96, 88, 96, 15);
		contentPane.add(lblLocalizacao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setColumns(10);
		txtLocalizacao.setBounds(209, 88, 114, 19);
		contentPane.add(txtLocalizacao);
		
		JRadioButton rdbEstaEmUso = new JRadioButton("Esta em uso");
		rdbEstaEmUso.setBounds(210, 190, 149, 23);
		contentPane.add(rdbEstaEmUso);
		contentPane.setLayout(null);
		
		JLabel lblnomeDaPista = new JLabel("Nome da Pista");
		lblnomeDaPista.setBounds(90, 134, 102, 15);
		contentPane.add(lblnomeDaPista);
		
		txtNomeDaPista = new JTextField();
		txtNomeDaPista.setBounds(210, 134, 114, 19);
		txtNomeDaPista.setColumns(10);
		contentPane.add(txtNomeDaPista);
		contentPane.add(lblCabeceira);
		contentPane.add(txtCabeceira);
		
		
		
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pistaDAO.selecionar();
			}
		});
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recurso.setTipoRecurso(tipoRecurso);
				recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
				
				try {
					recursoDAO.inserir(recurso);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				int idPista= recursoDAO.selecionarMaximoID();
				pista.setIdPista(idPista);
				pista.setNome(txtNomeDaPista.getText().toUpperCase());
				pista.setCabeceira((txtCabeceira.getText()));
				
				try {
					pistaDAO.inserir(pista);
					JOptionPane.showMessageDialog(null, "A pista foi cadastrada com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "A pista não foi cadastrada com sucesso!");
					e1.printStackTrace();
				}
			}
		});
		btnInserir.setBounds(57, 235, 96, 25);
		contentPane.add(btnInserir);
		btnConsultar.setBounds(188, 235, 126, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(328, 235, 96, 25);
		contentPane.add(btnCancelar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Digite o código da pista: "));
				try {
					recurso= recursoDAO.selecionarById(id);
					recurso.setTipoRecurso(tipoRecurso);
					recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
					recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
					recursoDAO.alterar(recurso);
					
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				pista= pistaDAO.selecionarById(id);
				//pista.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				pista.setNome(txtNomeDaPista.getText().toUpperCase());
				pista.setCabeceira(txtCabeceira.getText().toUpperCase());
				
				try {
					pistaDAO.alterar(pista);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAlterar.setBounds(53, 298, 87, 25);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Digite o código da pista que deseja deletar: "));
				pista= pistaDAO.selecionarById(id);
				pistaDAO.apagar(id);
			}
		});
		btnDeletar.setBounds(186, 298, 102, 25);
		contentPane.add(btnDeletar);
	}
}
