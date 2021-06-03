package sara.nemo.br.ufes.inf.view;


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

import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.domain.TipoAeronave;


public class CadastroTipoAeronave extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtEquipamento;
	private JTextField txtComprimento;
	private JTextField txtEnvergadura;
	private JTextField txtPMD;
	
	TipoAeronave tipoAeronave;
	TipoAeronaveDAO tipoAeronaveDAO;

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				CadastroTipoAeronave frm= new CadastroTipoAeronave(frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")) {
					frm.setVisible(true);
				}else if (acao.equals("Alterar")|| (acao.equals("Excluir"))) {
					TipoAeronave aux= frm.getTipoAeronave();
					frm.setDados(aux);
					frm.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroTipoAeronave(JFrame frmMenuPrincipal, String acao) {
		tipoAeronave= new TipoAeronave();
		tipoAeronaveDAO= new TipoAeronaveDAO();
		
		setTitle("CADASTRO DE TIPOS DE AERONAVES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					TipoAeronave aux= getDados();
					try {
						tipoAeronaveDAO.inserir(aux);
						limpar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					//tipoAeronave= getTipoAeronave();
					TipoAeronave aux = getDados();
					tipoAeronave.setComprimento(aux.getComprimento());
					tipoAeronave.setEnvergadura(aux.getEnvergadura());
					tipoAeronave.setEquipamento(aux.getEquipamento());
					tipoAeronave.setPmd(aux.getPmd());
					
					try {
						tipoAeronaveDAO.alterar(tipoAeronave);
						limpar();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (acao.equals("Excluir")) {
					tipoAeronaveDAO.apagar(tipoAeronave.getIdTipoAeronave());
					limpar();
				}
				frmMenuPrincipal.setVisible(true);
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
							.addGap(149)
							.addComponent(btnOK)
							.addGap(39)
							.addComponent(btnCancelar)))
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
						.addComponent(btnOK)
						.addComponent(btnCancelar))
					.addGap(93))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public TipoAeronave getDados() {
		tipoAeronave.setComprimento(Float.parseFloat(txtComprimento.getText()));
		tipoAeronave.setEnvergadura(Float.parseFloat(txtEnvergadura.getText()));
		tipoAeronave.setEquipamento(txtEquipamento.getText());
		tipoAeronave.setPmd(Float.parseFloat(txtPMD.getText()));
		
		return tipoAeronave;
	}
	
	public void setDados(TipoAeronave tipoAero) {
		txtComprimento.setText(Float.toString(tipoAero.getComprimento()));
		txtEnvergadura.setText(Float.toString(tipoAero.getEnvergadura()));
		txtEquipamento.setText(tipoAero.getEquipamento());
		txtPMD.setText(Float.toString(tipoAero.getPmd()));
	}
	
	public TipoAeronave getTipoAeronave() {
		String equip= JOptionPane.showInputDialog("Qual equipamento: ");
		if (equip != null) {
			tipoAeronave= tipoAeronaveDAO.selecionarByEquipamento(equip);
			return(tipoAeronave);
			
		}else return null;
	}
	
	public void limpar() {
		txtComprimento.setText("");
		txtEnvergadura.setText("");
		txtEquipamento.setText("");
		txtPMD.setText("");
	}
}
