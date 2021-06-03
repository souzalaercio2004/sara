package sara.nemo.br.ufes.inf.view;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioParticularDAO;
import sara.nemo.br.ufes.inf.domain.Proprietario;
import sara.nemo.br.ufes.inf.domain.ProprietarioParticular;

public class CadastroProprietarioParticular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtNomeProprietario;
	JRadioButton rdbtnQuerAbastecimento;
	JComboBox<String> cbxCombustivel;
	
	Proprietario proprietario= new Proprietario();
	ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	
	ProprietarioParticular proprietarioParticular= new ProprietarioParticular();
	ProprietarioParticularDAO proprietarioParticularDAO= new ProprietarioParticularDAO();

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastroProprietarioParticular frm= new CadastroProprietarioParticular(frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")){
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||(acao.equals("Excluir"))) {
					ProprietarioParticular prop= frm.getProprietarioParticular();
					frm.setDados(prop);
					frm.setVisible(true);
				}
			}
		});
	}

	public CadastroProprietarioParticular(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE PROPRIETÁRIO PARTICULAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoDeCombustivel = new JLabel("Tipo de Combustivel");
		lblTipoDeCombustivel.setBounds(106, 176, 148, 15);
		contentPane.add(lblTipoDeCombustivel);
		
		cbxCombustivel = new JComboBox<String>();
		cbxCombustivel.setModel(new DefaultComboBoxModel<String>(new String[] {"AVGAS", "JET-A1"}));
		cbxCombustivel.setBounds(283, 176, 102, 24);
		contentPane.add(cbxCombustivel);
		
		JLabel lblNomeDoProprietrio = new JLabel("Nome do Proprietário");
		lblNomeDoProprietrio.setBounds(91, 72, 169, 15);
		contentPane.add(lblNomeDoProprietrio);
		
		rdbtnQuerAbastecimento = new JRadioButton("Quer abastecimento");
		rdbtnQuerAbastecimento.setBounds(283, 138, 235, 23);
		contentPane.add(rdbtnQuerAbastecimento);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setBounds(289, 70, 225, 19);
		contentPane.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					proprietarioParticular= getDados();
					proprietario.setNomeProprietario(proprietarioParticular.getNomeProprietario());
					try {
						proprietarioDAO.inserir(proprietario);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					int id= proprietarioDAO.selecionarMaximoID();
					proprietarioParticular.setId(id);
					try {
						proprietarioParticularDAO.inserir(proprietarioParticular);
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					ProprietarioParticular aux= getDados();
					proprietarioParticular.setNomeProprietario(aux.getNomeProprietario());
					proprietarioParticular.setQuerAbastecimento(aux.isQuerAbastecimento());
					proprietarioParticular.setTipoCombustivel(aux.getTipoCombustivel());
					proprietario= proprietarioDAO.selecionarById(aux.getId());
					proprietario.setNomeProprietario(aux.getNomeProprietario());
					try {
						proprietarioDAO.alterar(proprietario);
						proprietarioParticularDAO.alterar(proprietarioParticular);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if (acao.equals("Excluir")) {
					proprietarioParticularDAO.apagar(proprietarioParticular.getId());
					proprietarioDAO.apagar(proprietarioParticular.getId());
				}
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnOK.setBounds(169, 274, 110, 25);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(305, 274, 110, 25);
		contentPane.add(btnCancelar);
	}
	
	public ProprietarioParticular getDados() {
		proprietarioParticular.setNomeProprietario(txtNomeProprietario.getText().toUpperCase());
		proprietarioParticular.setQuerAbastecimento(rdbtnQuerAbastecimento.isSelected());
		proprietarioParticular.setTipoCombustivel(cbxCombustivel.getSelectedItem().toString());
		
		return proprietarioParticular;
	}
	
	public void setDados(ProprietarioParticular propParticular) {
		Proprietario aux= proprietarioDAO.selecionarById(propParticular.getId());
		
		txtNomeProprietario.setText(aux.getNomeProprietario());
		rdbtnQuerAbastecimento.setSelected(propParticular.isQuerAbastecimento());
		cbxCombustivel.setSelectedItem(propParticular.getTipoCombustivel());
	}
	
	public ProprietarioParticular getProprietarioParticular() {
		String codigo= JOptionPane.showInputDialog("Codigo do proprietário: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			proprietarioParticular= proprietarioParticularDAO.selecionarById(id);
			return(proprietarioParticular);	
		}
		return null;
	}
}
