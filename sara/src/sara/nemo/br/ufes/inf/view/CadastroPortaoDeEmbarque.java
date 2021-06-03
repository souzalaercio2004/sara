package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadastroPortaoDeEmbarque extends JFrame {
	String acao;
	String tipoDeRecurso= "PORTÃO DE EMBARQUE";
	PortaoDeEmbarque portaoDeEmbarque;
	PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JTextField txtNumeroDoPortao;
	private JTextField txtLocalizacao;
	JRadioButton rdbEstaEmUso;

	
	public CadastroPortaoDeEmbarque(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE PORTÃO DE EMBARQUE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		getContentPane().setLayout(null);
		this.acao= acao;
		frmMenuPrincipal.setVisible(false);
		

		addWindowListener(new WindowAdapter(){ //Retorna ao menu inicial ao clicar no botao X do JFrame 
			public void windowClosing(WindowEvent event) {
				frmMenuPrincipal.setVisible(true);
				setVisible(false);
			}
		});	
		
		rdbEstaEmUso = new JRadioButton("Esta em uso?");
		rdbEstaEmUso.setBounds(256, 121, 149, 23);
		getContentPane().add(rdbEstaEmUso);
		getContentPane().setLayout(null);
		
		JLabel lblnumeroDoPortao = new JLabel("Numero do Portão");
		lblnumeroDoPortao.setBounds(99, 56, 129, 15);
		getContentPane().add(lblnumeroDoPortao);
		
		txtNumeroDoPortao = new JTextField();
		txtNumeroDoPortao.setBounds(256, 54, 114, 19);
		txtNumeroDoPortao.setColumns(10);
		getContentPane().add(txtNumeroDoPortao);
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(132, 97, 96, 15);
		getContentPane().add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(256, 94, 114, 19);
		getContentPane().add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.setToolTipText("Confirma a operação");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(acao.equals("Inserir")) {
					portaoDeEmbarque= getDados();
					recurso.setLocalizacao(portaoDeEmbarque.getLocalizacao().toUpperCase());
					recurso.setEstaEmUso(portaoDeEmbarque.getEstaEmUso());
					recurso.setTipoRecurso(portaoDeEmbarque.getTipoRecurso());
					try {
						recursoDAO.inserir(recurso);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int id= recursoDAO.selecionarMaximoID();
					portaoDeEmbarque.setIdPortaoDeEmbarque(id);
					try {
						portaoDeEmbarqueDAO.inserir(portaoDeEmbarque);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frmMenuPrincipal.setVisible(true);
					
				}else if (acao.equals("Alterar")) {
					
					try {					
						recurso= recursoDAO.selecionarById(portaoDeEmbarque.getIdPortaoDeEmbarque());
						PortaoDeEmbarque aux= getDados();
						
						recurso.setEstaEmUso(aux.getEstaEmUso());
						recurso.setTipoRecurso(aux.getTipoRecurso());
						recurso.setLocalizacao(aux.getLocalizacao().toUpperCase());
						recursoDAO.alterar(recurso);
						
						portaoDeEmbarque.setNome(aux.getNome());
						portaoDeEmbarqueDAO.alterar(portaoDeEmbarque);

						limpar();
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					frmMenuPrincipal.setVisible(true);
					
				}else if (acao.equals("Excluir")) {
					setDados(portaoDeEmbarque);
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						portaoDeEmbarqueDAO.apagar(portaoDeEmbarque.getIdPortaoDeEmbarque());
						recursoDAO.apagar(portaoDeEmbarque.getIdRecurso());
					}
					frmMenuPrincipal.setVisible(true);
				}
				
			}
		});
		btnOK.setBounds(179, 165, 117, 25);
		getContentPane().add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(326, 165, 96, 25);
		getContentPane().add(btnCancelar);
		
		
	}
	
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				if (acao.equals("Inserir")) {
					CadastroPortaoDeEmbarque frm = new CadastroPortaoDeEmbarque(frmMenuPrincipal, acao);
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||acao.equals("Excluir")) {
					
					CadastroPortaoDeEmbarque frm= new CadastroPortaoDeEmbarque(frmMenuPrincipal, acao);
					PortaoDeEmbarque portaoDeEmbarque= frm.getPortaoDeEmbarque();
					frm.setDados(portaoDeEmbarque);
					frm.setVisible(true);
				}
				
			}
		});
	}
	
	public PortaoDeEmbarque getDados() {
		PortaoDeEmbarque portaoDeEmbarque= new PortaoDeEmbarque();
		portaoDeEmbarque.setNome(txtNumeroDoPortao.getText());
		portaoDeEmbarque.setLocalizacao(txtLocalizacao.getText().toUpperCase());
		portaoDeEmbarque.setEstaEmUso(rdbEstaEmUso.isSelected());
		portaoDeEmbarque.setTipoRecurso(tipoDeRecurso);
		
		return(portaoDeEmbarque);
	}
	
	public void setDados(PortaoDeEmbarque portaoDeEmbarque) {
		
		try {
			txtNumeroDoPortao.setText(portaoDeEmbarque.getNome());
			recurso= recursoDAO.selecionarById(portaoDeEmbarque.getIdPortaoDeEmbarque());
			txtLocalizacao.setText(recurso.getLocalizacao().toUpperCase());
			rdbEstaEmUso.setSelected(recurso.getEstaEmUso());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public PortaoDeEmbarque getPortaoDeEmbarque() {
		portaoDeEmbarque= new PortaoDeEmbarque();
		String codigo= JOptionPane.showInputDialog("Codigo do portão de embarque: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			portaoDeEmbarque= portaoDeEmbarqueDAO.selecionarPortaoDeEmbarqueById(id);
		}
		return null;
	}
	
	public void limpar() {
		txtNumeroDoPortao.setText("");
		txtLocalizacao.setText("");
		rdbEstaEmUso.setSelected(false);
	}
}
