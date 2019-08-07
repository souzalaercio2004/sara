package sara.nemo.br.ufes.view;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.domain.TipoAeronave;


public class CadTipoAeronave extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEquipamento;
	private JTextField txtComprimento;
	private JTextField txtEnvergadura;
	private JTextField txtPMD;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
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
		TipoAeronave tipoAeronave= new TipoAeronave();
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		
		setTitle("CADASTRO DE TIPOS DE AERONAVES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 392);
		
		JLabel lblEquipamento = new JLabel("Equipamento");
		
		JLabel lblComprimento = new JLabel("Comprimento");
		
		JLabel lblEnvergadura = new JLabel("Envergadura");
		
		JLabel lblPMD = new JLabel("PMD");
		lblPMD.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtEquipamento = new JTextField();
		txtEquipamento.setColumns(10);
		
		
		txtComprimento = new JTextField();
		txtComprimento.setColumns(10);
		
		
		txtEnvergadura = new JTextField();
		txtEnvergadura.setColumns(10);
		
		
		
		txtPMD = new JTextField();
		txtPMD.setColumns(10);
		
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoAeronaveDAO.selecionar();
				
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
				String equipamento= (String)txtEquipamento.getText();
				equipamento= equipamento.toUpperCase();
				int id= tipoAeronaveDAO.selecionarId(equipamento);
				
				tipoAeronave.setIdTipoAeronave(id);
				tipoAeronave.setEquipamento(equipamento);
				tipoAeronave.setComprimento(Float.valueOf(txtComprimento.getText()));
				tipoAeronave.setEnvergadura(Float.valueOf(txtEnvergadura.getText()));
				tipoAeronave.setPmd(Float.valueOf(txtPMD.getText()));
				
				try {
					tipoAeronaveDAO.alterar(tipoAeronave);
					JOptionPane.showMessageDialog(null, "Tipo de aeronave alterado com sucesso!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Falhou: Tipo de aeronave não foi alterado!");
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoAeronave.setEquipamento((String)txtEquipamento.getText());
				tipoAeronave.setComprimento(Float.valueOf(txtComprimento.getText()));
				tipoAeronave.setEnvergadura(Float.valueOf(txtEnvergadura.getText()));
				tipoAeronave.setPmd(Float.valueOf(txtPMD.getText()));
				
				try {
					tipoAeronaveDAO.inserir(tipoAeronave);
					JOptionPane.showMessageDialog(null, "Tipo de aeronave inserido com sucesso!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Falha na Inserção: Tipo de aeronave não foi inserido com sucesso!");
					e1.printStackTrace();
				}
			}
		});
		btnInserir.setActionCommand("Inserir");
	
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Digite o código do tipo de aeronave: "));
				tipoAeronaveDAO.apagar(id);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(113)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEquipamento)
									.addGap(49)
									.addComponent(txtEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblComprimento)
									.addGap(41)
									.addComponent(txtComprimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnvergadura)
									.addGap(53)
									.addComponent(txtEnvergadura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPMD, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addGap(53)
									.addComponent(txtPMD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnInserir)
									.addGap(29)
									.addComponent(btnAtualizar)
									.addGap(39)
									.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnConsultar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDeletar)))))
					.addContainerGap(110, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(80, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEquipamento)
						.addComponent(txtEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblComprimento))
						.addComponent(txtComprimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblEnvergadura))
						.addComponent(txtEnvergadura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblPMD))
						.addComponent(txtPMD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtualizar)
						.addComponent(btnInserir)
						.addComponent(btnCancelar))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeletar)
						.addComponent(btnConsultar))
					.addGap(50))
		);
		getContentPane().setLayout(groupLayout);
	}
}
