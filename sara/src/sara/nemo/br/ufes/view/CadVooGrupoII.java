package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioParticularDAO;
import sara.nemo.br.ufes.inf.DAO.VooDAO;
import sara.nemo.br.ufes.inf.DAO.VooGrupoIIDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.domain.VooGrupoII;
import sara.nemo.br.ufes.inf.item.ItemCategoria;

public class CadVooGrupoII extends JFrame {
	List<String> proprietario;
	ProprietarioDAO proprietarioDAO=new ProprietarioDAO();
	ProprietarioParticularDAO proprietarioParticularDAO= new ProprietarioParticularDAO();
	CategoriaDAO categoriaDAO= new CategoriaDAO();
	Aeronave aeronave;
	AeronaveDAO aeronaveDAO= new AeronaveDAO();
	Voo voo= new Voo();
	VooDAO vooDAO= new VooDAO();
	
	VooGrupoII vooGrupoII= new VooGrupoII();
	VooGrupoIIDAO vooGrupoIIDAO= new VooGrupoIIDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtDataPrevistaPouso;
	private JTextField txtDataPrevistaDecolagem;
	private JTextField txtHoraPrevistaPouso;
	private JTextField txtHoraPrevistaDecolagem;
	private JTextField txtOrigem;
	private JTextField txtDestino;
	private JComboBox<String> cbxCategoria;
	private JTextField txtNomeDoComandante;
	private JTextField txtTelefoneDoComandante;
	private JTextField txtTempoDeSolo;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVooGrupoII frame = new CadVooGrupoII();
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
	
	public CadVooGrupoII() {
		setTitle("Cadastro de Voos do Grupo II");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(163, 69, 66, 15);
		
		JLabel lblDataPrevista = new JLabel("Data Prevista");
		lblDataPrevista.setBounds(288, 54, 96, 15);
		
		JLabel lblPouso = new JLabel("Pouso");
		lblPouso.setBounds(250, 69, 44, 15);
		
		JLabel lblDecolagem = new JLabel("Decolagem");
		lblDecolagem.setBounds(330, 69, 78, 15);
		
		JLabel lblHoraPrevista = new JLabel("Hora Prevista");
		lblHoraPrevista.setBounds(478, 54, 96, 15);
		
		JLabel lblPouso_1 = new JLabel("Pouso");
		lblPouso_1.setBounds(468, 69, 44, 15);
		
		JLabel lblDecolagem_1 = new JLabel("Decolagem");
		lblDecolagem_1.setBounds(518, 69, 78, 15);
		
		JLabel lblSituao = new JLabel("Situação");
		lblSituao.setBounds(136, 144, 62, 15);
		
		JLabel lblOrigem = new JLabel("Origem");
		lblOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrigem.setBounds(277, 144, 51, 15);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setHorizontalAlignment(SwingConstants.LEFT);
		lblDestino.setBounds(396, 144, 55, 15);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(47, 196, 70, 15);
		contentPane.setLayout(null);
		contentPane.add(lblDataPrevista);
		
		txtDataPrevistaPouso = new JFormattedTextField("dd/MM/aaaa");
		txtDataPrevistaPouso.setBounds(241, 96, 87, 19);
		txtDataPrevistaPouso.setColumns(10);
		contentPane.add(txtDataPrevistaPouso);
		
		txtDataPrevistaDecolagem = new JFormattedTextField("dd/MM/aaaa");
		txtDataPrevistaDecolagem.setBounds(346, 96, 92, 19);
		txtDataPrevistaDecolagem.setColumns(10);
		contentPane.add(txtDataPrevistaDecolagem);
		contentPane.add(lblHoraPrevista);
		
		txtHoraPrevistaPouso = new JFormattedTextField("HH:mm");
		txtHoraPrevistaPouso.setBounds(450, 96, 70, 19);
		txtHoraPrevistaPouso.setColumns(10);
		contentPane.add(txtHoraPrevistaPouso);
		
		txtHoraPrevistaDecolagem = new JFormattedTextField("HH:mm");
		txtHoraPrevistaDecolagem.setBounds(526, 96, 70, 19);
		txtHoraPrevistaDecolagem.setColumns(10);
		contentPane.add(txtHoraPrevistaDecolagem);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(163, 96, 66, 19);
		txtMatricula.setColumns(10);
		contentPane.add(txtMatricula);
		contentPane.add(lblPouso);
		contentPane.add(lblDecolagem);
		contentPane.add(lblPouso_1);
		contentPane.add(lblDecolagem_1);
		contentPane.add(lblSituao);
		
		JComboBox<String> cbxSituacao = new JComboBox<String>();
		cbxSituacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha um item", "ATRASADO", "CONFIRMADO", "DECOLADO", "ETAPA CONCLUIDA", "PREVISTO", "POUSADO", "PROCURE A CIA AÉREA"}));
		cbxSituacao.setBounds(93, 168, 162, 19);
		contentPane.add(cbxSituacao);
		contentPane.add(lblOrigem);
		
		txtOrigem = new JTextField();
		txtOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		txtOrigem.setBounds(270, 171, 114, 19);
		contentPane.add(txtOrigem);
		txtOrigem.setColumns(10);
		contentPane.add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setHorizontalAlignment(SwingConstants.LEFT);
		txtDestino.setBounds(396, 171, 114, 19);
		contentPane.add(txtDestino);
		txtDestino.setColumns(10);
		contentPane.add(lblCategoria);
		
		cbxCategoria = new JComboBox<String>();
		cbxCategoria.setBounds(136, 196, 471, 19);
		contentPane.add(cbxCategoria);
		
		JLabel lblProprietario = new JLabel("Proprietario");
		lblProprietario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProprietario.setBounds(175, 232, 139, 15);
		contentPane.add(lblProprietario);
		
		JComboBox<String> cbxProprietario = new JComboBox<String>();
		cbxProprietario.setBounds(332, 230, 261, 19);
		contentPane.add(cbxProprietario);
		
		JLabel lblComandante = new JLabel("Nome do Comandante");
		lblComandante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComandante.setBounds(152, 261, 162, 15);
		contentPane.add(lblComandante);
		
		txtNomeDoComandante = new JTextField();
		txtNomeDoComandante.setBounds(330, 259, 263, 19);
		contentPane.add(txtNomeDoComandante);
		txtNomeDoComandante.setColumns(10);
		
		JLabel lblTelefoneDoComandante = new JLabel("Telefone do Comandante");
		lblTelefoneDoComandante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefoneDoComandante.setBounds(136, 286, 192, 15);
		contentPane.add(lblTelefoneDoComandante);
	
		txtTelefoneDoComandante = new JFormattedTextField("(##) #####-####");
		txtTelefoneDoComandante.setBounds(337, 286, 155, 19);
		contentPane.add(txtTelefoneDoComandante);
		txtTelefoneDoComandante.setColumns(10);
		
		JLabel lblTempoDeSolo = new JLabel("Tempo de Solo");
		lblTempoDeSolo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTempoDeSolo.setBounds(210, 313, 114, 15);
		contentPane.add(lblTempoDeSolo);
		
		txtTempoDeSolo = new JFormattedTextField("HH:mm");
		txtTempoDeSolo.setBounds(342, 313, 70, 19);
		contentPane.add(txtTempoDeSolo);
		txtTempoDeSolo.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String matricula= txtMatricula.getText();
					aeronave= aeronaveDAO.selecionarByMatricula(matricula);
					voo.setIdAeronave(aeronave.getId());
					voo.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaPouso.getText(), "dd/MM/yyyy"));
					voo.setDataPrevistaParaDecolagem(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaDecolagem.getText(), "dd/MM/yyyy"));
					voo.setHoraPrevistaParaPouso(LocalTime.parse(txtHoraPrevistaPouso.getText()));
					voo.setHoraPrevistaParaDecolagem(LocalTime.parse(txtHoraPrevistaDecolagem.getText()));
					voo.setSituacao(cbxSituacao.getSelectedItem().toString());
					voo.setOrigem(txtOrigem.getText());
					voo.setDestino(txtDestino.getText());
				}catch(NullPointerException n1) {
					JOptionPane.showMessageDialog(null, "Matricula invalida, cadastre a Aeronave" + 238);
					n1.printStackTrace();
				}
				
				String[] dadosCategoria= (cbxCategoria.getSelectedItem().toString()).split(" ");
				System.out.println(dadosCategoria[0]);
				int idCategoria= Integer.valueOf(dadosCategoria[0]);
				voo.setIdCategoria(idCategoria);
				
				try {
					vooDAO.inserir(voo);
					int id= vooDAO.selecionarMaximoID();
					System.out.println("id do voo= "+ id);
					
					vooGrupoII.setIdVooGrupoII(id);
					vooGrupoII.setIdProprietarioParticular(proprietarioDAO.selecionarIdByName((String)cbxProprietario.getSelectedItem()));
					vooGrupoII.setNomeComandante(txtNomeDoComandante.getText());
					vooGrupoII.setTelefoneDoComandante(txtTelefoneDoComandante.getText());
					vooGrupoII.setTempoDeSolo(LocalTime.parse(txtTempoDeSolo.getText()));
					vooGrupoIIDAO.inserir(vooGrupoII);
										
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnIncluir.setBounds(153, 394, 117, 25);
		contentPane.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do voo que deseja alterar: "));
				voo= vooDAO.selecionarById(id);
				//voo.setIdAeronave(aeronave.getId());
				voo.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaPouso.getText(), "dd/MM/yyyy"));
				voo.setDataPrevistaParaDecolagem(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaDecolagem.getText(), "dd/MM/yyyy"));
				voo.setHoraPrevistaParaPouso(LocalTime.parse(txtHoraPrevistaPouso.getText()));
				voo.setHoraPrevistaParaDecolagem(LocalTime.parse(txtHoraPrevistaDecolagem.getText()));
				voo.setSituacao(cbxSituacao.getSelectedItem().toString());
				voo.setOrigem(txtOrigem.getText());
				voo.setDestino(txtDestino.getText());
				vooCategoria(voo); // Seta a categoria do voo
				
				try {
					vooDAO.alterar(voo);
					id= vooDAO.selecionarMaximoID();
					vooGrupoII.setIdVooGrupoII(id);
					vooGrupoII.setIdVooGrupoII(id);
					vooGrupoII.setIdProprietarioParticular(proprietarioDAO.selecionarIdByName((String)cbxProprietario.getSelectedItem()));
					vooGrupoII.setNomeComandante(txtNomeDoComandante.getText());
					vooGrupoII.setTelefoneDoComandante(txtTelefoneDoComandante.getText());
					vooGrupoII.setTempoDeSolo(LocalTime.parse(txtTempoDeSolo.getText()));
					vooGrupoIIDAO.alterar(vooGrupoII);					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAlterar.setBounds(288, 394, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(440, 394, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vooGrupoIIDAO.selecionar();
			}
		});
		btnConsultar.setBounds(152, 448, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do voo que deseja apagar: "));
				vooGrupoIIDAO.apagar(id);
				vooDAO.apagar(id);
			}
		});
		btnExcluir.setBounds(299, 448, 117, 25);
		contentPane.add(btnExcluir);
		
		proprietario= proprietarioParticularDAO.selecionarLista();
		for (String s: proprietario) {
			cbxProprietario.addItem(s);
		}
	
		for(ItemCategoria cat: categoriaDAO.selecionarLista()) {
			cbxCategoria.addItem(cat.getIdItemCategoria()+" "+cat.getDados());
		}
	}
	
	public Voo vooCategoria(Voo voo) {
		String[] dadosCategoria= (cbxCategoria.getSelectedItem().toString()).split(" ");
		//System.out.println(dadosCategoria[0]);
		int idCategoria= Integer.valueOf(dadosCategoria[0]);
		voo.setIdCategoria(idCategoria);
		return voo;
	}
}
