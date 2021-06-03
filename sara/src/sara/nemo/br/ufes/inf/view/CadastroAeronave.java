package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.Aeronave_has_ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.Aeronave_has_Proprietario;
import sara.nemo.br.ufes.inf.domain.Proprietario;

public class CadastroAeronave extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private String acao;
	public static AeronaveDAO aeronaveDAO= new AeronaveDAO();
	TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
	Aeronave aeronave= new Aeronave();
	private JTextField txtMatricula;
	JComboBox<String> txtTipoDeAsa;
	JComboBox<String> txtEquipamento;
	JComboBox<String> txtProprietario;
	

	public CadastroAeronave(JFrame frmMenuPrincipal, String acao) {
		setTitle("Cadastro de Aeronaves");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sair da tela sem fechar o programa
		setBounds(100, 100, 581, 388);
		getContentPane().setLayout(null);
		this.acao= acao;
		frmMenuPrincipal.setVisible(false);
		
		addWindowListener(new WindowAdapter(){ //Retorna ao menu inicial ao clicar no botao X do JFrame 
			public void windowClosing(WindowEvent event) {
				frmMenuPrincipal.setVisible(true);
				setVisible(false);
			}
		});		
		
		JLabel lblMatrícula = new JLabel("Matrícula");
		lblMatrícula.setBounds(168, 98, 70, 15);
		getContentPane().add(lblMatrícula);
		
		JLabel lblTipoDeAsa = new JLabel("Tipo de Asa");
		lblTipoDeAsa.setBounds(156, 130, 82, 15);
		getContentPane().add(lblTipoDeAsa);
		
		JLabel lblTipoAeronave_idTipoAeronave = new JLabel("Equipamento");
		lblTipoAeronave_idTipoAeronave.setBounds(136, 161, 102, 15);
		getContentPane().add(lblTipoAeronave_idTipoAeronave);
		
		JLabel lblProprietario = new JLabel("Proprietario");
		lblProprietario.setBounds(115, 206, 123, 15);
		getContentPane().add(lblProprietario);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(272, 97, 142, 20);
		getContentPane().add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtTipoDeAsa = new JComboBox<String>();
		txtTipoDeAsa.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha o tipo de asa", "ASA FIXA", "ASA MOVEL"}));
		txtTipoDeAsa.setBounds(272, 129, 250, 24);
		getContentPane().add(txtTipoDeAsa);
		
		txtEquipamento = new JComboBox<String>();
		txtEquipamento.addItem("Selecionar Equipamento");
		
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		ArrayList<String> equip = tipoAeronaveDAO.selecionarEquipamento();
		
		for (String s: equip) {
			txtEquipamento.addItem(s);
		}
		
		txtEquipamento.setBounds(270, 160, 252, 24);
		getContentPane().add(txtEquipamento);
		
		txtProprietario= new JComboBox<String>();
		txtProprietario.addItem("Selecionar Proprietario");
		ProprietarioDAO proprietarioDAO= new ProprietarioDAO();
		ArrayList<String> prop= proprietarioDAO.selecionarProprietario();
		
		for (String p: prop) {
			txtProprietario.addItem(p);
		}
		
		txtProprietario.setBounds(272, 201, 250, 24);
		getContentPane().add(txtProprietario);
		
		JButton btnOK = new JButton("OK");		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acao.equals("Inserir")) {
					Aeronave aero= getDados();
					try {
						System.out.println("inserindo "+aero.toString());
						aeronaveDAO.inserir(aero);
						
						//Insere na tabela Aeronave_has_Proprietario
						int idAeronaveCadastrada= aeronaveDAO.selecionarMaximoID();
						Aeronave_has_ProprietarioDAO aeronave_has_Proprietario= new Aeronave_has_ProprietarioDAO(); 
						Aeronave_has_Proprietario ahp= new Aeronave_has_Proprietario(idAeronaveCadastrada, aero.getIdProprietario());
						aeronave_has_Proprietario.inserir(ahp);

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Aeronave Inválida");
						e1.printStackTrace();
					}
					limpar();
				}else if (acao.equals("Alterar")) {
						Aeronave aux= getDados();
						Aeronave aero= aeronaveDAO.selecionarAeronaveByMatricula(aux.getMatricula());
						aero.setIdProprietario(aux.getIdProprietario());
						aero.setIdTipoAeronave(aux.getIdTipoAeronave());
						aero.setMatricula(aux.getMatricula().toUpperCase());
						aero.setTipoAsa(aux.getTipoAsa());
						try {
							aeronaveDAO.alterar(aero);
							JOptionPane.showMessageDialog(null, "Aeronave alterada");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Falha na alteracao da Aeronave");
							e1.printStackTrace();
						}			
						limpar();
						setVisible(false);
				}else if (acao.equals("Excluir")) {
					AeronaveDAO aeronaveDAO= new AeronaveDAO();
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						Aeronave aero= getDados();
						aeronaveDAO.apagar(aero.getMatricula());
						limpar();
						
					}else {
						frmMenuPrincipal.setVisible(true);
					}
					setVisible(false);
				}
			}
		});
		btnOK.setBounds(66, 260, 80, 25);
		getContentPane().add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
			}
		});

		btnCancelar.setBounds(169, 260, 113, 25);
		getContentPane().add(btnCancelar);
		
		frmMenuPrincipal.setVisible(true);
	}
	
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		frmMenuPrincipal.setVisible(false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (acao.equals("Inserir")) {
					CadastroAeronave frm = new CadastroAeronave(frmMenuPrincipal, acao);
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||acao.equals("Excluir")) {
					CadastroAeronave frm = new CadastroAeronave(frmMenuPrincipal, acao);
					String matricula= JOptionPane.showInputDialog("Matricula da aeronave: ");
					Aeronave aero= aeronaveDAO.selecionarAeronaveByMatricula(matricula);
					frm.setarDados(acao, aero);
					frm.setVisible(true);
				}
				
			}
		});
		frmMenuPrincipal.setVisible(true);
	}
	public void setarDados(String acao, Aeronave aero) {
		if (aero != null) {
			txtMatricula.setText(aero.getMatricula());
			txtTipoDeAsa.setSelectedItem(aero.getTipoAsa());
			int idTipoAeronave= aero.getIdTipoAeronave();
			TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
			String equipamento= tipoAeronaveDAO.selecionarNomeEquipamento(idTipoAeronave);
			txtEquipamento.setSelectedItem(equipamento);
			ProprietarioDAO  proprietarioDAO= new ProprietarioDAO();
			Proprietario proprietario= proprietarioDAO.selecionarById(aero.getIdProprietario());
			
			String nomeProp= proprietario.getNomeProprietario();
			if (nomeProp != null) {
				txtProprietario.setSelectedItem(nomeProp);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Aeronave Inválida");
			setVisible(false);
		}
	}
	
	public Aeronave getDados() { //Retorna Aeronave para Inserir no BD
		Aeronave aero= new Aeronave();
		ProprietarioDAO  proprietarioDAO= new ProprietarioDAO();
		int idProp=proprietarioDAO.selecionarIdProprietarioByName((String)txtProprietario.getSelectedItem());
		aero.setIdProprietario(idProp);
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		int idTipoAeronave= tipoAeronaveDAO.selecionarId((String)txtEquipamento.getSelectedItem());
		aero.setIdTipoAeronave(idTipoAeronave);
		aero.setMatricula(txtMatricula.getText().toUpperCase());
		aero.setTipoAsa((String)txtTipoDeAsa.getSelectedItem());
		
		return aero;
	}
	public void limpar() {
		txtMatricula.setText("");
		txtTipoDeAsa.setSelectedIndex(0);
		txtEquipamento.setSelectedIndex(0);
		txtProprietario.setSelectedIndex(0);
	}
	
	
	public Aeronave getAeronave() {
		String matricula= JOptionPane.showInputDialog("Matricula da aeronave: ");
		if (matricula.equals(null)) return null;
		
		Aeronave aeronave= aeronaveDAO.selecionarAeronaveByMatricula(matricula);
		return aeronave;
		
	}
	public void setAcao(String acao) {
		this.acao= acao;
	}
	
	public String getAcao() {
		return acao;
	}
	
}
