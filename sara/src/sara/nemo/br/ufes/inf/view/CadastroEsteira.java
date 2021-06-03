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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.Esteira;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadastroEsteira extends JFrame {
	RecursoDAO recursoDAO= new RecursoDAO();
	Recurso recurso;
	Esteira esteira= new Esteira();
	EsteiraDAO esteiraDAO= new EsteiraDAO();
	
	String tipoRecurso= "ESTEIRA";
	public static CadastroEsteira frm;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeDaEsteira;
	private JTextField txtLocalizacao;
	JRadioButton rdbEstaEmUso;

	/**
	 * Launch the application.
	 */
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frm= new CadastroEsteira(frmMenuPrincipal, acao);
				if (acao.equals("Inserir")){
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||(acao.equals("Excluir"))) {
					frm.setDados();
					frm.setVisible(true);
				}
			}
		});
	}

	
	public CadastroEsteira(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE ESTEIRA DE BAGAGEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(70, 111, 94, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(219, 109, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		
		JLabel lblNomeDaEsteira = new JLabel("Nome da Esteira");
		lblNomeDaEsteira.setBounds(70, 154, 133, 15);
		contentPane.add(lblNomeDaEsteira);
		
		txtNomeDaEsteira = new JTextField();
		txtNomeDaEsteira.setBounds(219, 154, 114, 19);
		contentPane.add(txtNomeDaEsteira);
		txtNomeDaEsteira.setColumns(10);
		
		rdbEstaEmUso = new JRadioButton("Esta em uso");
		rdbEstaEmUso.setBounds(219, 196, 149, 23);
		contentPane.add(rdbEstaEmUso);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					Recurso recurso= getDadosRecurso();
					try {
						recursoDAO.inserir(recurso);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Impossivel inserir o recurso: dados incorretos! ");
						e1.printStackTrace();
					}
					int idEsteira= recursoDAO.selecionarMaximoID();
					Esteira est= getDados();
					est.setIdEsteira(idEsteira);
					est.setNome(txtNomeDaEsteira.getText());
					try {
						esteiraDAO.inserir(est);
						limpar();
						frm.setVisible(false);
						JOptionPane.showMessageDialog(null, "Esteira inserida com sucesso!");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Impossivel inserir esteira: dados incorretos!");					e1.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					Esteira aux= getDados();
					try {
						recurso= recursoDAO.selecionarById(esteira.getIdEsteira());
						recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
						recursoDAO.alterar(recurso);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					esteira.setLocalizacao(aux.getLocalizacao());
					esteira.setNome(aux.getNome());
					
					try {
						esteiraDAO.alterar(esteira);
						limpar();
						frm.setVisible(false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Impossivel altera esteira: dados incorretos!");
						e1.printStackTrace();
					}
				}else if (acao.equals("Excluir")) {
					esteira= getEsteira();
					esteiraDAO.apagar(esteira.getIdEsteira());
					recursoDAO.apagar(recurso.getIdRecurso());
				}
			}
		});
		btnOK.setBounds(181, 296, 117, 25);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(325, 296, 117, 25);
		contentPane.add(btnCancelar);
		
		
	}
	
	public void setDados() {
		try {
			String idStr=JOptionPane.showInputDialog("Digite o código da Esteira: ");
			int id= Integer.parseInt(idStr);
			esteira= esteiraDAO.selecionarById(id);
			txtNomeDaEsteira.setText(esteira.getNome());
		
			Recurso rec= recursoDAO.selecionarById(id);
			txtLocalizacao.setText(rec.getLocalizacao());
			System.out.println(rec.getEstaEmUso());
			rdbEstaEmUso.setSelected(rec.getEstaEmUso());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados incorretos!"+ e.getMessage());
		}
	}
	
	public Esteira getDados() {
		Esteira est= new Esteira();
		est.setTipoRecurso("Esteira");
		est.setLocalizacao(txtLocalizacao.getText());
		est.setNome(txtNomeDaEsteira.getText());
		if (rdbEstaEmUso.isSelected()) {
			est.setEstaEmUso(true);
		}else est.setEstaEmUso(false);
		
		return est;
	}
	
	public Recurso getDadosRecurso() {
		Recurso recurso= new Recurso();
		recurso.setTipoRecurso(tipoRecurso);
		recurso.setLocalizacao((txtLocalizacao.getText().toUpperCase()));
		recurso.setEstaEmUso(Boolean.valueOf(rdbEstaEmUso.isSelected()));
		return recurso;
	}
	
	public Esteira getEsteira() {// Retorna informacoes do Data Base
		Esteira esteira;
		String idStr=JOptionPane.showInputDialog("Digite o código da Esteira: ");
		if(idStr!= null) {
			int id= Integer.parseInt(idStr);
			esteira= esteiraDAO.selecionarById(id);
			//Recurso recurso;
			try {
				recurso = recursoDAO.selecionarById(id);
				
				esteira.setEstaEmUso(recurso.getEstaEmUso());
				esteira.setIdEsteira(id);
				esteira.setIdRecurso(id);
				esteira.setLocalizacao(recurso.getLocalizacao());
				esteira.setTipoRecurso("Esteira");
				return esteira;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Dados incorretos!");
				e.printStackTrace();
				return null;
			}
			
		}else return null;
		
	}	
	
	void limpar() {
		txtLocalizacao.setText(" ");
		txtNomeDaEsteira.setText(" ");
		rdbEstaEmUso.setSelected(false);
	}
}
