package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.AcessoriosChegadaDAO;
import sara.nemo.br.ufes.inf.DAO.AeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoDAO;
import sara.nemo.br.ufes.inf.DAO.VooChegadaGrupoIDAO;
import sara.nemo.br.ufes.inf.DAO.VooDAO;
import sara.nemo.br.ufes.inf.controller.ControlSituacaoChegada;
import sara.nemo.br.ufes.inf.domain.Aeronave;
import sara.nemo.br.ufes.inf.domain.Esteira;
import sara.nemo.br.ufes.inf.domain.OcorrenciaVoo;
import sara.nemo.br.ufes.inf.domain.Pista;
import sara.nemo.br.ufes.inf.domain.PosicaoPatio;
import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.domain.VooChegadaGrupoI;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;
import sara.nemo.br.ufes.inf.view.tables.ChegadasTableModel;

public class CadastroSituacaoChegadas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtData;
	private JFormattedTextField txtHora;
	private JTextField txtVoo;
	private JTextField txtMatricula;
	private JTextField txtSituacao;
	private JLabel lblProcedencia;
	private JTextField txtProcedencia;
	private JLabel lblTipoVoo;
	private JTextField txtTipoVoo;
	private JLabel lblVooPartida;
	private JLabel lblDestino;
	private JLabel lblEquipamento;
	private JTextField txtVooPartida;
	private JTextField txtDestino;
	private JTextField txtEquipamento;
	private JLabel lblCabeceira;
	private JTextField txtCabeceira;
	private JLabel lblBox;
	private JTextField txtBox;
	private JLabel lblEsteira;
	private JTextField txtEsteira;
	
	AcessoriosChegada acessoriosChegada= new AcessoriosChegada();
	String acao= ControlSituacaoChegada.getSelecao();
	/**
	 * Launch the application.
	 */
	public static void showWindow(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSituacaoChegadas frame = new CadastroSituacaoChegadas(chegada, table, menu);
					frame.setDados(table); // Exibe na tela os dados da linha selecionada
					if((chegada.getNomeCabeceira()!= null) && (chegada.getNomeBox()!= null)&&(chegada.getNomeEsteira()!=null)){
						frame.setRecursos(chegada);
					}

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
	public CadastroSituacaoChegadas() {
		
	};
	
	public CadastroSituacaoChegadas(AcessoriosChegada chegada, JTable table, JPopupMenu menu) {
		setTitle("Situação do Voo");
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
		
		JLabel lblVooChegada = new JLabel("Voo Chegada");
		lblVooChegada.setBounds(378, 24, 127, 15);
		contentPane.add(lblVooChegada);
		
		txtVoo = new JTextField();
		txtVoo.setBounds(384, 47, 121, 19);
		contentPane.add(txtVoo);
		txtVoo.setColumns(10);
		
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
		
		lblVooPartida = new JLabel("Voo Partida");
		lblVooPartida.setBounds(22, 167, 104, 15);
		contentPane.add(lblVooPartida);
		
		txtVooPartida = new JTextField();
		txtVooPartida.setBounds(22, 194, 114, 19);
		contentPane.add(txtVooPartida);
		txtVooPartida.setColumns(10);
		
		lblDestino = new JLabel("Destino");
		lblDestino.setBounds(264, 167, 79, 15);
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
		
		lblCabeceira = new JLabel("Cabeceira");
		lblCabeceira.setBounds(28, 225, 104, 15);
		contentPane.add(lblCabeceira);
		
		txtCabeceira = new JTextField();
		txtCabeceira.setColumns(10);
		txtCabeceira.setBounds(28, 252, 114, 19);
		contentPane.add(txtCabeceira);
		
		lblBox = new JLabel("Box");
		lblBox.setBounds(272, 225, 79, 15);
		contentPane.add(lblBox);
		
		txtBox = new JTextField();
		txtBox.setColumns(10);
		txtBox.setBounds(257, 252, 80, 19);
		contentPane.add(txtBox);
		
		lblEsteira = new JLabel("Esteira");
		lblEsteira.setBounds(451, 225, 112, 15);
		contentPane.add(lblEsteira);
		
		txtEsteira = new JTextField();
		txtEsteira.setColumns(10);
		txtEsteira.setBounds(436, 252, 114, 19);
		contentPane.add(txtEsteira);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcessoriosChegada acessoriosChegada= new AcessoriosChegada();
				table.setValueAt(acao, table.getSelectedRow(), 4);

				acessoriosChegada= getAcessoriosChegada(table);
				acessoriosChegada.setSituacao(acao);
				txtSituacao.setText(acao);
			
				if (acao.equals("CFD")) {// CONFIRMADO
					acessoriosChegada= cadastrarVoo(acessoriosChegada);
					atualizar(acessoriosChegada, table, acao);
					table.clearSelection();
					apagarAcessoriosChegada(acessoriosChegada);
				}else if (acao.equals("POU")) {//POUSO
					atualizar(acessoriosChegada, table, acao);
					alterarVooChegadaGrupoI(acessoriosChegada); //Atualiza informacoes do voo no Banco de dados
					table.clearSelection();
				}else if (acao.equals("PTO")) {//AERONAVE NO PATIO
					atualizar(acessoriosChegada, table, acao);					
				}else if (acao.equals("CLD")) {//CANCELADO					
					acessoriosChegada=liberarRecursos(acessoriosChegada);
					atualizar(acessoriosChegada, table, acao);
					
				}else if (acao.equals("ETC")) {//ETAPA CONCLUIDA
					acessoriosChegada=liberarRecursos(acessoriosChegada);
					atualizar(acessoriosChegada, table, acao);
					//apagar a linha selecionada
					ChegadasTableModel model =(ChegadasTableModel)table.getModel();
					model.deleteRow(table.getSelectedRow());
					
					
				}else if (acao.equals("ALT")) {//ALTERNADO 
					acessoriosChegada=liberarRecursos(acessoriosChegada);
					atualizar(acessoriosChegada, table, acao);
					
				}else if (acao.equals("ATR")) {//ATRASADO
					acessoriosChegada=liberarRecursos(acessoriosChegada);
					atualizar(acessoriosChegada, table, acao);
					
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
	
	public void setDados(JTable table) { // Insere os dados da tabela no formulario
		int linha= table.getSelectedRow();
		String[] dataHoraSeparado = (table.getValueAt(table.getSelectedRow(), 1).toString()).split(" ");
		
		txtData.setText(dataHoraSeparado[0]);
		txtHora.setText(dataHoraSeparado[1]);
		txtVoo.setText(table.getValueAt(linha, 2).toString());
		txtMatricula.setText(table.getValueAt(linha, 3).toString().toUpperCase());
		txtSituacao.setText(acao);
		
		txtSituacao.setEditable(false); //Bloqueado para edicao
		txtProcedencia.setText(table.getValueAt(linha, 5).toString());
		txtTipoVoo.setText(table.getValueAt(linha, 6).toString());
		txtVooPartida.setText(table.getValueAt(linha, 7).toString());
		txtDestino.setText(table.getValueAt(linha, 8).toString());
		
		try {
			txtEquipamento.setText(table.getValueAt(linha, 9).toString());
		}catch (NullPointerException e) {
			txtEquipamento.setText("");
		}
		
		txtCabeceira.setText(table.getValueAt(linha, 10).toString());
		txtBox.setText(table.getValueAt(linha, 11).toString());
		txtEsteira.setText(table.getValueAt(linha, 12).toString());
		
	}
	
	public AcessoriosChegada getAcessoriosChegada(JTable table) { //Lê os dados do Formulario
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
		acessoriosChegada= acessoriosChegada.valoresDaLinhaSelecionada(table);
		acessoriosChegada.setDataHoraAtualizada(txtData.getText()+" "+txtHora.getText());
		acessoriosChegada.setDestino(txtDestino.getText());
		String matricula= txtMatricula.getText().toUpperCase();
		if (matricula!= null) {
			acessoriosChegada.setEquipamento(aeronaveDAO.selecionarEquipamento(matricula));
			
		}

		acessoriosChegada.setMatricula(matricula);
		
		acessoriosChegada.setProcedencia(txtProcedencia.getText());
		acessoriosChegada.setSituacao(txtSituacao.getText());
		acessoriosChegada.setTipo(txtTipoVoo.getText());
		acessoriosChegada.setVooChegada(txtVoo.getText());
		acessoriosChegada.setVooPartida(txtVooPartida.getText());
		
		if (txtCabeceira.getText()!= null) acessoriosChegada.setNomeCabeceira(txtCabeceira.getText());
		if(txtBox.getText()!= null) acessoriosChegada.setNomeBox(txtBox.getText());
		if(txtEsteira.getText()!= null) acessoriosChegada.setNomeEsteira(txtEsteira.getText());
		
		
		//acessoriosChegada.setIdHotran(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 13).toString())); // Pegar valor da tabela
		return acessoriosChegada;
	}
	
	public void atualizarTabela(JTable table) {
		String dataHoraAtualizada= txtData.getText()+" "+ txtHora.getText();
		table.setVisible(false);
		
		table.setValueAt(dataHoraAtualizada, table.getSelectedRow(), 1);		
		table.setValueAt(txtVoo.getText(), table.getSelectedRow(), 2);
		table.setValueAt(txtMatricula.getText().toUpperCase(), table.getSelectedRow(), 3);
		
		table.setValueAt(txtProcedencia.getText(), table.getSelectedRow(), 5);
		table.setValueAt(txtTipoVoo.getText(), table.getSelectedRow(), 6);
		table.setValueAt(txtVooPartida.getText(), table.getSelectedRow(), 7);
		table.setValueAt(txtDestino.getText(), table.getSelectedRow(), 8);
		table.setValueAt(txtEquipamento.getText(), table.getSelectedRow(), 9);
		table.setValueAt(txtCabeceira.getText(), table.getSelectedRow(), 10);
		table.setValueAt(txtBox.getText(), table.getSelectedRow(), 11);
		table.setValueAt(txtEsteira.getText(), table.getSelectedRow(), 12);
		
		setRecursos(table);
		table.setVisible(true);
	}
	
	public AcessoriosChegada cadastrarVoo(AcessoriosChegada acessoriosChegada) {
		
		AeronaveDAO aeronaveDAO= new AeronaveDAO();
		CategoriaDAO  categoriaDAO= new CategoriaDAO();
		VooChegadaGrupoI vooChegadaGrupoI= new VooChegadaGrupoI();
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();

		VooDAO vooDAO= new VooDAO();
		Voo voo= new Voo();
		Aeronave aero;

		int id=0;
		int idVoo = 0;
		int idProprietario= 0;
		//verificar se o voo já esta cadastrado: Cadastrar Apenas voo não cadastrado
		String[] data= acessoriosChegada.getDataHoraPrevista().split(" ");
		String []numeroVoo= acessoriosChegada.getVooChegada().split(" ");
		LocalDate dataPrevista=Converte.converteStringToLocalDateNoFormatoDDMMAAAA(data[0], "dd/MM/yyyy");
		System.out.println("Data Prevista: "+ Converte.converterLocalDateToJavaSqlDate(dataPrevista.minusDays(1)));

		VooChegadaGrupoI vooDado= vooChegadaGrupoIDAO.selecionarVooGrupoIPouso(Converte.converterLocalDateToJavaSqlDate(dataPrevista), Integer.parseInt(numeroVoo[1]));
		
		String matricula= acessoriosChegada.getMatricula();

		while (matricula.length() < 5) {
			matricula= JOptionPane.showInputDialog("Informe Matricula da aeronave: ");
			if (matricula != null) {
				acessoriosChegada.setMatricula(matricula);
				txtMatricula.setText(matricula);
			}
		}

		if(vooDado==null) {// O voo ainda não esta cadastrado
			aero= aeronaveDAO.selecionarAeronaveByMatricula(matricula);
			System.out.println("Cadastranndo voo Matricula: "+matricula);
			if (aero!= null) {
				idProprietario= aero.getIdProprietario();
				if ((idProprietario>0 ) &&(acessoriosChegada.getSituacao() ==("CFD"))) {
					acessoriosChegada= alocaRecursosChegada(acessoriosChegada, idProprietario);
				} 
				try {
					voo.setIdAeronave(aero.getIdAeronave());
				}catch(NullPointerException e){
					System.out.println("Nenhuma aeronave com esta matricula foi cadastrada "+ e.getMessage());
				}
			}else return acessoriosChegada;

			try {
				id= categoriaDAO.selecionarIdCategoria("Domestico", "Passageiro", "Regular", "Regular");
				voo.setIdCategoria(id);
				voo.setNomeBox(acessoriosChegada.getNomeBox());
			}catch(NullPointerException e1) {
				System.out.println("Categoria de voo não cadastrada");
			}

			try {
				vooDAO.inserir(voo);
				idVoo= vooDAO.selecionarMaximoID();
				if (idVoo >0 ) {
					cadastrarOcorrenciaDeVoo(acessoriosChegada, idVoo, voo.getIdAeronave());
				}else {
					System.out.println("Erro ao Cadastrar Ocoorencia de Voo, id do Voo invalido = "+idVoo);
				}
			}catch(Exception e2) {
				System.out.println("O cadastro do voo não foi realizado "+ e2.getMessage());
			}
			
			
			if (idVoo != 0) {
				
				vooChegadaGrupoI.setIdVooChegadaGrupoI(idVoo);
				vooChegadaGrupoI.setIdHotran(acessoriosChegada.getIdHotran());
				String[] vooPouso= acessoriosChegada.getVooChegada().split(" ");
				vooChegadaGrupoI.setNumeroVooPouso(Integer.valueOf(vooPouso[1]));

				String[] dataHora= acessoriosChegada.getDataHoraAtualizada().split(" ");
			
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
				vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
				dataHora= acessoriosChegada.getDataHoraPrevista().split(" ");
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
				vooChegadaGrupoI.setSituacao(acessoriosChegada.getSituacao());
				vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
				
				if (acessoriosChegada.getSituacao().equals("CFD")) {
					
					vooChegadaGrupoI.setNomeCabeceira(Integer.parseInt(acessoriosChegada.getNomeCabeceira()));
					vooChegadaGrupoI.setNomeEsteira(acessoriosChegada.getNomeEsteira());
					try {

						if (idProprietario>0) vooChegadaGrupoI.setIdProprietarioCiaAerea(idProprietario);
							
						vooChegadaGrupoIDAO.inserir(vooChegadaGrupoI);
						
					}catch(Exception e3) {
						System.out.println("Impossivel cadastrar Voo GrupoI "+ e3.getMessage());
					}
				}else {
					vooChegadaGrupoI.setNomeCabeceira(00);
					vooChegadaGrupoI.setNomeBox("00");
					vooChegadaGrupoI.setNomeEsteira("00");
					try {
						vooDAO.alterar(vooChegadaGrupoI);
						vooChegadaGrupoIDAO.alterar(vooChegadaGrupoI);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


				
			
			}
		
		}
		
		return acessoriosChegada;
	}
	
	
	
	
	public void cadastrarOcorrenciaDeVoo(AcessoriosChegada acessoriosChegada, int idDoVoo, int idAeronave) {
		OcorrenciaVoo ocorrenciaVoo= new OcorrenciaVoo();
		OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
		
		ocorrenciaVoo.setData(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraAtualizada()).toLocalDate());
		ocorrenciaVoo.setHora(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraAtualizada()).toLocalTime());
		ocorrenciaVoo.setIdAeronave(idAeronave);
		ocorrenciaVoo.setIdVoo(idDoVoo);
		ocorrenciaVoo.setSituacao(acessoriosChegada.getSituacao());
		
		try {
			ocorrenciaVooDAO.inserir(ocorrenciaVoo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public AcessoriosChegada alocaRecursosChegada(AcessoriosChegada acessoriosChegada, int idProprietario) {
		PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
		PosicaoPatio posicaoPatio= new PosicaoPatio();
		
		PistaDAO pistaDAO= new PistaDAO();
		Pista pista= new Pista();

		EsteiraDAO esteiraDAO= new EsteiraDAO();
		Esteira esteira= new Esteira();

		RecursoDAO recursoDAO = new RecursoDAO();
		
		posicaoPatio=posicaoPatioDAO.selecionarPosicaoPatio(idProprietario);
		
		try {
			recursoDAO.alterarEstaEmUso(true, posicaoPatio.getIdPosicaoPatio());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (posicaoPatio!= null) {
			acessoriosChegada.setNomeBox(posicaoPatio.getNome());
			posicaoPatio.setEstaEmUso(true);
		}else acessoriosChegada.setNomeBox("00");
		
		pista=pistaDAO.selecionarPistaEmUso();
		if (pista!= null) {						
			acessoriosChegada.setNomeCabeceira(pista.getNomeCabeceira());
		}else acessoriosChegada.setNomeCabeceira("00");
		
		esteira=esteiraDAO.selecionarEsteira(idProprietario);
		if(esteira!= null) {
			acessoriosChegada.setNomeEsteira(esteira.getNome());
			esteira.setEstaEmUso(true);
		}else acessoriosChegada.setNomeEsteira("00");
		
		
		try {
			if (esteira.getNome()!= "00")recursoDAO.alterar(esteira);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return acessoriosChegada;
	}

	public void liberarCabeceira(String cabeceira) {
		PistaDAO pistaDAO= new PistaDAO();
		int idPista= pistaDAO.selecionarIdDadoNome(cabeceira);
		RecursoDAO recursoDAO= new RecursoDAO();
		Pista pista= pistaDAO.selecionarById(idPista);
		try {
			if(pista!= null) recursoDAO.alterarEstaEmUso(false, pista.getIdRecurso());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void liberarPosicaoPatio(String nomeBox) {
		PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
		PosicaoPatio posicaoPatio= posicaoPatioDAO.selecionarPosicaoPatioByNome(nomeBox);
		RecursoDAO recursoDAO= new RecursoDAO();
		try {
			if(posicaoPatio!= null) {
				
				recursoDAO.alterarEstaEmUso(false, posicaoPatio.getIdRecurso());
			} 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void liberarEsteira(String nomeEsteira) {
		EsteiraDAO esteiraDAO= new EsteiraDAO();
		Esteira esteira= esteiraDAO.selecionarByNomeDaEsteira(nomeEsteira);
		RecursoDAO recursoDAO= new RecursoDAO();
		try {
			if(esteira!= null) recursoDAO.alterarEstaEmUso(false, esteira.getIdRecurso());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void setRecursos(AcessoriosChegada chegada) {// Exibe os recursos no formulario
		 
		txtCabeceira.setText(chegada.getNomeCabeceira());
		txtBox.setText(chegada.getNomeBox());
		txtEsteira.setText(chegada.getNomeEsteira());
	 }
	 
	public void setRecursos(JTable table){ //Exibe os recursos na tabela
		if (txtCabeceira.getText()!= null) table.setValueAt(txtCabeceira.getText(), table.getSelectedRow(), 10);
		if(txtBox.getText()!= null)table.setValueAt(txtBox.getText(), table.getSelectedRow(), 11);
		if(txtEsteira.getText()!= null)table.setValueAt(txtEsteira.getText(), table.getSelectedRow(), 12);
		
	}
	
	
	public void atualizarVoo(AcessoriosChegada acessoriosChegada, JTable table, String acao){
		
		String[] vooChegada= acessoriosChegada.getVooChegada().split(" ");
		int numeroVooChegada= Integer.parseInt(vooChegada[1]);
		
		LocalDateTime localDateTime= Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista());
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
		
		VooChegadaGrupoI vooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarVooGrupoIPouso(Converte.converterLocalDateToJavaSqlDate(localDateTime.toLocalDate()), numeroVooChegada);
				
		if(vooChegadaGrupoI != null) {
			
			try {
				
				String[] vooPouso= acessoriosChegada.getVooChegada().split(" ");
				vooChegadaGrupoI.setNumeroVooPouso(Integer.valueOf(vooPouso[1]));

				String[] dataHora= acessoriosChegada.getDataHoraAtualizada().split(" ");
			
				vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
				vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
				vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
				dataHora= acessoriosChegada.getDataHoraPrevista().split(" ");
				vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
				vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
				vooChegadaGrupoI.setSituacao(acessoriosChegada.getSituacao());
				vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
				
				System.out.println("Recursos alocados: ");
				System.out.println("Box: "+vooChegadaGrupoI.getNomeBox());
				System.out.println("Esteira: "+vooChegadaGrupoI.getNomeEsteira());
				System.out.println("Cabeceira: "+vooChegadaGrupoI.getNomeCabeceira());
				//
				
				vooChegadaGrupoIDAO.alterar(vooChegadaGrupoI);
			} catch (NullPointerException np) {
				JOptionPane.showMessageDialog(null, "O voo chegada do  grupo I não foi atualizado no sistema ");
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if((acao.equals("CLD")) ||acao.equals("ATR")||(acao.equals("ALT"))) {
				vooChegadaGrupoI.setNomeCabeceira(00);
				vooChegadaGrupoI.setNomeBox("00");
				vooChegadaGrupoI.setNomeEsteira("00");
				
					try {
						VooDAO vooDAO= new VooDAO();
						vooDAO.alterar(vooChegadaGrupoI);
						vooChegadaGrupoIDAO.alterar(vooChegadaGrupoI);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					AcessoriosChegadaDAO acessoriosChegadaDAO= new AcessoriosChegadaDAO();
					LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista());
					Date dataPouso= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate());
					AcessoriosChegada aux= acessoriosChegadaDAO.selecionar(dataPouso, acessoriosChegada.getVooChegada());
					acessoriosChegada= liberarRecursos(acessoriosChegada);
					acessoriosChegada.setSituacao(acao);
					if (aux!= null) {
						aux.setDataHoraAtualizada(acessoriosChegada.getDataHoraAtualizada());
						aux.setDataHoraPrevista(acessoriosChegada.getDataHoraPrevista());
						
						aux.setDestino(acessoriosChegada.getDestino());
						aux.setEquipamento(acessoriosChegada.getEquipamento());
						aux.setIdHotran(acessoriosChegada.getIdHotran());
						aux.setMatricula(acessoriosChegada.getMatricula());
						aux.setNomeBox(acessoriosChegada.getNomeBox());
						aux.setNomeCabeceira(acessoriosChegada.getNomeCabeceira());
						aux.setNomeEsteira(acessoriosChegada.getNomeEsteira());
						aux.setProcedencia(acessoriosChegada.getProcedencia());
						aux.setSituacao(acessoriosChegada.getSituacao());
						aux.setTipo(acessoriosChegada.getTipo());
						aux.setVooChegada(acessoriosChegada.getVooChegada());
						aux.setVooPartida(acessoriosChegada.getVooPartida());
						
						acessoriosChegadaDAO.alterar(aux);
						
					}
				}
				
			}else {
			AcessoriosChegadaDAO acessoriosChegadaDAO= new AcessoriosChegadaDAO();
			
			LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista());
			
			//Ao converter LocalDate para java.sql.date é necessario somar 1 dia ao LocalDate usando .plusDays(1)
			//Trata-se de um bug do sistema
			
			Date dataPouso= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate());
			AcessoriosChegada aux= acessoriosChegadaDAO.selecionar(dataPouso, acessoriosChegada.getVooChegada());
			acessoriosChegada= liberarRecursos(acessoriosChegada);
			acessoriosChegada.setSituacao(acao);
			if (aux != null) {
				aux.setDataHoraAtualizada(acessoriosChegada.getDataHoraAtualizada());
				aux.setDataHoraPrevista(acessoriosChegada.getDataHoraPrevista());
				aux.setDestino(acessoriosChegada.getDestino());
				aux.setEquipamento(acessoriosChegada.getEquipamento());
				aux.setIdHotran(acessoriosChegada.getIdHotran());
				aux.setMatricula(acessoriosChegada.getMatricula());
				aux.setNomeBox(acessoriosChegada.getNomeBox());
				aux.setNomeCabeceira(acessoriosChegada.getNomeCabeceira());
				aux.setNomeEsteira(acessoriosChegada.getNomeEsteira());
				aux.setProcedencia(acessoriosChegada.getProcedencia());
				aux.setSituacao(acessoriosChegada.getSituacao());
				aux.setTipo(acessoriosChegada.getTipo());
				aux.setVooChegada(acessoriosChegada.getVooChegada());
				aux.setVooPartida(acessoriosChegada.getVooPartida());
				
				acessoriosChegadaDAO.alterar(aux);
				
			}
		}
	}
	
	public void atualizar(AcessoriosChegada acessoriosChegada, JTable table, String acao) {
		if (acessoriosChegada!= null) {
			setRecursos(acessoriosChegada);
			atualizarTabela(table);
			atualizarVoo(acessoriosChegada, table, acao);
		}
	}
	
	public AcessoriosChegada liberarRecursos(AcessoriosChegada acessoriosChegada) {
		liberarCabeceira(acessoriosChegada.getNomeCabeceira());
		liberarPosicaoPatio(acessoriosChegada.getNomeBox()); 
		liberarEsteira(acessoriosChegada.getNomeEsteira());
		
		acessoriosChegada.setNomeCabeceira("00");
		acessoriosChegada.setNomeBox("00");
		acessoriosChegada.setNomeEsteira("00");
		setRecursos(acessoriosChegada);
		
		return acessoriosChegada;
	}
	
	public void apagarAcessoriosChegada(AcessoriosChegada acessoriosChegada) {
		AcessoriosChegadaDAO acessoriosChegadaDAO= new AcessoriosChegadaDAO();
		acessoriosChegadaDAO.apagar(acessoriosChegada.getIdAcessoriosChegada());
	}
	
	//VERIFICAR ESTE METODO - vooChegadaGrupoI retornando null;
	public void alterarVooChegadaGrupoI(AcessoriosChegada acessoriosChegada) {
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
		String []vooChegada= acessoriosChegada.getVooChegada().split(" ");
		int numeroVooPouso= Integer.parseInt(vooChegada[1]);
		LocalDateTime ldt= Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista());
		Date dataPrevistax= Converte.converterLocalDateToJavaSqlDate(ldt.toLocalDate());
		System.out.println("Data Prevista: "+dataPrevistax +" Numero do voo Pousando: "+numeroVooPouso);
		VooChegadaGrupoI vooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarVooGrupoIPouso(dataPrevistax, numeroVooPouso);
		
		System.out.println("Hotran Voo Chegada: "+vooChegadaGrupoI.getIdHotran());
		if (vooChegadaGrupoI != null) {
			
			vooChegadaGrupoI.setIdHotran(acessoriosChegada.getIdHotran());
			String[] vooPouso= acessoriosChegada.getVooChegada().split(" ");
			vooChegadaGrupoI.setNumeroVooPouso(Integer.valueOf(vooPouso[1]));

			String[] dataHora= acessoriosChegada.getDataHoraAtualizada().split(" ");
		
			vooChegadaGrupoI.setDataConfirmadaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
			vooChegadaGrupoI.setHoraConfirmadaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
			vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
			dataHora= acessoriosChegada.getDataHoraPrevista().split(" ");
			vooChegadaGrupoI.setDataPrevistaParaPouso(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(dataHora[0], "dd/MM/yyyy"));
			vooChegadaGrupoI.setHoraPrevistaParaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime()); 
			vooChegadaGrupoI.setSituacao(acessoriosChegada.getSituacao());
			vooChegadaGrupoI.setOrigem(acessoriosChegada.getProcedencia());
			
			/*
			if(vooChegadaGrupoI.getNomeEsteira()== "00") {
				if (vooChegadaGrupoI.getSituacao() == "CFD") {
					Aeronave aero= aeronaveDAO.selecionarAeronaveById(vooChegadaGrupoI.getIdAeronave());
					int idProprietario= aero.getIdProprietario();
					acessoriosChegada= alocaRecursosChegada(acessoriosChegada, idProprietario);
					setRecursos(acessoriosChegada);
				}
			}
			
			
			vooChegadaGrupoI.setNomeBox(acessoriosChegada.getNomeBox());
			vooChegadaGrupoI.setNomeCabeceira(Integer.parseInt(acessoriosChegada.getNomeCabeceira()));
			vooChegadaGrupoI.setNomeEsteira(acessoriosChegada.getNomeEsteira());
			
			try {
				vooDAO.alterar(vooChegadaGrupoI);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("........Alterando voo Chegada");
			*/
			
			vooChegadaGrupoIDAO.alterar(vooChegadaGrupoI);
		}
	}
}
