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

import sara.nemo.br.ufes.inf.DAO.ProprietarioCiaAereaDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.domain.Proprietario;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;

public class CadProprietarioCiaAerea extends JFrame {
	static CadProprietarioCiaAerea frame = new CadProprietarioCiaAerea();
	
	
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSiglaCiaAerea;
	private JTextField txtNomeDoProprietario;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public CadProprietarioCiaAerea() {
		
		setTitle("CADASTRO DE PROPRIETARIO CIA AÉREA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(319, 223, 96, 25);
		contentPane.setLayout(null);
		
		JLabel lblNomeDoProprietrio = new JLabel("Nome do proprietário");
		lblNomeDoProprietrio.setBounds(63, 62, 169, 15);
		contentPane.add(lblNomeDoProprietrio);
		
		txtNomeDoProprietario = new JTextField();
		txtNomeDoProprietario.setBounds(236, 66, 261, 19);
		contentPane.add(txtNomeDoProprietario);
		txtNomeDoProprietario.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proprietario proprietario= new Proprietario();
				ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
				
				proprietario.setNomeProprietario(txtNomeDoProprietario.getText().toUpperCase());
				
				try {
					proprietarioDAO.inserir(proprietario);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				int id= proprietarioDAO.selecionarMaximoID();
				System.out.println(id);
				//proprietarioCiaAerea.setIdCiaAerea(idProprietario);
				proprietarioCiaAerea.setIdCiaAerea(id);
				proprietarioCiaAerea.setSiglaCiaAerea(txtSiglaCiaAerea.getText().toUpperCase());
				
				try {
					proprietarioCiaAereaDAO.inserir(proprietarioCiaAerea);
					CadRecursosPorProprietario.showWindow();// Cadastrar os recursos para esta cia aérea
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, "Falha no cadastro: Proprietário de companhia aérea invalido!");
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblsigla = new JLabel("Sigla da Cia Aérea");
		lblsigla.setBounds(58, 97, 148, 15);
		contentPane.add(lblsigla);
		
		txtSiglaCiaAerea = new JTextField();
		txtSiglaCiaAerea.setBounds(224, 95, 114, 19);
		txtSiglaCiaAerea.setColumns(10);
		contentPane.add(txtSiglaCiaAerea);
		btnIncluir.setBounds(51, 223, 96, 25);
		contentPane.add(btnIncluir);
		
		
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Qual o id do proprietario da cia aérea? "));
				ProprietarioCiaAerea prop= proprietarioCiaAereaDAO.selecionarById(id);	
				prop.setNomeProprietario(txtNomeDoProprietario.getText().toUpperCase());
				prop.setSiglaCiaAerea(txtSiglaCiaAerea.getText().toUpperCase());
				
				try {
					proprietarioCiaAereaDAO.alterar(prop);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAlterar.setBounds(176, 223, 83, 25);
		contentPane.add(btnAlterar);
		contentPane.add(btnCancelar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Qual o id do proprietario da cia aérea? "));
				proprietarioCiaAereaDAO.apagar(id);
			}
		});
		btnExcluir.setBounds(184, 268, 117, 25);
		contentPane.add(btnExcluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proprietarioCiaAereaDAO.selecionar();
			}
		});
		btnConsultar.setBounds(51, 268, 103, 25);
		contentPane.add(btnConsultar);
	}
}
