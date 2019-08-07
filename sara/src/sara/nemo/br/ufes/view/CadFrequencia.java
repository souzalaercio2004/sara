package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.FrequenciaDAO;
import sara.nemo.br.ufes.inf.domain.Frequencia;

public class CadFrequencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadFrequencia frame = new CadFrequencia();
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
	public CadFrequencia() {
		FrequenciaDAO frequenciaDAO= new FrequenciaDAO();
		
		setTitle("CADASTRO DE FREQUENCIAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbDomingo = new JRadioButton("Domingo");
		rdbDomingo.setBounds(69, 48, 149, 23);
		contentPane.add(rdbDomingo);
		
		JRadioButton rdbSegundafeira = new JRadioButton("Segunda-Feira");
		rdbSegundafeira.setBounds(69, 80, 149, 23);
		contentPane.add(rdbSegundafeira);
		
		JRadioButton rdbTercafeira = new JRadioButton("Terça-Feira");
		rdbTercafeira.setBounds(69, 126, 149, 23);
		contentPane.add(rdbTercafeira);
		
		JRadioButton rdbQuartafeira = new JRadioButton("Quarta-Feira");
		rdbQuartafeira.setBounds(69, 153, 149, 23);
		contentPane.add(rdbQuartafeira);
		
		JRadioButton rdbQuintafeira = new JRadioButton("Quinta-Feira");
		rdbQuintafeira.setBounds(251, 48, 149, 23);
		contentPane.add(rdbQuintafeira);
		
		JRadioButton rdbSextafeira = new JRadioButton("Sexta-Feira");
		rdbSextafeira.setBounds(251, 80, 149, 23);
		contentPane.add(rdbSextafeira);
		
		JRadioButton rdbSabado = new JRadioButton("Sábado");
		rdbSabado.setBounds(251, 126, 149, 23);
		contentPane.add(rdbSabado);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frequencia frequencia= new Frequencia();
				
				frequencia.setDomingo(rdbDomingo.isSelected());
				frequencia.setSegunda(rdbSegundafeira.isSelected());
				frequencia.setTerca(rdbTercafeira.isSelected());
				frequencia.setQuarta(rdbQuartafeira.isSelected());
				frequencia.setQuinta(rdbQuintafeira.isSelected());
				frequencia.setSexta(rdbSextafeira.isSelected());
				frequencia.setSabado(rdbSabado.isSelected());
				
				try {
					frequenciaDAO.inserir(frequencia);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInserir.setBounds(30, 237, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Digite o id da frequencia que deseja alterar: "));
				Frequencia freq= new Frequencia();
				freq= frequenciaDAO.selecionarById(id);
				
				freq.setDomingo(rdbDomingo.isSelected());
				freq.setSegunda(rdbSegundafeira.isSelected());
				freq.setTerca(rdbTercafeira.isSelected());
				freq.setQuarta(rdbQuartafeira.isSelected());
				freq.setQuinta(rdbQuintafeira.isSelected());
				freq.setSexta(rdbSextafeira.isSelected());
				freq.setSabado(rdbSabado.isSelected());
				freq.setIdFrequencia(id);
				
				try {
					frequenciaDAO.alterar(freq);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAlterar.setBounds(170, 237, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(321, 237, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frequenciaDAO.selecionar();
			}
		});
		btnConsultar.setBounds(30, 287, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Digite o id da frequencia que deseja excluir: "));
				frequenciaDAO.apagar(id);
			}
		});
		btnExcluir.setBounds(170, 287, 117, 25);
		contentPane.add(btnExcluir);
	}
}
