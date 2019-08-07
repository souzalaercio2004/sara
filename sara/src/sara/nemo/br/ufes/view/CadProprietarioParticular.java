package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.ProprietarioParticularDAO;
import sara.nemo.br.ufes.inf.domain.ProprietarioParticular;

public class CadProprietarioParticular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeProprietario;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadProprietarioParticular frame = new CadProprietarioParticular();
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
	public CadProprietarioParticular() {
		setTitle("CADASTRO DE PROPRIETÁRIO PARTICULAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		JLabel lblQuerAbastecimento = new JLabel("Quer abastecimento");
		lblQuerAbastecimento.setBounds(99, 137, 155, 15);
		contentPane.add(lblQuerAbastecimento);
		
		JComboBox<String> cbxAbastecimento = new JComboBox<String>();
		cbxAbastecimento.setModel(new DefaultComboBoxModel<String>(new String[] {"SIM", "NÃO"}));
		cbxAbastecimento.setBounds(283, 132, 54, 24);
		contentPane.add(cbxAbastecimento);
		
		JLabel lblTipoDeCombustivel = new JLabel("Tipo de Combustivel");
		lblTipoDeCombustivel.setBounds(106, 176, 148, 15);
		contentPane.add(lblTipoDeCombustivel);
		
		JComboBox<String> cbxCombustivel = new JComboBox<String>();
		cbxCombustivel.setModel(new DefaultComboBoxModel<String>(new String[] {"AVGAS", "JET-A1"}));
		cbxCombustivel.setBounds(283, 176, 102, 24);
		contentPane.add(cbxCombustivel);
		
		JLabel lblNomeDoProprietrio = new JLabel("Nome do Proprietário");
		lblNomeDoProprietrio.setBounds(91, 72, 169, 15);
		contentPane.add(lblNomeDoProprietrio);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setBounds(289, 70, 225, 19);
		contentPane.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProprietarioParticularDAO prop= new ProprietarioParticularDAO();
				//nomeProprietario=  txtNomeProprietario.getText();
				ProprietarioParticular propParticular= new ProprietarioParticular();
				propParticular.setNomeProprietario(txtNomeProprietario.getText().toUpperCase());
				
				if (((String)cbxAbastecimento.getSelectedItem()) == "SIM") {// Quer abastecimento
					propParticular.setQuerAbastecimento(true);
					propParticular.setTipoCombustivel((String)cbxCombustivel.getSelectedItem());
				}else propParticular.setQuerAbastecimento(false); // Não quer abastecimento
				
				try {
					prop.inserir(propParticular);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIncluir.setBounds(54, 271, 84, 25);
		contentPane.add(btnIncluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(305, 271, 96, 30);
		contentPane.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProprietarioParticularDAO prop= new ProprietarioParticularDAO();
				prop.selecionar();
			}
		});
		btnConsultar.setBounds(174, 271, 103, 25);
		contentPane.add(btnConsultar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProprietarioParticularDAO prop= new ProprietarioParticularDAO();
				
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id do proprietario particular? "));
				ProprietarioParticular proprietarioParticular=prop.selecionarById(id);
				proprietarioParticular.setNomeProprietario(txtNomeProprietario.getText().toUpperCase());
				String opcao= cbxAbastecimento.getSelectedItem().toString();
				if (opcao.equals("SIM")) {
					proprietarioParticular.setQuerAbastecimento(true);
					proprietarioParticular.setTipoCombustivel((String) cbxCombustivel.getSelectedItem());
				} else {
					proprietarioParticular.setQuerAbastecimento(false);
					proprietarioParticular.setTipoCombustivel(null);
				}
				try {
					prop.alterar(proprietarioParticular);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(64, 324, 89, 25);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id do proprietario particular quer deletar? "));
				ProprietarioParticularDAO prop= new ProprietarioParticularDAO();
				prop.apagar(id);
			}
		});
		btnDeletar.setBounds(174, 324, 96, 25);
		contentPane.add(btnDeletar);
	}
}
