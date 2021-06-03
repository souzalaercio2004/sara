package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoHelipontoDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.DAO.RecursosPorProprietarioDAO;
import sara.nemo.br.ufes.inf.domain.Esteira;
import sara.nemo.br.ufes.inf.domain.Pista;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;
import sara.nemo.br.ufes.inf.domain.PosicaoHeliponto;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.Proprietario;
import sara.nemo.br.ufes.inf.domain.Recurso;
import sara.nemo.br.ufes.inf.domain.RecursosPorProprietario;


public class CadastroRecursosPorProprietario extends JFrame {
	static RecursosPorProprietario rec;
	RecursosPorProprietarioDAO recursosPorProprietarioDAO= new RecursosPorProprietarioDAO();
	ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	
	
	RecursoDAO recursoDAO= new RecursoDAO();
	ArrayList<String> nomeRecurso;
	String tipoRec;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbxProprietario;
	private JComboBox<String> cbxTipoRecurso;
	private JComboBox<String> cbxNomeRecurso;
	private JTextField txtPrioridade;

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastroRecursosPorProprietario frm= new CadastroRecursosPorProprietario(frmMenuPrincipal, acao);
				frmMenuPrincipal.setVisible(false);
				if (acao.equals("Inserir")) {
					frm.setVisible(true);
				}else if (acao.equals("Alterar")|| acao.equals("Excluir")) {
					rec= frm.getRecursosPorProprietario();
					frm.setDados(rec);
					frm.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroRecursosPorProprietario(JFrame frmMenuPrincipal, String acao) {
		setTitle("PRIORIDADE NO USO DE RECURSOS POR PROPRIETÁRIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 469);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProprietario = new JLabel("Proprietário");
		lblProprietario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProprietario.setBounds(63, 65, 96, 15);
		contentPane.add(lblProprietario);
		
		cbxProprietario = new JComboBox<String>();
		cbxProprietario.setBounds(187, 63, 222, 19);
		contentPane.add(cbxProprietario);
		ArrayList<String> prop= proprietarioDAO.selecionarProprietario();
		
		for (String p: prop) {
			cbxProprietario.addItem(p);
		}
		
		JLabel lblTipoDeRecurso = new JLabel("Recurso");
		lblTipoDeRecurso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeRecurso.setBounds(89, 116, 70, 15);
		contentPane.add(lblTipoDeRecurso);
		
		cbxTipoRecurso = new JComboBox<String>();

		cbxTipoRecurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione um item", "ESTEIRA", "CABECEIRA", "PORTÃO DE EMBARQUE", "HELIPONTO", "POSIÇÃO NO PÁTIO"}));
		cbxTipoRecurso.setBounds(190, 116, 219, 24);
		contentPane.add(cbxTipoRecurso);

		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(89, 170, 70, 24);
		contentPane.add(lblNome);
		
		cbxNomeRecurso = new JComboBox<String>();
			
		cbxNomeRecurso.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseEntered(MouseEvent e) {
				cbxNomeRecurso.removeAllItems();
				tipoRec= (String) cbxTipoRecurso.getSelectedItem();
				preencherComboboxNomeRecurso(tipoRec);
			}
		});
		
		cbxNomeRecurso.setBounds(190, 170, 114, 19);
		contentPane.add(cbxNomeRecurso);
		
		//
		tipoRec= (String) cbxTipoRecurso.getSelectedItem();
		preencherComboboxNomeRecurso(tipoRec);
		
		//
		JLabel lblPrioridade = new JLabel("Prioridade");
		lblPrioridade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrioridade.setBounds(78, 206, 81, 15);
		contentPane.add(lblPrioridade);
		
		txtPrioridade = new JTextField();
		txtPrioridade.setBounds(190, 206, 114, 19);
		contentPane.add(txtPrioridade);
		txtPrioridade.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					rec= getDados();
					System.out.println("prioridade: "+rec.getPrioridade());
					try {
						recursosPorProprietarioDAO.inserir(rec);
					
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					RecursosPorProprietario recursosPorProprietario= getDados();
					rec.setPrioridade(recursosPorProprietario.getPrioridade());
					System.out.println("Vamos alterar: "+ recursosPorProprietario.toString());
					try {
						recursosPorProprietarioDAO.alterar(rec);
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (acao.equals("Excluir")) {
					recursosPorProprietarioDAO.apagar(rec.getIdRecurso(), rec.getIdProprietario());
					
				}
				limpar();
			}
		});
		btnOK.setBounds(187, 289, 103, 25);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(324, 289, 96, 25);
		contentPane.add(btnCancelar);
	}
	
	public void preencherComboboxNomeRecurso(String tipoRec) {
		cbxNomeRecurso.removeAllItems();
		if (tipoRec.equals("ESTEIRA")){
			EsteiraDAO esteiraDAO = new EsteiraDAO();
			nomeRecurso= esteiraDAO.selecionarNomes();
			for (String s: nomeRecurso) {
				cbxNomeRecurso.addItem(s);
			}
			
		}else if (tipoRec.equals("CABECEIRA")) {
			PistaDAO pistaDAO= new PistaDAO();
			nomeRecurso= pistaDAO.selecionarNomeCabeceira();
			for (String s: nomeRecurso) {
				cbxNomeRecurso.addItem(s);
			}
		}else if(tipoRec.equals("PORTÃO DE EMBARQUE")) {
			PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
			nomeRecurso= portaoDeEmbarqueDAO.selecionarNomes();
			for (String s: nomeRecurso) {
				cbxNomeRecurso.addItem(s);
			}
		}else if (tipoRec.equals("HELIPONTO")) {
			PosicaoHelipontoDAO posicaoHelipontoDAO= new PosicaoHelipontoDAO();
			nomeRecurso= posicaoHelipontoDAO.selecionarNomes();
			for (String s: nomeRecurso) {
				cbxNomeRecurso.addItem(s);
			}
		}else if (tipoRec.equals("POSIÇÃO NO PÁTIO")) {
			PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
			nomeRecurso= posicaoPatioDAO.selecionarNomes();
			for (String s: nomeRecurso) {
				cbxNomeRecurso.addItem(s);
			}
		}
		
	}
	
	public int obterIdRecurso(String tipoRec, String nomeRec) {
		int idRecurso=0;
		if (tipoRec.equals("ESTEIRA")){
			EsteiraDAO esteiraDAO = new EsteiraDAO();
			idRecurso= esteiraDAO.selecionarIdDadoNome(nomeRec);			
		}else if (tipoRec.equals("CABECEIRA")) {
			PistaDAO pistaDAO= new PistaDAO();
			idRecurso= pistaDAO.selecionarIdDadoNome(nomeRec);
		}else if(tipoRec.equals("PORTÃO DE EMBARQUE")) {
			PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
			idRecurso= portaoDeEmbarqueDAO.selecionarIdDadoNome(nomeRec);			
		}else if (tipoRec.equals("HELIPONTO")) {
			PosicaoHelipontoDAO posicaoHelipontoDAO= new PosicaoHelipontoDAO();
			idRecurso= posicaoHelipontoDAO.selecionarIdDadoNome(nomeRec);
		}else if (tipoRec.equals("POSIÇÃO NO PÁTIO")) {
			PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
			idRecurso= posicaoPatioDAO.selecionarIdDadoNome(nomeRec);
			
		}
		return (idRecurso);
	}
	

	
	public RecursosPorProprietario getDados() {  
		RecursosPorProprietario recursosPorProprietario= null;
		String nome= cbxProprietario.getSelectedItem().toString();
		try {
			recursosPorProprietario= new RecursosPorProprietario();
			int idProp= proprietarioDAO.selecionarIdByName(nome);
			Proprietario proprietario= proprietarioDAO.selecionarById(idProp);
			
			String tipoRecurso= cbxTipoRecurso.getSelectedItem().toString();
			//cbxNomeRecurso.removeAllItems();
			//tipoRec= (String) cbxTipoRecurso.getSelectedItem();
			//preencherComboboxNomeRecurso(tipoRecurso);
			
			String nomeRec= (String)cbxNomeRecurso.getSelectedItem();
			int idRecurso= obterIdRecurso(tipoRecurso, nomeRec);
			
			recursosPorProprietario.setIdProprietario(proprietario.getIdProprietario());
			recursosPorProprietario.setIdRecurso(idRecurso);
			recursosPorProprietario.setPrioridade(Integer.parseInt(txtPrioridade.getText()));
			System.out.println("A prioridade sera: "+recursosPorProprietario.getPrioridade());
		} catch (Exception e) {
			System.out.println("Falha em obter recursos por proprietario "+ e.getMessage());
			e.printStackTrace();		
		}
		
		return recursosPorProprietario;
	}
	
	public void setDados(RecursosPorProprietario recPorProprietario) {
		
		int id= recPorProprietario.getIdProprietario();
		Proprietario prop= proprietarioDAO.selecionarById(id);
		cbxProprietario.setSelectedItem(prop.getNomeProprietario());
		String prioridade= Integer.toString(recPorProprietario.getPrioridade());
		txtPrioridade.setText(prioridade);
		int idRec= recPorProprietario.getIdRecurso();
		RecursoDAO recursoDAO= new RecursoDAO();
		Recurso recurso;
		try {
			
			recurso = recursoDAO.selecionarById(idRec);
			cbxTipoRecurso.setSelectedItem(recurso.getTipoRecurso());
			cbxNomeRecurso.removeAllItems();
			tipoRec= (String) cbxTipoRecurso.getSelectedItem();
			preencherComboboxNomeRecurso(tipoRec);
			
			if (recurso.getTipoRecurso().equals("ESTEIRA")) {
				Esteira esteira=recursoDAO.selecionarEsteiraDadoIdRecurso(idRec);
				cbxNomeRecurso.setSelectedItem(esteira.getNome());
			}else if (recurso.getTipoRecurso().equals("HELIPONTO")) {
				PosicaoHeliponto posicaoHeliponto=recursoDAO.selecionarPosicaoHelipontoDadoIdRecurso(idRec);
				cbxNomeRecurso.setSelectedItem(posicaoHeliponto.getNome());
			}else if (recurso.getTipoRecurso().equals("PISTA")) {
				Pista pista=recursoDAO.selecionarPistaDadoIdRecurso(idRec);
				cbxNomeRecurso.setSelectedItem(pista.getNome());
			}else if (recurso.getTipoRecurso().equals("PORTÃO DE EMBARQUE")) {
				PortaoDeEmbarque portaoDeEmbarque=recursoDAO.selecionarPortaoDeEmbarqueDadoIdRecurso(idRec);
				cbxNomeRecurso.setSelectedItem(portaoDeEmbarque.getNome());
			}else if (recurso.getTipoRecurso().equals("POSICAO NO PÁTIO")) {
				PosicaoPatio posicaoPatio=recursoDAO.selecionarPosicaoPatioDadoIdRecurso(idRec);
				cbxNomeRecurso.setSelectedItem(posicaoPatio.getNome());
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados invalidos !"+ e.getMessage());
		} 	
	}
	
	public RecursosPorProprietario getRecursosPorProprietario() { //Busca o recurso no Banco de Dados
		RecursosPorProprietario recProp= new RecursosPorProprietario();
		String codigoRec= JOptionPane.showInputDialog("Codigo do Recurso: ");
		String codigoProp= JOptionPane.showInputDialog("Codigo do proprietario: ");
		if ((codigoRec != null)&&(codigoProp != null)) {
			int idRec= Integer.parseInt(codigoRec);
			int idProp= Integer.parseInt(codigoProp);
			try {
				recProp= recursosPorProprietarioDAO.selecionarById(idProp, idRec);
				return(recProp);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return null;
	}
	
	public void limpar() {
		
		cbxProprietario.setSelectedIndex(0);
		cbxTipoRecurso.setSelectedIndex(0);;
		cbxNomeRecurso.setSelectedIndex(0);
		txtPrioridade.setText(" ");

	}
}
