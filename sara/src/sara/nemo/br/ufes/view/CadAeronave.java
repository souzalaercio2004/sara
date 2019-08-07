package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.tables.TableViewAeronaves;

public class CadAeronave extends JFrame {
	
	private static final long serialVersionUID = 1L;
	TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
	Aeronave aeronave= new Aeronave();
	private JTextField txtMatricula;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAeronave frame = new CadAeronave();
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
	public CadAeronave() {
		setTitle("Cadastro de Aeronaves");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 388);
		getContentPane().setLayout(null);
		
		JLabel lblMatrícula = new JLabel("Matrícula");
		lblMatrícula.setBounds(129, 97, 70, 15);
		getContentPane().add(lblMatrícula);
		
		JLabel lblTipoDeAsa = new JLabel("Tipo de Asa");
		lblTipoDeAsa.setBounds(129, 129, 82, 15);
		getContentPane().add(lblTipoDeAsa);
		
		JLabel lblTipoAeronave_idTipoAeronave = new JLabel("Equipamento");
		lblTipoAeronave_idTipoAeronave.setBounds(115, 160, 102, 15);
		getContentPane().add(lblTipoAeronave_idTipoAeronave);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(272, 97, 142, 20);
		getContentPane().add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JComboBox<String> txtTipoDeAsa = new JComboBox<String>();
		txtTipoDeAsa.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha um tipo", "ASA FIXA", "ASA MOVEL"}));
		txtTipoDeAsa.setBounds(272, 129, 142, 24);
		getContentPane().add(txtTipoDeAsa);
		
		JComboBox<String> txtEquipamento = new JComboBox<String>();
		TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
		ArrayList<String> equip = tipoAeronaveDAO.selecionarEquipamento();
		
		for (String s: equip) {
			txtEquipamento.addItem(s);
		}
		
		txtEquipamento.setBounds(270, 160, 142, 24);
		getContentPane().add(txtEquipamento);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AeronaveDAO aeronaveDAO= new AeronaveDAO();
				aeronave.setMatricula(txtMatricula.getText().toUpperCase());
				aeronave.setTipoAsa(((String)txtTipoDeAsa.getSelectedItem()));
								
				String equipamento= (String)txtEquipamento.getSelectedItem(); //Seleciona um equipamento
				int id= tipoAeronaveDAO.selecionarId(equipamento); // Consulta no BD o id do Equipamento
				aeronave.setIdTipoAeronave(id);// Seta o id do Equipamento
				
				try {
					aeronaveDAO.inserir(aeronave);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnInserir.setBounds(66, 260, 80, 25);
		getContentPane().add(btnInserir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AeronaveDAO aeronaveDAO= new AeronaveDAO();
				/*
				String m = JOptionPane.showInputDialog("Consultar por Id (S/N) ? ");
				if ((m.equals("s"))||(m.equals("S"))) {
					int id= Integer.parseInt(JOptionPane.showInputDialog("Qual o id? "));
					aeronaveDAO.selecionarAeronaveById(id);
				}else {					
					aeronaveDAO.selecionar();
				}
				*/
				TableViewAeronaves.showTableViewAeronaves();
			}
		});
		btnConsultar.setBounds(169, 260, 113, 25);
		getContentPane().add(btnConsultar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AeronaveDAO aeronaveDAO= new AeronaveDAO();
				String matricula= txtMatricula.getText().toUpperCase();
				System.out.println("Matricula: "+matricula);
				aeronave= aeronaveDAO.selecionarByMatricula(matricula);
				
				aeronave.setMatricula(txtMatricula.getText().toUpperCase());
				aeronave.setTipoAsa(((String)txtTipoDeAsa.getSelectedItem()));				
				String equipamento= (String)txtEquipamento.getSelectedItem(); //Seleciona um equipamento
				int id= tipoAeronaveDAO.selecionarId(equipamento); // Consulta no BD o id do Equipamento
				aeronave.setIdTipoAeronave(id);// Seta o id do Equipamento
				
				try {
					aeronaveDAO.alterar(aeronave);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(52, 300, 94, 25);
		getContentPane().add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AeronaveDAO aeronaveDAO= new AeronaveDAO();
				String mat= JOptionPane.showInputDialog("Matricula= ? ");
				mat= mat.toUpperCase();
				String confirma= JOptionPane.showInputDialog("Confirma Exclusão "+mat +" (S/N)? ");
				if ((confirma.equals("s"))||(confirma.equals("S"))) aeronaveDAO.apagar(mat);
				
			}
		});
		btnDeletar.setBounds(169, 297, 117, 25);
		getContentPane().add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(323, 260, 117, 25);
		getContentPane().add(btnCancelar);
	}
}
