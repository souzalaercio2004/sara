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

import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadPortaoDeEmbarque extends JFrame {
	String tipoDeRecurso= "PORTÃO DE EMBARQUE";
	PortaoDeEmbarque portaoDeEmbarque= new PortaoDeEmbarque();
	PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroDoPortao;
	private JTextField txtLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPortaoDeEmbarque frame = new CadPortaoDeEmbarque();
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
	public CadPortaoDeEmbarque() {
		setTitle("CADASTRO DE PORTÃO DE EMBARQUE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JRadioButton rdbEstaEmUso = new JRadioButton("Esta em uso?");
		rdbEstaEmUso.setBounds(256, 121, 149, 23);
		contentPane.add(rdbEstaEmUso);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(39, 224, 103, 25);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id do portão de embarque que deseja alterar? "));
				
				portaoDeEmbarque= portaoDeEmbarqueDAO.selecionarById(id);
				try {
					recurso= recursoDAO.selecionarById(id);
					recurso.setTipoRecurso(tipoDeRecurso);
					recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
					recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
					recursoDAO.alterar(recurso);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				portaoDeEmbarque.setNome(txtNumeroDoPortao.getText());
				
				try {
					portaoDeEmbarqueDAO.alterar(portaoDeEmbarque);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblnumeroDoPortao = new JLabel("Numero do Portão");
		lblnumeroDoPortao.setBounds(99, 56, 129, 15);
		contentPane.add(lblnumeroDoPortao);
		
		txtNumeroDoPortao = new JTextField();
		txtNumeroDoPortao.setBounds(256, 54, 114, 19);
		txtNumeroDoPortao.setColumns(10);
		contentPane.add(txtNumeroDoPortao);
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(132, 97, 96, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(256, 94, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recurso.setTipoRecurso(tipoDeRecurso);
				recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
				
				try {
					recursoDAO.inserir(recurso);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				int idPortaoDeEmbarque= recursoDAO.selecionarMaximoID();
				
				portaoDeEmbarque.setIdPortaoDeEmbarque(idPortaoDeEmbarque);
				portaoDeEmbarque.setNome(txtNumeroDoPortao.getText());
				
				try {
					portaoDeEmbarqueDAO.inserir(portaoDeEmbarque);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Falha no cadastro: Portão de embarque invalido");
					e1.printStackTrace();
				}
				
			}
		});
		btnInserir.setBounds(39, 165, 117, 25);
		contentPane.add(btnInserir);
		
		
		
		
		contentPane.add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				portaoDeEmbarqueDAO.selecionar();
			}
		});
		btnConsultar.setBounds(179, 165, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 165, 96, 25);
		contentPane.add(btnCancelar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id do portão de embarque que deseja deletar? "));
				portaoDeEmbarqueDAO.apagar(id);
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Nenhum dado excluido");
					setVisible(false);
				}
			}
		});
		btnDeletar.setBounds(179, 224, 117, 25);
		contentPane.add(btnDeletar);
		
		
	}
}
