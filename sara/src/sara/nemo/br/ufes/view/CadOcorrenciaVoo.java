package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.OcorrenciaVoo;

public class CadOcorrenciaVoo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtdata;
	private JFormattedTextField txthora;
	private JComboBox<String> txtSituacao;
	private JTextField txtMatricula;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadOcorrenciaVoo frame = new CadOcorrenciaVoo();
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
	public CadOcorrenciaVoo() {
		setTitle("CADASTRO DE OCORRENCIAS DE VOO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbldata = new JLabel("data");
		
		txtdata = new JFormattedTextField("dd/MM/aaaa");
		//txtdata.setText("dd/MM/aaaa");
		txtdata.setColumns(10);
		
		JLabel lblhora = new JLabel("hora");
		
		txthora = new JFormattedTextField("HH:mm");
		//txthora.setText("HH:mm");
		txthora.setHorizontalAlignment(SwingConstants.TRAILING);
		txthora.setColumns(10);
		
		JLabel lblsituacao = new JLabel("situacao");
		
		txtSituacao = new JComboBox<>();
		txtSituacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Atrasado", "Cancelado", "Confirmado", "Decolado", "Encerrado", "Etapa Concluída", "Pousado", "Previsto", "Procure a Cia Aérea"}));
		
		JLabel lblaeronave = new JLabel("Matricula da Aeronave");
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OcorrenciaVoo ocorrenciaVoo= new OcorrenciaVoo();
				OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
				AeronaveDAO AeronaveDAO= new AeronaveDAO();
				ocorrenciaVoo.setData(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtdata.getText(), "dd/MM/yyyy"));
				ocorrenciaVoo.setHora(LocalTime.parse(txthora.getText()));
				String matricula= txtMatricula.getText();
				Aeronave aero= AeronaveDAO.selecionarByMatricula(matricula);
				
				ocorrenciaVoo.setIdAeronave(aero.getId());
				ocorrenciaVoo.setSituacao((txtSituacao.getSelectedItem().toString()));
				try {
					ocorrenciaVooDAO.inserir(ocorrenciaVoo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "Matricula não Cadastrada");
				}
			}
			
		});
		
		JButton btnAlterar = new JButton("Alterar");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
				ocorrenciaVooDAO.selecionar();
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(138)
					.addComponent(lbldata)
					.addGap(12)
					.addComponent(txtdata, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(138)
					.addComponent(lblhora)
					.addGap(12)
					.addComponent(txthora, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblaeronave)
					.addGap(12)
					.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addComponent(lblsituacao)
					.addGap(12)
					.addComponent(txtSituacao, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addComponent(btnIncluir, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAlterar)
					.addGap(6)
					.addComponent(btnCancelar))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(btnConsultar)
					.addGap(18)
					.addComponent(btnExcluir))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lbldata))
						.addComponent(txtdata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblhora))
						.addComponent(txthora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblaeronave)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblsituacao))
						.addComponent(txtSituacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(116)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnCancelar))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConsultar)
						.addComponent(btnExcluir)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
