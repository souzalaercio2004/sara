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

import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.Pista;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadastroPista extends JFrame {
	private String acao;
	Recurso recurso= new Recurso();
	Pista pista= null;
	RecursoDAO recursoDAO= new RecursoDAO();
	PistaDAO pistaDAO= new PistaDAO();
	String tipoRecurso= "PISTA";
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNomeDaPista;
	private JTextField txtCabeceira;
	private JTextField txtLocalizacao;
	JRadioButton rdbEstaEmUso;
	
	public CadastroPista(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE PISTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		
		getContentPane().setLayout(null);
		this.acao= acao;
		frmMenuPrincipal.setVisible(false);
		
		addWindowListener(new WindowAdapter(){ //Retorna ao menu inicial ao clicar no botao X do JFrame 
			public void windowClosing(WindowEvent event) {
				frmMenuPrincipal.setVisible(true);
				setVisible(false);
			}
		});	
		
		JLabel lblCabeceira = new JLabel("Cabeceira");
		lblCabeceira.setBounds(121, 165, 71, 15);
		
		txtCabeceira = new JTextField();
		txtCabeceira.setBounds(210, 163, 114, 19);
		txtCabeceira.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localização");
		lblLocalizacao.setBounds(96, 88, 96, 15);
		getContentPane().add(lblLocalizacao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setColumns(10);
		txtLocalizacao.setBounds(209, 88, 114, 19);
		getContentPane().add(txtLocalizacao);
		
		rdbEstaEmUso = new JRadioButton("Esta em uso");
		rdbEstaEmUso.setBounds(210, 190, 149, 23);
		getContentPane().add(rdbEstaEmUso);
		//getContentPane().setLayout(null);
		
		JLabel lblnomeDaPista = new JLabel("Nome da Pista");
		lblnomeDaPista.setBounds(90, 134, 102, 15);
		getContentPane().add(lblnomeDaPista);
		
		txtNomeDaPista = new JTextField();
		txtNomeDaPista.setBounds(210, 134, 114, 19);
		txtNomeDaPista.setColumns(10);
		getContentPane().add(txtNomeDaPista);
		getContentPane().add(lblCabeceira);
		getContentPane().add(txtCabeceira);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (acao.equals("Inserir")){
					pista= getDados();
					recurso.setEstaEmUso(pista.getEstaEmUso());
					recurso.setLocalizacao(pista.getLocalizacao());
					recurso.setTipoRecurso(pista.getTipoRecurso());
					
					try {
						recursoDAO.inserir(recurso);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pista.setIdPista(recursoDAO.selecionarMaximoID());
					
					try {
						pistaDAO.inserir(pista);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					limpar();
					frmMenuPrincipal.setVisible(true);
				}else if (acao.equals("Alterar")) {
					
					try {
						recurso= recursoDAO.selecionarById(pista.getIdPista());
						Pista aux= getDados();
						recurso.setEstaEmUso(aux.getEstaEmUso());
						recurso.setTipoRecurso(aux.getTipoRecurso());
						recurso.setLocalizacao(aux.getLocalizacao());
						recursoDAO.alterar(recurso);
						
						pista.setNomeCabeceira(aux.getNomeCabeceira());
						pista.setNome(aux.getNome());
						pistaDAO.alterar(pista);
						limpar();
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					frmMenuPrincipal.setVisible(true);
				}else if (acao.equals("Excluir")) {
					setDados(pista);
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						pistaDAO.apagar(pista.getIdPista());
						recursoDAO.apagar(pista.getIdRecurso());
					}
					frmMenuPrincipal.setVisible(true);
				}
				
			}
			
		});
		btnOk.setBounds(121, 264, 117, 25);
		getContentPane().add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(291, 264, 117, 25);
		getContentPane().add(btnCancelar);
		
		
	}
	
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				if (acao.equals("Inserir")) {
					CadastroPista frm= new CadastroPista(frmMenuPrincipal, acao);
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||acao.equals("Excluir")) {
					CadastroPista frm= new CadastroPista(frmMenuPrincipal, acao);
					Pista pista= frm.getPista();
					if (pista!= null) {
						frm.setDados(pista);
						frm.setVisible(true);
					}else frmMenuPrincipal.setVisible(true);
				}
				
			}
		});
	}
	
	public void setAcao(String acao) {
		this.acao= acao;
	}
	
	public String getAcao() {
		return acao;
	}
	
	public Pista getDados() {
		Pista pista= new Pista();
		
		pista.setNome(txtNomeDaPista.getText());
		pista.setTipoRecurso(tipoRecurso);
		pista.setNomeCabeceira(txtCabeceira.getText());
		pista.setLocalizacao(txtLocalizacao.getText());
		pista.setEstaEmUso(rdbEstaEmUso.isSelected());
		return pista;
	}
	
	public void setDados(Pista pista) {
		txtNomeDaPista.setText(pista.getNome());
		txtCabeceira.setText(pista.getNomeCabeceira());
		txtLocalizacao.setText(pista.getLocalizacao());
		rdbEstaEmUso.setSelected(pista.getEstaEmUso());
	}
	
	public Pista getPista() {
		PistaDAO pistaDAO= new PistaDAO();
		String codigo= JOptionPane.showInputDialog("Codigo da Pista: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			
			try {
				recurso= recursoDAO.selecionarById(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pista= pistaDAO.selecionarById(id);
			pista.setTipoRecurso(recurso.getTipoRecurso());
			pista.setLocalizacao(recurso.getLocalizacao().toUpperCase());
			pista.setEstaEmUso(recurso.getEstaEmUso());
			pista.setIdPista(recurso.getIdRecurso());
			return(pista);
		}
		return null;
	}
	
	public void limpar() {
		txtNomeDaPista.setText("");
		txtCabeceira.setText("");
		txtLocalizacao.setText("");
		rdbEstaEmUso.setSelected(false);
	}
	
}
