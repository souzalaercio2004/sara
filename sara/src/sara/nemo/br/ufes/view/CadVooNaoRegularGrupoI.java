package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;

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
import sara.nemo.br.ufes.inf.DAO.ProprietarioCiaAereaDAO;
import sara.nemo.br.ufes.inf.DAO.VooDAO;
import sara.nemo.br.ufes.inf.DAO.VooNaoRegularGrupoIDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;
import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.domain.VooNaoRegularGrupoI;
import sara.nemo.br.ufes.inf.item.ItemCategoria;

public class CadVooNaoRegularGrupoI extends JFrame {
	Aeronave aeronave;
	AeronaveDAO aeronaveDAO= new AeronaveDAO();
	
	Voo voo= new Voo();
	VooDAO vooDAO= new VooDAO();
	
	CategoriaDAO categoriaDAO= new CategoriaDAO();
	
	VooNaoRegularGrupoI vooNaoRegularGrupoI= new VooNaoRegularGrupoI();
	VooNaoRegularGrupoIDAO vooNaoRegularGrupoIDAO= new VooNaoRegularGrupoIDAO();
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSigla;
	private JComboBox<String> cbxCategoria;
	private JTextField txtNumeroVooPouso;
	private JTextField txtNumeroVooDecolagem;
	private JTextField txtEquipamento;
	private JTextField txtMatricula;
	private JTextField txtOrigem;
	private JTextField txtDestino;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVooNaoRegularGrupoI frame = new CadVooNaoRegularGrupoI();
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
	public CadVooNaoRegularGrupoI() {
		setTitle("Cadastro de voo Não Regular - Grupo I");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSiglaDaCia = new JLabel("Sigla da Cia");
		lblSiglaDaCia.setBounds(36, 45, 83, 15);
		contentPane.add(lblSiglaDaCia);
		
		txtSigla = new JTextField();
		txtSigla.setColumns(10);
		txtSigla.setBounds(46, 62, 73, 19);
		contentPane.add(txtSigla);
		
		JLabel lblNumeroVoo = new JLabel("Numero do voo");
		lblNumeroVoo.setBounds(146, 25, 140, 15);
		contentPane.add(lblNumeroVoo);
		
		JLabel lblNumeroVooPouso = new JLabel("Pouso");
		lblNumeroVooPouso.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNumeroVooPouso.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroVooPouso.setBounds(137, 45, 70, 15);
		contentPane.add(lblNumeroVooPouso);
		
		txtNumeroVooPouso = new JTextField();
		txtNumeroVooPouso.setColumns(10);
		txtNumeroVooPouso.setBounds(138, 62, 78, 19);
		contentPane.add(txtNumeroVooPouso);
		
		JLabel lblNumeroVooDecolagem = new JLabel("Decolagem");
		lblNumeroVooDecolagem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNumeroVooDecolagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroVooDecolagem.setBounds(257, 45, 94, 15);
		contentPane.add(lblNumeroVooDecolagem);
		
		txtNumeroVooDecolagem = new JTextField();
		txtNumeroVooDecolagem.setColumns(10);
		txtNumeroVooDecolagem.setBounds(254, 62, 94, 19);
		contentPane.add(txtNumeroVooDecolagem);
		
		JLabel lblEquipamento = new JLabel("Equipamento");
		lblEquipamento.setBounds(369, 45, 105, 15);
		contentPane.add(lblEquipamento);
		
		txtEquipamento = new JTextField();
		txtEquipamento.setColumns(10);
		txtEquipamento.setBounds(369, 62, 96, 19);
		contentPane.add(txtEquipamento);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(39, 135, 66, 15);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(39, 150, 73, 19);
		contentPane.add(txtMatricula);
		
		JLabel lblDataPrevista = new JLabel("Data Prevista");
		lblDataPrevista.setBounds(146, 123, 96, 15);
		contentPane.add(lblDataPrevista);
		
		JLabel lblDataPrevvistaPouso = new JLabel("Pouso");
		lblDataPrevvistaPouso.setBounds(138, 135, 44, 15);
		contentPane.add(lblDataPrevvistaPouso);
		
		JFormattedTextField txtDataPrevistaPouso = new JFormattedTextField("dd/MM/aaaa");
		txtDataPrevistaPouso.setColumns(10);
		txtDataPrevistaPouso.setBounds(124, 150, 83, 19);
		contentPane.add(txtDataPrevistaPouso);
		
		JLabel lblDataPrevistaDecolagem = new JLabel("Decolagem");
		lblDataPrevistaDecolagem.setBounds(210, 135, 78, 15);
		contentPane.add(lblDataPrevistaDecolagem);
		
		JFormattedTextField txtDataPrevistaDecolagem = new JFormattedTextField("dd/MM/aaaa");
		txtDataPrevistaDecolagem.setColumns(10);
		txtDataPrevistaDecolagem.setBounds(211, 150, 84, 19);
		contentPane.add(txtDataPrevistaDecolagem);
		
		JLabel lblHorarioPrevisto = new JLabel("Horário Previso");
		lblHorarioPrevisto.setBounds(338, 123, 122, 15);
		contentPane.add(lblHorarioPrevisto);
		
		JLabel lblHorarioPrevistoPouso = new JLabel("Pouso");
		lblHorarioPrevistoPouso.setBounds(307, 135, 44, 15);
		contentPane.add(lblHorarioPrevistoPouso);
		
		JFormattedTextField txtHoraPrevistaPouso = new JFormattedTextField("HH:mm");
		txtHoraPrevistaPouso.setColumns(10);
		txtHoraPrevistaPouso.setBounds(307, 150, 64, 19);
		contentPane.add(txtHoraPrevistaPouso);
		
		JLabel lblHorarioPrevistoDecolagem = new JLabel("Decolagem");
		lblHorarioPrevistoDecolagem.setBounds(383, 135, 91, 15);
		contentPane.add(lblHorarioPrevistoDecolagem);
		
		JFormattedTextField txtHoraPrevistaDecolagem = new JFormattedTextField("HH:mm");
		txtHoraPrevistaDecolagem.setColumns(10);
		txtHoraPrevistaDecolagem.setBounds(393, 150, 77, 19);
		contentPane.add(txtHoraPrevistaDecolagem);
		
		JLabel lblOrigem = new JLabel("Origem");
		lblOrigem.setBounds(12, 180, 70, 15);
		contentPane.add(lblOrigem);
		
		txtOrigem = new JTextField();
		txtOrigem.setColumns(10);
		txtOrigem.setBounds(12, 207, 240, 19);
		contentPane.add(txtOrigem);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(272, 180, 70, 15);
		contentPane.add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setColumns(10);
		txtDestino.setBounds(272, 207, 252, 19);
		contentPane.add(txtDestino);
		
		JLabel lblSituacao = new JLabel("Situação");
		lblSituacao.setBounds(12, 238, 94, 15);
		contentPane.add(lblSituacao);
		
		JComboBox<String> cbxSituacao = new JComboBox<String>();
		cbxSituacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha um item", "ATRASADO", "CONFIRMADO", "DECOLADO", "ETAPA CONCLUIDA", "PREVISTO", "POUSADO", "PROCURE A CIA AÉREA"}));
		cbxSituacao.setBounds(12, 265, 160, 24);
		contentPane.add(cbxSituacao);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(200, 238, 70, 15);
		contentPane.add(lblCategoria);
		
		cbxCategoria = new JComboBox<String>();
		cbxCategoria.setBounds(200, 265, 398, 24);
		contentPane.add(cbxCategoria);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String matricula= txtMatricula.getText().toUpperCase();
					aeronave= aeronaveDAO.selecionarByMatricula(matricula);
					voo.setIdAeronave(aeronave.getId());
					voo.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaPouso.getText(), "dd/MM/yyyy"));
					voo.setDataPrevistaParaDecolagem(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtDataPrevistaDecolagem.getText(), "dd/MM/yyyy"));
					voo.setHoraPrevistaParaPouso(LocalTime.parse(txtHoraPrevistaPouso.getText()));
					voo.setHoraPrevistaParaDecolagem(LocalTime.parse(txtHoraPrevistaDecolagem.getText()));
					voo.setSituacao(cbxSituacao.getSelectedItem().toString().toUpperCase());
					voo.setOrigem(txtOrigem.getText().toUpperCase());
					voo.setDestino(txtDestino.getText().toUpperCase());
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
					
					vooNaoRegularGrupoI.setIdVoo(id);
					vooNaoRegularGrupoI.setNumeroVooPouso(Integer.valueOf(txtNumeroVooPouso.getText()));
					vooNaoRegularGrupoI.setNumeroVooDecolagem(Integer.valueOf(txtNumeroVooDecolagem.getText()));
					//Hora confirmada começa igual a hora prevista
					vooNaoRegularGrupoI.setHoraConfirmadaPouso(LocalTime.parse(txtHoraPrevistaPouso.getText()));
					vooNaoRegularGrupoI.setHoraConfirmadaDecolagem(LocalTime.parse(txtHoraPrevistaDecolagem.getText()));
					ProprietarioCiaAerea ProprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarBySigla(txtSigla.getText());
					vooNaoRegularGrupoI.setIdProprietarioCiaAerea(ProprietarioCiaAerea.getIdCiaAerea());

					vooNaoRegularGrupoIDAO.inserir(vooNaoRegularGrupoI);
					JOptionPane.showMessageDialog(null, "Voo Nao Regular GrupoI cadastrado!");					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnIncluir.setBounds(12, 333, 117, 25);
		contentPane.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(157, 333, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 333, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vooNaoRegularGrupoIDAO.selecionar();
			}
		});
		btnConsultar.setBounds(12, 384, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do Voo Não Regular Grupo I ha apagar: "));
				vooNaoRegularGrupoIDAO.apagar(id);
			}
		});
		btnExcluir.setBounds(157, 384, 117, 25);
		contentPane.add(btnExcluir);
		
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
