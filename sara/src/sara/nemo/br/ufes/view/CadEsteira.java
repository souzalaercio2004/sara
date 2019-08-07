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

import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.Esteira;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadEsteira extends JFrame {
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();
	Esteira esteira= new Esteira();
	EsteiraDAO esteiraDAO= new EsteiraDAO();
	
	String tipoRecurso= "ESTEIRA";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroDaEsteira;
	private JTextField txtLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadEsteira frame = new CadEsteira();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CadEsteira() {
		EsteiraDAO esteiraDAO= new EsteiraDAO();
		
		
		setTitle("CADASTRO DE ESTEIRA DE BAGAGEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(70, 111, 94, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(219, 109, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Número da Esteira");
		lblNewLabel.setBounds(70, 154, 133, 15);
		contentPane.add(lblNewLabel);
		
		txtNumeroDaEsteira = new JTextField();
		txtNumeroDaEsteira.setBounds(219, 154, 114, 19);
		contentPane.add(txtNumeroDaEsteira);
		txtNumeroDaEsteira.setColumns(10);
		
		JRadioButton rdbEstaEmUso = new JRadioButton("Esta em uso");
		rdbEstaEmUso.setBounds(219, 196, 149, 23);
		contentPane.add(rdbEstaEmUso);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				recurso.setTipoRecurso(tipoRecurso);
				recurso.setLocalizacao((txtLocalizacao.getText().toUpperCase()));
				recurso.setEstaEmUso(Boolean.valueOf(rdbEstaEmUso.isSelected()));
				
				try {
					recursoDAO.inserir(recurso);	
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Impossivel inserir o recurso: dados incorretos! 105");
					e2.printStackTrace();
				}
				int idEsteira= recursoDAO.selecionarMaximoID();
				
				esteira.setIdEsteira(idEsteira);
				esteira.setNome(txtNumeroDaEsteira.getText());
				
				try {
					esteiraDAO.inserir(esteira);
					JOptionPane.showMessageDialog(null, "Esteira inserida com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Impossivel inserir esteira: dados incorretos!");					e1.printStackTrace();
				}
			}
		});
		btnInserir.setBounds(36, 296, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esteiraDAO.selecionar();
			}
		});
		btnConsultar.setBounds(181, 296, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recurso recurso= new Recurso();
				RecursoDAO recursoDAO= new RecursoDAO();
				
				int codigo= Integer.parseInt(JOptionPane.showInputDialog("Digite o código da Esteira: "));
				Esteira esteira= esteiraDAO.selecionarById(codigo);
				
				try {
					recurso= recursoDAO.selecionarById(esteira.getIdEsteira());
					
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (recurso != null) {
					recurso.setTipoRecurso(tipoRecurso);
					recurso.setLocalizacao((txtLocalizacao.getText().toUpperCase()));
					recurso.setEstaEmUso(Boolean.valueOf(rdbEstaEmUso.isSelected()));
					
					try {
						recursoDAO.alterar(recurso);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					esteira.setIdEsteira(recurso.getIdRecurso());
					esteira.setNome(txtNumeroDaEsteira.getText());				
					
					try {
						esteiraDAO.alterar(esteira);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Impossível alterar esteira!");
						e1.printStackTrace();
					}
				}
			}
		});
		btnAlterar.setBounds(36, 356, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int numero= Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo da esteira que deseja excluir: "));
				esteiraDAO.apagar(numero);
			}
		});
		btnExcluir.setBounds(195, 356, 117, 25);
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(325, 296, 117, 25);
		contentPane.add(btnCancelar);
		
		
	}
}
