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

import sara.nemo.br.ufes.inf.DAO.PosicaoHelipontoDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PosicaoHeliponto;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadastroPosicaoHeliponto extends JFrame {
	String tipoRecurso= "HELIPONTO";
	
	PosicaoHeliponto posicaoHeliponto= new PosicaoHeliponto();
	PosicaoHelipontoDAO posicaoHelipontoDAO= new PosicaoHelipontoDAO();
	Recurso recurso= new Recurso();
	RecursoDAO recursoDAO= new RecursoDAO();
	String acao;
	
	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JTextField txtNomeDaPosicao;
	private JTextField txtLocalizacao;
	
	JRadioButton rdbEstaEmUso;

	/**
	 * Launch the application.
	 */
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (acao.equals("Inserir")) {
					CadastroPosicaoHeliponto frm = new CadastroPosicaoHeliponto(frmMenuPrincipal, acao);
					frm.setVisible(true);
				}else if (acao.equals("Alterar")|| acao.equals("Excluir")) {
					CadastroPosicaoHeliponto frm = new CadastroPosicaoHeliponto(frmMenuPrincipal, acao);
					PosicaoHeliponto posicao=frm.getPosicaoHeliponto();
					frm.setDados(posicao);
					frm.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroPosicaoHeliponto(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE POSICOES DO HELIPONTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		
		getContentPane().setLayout(null);
		this.acao= acao;
		frmMenuPrincipal.setVisible(false);
		
		addWindowListener(new WindowAdapter(){ //Retorna ao menu inicial ao clicar no botao X do JFrame 
			public void windowClosing(WindowEvent event) {
				frmMenuPrincipal.setVisible(true);
				setVisible(false);
			}
		});		
		
		JLabel lblLocalizacao = new JLabel("Localização");
		lblLocalizacao.setBounds(76, 106, 88, 15);
		getContentPane().add(lblLocalizacao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setColumns(10);
		txtLocalizacao.setBounds(196, 104, 114, 19);
		getContentPane().add(txtLocalizacao);
		
		JLabel lblNomeDaPosicao = new JLabel("Nome da Posicão");
		lblNomeDaPosicao.setBounds(43, 133, 121, 15);
		getContentPane().add(lblNomeDaPosicao);
		
		txtNomeDaPosicao = new JTextField();
		txtNomeDaPosicao.setBounds(196, 133, 114, 19);
		getContentPane().add(txtNomeDaPosicao);
		txtNomeDaPosicao.setColumns(10);
		
		rdbEstaEmUso = new JRadioButton("Esta Em Uso");
		rdbEstaEmUso.setBounds(196, 160, 149, 23);
		getContentPane().add(rdbEstaEmUso);
		

		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(297, 205, 96, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")){
					posicaoHeliponto= getDados();
					recurso.setLocalizacao(posicaoHeliponto.getLocalizacao());
					recurso.setTipoRecurso(tipoRecurso);
					recurso.setEstaEmUso(posicaoHeliponto.getEstaEmUso());
					try {
						recursoDAO.inserir(recurso);
						int id= recursoDAO.selecionarMaximoID();
						posicaoHeliponto.setIdPosicaoHeliponto(id);
						posicaoHelipontoDAO.inserir(posicaoHeliponto);
						limpar();
						frmMenuPrincipal.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(acao.equals("Alterar")) {
					PosicaoHeliponto aux= getDados();
					posicaoHeliponto.setNome(aux.getNome());
					
					try {
						int id= posicaoHeliponto.getIdPosicaoHeliponto();
						recurso= recursoDAO.selecionarById(id);
						recurso.setEstaEmUso(rdbEstaEmUso.isSelected());
						recurso.setTipoRecurso(tipoRecurso);
						recurso.setLocalizacao(txtLocalizacao.getText().toUpperCase());
						recursoDAO.alterar(recurso);
						posicaoHelipontoDAO.alterar(posicaoHeliponto);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					limpar();
					frmMenuPrincipal.setVisible(true);
				}else if(acao.equals("Excluir")) {
					setDados(posicaoHeliponto);
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						posicaoHelipontoDAO.apagar(posicaoHeliponto.getIdPosicaoHeliponto());
						recursoDAO.apagar(posicaoHeliponto.getIdRecurso());
					}
					frmMenuPrincipal.setVisible(true);
				}
				setVisible(false);
			}
		
		});
		btnOk.setBounds(162, 205, 117, 25);
		getContentPane().add(btnOk);
		
		
	}
	public PosicaoHeliponto getDados() {
		PosicaoHeliponto posicao= new PosicaoHeliponto();
		posicao.setNome(txtNomeDaPosicao.getText().toUpperCase());
		posicao.setTipoRecurso(tipoRecurso);
		posicao.setLocalizacao(txtLocalizacao.getText().toUpperCase());
		posicao.setEstaEmUso(rdbEstaEmUso.isSelected());
		
		return posicao;
	}
	
	public void setDados(PosicaoHeliponto posicao){
		int id= posicao.getIdPosicaoHeliponto();
		try {
			recurso= recursoDAO.selecionarById(id);
			txtNomeDaPosicao.setText(posicao.getNome());
			txtLocalizacao.setText(recurso.getLocalizacao());
			rdbEstaEmUso.setSelected(recurso.getEstaEmUso());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public PosicaoHeliponto getPosicaoHeliponto() {
		//posicaoHeliponto= new PosicaoHeliponto();
		String codigo= JOptionPane.showInputDialog("Codigo da posição no heliponto: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			posicaoHeliponto= posicaoHelipontoDAO.selecionarById(id);
			return(posicaoHeliponto);
		}
		return null;
	}
	
	public void limpar() {
		txtLocalizacao.setText("");
		txtNomeDaPosicao.setText("");
		rdbEstaEmUso.setSelected(false);
	}
}
