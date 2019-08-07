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

import sara.nemo.br.ufes.inf.DAO.PosicaoHelipontoDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PosicaoHeliponto;
import sara.nemo.br.ufes.inf.domain.Recurso;
import javax.swing.JRadioButton;

public class CadPosicaoHeliponto extends JFrame {
	String tipoRecurso= "HELIPONTO";
	
	PosicaoHeliponto posicaoHeliponto= new PosicaoHeliponto();
	PosicaoHelipontoDAO posicaoHelipontoDAO= new PosicaoHelipontoDAO();
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeDaPosicao;
	private JTextField txtLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPosicaoHeliponto frame = new CadPosicaoHeliponto();
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
	public CadPosicaoHeliponto() {
		setTitle("CADASTRO DE POSICOES DO HELIPONTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLocalizacao = new JLabel("Localização");
		lblLocalizacao.setBounds(76, 106, 88, 15);
		contentPane.add(lblLocalizacao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setColumns(10);
		txtLocalizacao.setBounds(196, 104, 114, 19);
		contentPane.add(txtLocalizacao);
		
		JLabel lblNomeDaPosicao = new JLabel("Nome da Posicão");
		lblNomeDaPosicao.setBounds(43, 133, 121, 15);
		contentPane.add(lblNomeDaPosicao);
		
		txtNomeDaPosicao = new JTextField();
		txtNomeDaPosicao.setBounds(196, 133, 114, 19);
		contentPane.add(txtNomeDaPosicao);
		txtNomeDaPosicao.setColumns(10);
		
		JRadioButton rdbEstaEmUso = new JRadioButton("Esta Em Uso");
		rdbEstaEmUso.setBounds(196, 160, 149, 23);
		contentPane.add(rdbEstaEmUso);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				recurso.setTipoRecurso(tipoRecurso);
				recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				recurso.setEstaEmUso(Boolean.valueOf(rdbEstaEmUso.isSelected()));
				
				try {
					recursoDAO.inserir(recurso);
					int idPosicaoHeliponto= recursoDAO.selecionarMaximoID();
					posicaoHeliponto.setIdPosicaoHeliponto(idPosicaoHeliponto);
					posicaoHeliponto.setNome(txtNomeDaPosicao.getText().toUpperCase());
					posicaoHelipontoDAO.inserir(posicaoHeliponto);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Falha no cadastro da Posição no Heliponto");
					e2.printStackTrace();
				}
			}
		});
		btnInserir.setBounds(36, 205, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoHelipontoDAO.selecionar();
			}
		});
		btnConsultar.setBounds(168, 205, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(297, 205, 96, 25);
		contentPane.add(btnCancelar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idPosicao= Integer.valueOf(JOptionPane.showInputDialog("Digite o código da posicao que deseja alterar: "));
				try {
					recurso= recursoDAO.selecionarById(idPosicao);
					recurso.setTipoRecurso(tipoRecurso);
					recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
					recurso.setEstaEmUso(Boolean.valueOf(rdbEstaEmUso.isSelected()));
					recursoDAO.alterar(recurso);
					
					posicaoHeliponto= posicaoHelipontoDAO.selecionarById(recurso.getIdRecurso());
					posicaoHeliponto.setNome(txtNomeDaPosicao.getText().toUpperCase());
					posicaoHelipontoDAO.alterar(posicaoHeliponto);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Código da posicao invalido!");
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(36, 242, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.valueOf(JOptionPane.showInputDialog("Digite o código da posicao que deseja deletar: "));
				posicaoHelipontoDAO.apagar(id);
			}
		});
		btnDeletar.setBounds(168, 242, 96, 25);
		contentPane.add(btnDeletar);
		
	}
}
