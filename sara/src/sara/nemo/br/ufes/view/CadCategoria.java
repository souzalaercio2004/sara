package sara.nemo.br.ufes.view;



import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class CadCategoria extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadCategoria frame = new CadCategoria();
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
	
	
	public CadCategoria() {
		
		setTitle("Cadastro de Categoria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Tipo Categoria");
		lblNewLabel.setBounds(44, 85, 104, 15);
		
		JLabel lblClasse = new JLabel("Classe");
		lblClasse.setBounds(54, 115, 94, 15);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblClasse);
		
		JLabel lblEspecificao = new JLabel("Especificação");
		lblEspecificao.setBounds(44, 150, 139, 15);
		contentPane.add(lblEspecificao);
		
		JLabel lblPassageiroOuCargueiro = new JLabel("Passageiro ou Cargueiro");
		lblPassageiroOuCargueiro.setBounds(51, 203, 197, 15);
		contentPane.add(lblPassageiroOuCargueiro);
		
		JComboBox<String> comboBoxCategoria = new JComboBox<String>();
		
		comboBoxCategoria.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha um ítem", "Doméstico", "Internacional"}));
		
		comboBoxCategoria.setBounds(207, 80, 116, 24);
		contentPane.add(comboBoxCategoria);
		
		JComboBox<String> comboBox_Classe = new JComboBox<String>();
		comboBox_Classe.setModel(new DefaultComboBoxModel<String>(new String[] {"Regular", "Não Regular"}));
		comboBox_Classe.setBounds(207, 110, 116, 24);
		contentPane.add(comboBox_Classe);
		
		JComboBox<String> comboBox_Especificacao = new JComboBox<String>();
		comboBox_Especificacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Regular", "Alternado", "Charter", "Fretado", "Militar", "Translado"}));
		comboBox_Especificacao.setBounds(201, 145, 118, 24);
		contentPane.add(comboBox_Especificacao);
		
		JComboBox<String> PassageiroOuCargueiro = new JComboBox<String>();
		PassageiroOuCargueiro.setModel(new DefaultComboBoxModel<String>(new String[] {"Passageiro", "Cargueiro"}));
		PassageiroOuCargueiro.setBounds(237, 198, 150, 24);
		contentPane.add(PassageiroOuCargueiro);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		btnCancelar.setBounds(66, 248, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(253, 248, 117, 25);
		contentPane.add(btnOk);
	}
}
