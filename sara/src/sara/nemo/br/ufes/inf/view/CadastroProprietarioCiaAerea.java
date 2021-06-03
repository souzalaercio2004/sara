package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.ProprietarioCiaAereaDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.domain.Proprietario;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;

public class CadastroProprietarioCiaAerea extends JFrame {
	
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	Proprietario proprietario= new Proprietario();
	
	ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSiglaCiaAerea;
	private JTextField txtNomeDoProprietario;
	private JButton btnOk;
	private JButton btnCancelar_1;
	



	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastroProprietarioCiaAerea  frm = new CadastroProprietarioCiaAerea (frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if(acao.equals("Inserir")) {
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||acao.equals("Excluir")) {
					ProprietarioCiaAerea prop= frm.getProprietarioCiaAerea();
					frm.setDados(prop);
					frm.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroProprietarioCiaAerea(JFrame frmMenuPrincipal, String acao) {
		
		setTitle("CADASTRO DE PROPRIETARIO CIA AÉREA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(319, 223, 96, 25);
		contentPane.setLayout(null);
		
		JLabel lblNomeDoProprietrio = new JLabel("Nome do proprietário");
		lblNomeDoProprietrio.setBounds(63, 62, 169, 15);
		contentPane.add(lblNomeDoProprietrio);
		
		txtNomeDoProprietario = new JTextField();
		txtNomeDoProprietario.setBounds(236, 66, 337, 19);
		contentPane.add(txtNomeDoProprietario);
		txtNomeDoProprietario.setColumns(10);
		

		
		JLabel lblsigla = new JLabel("Sigla da Cia Aérea");
		lblsigla.setBounds(58, 97, 148, 15);
		contentPane.add(lblsigla);
		
		txtSiglaCiaAerea = new JTextField();
		txtSiglaCiaAerea.setBounds(224, 95, 114, 19);
		txtSiglaCiaAerea.setColumns(10);
		contentPane.add(txtSiglaCiaAerea);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					proprietarioCiaAerea= getDados();
					try {
						proprietarioCiaAereaDAO.inserir(proprietarioCiaAerea);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					limpar();
				}else if (acao.equals("Alterar")){
					ProprietarioCiaAerea aux= getDados();
					proprietario= proprietarioDAO.selecionarById(proprietarioCiaAerea.getIdCiaAerea());
					proprietario.setNomeProprietario(aux.getNomeProprietario());
					try {
						proprietarioDAO.alterar(proprietario);
						proprietarioCiaAerea.setSiglaCiaAerea(aux.getSiglaCiaAerea());
						
						proprietarioCiaAereaDAO.alterar(proprietarioCiaAerea);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					limpar();
					
				}else if (acao.equals("Excluir")) {
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						proprietarioCiaAereaDAO.apagar(proprietarioCiaAerea.getIdProprietario());
						
					}
				}
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnOk.setBounds(115, 165, 117, 25);
		contentPane.add(btnOk);
		
		btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar_1.setBounds(309, 165, 117, 25);
		contentPane.add(btnCancelar_1);

		
		
	}
	
	void limpar() {
		txtNomeDoProprietario.setText("");
		txtSiglaCiaAerea.setText("");
	}
	public ProprietarioCiaAerea getDados() {
		proprietarioCiaAerea.setNomeProprietario(txtNomeDoProprietario.getText());
		proprietarioCiaAerea.setSiglaCiaAerea(txtSiglaCiaAerea.getText());
		
		return proprietarioCiaAerea;
		
	}
	
	public void setDados(ProprietarioCiaAerea prop) {
		Proprietario pr= proprietarioDAO.selecionarById(prop.getIdCiaAerea());
		if (pr != null)txtNomeDoProprietario.setText(pr.getNomeProprietario());
		txtSiglaCiaAerea.setText(prop.getSiglaCiaAerea());
	}
	
	public ProprietarioCiaAerea getProprietarioCiaAerea() {
		String codigo= JOptionPane.showInputDialog("Codigo da Cia Aerea: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			proprietarioCiaAerea=proprietarioCiaAereaDAO.selecionarById(id);
			return(proprietarioCiaAerea);	
		}
		return null;
		
		
	}
}
