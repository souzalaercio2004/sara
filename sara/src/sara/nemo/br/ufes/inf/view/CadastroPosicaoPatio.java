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

import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.Recurso;

public class CadastroPosicaoPatio extends JFrame {
	PosicaoPatio posicaoPatio= new PosicaoPatio();
	PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
	RecursoDAO recursoDAO= new RecursoDAO();
	Recurso recurso = new Recurso();
	
	String tipoRecurso= "POSIÇÃO NO PÁTIO";
	String acao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeDaPosicao;
	private JTextField comprimentoToleravel;
	private JTextField envergaduratoleravel;
	private JTextField aeronaveCritica;
	private JButton btnCancelar;
	private JButton btnOK;
	private JRadioButton rdbEstaemUso;
	private JTextField txtLocalizacao;
	

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastroPosicaoPatio frm = new CadastroPosicaoPatio(frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")) {
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||(acao.equals("Excluir"))){
					PosicaoPatio posicao= frm.getPosicaoPatio();
					frm.setDados(posicao);
					frm.setVisible(true);
				}
			}
			
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroPosicaoPatio(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE POSIÇOES NO PATIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.acao= acao;
		
		JLabel lblcomprimentoToleravel = new JLabel("Comprimento Toleravel");
		lblcomprimentoToleravel.setBounds(40, 120, 163, 15);
		
		JLabel lblenvergaduratoleravel = new JLabel("Envergadura Toleravel");
		lblenvergaduratoleravel.setBounds(44, 147, 159, 15);
		
		envergaduratoleravel = new JTextField();
		envergaduratoleravel.setBounds(242, 143, 114, 19);
		envergaduratoleravel.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(362, 226, 113, 25);
		
		JLabel lblaeronaveCritica = new JLabel("Aeronave Critica");
		lblaeronaveCritica.setBounds(87, 180, 116, 15);
		contentPane.setLayout(null);	
		
		JLabel lblLocalizao = new JLabel("Localização");
		lblLocalizao.setBounds(85, 54, 123, 15);
		contentPane.add(lblLocalizao);
		
		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(244, 52, 114, 19);
		contentPane.add(txtLocalizacao);
		txtLocalizacao.setColumns(10);
		
		JLabel lblnomeDaPosicao = new JLabel("Nome da Posicao");
		lblnomeDaPosicao.setBounds(87, 83, 121, 23);
		lblnomeDaPosicao.setToolTipText("");
		contentPane.add(lblnomeDaPosicao);
		
		nomeDaPosicao = new JTextField();
		nomeDaPosicao.setBounds(244, 83, 114, 19);
		nomeDaPosicao.setColumns(10);
		contentPane.add(nomeDaPosicao);
		
		rdbEstaemUso = new JRadioButton("EstaEmUso?");
		rdbEstaemUso.setBounds(381, 85, 110, 23);
		contentPane.add(rdbEstaemUso);
		contentPane.add(lblcomprimentoToleravel);
		
		comprimentoToleravel = new JTextField();
		comprimentoToleravel.setBounds(244, 116, 114, 19);
		comprimentoToleravel.setColumns(10);
		contentPane.add(comprimentoToleravel);
		contentPane.add(lblaeronaveCritica);
		
		aeronaveCritica = new JTextField();
		aeronaveCritica.setBounds(244, 180, 114, 19);
		aeronaveCritica.setColumns(10);
		contentPane.add(aeronaveCritica);
		contentPane.add(lblenvergaduratoleravel);
		contentPane.add(envergaduratoleravel);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					posicaoPatio= getDados();
					recurso.setEstaEmUso(posicaoPatio.getEstaEmUso());
					recurso.setLocalizacao(posicaoPatio.getLocalizacao());
					recurso.setTipoRecurso(posicaoPatio.getTipoRecurso());
					try {
						recursoDAO.inserir(recurso);
						posicaoPatio.setIdPosicaoPatio(recursoDAO.selecionarMaximoID());
						posicaoPatioDAO.inserir(posicaoPatio);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					limpar();
				}else if (acao.equals("Alterar")) {
					PosicaoPatio aux= getDados();
					posicaoPatio.setNome(aux.getNome());
					posicaoPatio.setComprimentoToleravel(aux.getComprimentoToleravel());
					posicaoPatio.setEnvergaduratoleravel(aux.getEnvergaduratoleravel());
					posicaoPatio.setAeronaveCritica(aux.getAeronaveCritica());
					try {
						posicaoPatioDAO.alterar(posicaoPatio);
						recurso= recursoDAO.selecionarById(aux.getIdPosicaoPatio());
						recurso.setEstaEmUso(aux.getEstaEmUso());
						recurso.setLocalizacao(aux.getLocalizacao());
						recurso.setTipoRecurso(aux.getTipoRecurso());
						
						recursoDAO.alterar(recurso);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if (acao.equals("Excluir")) {
					
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						posicaoPatioDAO.apagar(posicaoPatio.getIdPosicaoPatio());
						recursoDAO.apagar(posicaoPatio.getIdRecurso());
					}
					
				}
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});
		btnOK.setBounds(215, 226, 103, 25);
		contentPane.add(btnOK);
		contentPane.add(btnCancelar);
	}
	
	public PosicaoPatio getDados() {
		posicaoPatio.setLocalizacao(txtLocalizacao.getText().toUpperCase());
		posicaoPatio.setNome(nomeDaPosicao.getText().toUpperCase());
		posicaoPatio.setComprimentoToleravel(Float.parseFloat(comprimentoToleravel.getText()));
		posicaoPatio.setEnvergaduratoleravel(Float.parseFloat((envergaduratoleravel.getText())));
		posicaoPatio.setAeronaveCritica(aeronaveCritica.getText().toUpperCase());
		posicaoPatio.setEstaEmUso(rdbEstaemUso.isSelected());
		posicaoPatio.setTipoRecurso(tipoRecurso);
		
		return posicaoPatio;
	}
	
	public void setDados(PosicaoPatio posicao) {
		try {
			recurso= recursoDAO.selecionarById(posicao.getIdPosicaoPatio());
			txtLocalizacao.setText(recurso.getLocalizacao());
			nomeDaPosicao.setText(posicao.getNome());
			comprimentoToleravel.setText(Float.toString(posicao.getComprimentoToleravel()));
			envergaduratoleravel.setText(Float.toString(posicao.getEnvergaduratoleravel()));
			aeronaveCritica.setText(posicao.getAeronaveCritica());
			rdbEstaemUso.setSelected(recurso.getEstaEmUso());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public PosicaoPatio getPosicaoPatio() {
		String codigo= JOptionPane.showInputDialog("Codigo da posição no patio: ");
		if (codigo != null) {
			int id= Integer.parseInt(codigo);
			try {
				posicaoPatio= posicaoPatioDAO.selecionarById(id);
				return(posicaoPatio);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public void limpar() {
		txtLocalizacao.setText("");
		nomeDaPosicao.setText("");
		comprimentoToleravel.setText("");
		envergaduratoleravel.setText("");
		aeronaveCritica.setText("");
		rdbEstaemUso.setSelected(false);
	}
}
