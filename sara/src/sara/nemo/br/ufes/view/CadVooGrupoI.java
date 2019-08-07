package sara.nemo.br.ufes.view;


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadVooGrupoI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCompanhiaAerea;
	private JLabel lblNumeroDoVoo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVooGrupoI frame = new CadVooGrupoI();
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
	public CadVooGrupoI() {
		setTitle("CADASTRO DE VOO GRUPO I");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewJLabel = DefaultComponentFactory.getInstance().createLabel("Hora Confirmada do Pouso");
		lblNewJLabel.setBounds(77, 126, 189, 15);
		
		JLabel lblNewLabel = new JLabel("Hora Confirmada da decolagem");
		lblNewLabel.setBounds(44, 174, 222, 15);
		
		lblCompanhiaAerea = new JLabel("Companhia Aerea");
		lblCompanhiaAerea.setBounds(141, 31, 125, 15);
		
		lblNumeroDoVoo = new JLabel("Numero do Voo");
		lblNumeroDoVoo.setBounds(158, 76, 108, 15);
		contentPane.setLayout(null);
		contentPane.add(lblCompanhiaAerea);
		contentPane.add(lblNumeroDoVoo);
		contentPane.add(lblNewJLabel);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(284, 27, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(284, 72, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(284, 122, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(284, 170, 114, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(102, 215, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(281, 215, 117, 25);
		contentPane.add(btnOk);
	}
}
