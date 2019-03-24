package sara.nemo.br.ufes.view;


import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class CadTipoAeronave extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEquipamento;
	private JTextField textComprimento;
	private JTextField textEnvergadura;
	private JTextField textPMD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadTipoAeronave frame = new CadTipoAeronave();
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
	public CadTipoAeronave() {
		setTitle("CADASTRO DE TIPOS DE AERONAVES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 370);
		
		JLabel lblEquipamento = new JLabel("Equipamento");
		
		JLabel lblComprimento = new JLabel("Comprimento");
		
		JLabel lblEnvergadura = new JLabel("Envergadura");
		
		JLabel lblPMD = new JLabel("PMD");
		lblPMD.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtEquipamento = new JTextField();
		txtEquipamento.setColumns(10);
		
		textComprimento = new JTextField();
		textComprimento.setColumns(10);
		
		textEnvergadura = new JTextField();
		textEnvergadura.setColumns(10);
		
		textPMD = new JTextField();
		textPMD.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		
		JButton btnAtualizar = new JButton("Atualizar");
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setActionCommand("Inserir");
	
		
		JButton btnDeletar = new JButton("Deletar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JButton btnOk = new JButton("OK");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(95)
							.addComponent(lblEquipamento)
							.addGap(49)
							.addComponent(txtEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addComponent(lblComprimento)
							.addGap(41)
							.addComponent(textComprimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addComponent(lblEnvergadura)
							.addGap(53)
							.addComponent(textEnvergadura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(93)
							.addComponent(lblPMD, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(textPMD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(btnInserir)
							.addGap(29)
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnConsultar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnOk)
								.addComponent(btnDeletar))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(235, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(161))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblEquipamento))
						.addComponent(txtEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblComprimento))
						.addComponent(textComprimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblEnvergadura))
						.addComponent(textEnvergadura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblPMD))
						.addComponent(textPMD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeletar)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnConsultar)
							.addComponent(btnAtualizar)
							.addComponent(btnInserir)))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addGap(31))
		);
		getContentPane().setLayout(groupLayout);
	}
}
