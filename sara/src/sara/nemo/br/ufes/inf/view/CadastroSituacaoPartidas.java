package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.AcessoriosDAO;
import sara.nemo.br.ufes.inf.DAO.AcessoriosPartidaDAO;
import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoEmOcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.VooChegadaGrupoIDAO;
import sara.nemo.br.ufes.inf.DAO.VooPartidaGrupoIDAO;
import sara.nemo.br.ufes.inf.controller.ControlSituacaoPartida;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.PortaoDeEmbarque;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.RecursoEmOcorrenciaVoo;
import sara.nemo.br.ufes.inf.domain.TipoAeronave;
import sara.nemo.br.ufes.inf.domain.VooChegadaGrupoI;
import sara.nemo.br.ufes.inf.domain.VooPartidaGrupoI;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;
import sara.nemo.br.ufes.inf.view.tables.ChegadasTableModel;

public class CadastroSituacaoPartidas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtData;
	private JFormattedTextField txtHora;
	private JTextField txtVooPartida;
	private JTextField txtMatricula;
	private JTextField txtSituacao;
	private JLabel lblProcedencia;
	private JTextField txtProcedencia;
	private JLabel lblTipoVoo;
	private JTextField txtTipoVoo;
	private JLabel lblVooPartida;
	private JLabel lblDestino;
	private JLabel lblEquipamento;
	private JTextField txtVooChegada;
	private JTextField txtDestino;
	private JTextField txtEquipamento;
	private JLabel lblNomeBox;
	private JTextField txtNomeBox;
	private JTextField txtPortao;
	
	AcessoriosPartida acessoriosPartida= new AcessoriosPartida();
	TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
	AeronaveDAO aeronaveDAO= new AeronaveDAO();
	
	/**
	 * Launch the application.
	 */
	public static void showWindow(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSituacaoPartidas frame = new CadastroSituacaoPartidas(partida, table, menu);
					frame.setDados(partida, table); // Exibe na tela os dados da linha selecionada
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
	public CadastroSituacaoPartidas(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		setTitle("Situação do Voo Partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(28, 24, 53, 15);
		contentPane.add(lblData);
		
		txtData = new JFormattedTextField("dd/MM/aaaa");
		txtData.setBounds(28, 47, 104, 19);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(241, 24, 80, 15);
		contentPane.add(lblHora);
		
		txtHora = new JFormattedTextField("HH:mm");
		txtHora.setBounds(241, 47, 92, 19);
		contentPane.add(txtHora);
		txtHora.setColumns(5);
		
		lblVooPartida = new JLabel("Voo Partida");
		lblVooPartida.setBounds(385, 24, 104, 15);
		contentPane.add(lblVooPartida);
		
		txtVooPartida = new JTextField();
		txtVooPartida.setBounds(385, 51, 114, 19);
		contentPane.add(txtVooPartida);
		txtVooPartida.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(531, 28, 70, 15);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(532, 47, 92, 19);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblSituacao = new JLabel("Situação");
		lblSituacao.setBounds(28, 97, 70, 15);
		contentPane.add(lblSituacao);
		
		txtSituacao = new JTextField();
		txtSituacao.setHorizontalAlignment(SwingConstants.LEFT);
		txtSituacao.setBounds(22, 124, 114, 19);
		contentPane.add(txtSituacao);
		txtSituacao.setColumns(10);
		
		
		lblProcedencia = new JLabel("Procedencia");
		lblProcedencia.setBounds(241, 97, 104, 15);
		contentPane.add(lblProcedencia);
		
		txtProcedencia = new JTextField();
		txtProcedencia.setBounds(201, 124, 191, 19);
		contentPane.add(txtProcedencia);
		txtProcedencia.setColumns(10);
		
		lblTipoVoo = new JLabel("Tipo Voo");
		lblTipoVoo.setBounds(419, 97, 138, 17);
		contentPane.add(lblTipoVoo);
		
		txtTipoVoo = new JTextField();
		txtTipoVoo.setBounds(419, 124, 138, 19);
		contentPane.add(txtTipoVoo);
		txtTipoVoo.setColumns(10);
		
		JLabel lblVooChegada = new JLabel("Voo Chegada");
		lblVooChegada.setBounds(30, 167, 127, 15);
		contentPane.add(lblVooChegada);
		
		txtVooChegada = new JTextField();
		txtVooChegada.setBounds(28, 194, 121, 19);
		contentPane.add(txtVooChegada);
		txtVooChegada.setColumns(10);
		
		lblDestino = new JLabel("Destino");
		lblDestino.setBounds(266, 167, 79, 15);
		contentPane.add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setBounds(201, 194, 199, 19);
		contentPane.add(txtDestino);
		txtDestino.setColumns(10);
		
		lblEquipamento = new JLabel("Equipamento");
		lblEquipamento.setBounds(445, 167, 112, 15);
		contentPane.add(lblEquipamento);
		
		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(430, 194, 114, 19);
		contentPane.add(txtEquipamento);
		txtEquipamento.setColumns(10);
		
		lblNomeBox = new JLabel("Box");
		lblNomeBox.setBounds(28, 225, 104, 15);
		contentPane.add(lblNomeBox);
		
		txtNomeBox = new JTextField();
		txtNomeBox.setColumns(10);
		txtNomeBox.setBounds(28, 252, 114, 19);
		contentPane.add(txtNomeBox);
		
		JLabel lblportao = new JLabel("Portão");
		lblportao.setBounds(201, 225, 80, 25);
		contentPane.add(lblportao);
		
		txtPortao = new JTextField();
		txtPortao.setBounds(201, 252, 114, 19);
		contentPane.add(txtPortao);
		txtPortao.setColumns(10);
		
		setDados(partida, table);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcessoriosPartida acessoriosPartida= new AcessoriosPartida();
				String acao= ControlSituacaoPartida.getSelecao();
				
				acessoriosPartida= getAcessoriosPartida(table);
				acessoriosPartida.setSituacao(acao);
				txtSituacao.setText(acao);
				
				//table.setValueAt(acao, table.getSelectedRow(), 4);
				//atualizarTabela(table);
				
				if (acao.equals("CFD")) {// CONFIRMADO
					acessoriosPartida= cadastrarVoo(acessoriosPartida);
					atualizar(acessoriosPartida, table, acao);
					excluir(acessoriosPartida);
					table.clearSelection();
				}else if (acao.equals("POU")) {//POUSO
					atualizar(acessoriosPartida, table, acao);
				}else if (acao.equals("PTO")) {//AERONAVE NO PATIO
					atualizar(acessoriosPartida, table, acao);					
				}else if (acao.equals("CLD")) {//CANCELADO					
					acessoriosPartida=liberarRecursos(acessoriosPartida);
					atualizar(acessoriosPartida, table, acao);
					
				}else if (acao.equals("ETC")) {//ETAPA CONCLUIDA
					acessoriosPartida=liberarRecursos(acessoriosPartida);
					atualizar(acessoriosPartida, table, acao);
					//apagar a linha selecionada
					ChegadasTableModel model =(ChegadasTableModel)table.getModel();
					model.deleteRow(table.getSelectedRow());
					
					
				}else if (acao.equals("ALT")) {//ALTERNADO 
					acessoriosPartida=liberarRecursos(acessoriosPartida);
					atualizar(acessoriosPartida, table, acao);
					
				}else if (acao.equals("ATR")) {//ATRASADO
					acessoriosPartida=liberarRecursos(acessoriosPartida);
					atualizar(acessoriosPartida, table, acao);
					
				}else if (acao.equals("EMB")) {//EMBARQUE PROXIMO
					atualizar(acessoriosPartida, table, acao);
					
				}else if (acao.equals("EMI")) {//EMBARQUE IMEDIATO
					atualizar(acessoriosPartida, table, acao);
					
				}
				
				setVisible(false);
				
				}
		});
		
		
		
		btnOk.setBounds(264, 311, 117, 25);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(447, 311, 117, 25);
		contentPane.add(btnCancelar);
		
		
		
	}
	
	public void setDados(AcessoriosPartida partida, JTable table) {
		int linha= table.getSelectedRow();
		
		String[] dataHoraSeparado = (table.getValueAt(table.getSelectedRow(), 1).toString()).split(" ");
		
		txtData.setText(dataHoraSeparado[0]);
		txtHora.setText(dataHoraSeparado[1]);
		txtVooPartida.setText(table.getValueAt(linha, 2).toString());
		txtMatricula.setText(table.getValueAt(linha, 3).toString());
		txtSituacao.setText(table.getValueAt(linha, 4).toString());
		txtSituacao.setEditable(false);
		txtDestino.setText(table.getValueAt(linha, 5).toString());
		txtTipoVoo.setText(table.getValueAt(linha, 6).toString());
		txtVooChegada.setText(table.getValueAt(linha, 7).toString());
		txtProcedencia.setText(table.getValueAt(linha, 8).toString());
		txtEquipamento.setText(table.getValueAt(linha, 9).toString());
		try {
			txtNomeBox.setText(table.getValueAt(linha, 10).toString());
		}catch (NullPointerException e) {
			txtNomeBox.setText("00");
		}
		txtPortao.setText(table.getValueAt(linha, 11).toString());
		
	}
	
	public AcessoriosPartida getAcessoriosPartida(JTable table) {
		//acessoriosPartida.setNomeCabeceira((txtCabeceira.getText()));
		acessoriosPartida.setDataHoraAtualizada(txtData.getText()+" "+ txtHora.getText());
		acessoriosPartida.setDataHoraPrevista(table.getValueAt(table.getSelectedRow(), 1).toString());
		acessoriosPartida.setDestino(txtDestino.getText());
		String equipamento= aeronaveDAO.selecionarEquipamento(txtMatricula.getText().toUpperCase());
		
		acessoriosPartida.setEquipamento(equipamento);
		acessoriosPartida.setMatricula(txtMatricula.getText().toUpperCase());
		acessoriosPartida.setProcedencia(txtProcedencia.getText());
		acessoriosPartida.setPortao(txtPortao.getText());
		acessoriosPartida.setSituacao(txtSituacao.getText());
		acessoriosPartida.setTipo(txtTipoVoo.getText());
		acessoriosPartida.setVooChegada(txtVooChegada.getText());
		acessoriosPartida.setVooPartida(txtVooPartida.getText());
		acessoriosPartida.setNomeBox(txtNomeBox.getText());
		acessoriosPartida.setIdHotran(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 12).toString()));
		acessoriosPartida.setIdAcessoriosPartida(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 13).toString()));
		return acessoriosPartida;
		
	}
	public void atualizarTabela(JTable table) {
		String dataHoraAtualizada= txtData.getText()+" "+ txtHora.getText();
		table.setVisible(false);
		
		table.setValueAt(dataHoraAtualizada, table.getSelectedRow(), 1);
		table.setValueAt(txtVooPartida.getText(), table.getSelectedRow(), 2);
		
		table.setValueAt(txtMatricula.getText().toUpperCase(), table.getSelectedRow(), 3);
		table.setValueAt(txtDestino.getText().toUpperCase(), table.getSelectedRow(), 5);
		table.setValueAt(txtTipoVoo.getText(), table.getSelectedRow(), 6);
		table.setValueAt(txtVooChegada.getText(), table.getSelectedRow(), 7);
		table.setValueAt(txtProcedencia.getText(), table.getSelectedRow(), 8);
		table.setValueAt(txtEquipamento.getText().toUpperCase(), table.getSelectedRow(), 9);
		table.setValueAt(txtNomeBox.getText(), table.getSelectedRow(), 10);
		table.setValueAt(txtPortao.getText(), table.getSelectedRow(), 11);
		table.setVisible(true);
	}
	
	public void atualizarTabela(JTable table, AcessoriosPartida acessoriosPartida ) {
		table.setVisible(false);
		
		table.setValueAt(acessoriosPartida.getDataHoraAtualizada(), table.getSelectedRow(), 1);
		table.setValueAt(acessoriosPartida.getVooPartida(), table.getSelectedRow(), 2);
		
		table.setValueAt(acessoriosPartida.getMatricula().toUpperCase(), table.getSelectedRow(), 3);
		table.setValueAt(acessoriosPartida.getSituacao().toUpperCase(), table.getSelectedRow(), 4);
		table.setValueAt(acessoriosPartida.getDestino().toUpperCase(), table.getSelectedRow(), 5);
		table.setValueAt(acessoriosPartida.getTipo(), table.getSelectedRow(), 6);
		table.setValueAt(acessoriosPartida.getVooChegada(), table.getSelectedRow(), 7);
		table.setValueAt(acessoriosPartida.getProcedencia(), table.getSelectedRow(), 8);
		table.setValueAt(acessoriosPartida.getEquipamento().toUpperCase(), table.getSelectedRow(), 9);
		table.setValueAt(acessoriosPartida.getNomeBox(), table.getSelectedRow(), 10);
		table.setValueAt(acessoriosPartida.getPortao(), table.getSelectedRow(), 11);
		if (acessoriosPartida.getIdHotran()!=0) table.setValueAt(acessoriosPartida.getIdHotran(), table.getSelectedRow(), 12);
		if (acessoriosPartida.getIdAcessorios()!=0) table.setValueAt(acessoriosPartida.getIdAcessoriosPartida(), table.getSelectedRow(), 13);
		table.setVisible(true);
	}
	
	public AcessoriosPartida cadastrarVoo(AcessoriosPartida acessoriosPartida) {
			
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
		VooChegadaGrupoI vooChegadaGrupoI= null;
		
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
		VooPartidaGrupoI vooPartidaGrupoI= new VooPartidaGrupoI();
		VooPartidaGrupoIDAO vooPartidaGrupoIDAO= new VooPartidaGrupoIDAO();
		
		LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista());
		Date dataPrevista= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate());
		String matr= acessoriosPartida.getMatricula();
		while (matr.length()< 5) {
			matr= JOptionPane.showInputDialog("Informe Matricula da aeronave: ");
		}
		
		Aeronave aeronave= aeronaveDAO.selecionarAeronaveByMatricula(matr);
		
		if (aeronave != null) {
			
			int idTipoAeronave= aeronave.getIdTipoAeronave();
			TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
			TipoAeronave tipoAeronave= tipoAeronaveDAO.selecionarTipoAeronave(idTipoAeronave);
			acessoriosPartida.setEquipamento(tipoAeronave.getEquipamento());
			acessoriosPartida.setMatricula(aeronave.getMatricula());

			vooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarVooChegadaGrupoIByDataPrevistaParaPousoAndIdAeronave(aeronave.getIdAeronave(), dataPrevista);
			
			
			if(vooChegadaGrupoI!= null) {
				System.out.println("Voo Chagada: "+vooChegadaGrupoI.getNumeroVooPouso());
				CategoriaDAO categoriaDAO= new CategoriaDAO();
				int id= categoriaDAO.selecionarIdCategoria("Domestico", "Passageiro", "Regular", "Regular");
				acessoriosPartida= alocaRecursosPartida(acessoriosPartida, vooChegadaGrupoI.getIdProprietarioCiaAerea());
				
				vooPartidaGrupoI.setDataConfirmadaDecolagem(Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraAtualizada()).toLocalDate());
				vooPartidaGrupoI.setDataPrevistaParaDecolagem(Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista()).toLocalDate());
				vooPartidaGrupoI.setDestino(acessoriosPartida.getDestino());
				vooPartidaGrupoI.setHoraConfirmadaDecolagem(Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraAtualizada()).toLocalTime());
				vooPartidaGrupoI.setHoraPrevistaParaDecolagem(Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista()).toLocalTime());
				vooPartidaGrupoI.setHotran_idHotran(acessoriosPartida.getIdHotran());
				vooPartidaGrupoI.setIdVooPartidaGrupoI(vooChegadaGrupoI.getIdVooChegadaGrupoI());
				String []vooPartida= (acessoriosPartida.getVooPartida().split(" "));
				vooPartidaGrupoI.setNumeroVooDecolagem(Integer.parseInt(vooPartida[1]));
				
				System.out.println("Box: "+vooChegadaGrupoI.getNomeBox()+" Portao: "+acessoriosPartida.getPortao());
				acessoriosPartida.setNomeBox(vooChegadaGrupoI.getNomeBox());
								
				vooPartidaGrupoI.setPortaoDeEmbarque(acessoriosPartida.getPortao());
				vooPartidaGrupoI.setSituacaoPartida(acessoriosPartida.getSituacao());
				vooPartidaGrupoI.setIdCategoria(id);
				vooPartidaGrupoI.setIdVoo(vooChegadaGrupoI.getIdVoo());
							
				try {
					vooPartidaGrupoIDAO.inserir(vooPartidaGrupoI);
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Falha no Cadastro :Voo invalido!"+e.getMessage());
		
				}
			}else System.out.println("O voo chegada não foi localizado na base de dados");
		}
		return acessoriosPartida;
	}
	
	 public void setRecursos(AcessoriosPartida partida) {// Exibe os recursos no formulario
		txtNomeBox.setText(partida.getNomeBox());
		txtPortao.setText(partida.getPortao());
		
	 }
	 
	 public AcessoriosPartida alocaRecursosPartida(AcessoriosPartida acessoriosPartida, int idProprietario) {
						
			PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
			PortaoDeEmbarque portaoDeEmbarque= new PortaoDeEmbarque();
			
			RecursoDAO recursoDAO = new RecursoDAO();
			OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
			
			VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
			int idVoo= acessoriosPartida.getIdVoo();
			
			if(idVoo> 0) {
				VooChegadaGrupoI VooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarById(idVoo);
				acessoriosPartida.setNomeBox(VooChegadaGrupoI.getNomeBox());
				
			}
			portaoDeEmbarque= portaoDeEmbarqueDAO.selecionarPortaoDeEmbarque(idProprietario);
			
			if (portaoDeEmbarque != null) {
				acessoriosPartida.setPortao(portaoDeEmbarque.getNome());
				
				try {
					recursoDAO.alterarEstaEmUso(true, portaoDeEmbarque.getIdPortaoDeEmbarque());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else acessoriosPartida.setPortao("00"); 
			
			
			// ACERTAR TRECHO
			RecursoEmOcorrenciaVooDAO recursoEmOcorrenciaVooDAO=new RecursoEmOcorrenciaVooDAO();
			RecursoEmOcorrenciaVoo recursoEmOcorrenciaVoo= new RecursoEmOcorrenciaVoo();
			
			int idOcorrenciaVoo= ocorrenciaVooDAO.selecionarMaximoID();
			recursoEmOcorrenciaVoo.setIdRecurso(portaoDeEmbarque.getIdPortaoDeEmbarque());
			recursoEmOcorrenciaVoo.setIdOcorrenciaVoo(idOcorrenciaVoo);
			
			try {
				System.out.println("Cadastrando idRecurso= "+recursoEmOcorrenciaVoo.getIdRecurso()+" idOcorrenciaVoo= "+idOcorrenciaVoo);
				recursoEmOcorrenciaVooDAO.inserir(recursoEmOcorrenciaVoo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return acessoriosPartida;
		}
	 
	 
		public void liberarPosicaoPatio(String nomeBox) {
			PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
			PosicaoPatio posicaoPatio= posicaoPatioDAO.selecionarPosicaoPatioByNome(nomeBox);
			RecursoDAO recursoDAO= new RecursoDAO();
			try {
				if(posicaoPatio!= null) recursoDAO.alterarEstaEmUso(false, posicaoPatio.getIdRecurso());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
		public void liberarPortaoDeEmbarque(String portao) {
			PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
			RecursoDAO recursoDAO= new RecursoDAO();
			PortaoDeEmbarque portaoDeEmbarque= null;
			if ((portao.length() > 0) &&(portao!= "00")) {
				portaoDeEmbarque= portaoDeEmbarqueDAO.selecionarPortaoDeEmbarqueByNome(portao);
			
				try {
					if(portaoDeEmbarque!= null) recursoDAO.alterarEstaEmUso(false, portaoDeEmbarque.getIdRecurso());
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	 public AcessoriosPartida liberarRecursos(AcessoriosPartida acessoriosPartida) {
			if(acessoriosPartida.getNomeBox().length()> 0) liberarPosicaoPatio(acessoriosPartida.getNomeBox()); 
			liberarPortaoDeEmbarque(acessoriosPartida.getPortao());
			acessoriosPartida.setNomeBox("00");
			acessoriosPartida.setPortao("00");
			
			return acessoriosPartida;
		} 
	 
	 public void atualizarVoo(AcessoriosPartida acessoriosPartida, JTable table, String acao){
		 AcessoriosPartidaDAO acessoriosPartidaDAO= new AcessoriosPartidaDAO();
		 
			String[] vooPartida= acessoriosPartida.getVooPartida().split(" ");
			
			int numeroVooPartida= Integer.parseInt(vooPartida[1]);
			
			LocalDateTime localDateTime= Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista());
			VooPartidaGrupoIDAO vooPartidaGrupoIDAO= new VooPartidaGrupoIDAO();
			
			VooPartidaGrupoI vooPartidaGrupoI= vooPartidaGrupoIDAO.selecionarVooGrupoIPartida(Converte.converterLocalDateToJavaSqlDate(localDateTime.toLocalDate()), numeroVooPartida);
			if(vooPartidaGrupoI != null) {
				
				try {
					vooPartidaGrupoI.setSituacaoPartida(acao);
					vooPartidaGrupoIDAO.alterar(vooPartidaGrupoI);
				} catch (NullPointerException np) {
					JOptionPane.showMessageDialog(null, "O voo Partida do  grupo I não foi atualizado no sistema ");
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if((acao.equals("CLD")) ||acao.equals("ATR")||(acao.equals("ALT"))) {
					vooPartidaGrupoI.setPortaoDeEmbarque("00");
					
						try {
							vooPartidaGrupoIDAO.alterar(vooPartidaGrupoI);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						
						LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista());
						Date dataPouso= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate());
						AcessoriosPartida aux= acessoriosPartidaDAO.selecionar(dataPouso, acessoriosPartida.getVooPartida());
						acessoriosPartida= liberarRecursos(acessoriosPartida);
						acessoriosPartida.setSituacao(acao);
						if (aux!= null) {
							aux.setDataHoraAtualizada(acessoriosPartida.getDataHoraAtualizada());
							aux.setDataHoraPrevista(acessoriosPartida.getDataHoraPrevista());
							aux.setDestino(acessoriosPartida.getDestino());
							aux.setEquipamento(acessoriosPartida.getEquipamento());
							aux.setIdHotran(acessoriosPartida.getIdHotran());
							aux.setMatricula(acessoriosPartida.getMatricula());
							aux.setNomeBox(acessoriosPartida.getNomeBox());
							aux.setPortao(acessoriosPartida.getPortao());
							aux.setProcedencia(acessoriosPartida.getProcedencia());
							aux.setSituacao(acessoriosPartida.getSituacao());
							aux.setTipo(acessoriosPartida.getTipo());
							aux.setVooChegada(acessoriosPartida.getVooChegada());
							aux.setVooPartida(acessoriosPartida.getVooPartida());
							
							
							try {
								acessoriosPartidaDAO.alterar(aux);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				}else {
				
				LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosPartida.getDataHoraPrevista());
				
				//Ao converter LocalDate para java.sql.date é necessario somar 1 dia ao LocalDate usando .plusDays(1)
				//Trata-se de um bug do sistema
				
				Date dataPouso= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate().minusDays(1));
				AcessoriosPartida aux= acessoriosPartidaDAO.selecionar(dataPouso, acessoriosPartida.getVooPartida());
				acessoriosPartida= liberarRecursos(acessoriosPartida);
				acessoriosPartida.setSituacao(acao);
				if (aux != null) {
					aux.setDataHoraAtualizada(acessoriosPartida.getDataHoraAtualizada());
					aux.setDataHoraPrevista(acessoriosPartida.getDataHoraPrevista());
					aux.setDestino(acessoriosPartida.getDestino());
					aux.setEquipamento(acessoriosPartida.getEquipamento());
					aux.setIdHotran(acessoriosPartida.getIdHotran());
					aux.setMatricula(acessoriosPartida.getMatricula());
					aux.setNomeBox(acessoriosPartida.getNomeBox());
					aux.setProcedencia(acessoriosPartida.getProcedencia());
					aux.setSituacao(acessoriosPartida.getSituacao());
					aux.setTipo(acessoriosPartida.getTipo());
					aux.setVooPartida(acessoriosPartida.getVooPartida());
					
					
					try {
						acessoriosPartidaDAO.alterar(aux);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	
	public void atualizar(AcessoriosPartida acessoriosPartida, JTable table, String acao) {
		if (acessoriosPartida!= null) {
			setRecursos(acessoriosPartida);
			if (acessoriosPartida.getSituacao()== "CFD") {
				atualizarTabela(table, acessoriosPartida);
			}else atualizarTabela(table);
			
			atualizarVoo(acessoriosPartida, table, acao);
		}
	}
	
	public void excluir(AcessoriosPartida acessoriosPartida) {
		AcessoriosDAO acessoriosDAO= new AcessoriosDAO();
		AcessoriosPartidaDAO acessoriosPartidaDAO= new AcessoriosPartidaDAO();
		int id= acessoriosPartida.getIdAcessoriosPartida();
		
		acessoriosPartidaDAO.apagar(id);
		acessoriosDAO.apagar(id);
	}
}
