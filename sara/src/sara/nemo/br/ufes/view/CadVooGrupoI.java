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
import sara.nemo.br.ufes.inf.DAO.VooGrupoIDAO;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;
import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.domain.VooGrupoI;
import sara.nemo.br.ufes.inf.item.ItemCategoria;
import javax.swing.SpringLayout;

public class CadVooGrupoI extends JFrame {
	Aeronave aeronave;
	AeronaveDAO aeronaveDAO= new AeronaveDAO();
	
	Voo voo= new Voo();
	VooDAO vooDAO= new VooDAO();
	
	CategoriaDAO categoriaDAO= new CategoriaDAO();
	
	VooGrupoI vooGrupoI= new VooGrupoI();
	VooGrupoIDAO vooGrupoIDAO= new VooGrupoIDAO();
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSigla;
	private JTextField txtMatricula;
	private JTextField txtDataPrevistaPouso;
	private JTextField txtDataPrevistaDecolagem;
	private JTextField txtHoraPrevistaPouso;
	private JTextField txtHoraPrevistaDecolagem;
	private JTextField txtOrigem;
	private JTextField txtDestino;
	private JComboBox<String> cbxCategoria;
	private JTextField txtNumeroVooPouso;
	private JTextField txtNumeroVooDecolagem;
	private JTextField txtEquipamento;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVooGrupoI frame = new CadVooGrupoI();
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
	public CadVooGrupoI() {
		setTitle("Cadastro de Voo do Gupo I");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSigla = new JLabel("Sigla da Cia");
		
		txtSigla = new JTextField();
		txtSigla.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		
		JLabel lblDataPrevista = new JLabel("Data Prevista");
		
		JLabel lblPouso = new JLabel("Pouso");
		
		JLabel lblDecolagem = new JLabel("Decolagem");
		
		txtDataPrevistaDecolagem = new JFormattedTextField("dd/MM/aaaa");
		txtDataPrevistaDecolagem.setColumns(10);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDataPrevistaDecolagem, 152, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDataPrevistaDecolagem, 278, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDataPrevistaDecolagem, 362, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDecolagem, 137, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDecolagem, 277, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPouso, 137, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPouso, 205, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDataPrevista, 125, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDataPrevista, 213, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtMatricula, 152, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtMatricula, 106, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtMatricula, 179, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMatricula, 137, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblMatricula, 106, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtSigla, 66, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtSigla, 99, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtSigla, 172, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSigla, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSigla, 89, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		contentPane.add(lblSigla);
		contentPane.add(txtSigla);
		
		JLabel lblNumeroDoVoo = new JLabel("Numero do voo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumeroDoVoo, 29, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumeroDoVoo, 213, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumeroDoVoo, 353, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumeroDoVoo);
		
		JLabel lblNumeroVooPouso = new JLabel("Pouso");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumeroVooPouso, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumeroVooPouso, 204, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumeroVooPouso, 274, SpringLayout.WEST, contentPane);
		lblNumeroVooPouso.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroVooPouso.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblNumeroVooPouso);
		
		txtNumeroVooPouso = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtNumeroVooPouso, 66, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtNumeroVooPouso, 205, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtNumeroVooPouso, 283, SpringLayout.WEST, contentPane);
		contentPane.add(txtNumeroVooPouso);
		txtNumeroVooPouso.setColumns(10);
		
		JLabel lblNumeroVooDecolagem = new JLabel("Decolagem");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumeroVooDecolagem, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumeroVooDecolagem, 324, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumeroVooDecolagem, 418, SpringLayout.WEST, contentPane);
		lblNumeroVooDecolagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroVooDecolagem.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblNumeroVooDecolagem);
		
		txtNumeroVooDecolagem = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtNumeroVooDecolagem, 66, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtNumeroVooDecolagem, 321, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtNumeroVooDecolagem, 415, SpringLayout.WEST, contentPane);
		txtNumeroVooDecolagem.setColumns(10);
		contentPane.add(txtNumeroVooDecolagem);
		
		JLabel lblEquipamento = new JLabel("Equipamento");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEquipamento, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEquipamento, 436, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblEquipamento, 541, SpringLayout.WEST, contentPane);
		contentPane.add(lblEquipamento);
		
		txtEquipamento = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtEquipamento, 66, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtEquipamento, 436, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtEquipamento, 532, SpringLayout.WEST, contentPane);
		contentPane.add(txtEquipamento);
		txtEquipamento.setColumns(10);
		contentPane.add(lblMatricula);
		contentPane.add(txtMatricula);
		contentPane.add(lblDataPrevista);
		contentPane.add(lblPouso);
		
		txtDataPrevistaPouso = new JFormattedTextField("dd/MM/aaaa");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDataPrevistaPouso, 152, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDataPrevistaPouso, 191, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDataPrevistaPouso, 274, SpringLayout.WEST, contentPane);
		txtDataPrevistaPouso.setColumns(10);
		contentPane.add(txtDataPrevistaPouso);
		contentPane.add(lblDecolagem);
		contentPane.add(txtDataPrevistaDecolagem);
		
		JLabel lblHorario = new JLabel("Horário Previso");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHorario, 125, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHorario, 405, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblHorario, 527, SpringLayout.WEST, contentPane);
		contentPane.add(lblHorario);
		
		JLabel lblHorarioPouso = new JLabel("Pouso");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHorarioPouso, 137, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHorarioPouso, 374, SpringLayout.WEST, contentPane);
		contentPane.add(lblHorarioPouso);
		
		txtHoraPrevistaPouso = new JFormattedTextField("HH:mm");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtHoraPrevistaPouso, 152, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtHoraPrevistaPouso, 374, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtHoraPrevistaPouso, 438, SpringLayout.WEST, contentPane);
		txtHoraPrevistaPouso.setColumns(10);
		contentPane.add(txtHoraPrevistaPouso);
		
		JLabel lblHoraDecolagem = new JLabel("Decolagem");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHoraDecolagem, 137, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHoraDecolagem, 450, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblHoraDecolagem, 541, SpringLayout.WEST, contentPane);
		contentPane.add(lblHoraDecolagem);
		
		txtHoraPrevistaDecolagem = new JFormattedTextField("HH:mm");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtHoraPrevistaDecolagem, 152, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtHoraPrevistaDecolagem, 450, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtHoraPrevistaDecolagem, 527, SpringLayout.WEST, contentPane);
		txtHoraPrevistaDecolagem.setColumns(10);
		contentPane.add(txtHoraPrevistaDecolagem);
		
		JLabel lblOrigem = new JLabel("Origem");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblOrigem, 199, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblOrigem, 25, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblOrigem, 95, SpringLayout.WEST, contentPane);
		contentPane.add(lblOrigem);
		
		txtOrigem = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtOrigem, 226, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtOrigem, 25, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtOrigem, 265, SpringLayout.WEST, contentPane);
		contentPane.add(txtOrigem);
		txtOrigem.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDestino, 199, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDestino, 285, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDestino, 355, SpringLayout.WEST, contentPane);
		contentPane.add(lblDestino);
		
		txtDestino = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDestino, 226, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDestino, 285, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDestino, 537, SpringLayout.WEST, contentPane);
		contentPane.add(txtDestino);
		txtDestino.setColumns(10);
		
		JLabel lblSituacao = new JLabel("Situação");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSituacao, 257, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSituacao, 25, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSituacao, 119, SpringLayout.WEST, contentPane);
		contentPane.add(lblSituacao);
		
		JComboBox<String> cbxSituacao = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, cbxSituacao, 284, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, cbxSituacao, 25, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cbxSituacao, 185, SpringLayout.WEST, contentPane);
		cbxSituacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha um item", "ATRASADO", "CONFIRMADO", "DECOLADO", "ETAPA CONCLUIDA", "PREVISTO", "POUSADO", "PROCURE A CIA AÉREA"}));
		contentPane.add(cbxSituacao);
		
		JLabel lblCategoria = new JLabel("Categoria");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCategoria, 0, SpringLayout.NORTH, lblSituacao);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCategoria, 0, SpringLayout.WEST, lblNumeroDoVoo);
		contentPane.add(lblCategoria);
		
		cbxCategoria = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, cbxCategoria, 0, SpringLayout.NORTH, cbxSituacao);
		sl_contentPane.putConstraint(SpringLayout.WEST, cbxCategoria, 0, SpringLayout.WEST, lblNumeroDoVoo);
		sl_contentPane.putConstraint(SpringLayout.EAST, cbxCategoria, 611, SpringLayout.WEST, contentPane);
		contentPane.add(cbxCategoria);
		
		JButton btnIncluir = new JButton("Incluir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnIncluir, 330, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnIncluir, 35, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnIncluir, 152, SpringLayout.WEST, contentPane);
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
					
					vooGrupoI.setIdVoo(id);
					vooGrupoI.setIdHotran(0);
					vooGrupoI.setNumeroVooPouso(Integer.valueOf(txtNumeroVooPouso.getText()));
					vooGrupoI.setNumeroVooDecolagem(Integer.valueOf(txtNumeroVooDecolagem.getText()));
					//Hora confirmada começa igual a hora prevista
					vooGrupoI.setHoraConfirmadaPouso(LocalTime.parse(txtHoraPrevistaPouso.getText()));
					vooGrupoI.setHoraConfirmadaDecolagem(LocalTime.parse(txtHoraPrevistaDecolagem.getText()));
					ProprietarioCiaAerea ProprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarBySigla(txtSigla.getText());
					vooGrupoI.setIdProprietarioCiaAerea(ProprietarioCiaAerea.getIdCiaAerea());

					vooGrupoIDAO.inserir(vooGrupoI);
										
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		contentPane.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAlterar, 330, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAlterar, 180, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAlterar, 297, SpringLayout.WEST, contentPane);
		contentPane.add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCancelar, 330, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancelar, 349, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnCancelar, 466, SpringLayout.WEST, contentPane);
		contentPane.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnConsultar, 381, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnConsultar, 35, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnConsultar, 152, SpringLayout.WEST, contentPane);
		contentPane.add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnExcluir, 381, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnExcluir, 180, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExcluir, 297, SpringLayout.WEST, contentPane);
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
