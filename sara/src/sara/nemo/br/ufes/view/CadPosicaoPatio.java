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

import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadPosicaoPatio extends JFrame {
	PosicaoPatio posicaoPatio= new PosicaoPatio();
	PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
	RecursoDAO recursoDAO= new RecursoDAO();
	Recurso recurso = new Recurso();
	
	String tipoRecurso= "POSIÇÃO NO PÁTIO";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeDaPosicao;
	private JTextField comprimentoToleravel;
	private JTextField envergaduratoleravel;
	private JTextField aeronaveCritica;
	private JButton btnCancelar;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JRadioButton rdbEstaemUso;
	private JTextField txtLocalizacao;
	

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPosicaoPatio frame = new CadPosicaoPatio();
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
	public CadPosicaoPatio() {
		setTitle("CADASTRO DE POSIÇOES NO PATIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblcomprimentoToleravel = new JLabel("Comprimento Toleravel");
		lblcomprimentoToleravel.setBounds(40, 120, 163, 15);
		
		JLabel lblenvergaduratoleravel = new JLabel("Envergadura Toleravel");
		lblenvergaduratoleravel.setBounds(44, 147, 159, 15);
		
		envergaduratoleravel = new JTextField();
		envergaduratoleravel.setBounds(242, 143, 114, 19);
		envergaduratoleravel.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(362, 226, 113, 25);
		
		JLabel lblaeronaveCritica = new JLabel("Aeronave Critica");
		lblaeronaveCritica.setBounds(87, 180, 116, 15);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id da  posicao no pátio que deseja deletar? "));
				posicaoPatioDAO.apagar(id);
			}
		});
		btnDeletar.setBounds(220, 277, 87, 25);
		contentPane.setLayout(null);	
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(85, 54, 123, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(244, 52, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		JLabel lblnomeDaPosicao = new JLabel("Nome da Posicao");
		lblnomeDaPosicao.setBounds(87, 83, 121, 23);
		lblnomeDaPosicao.setToolTipText("");
		contentPane.add(lblnomeDaPosicao);
		
		nomeDaPosicao = new JTextField();
		nomeDaPosicao.setBounds(244, 83, 114, 19);
		nomeDaPosicao.setColumns(10);
		contentPane.add(nomeDaPosicao);
		
		rdbEstaemUso = new JRadioButton("EstaEmUso?");
		rdbEstaemUso.setBounds(381, 85, 110, 23);
		contentPane.add(rdbEstaemUso);
		contentPane.add(lblcomprimentoToleravel);
		
		comprimentoToleravel = new JTextField();
		comprimentoToleravel.setBounds(244, 116, 114, 19);
		comprimentoToleravel.setColumns(10);
		contentPane.add(comprimentoToleravel);
		contentPane.add(lblaeronaveCritica);
		
		aeronaveCritica = new JTextField();
		aeronaveCritica.setBounds(244, 180, 114, 19);
		aeronaveCritica.setColumns(10);
		contentPane.add(aeronaveCritica);
		contentPane.add(lblenvergaduratoleravel);
		contentPane.add(envergaduratoleravel);
		
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(54, 226, 93, 25);
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
				recurso.setTipoRecurso(tipoRecurso);
				recurso.setEstaEmUso(rdbEstaemUso.isSelected());
				try {
					recursoDAO.inserir(recurso);
					int idPosicao= recursoDAO.selecionarMaximoID();
					
					posicaoPatio.setIdPosicaoPatio(idPosicao);
					posicaoPatio.setNome(nomeDaPosicao.getText());
					posicaoPatio.setComprimentoToleravel(Float.valueOf(comprimentoToleravel.getText()));
					posicaoPatio.setEnvergaduratoleravel(Float.valueOf(envergaduratoleravel.getText()));
					posicaoPatio.setAeronaveCritica(aeronaveCritica.getText().toUpperCase());
					posicaoPatioDAO.inserir(posicaoPatio);
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnInserir);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoPatioDAO.selecionar();
			}
		});
		btnConsultar.setBounds(215, 226, 103, 25);
		contentPane.add(btnConsultar);
		contentPane.add(btnCancelar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(40, 277, 117, 25);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id da  posicao no pátio que deseja alterar? "));
				
				try {
					recurso= recursoDAO.selecionarById(id);
					recurso.setTipoRecurso(tipoRecurso);
					recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
					recurso.setEstaEmUso(rdbEstaemUso.isSelected());
					recursoDAO.alterar(recurso);
					
					posicaoPatio= posicaoPatioDAO.selecionarById(recurso.getIdRecurso());
					posicaoPatio.setNome(nomeDaPosicao.getText());
					posicaoPatio.setComprimentoToleravel(Float.valueOf(comprimentoToleravel.getText()));
					posicaoPatio.setEnvergaduratoleravel(Float.valueOf(envergaduratoleravel.getText()));
					posicaoPatio.setAeronaveCritica(aeronaveCritica.getText().toUpperCase());
					posicaoPatio.setEstaEmUso((rdbEstaemUso.isSelected()));
					posicaoPatioDAO.alterar(posicaoPatio);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Falha no cadastro:Não existe da posição de pátio com este id");
					e1.printStackTrace();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Falha no cadastro da posição de pátio");
					e2.printStackTrace();
				}
								
			}
		});
		contentPane.add(btnAlterar);
		contentPane.add(btnDeletar);
	}
}
