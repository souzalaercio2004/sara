package sara.nemo.br.ufes.view;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoHelipontoDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.DAO.RecursosPorProprietarioDAO;
import sara.nemo.br.ufes.inf.domain.RecursosPorProprietario;
import sara.nemo.br.ufes.inf.tables.TableViewRecursosPorProprietario;
import javax.swing.SwingConstants;


public class CadRecursosPorProprietario extends JFrame {
	RecursosPorProprietario recursosPorProprietario= new RecursosPorProprietario();
	RecursosPorProprietarioDAO recursosPorProprietarioDAO= new RecursosPorProprietarioDAO();
	ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	
	//ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
	RecursoDAO recursoDAO= new RecursoDAO();
	ArrayList<String> nomeRecurso;
	String tipoRec;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbxProprietario;
	private JComboBox<String> cbxNomeRecurso;
	private JTextField txtPrioridade;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadRecursosPorProprietario frame = new CadRecursosPorProprietario();
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
	public CadRecursosPorProprietario() {
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
		
		JComboBox<String> cbxTipoRecurso = new JComboBox<String>();

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
		
		JLabel lblPrioridade = new JLabel("Prioridade");
		lblPrioridade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrioridade.setBounds(78, 206, 81, 15);
		contentPane.add(lblPrioridade);
		
		txtPrioridade = new JTextField();
		txtPrioridade.setBounds(190, 206, 114, 19);
		contentPane.add(txtPrioridade);
		txtPrioridade.setColumns(10);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			String nome= (String) cbxProprietario.getSelectedItem();
			int idProp= proprietarioDAO.selecionarIdByName(nome);
			int idRecurso= obterIdRecurso(tipoRec, (String)cbxNomeRecurso.getSelectedItem());
			recursosPorProprietario.setIdProprietario(idProp);
			try {
				recursosPorProprietario.setIdRecurso(idRecurso);
				recursosPorProprietario.setPrioridade(Integer.valueOf(txtPrioridade.getText()));		
				recursosPorProprietarioDAO.inserir(recursosPorProprietario);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnInserir.setBounds(49, 289, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TableViewRecursosPorProprietario exibir= new TableViewRecursosPorProprietario();
				TableViewRecursosPorProprietario.showTableViewRecursosPorProprietario();
			}
		});
		btnConsultar.setBounds(187, 289, 103, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(324, 289, 96, 25);
		contentPane.add(btnCancelar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idRecurso= obterIdRecurso(tipoRec, (String)cbxNomeRecurso.getSelectedItem());
				String nome= (String) cbxProprietario.getSelectedItem();
				int idProprietario= proprietarioDAO.selecionarIdByName(nome);
				try {
					recursosPorProprietario= recursosPorProprietarioDAO.selecionarById(idProprietario ,idRecurso);
					recursosPorProprietario.setPrioridade(Integer.valueOf(txtPrioridade.getText()));
					recursosPorProprietarioDAO.alterar(recursosPorProprietario);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAlterar.setBounds(45, 354, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(198, 354, 96, 25);
		contentPane.add(btnExcluir);
	}
	
	public void preencherComboboxNomeRecurso(String tipoRec) {
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
}
