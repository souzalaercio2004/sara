package sara.nemo.br.ufes.inf.view;

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

public class CadastroFrequencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	FrequenciaDAO frequenciaDAO= new FrequenciaDAO();
	Frequencia frequencia;
	
	JRadioButton rdbDomingo;
	JRadioButton rdbSegundafeira;
	JRadioButton rdbTercafeira;
	JRadioButton rdbQuartafeira;
	JRadioButton rdbQuintafeira;
	JRadioButton rdbSextafeira;
	JRadioButton rdbSabado;

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastroFrequencia frm= new CadastroFrequencia(frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")) {
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||(acao.equals("Excluir"))) {
					Frequencia freq= frm.getFrequencia();
					frm.setDados(freq);
					frm.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroFrequencia(JFrame frmMenuPrincipal, String acao) {
		frequenciaDAO= new FrequenciaDAO();
		
		setTitle("CADASTRO DE FREQUENCIAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbDomingo = new JRadioButton("Domingo");
		rdbDomingo.setBounds(69, 48, 149, 23);
		contentPane.add(rdbDomingo);
		
		rdbSegundafeira = new JRadioButton("Segunda-Feira");
		rdbSegundafeira.setBounds(69, 80, 149, 23);
		contentPane.add(rdbSegundafeira);
		
		rdbTercafeira = new JRadioButton("Terça-Feira");
		rdbTercafeira.setBounds(69, 126, 149, 23);
		contentPane.add(rdbTercafeira);
		
		rdbQuartafeira = new JRadioButton("Quarta-Feira");
		rdbQuartafeira.setBounds(69, 153, 149, 23);
		contentPane.add(rdbQuartafeira);
		
		rdbQuintafeira = new JRadioButton("Quinta-Feira");
		rdbQuintafeira.setBounds(251, 48, 149, 23);
		contentPane.add(rdbQuintafeira);
		
		rdbSextafeira = new JRadioButton("Sexta-Feira");
		rdbSextafeira.setBounds(251, 80, 149, 23);
		contentPane.add(rdbSextafeira);
		
		rdbSabado = new JRadioButton("Sábado");
		rdbSabado.setBounds(251, 126, 149, 23);
		contentPane.add(rdbSabado);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")) {
					frequencia= getDados();
					try {
						frequenciaDAO.inserir(frequencia);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					//frequencia= getFrequencia();
					Frequencia aux= getDados();
					frequencia.setDomingo(aux.isDomingo());
					frequencia.setSegunda(aux.isSegunda());
					frequencia.setTerca(aux.isTerca());
					frequencia.setQuarta(aux.isQuarta());
					frequencia.setQuinta(aux.isQuinta());
					frequencia.setSexta(aux.isSexta());
					frequencia.setSabado(aux.isSabado());
					try {
						frequenciaDAO.alterar(frequencia);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (acao.equals("Excluir")) {
					frequenciaDAO.apagar(frequencia.getIdFrequencia());
				}
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		
		btnOK.setBounds(170, 237, 117, 25);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(321, 237, 117, 25);
		contentPane.add(btnCancelar);
	}
	
	public Frequencia getDados() {
		Frequencia freq= new Frequencia();
		freq.setDomingo(rdbDomingo.isSelected());
		freq.setSegunda(rdbSegundafeira.isSelected());
		freq.setTerca(rdbTercafeira.isSelected());
		freq.setQuarta(rdbQuartafeira.isSelected());
		freq.setQuinta(rdbQuintafeira.isSelected());
		freq.setSexta(rdbSextafeira.isSelected());
		freq.setSabado(rdbSabado.isSelected());
		return freq;
	}
	
	public void setDados(Frequencia freq) {
		rdbDomingo.setSelected(freq.isDomingo());
		rdbSegundafeira.setSelected(freq.isSegunda());
		rdbTercafeira.setSelected(freq.isTerca());
		rdbQuartafeira.setSelected(freq.isQuarta());
		rdbQuintafeira.setSelected(freq.isQuinta());
		rdbSextafeira.setSelected(freq.isSexta());
		rdbSabado.setSelected(freq.isSabado());
	}
	public Frequencia getFrequencia() {
		String codigo= JOptionPane.showInputDialog("Codigo da frequencia: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			frequencia= frequenciaDAO.selecionarById(id);
			return(frequencia);			
		}
		return null;
	}
}
