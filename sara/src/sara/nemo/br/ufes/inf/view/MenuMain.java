package sara.nemo.br.ufes.inf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.DAO.ChegadasDAO;
import sara.nemo.br.ufes.inf.DAO.EsteiraDAO;
import sara.nemo.br.ufes.inf.DAO.FrequenciaDAO;
import sara.nemo.br.ufes.inf.DAO.HotranDAO;
import sara.nemo.br.ufes.inf.DAO.MovimentoDAO;
import sara.nemo.br.ufes.inf.DAO.OcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.PistaDAO;
import sara.nemo.br.ufes.inf.DAO.PortaoDeEmbarqueDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoHelipontoDAO;
import sara.nemo.br.ufes.inf.DAO.PosicaoPatioDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioCiaAereaDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioParticularDAO;
import sara.nemo.br.ufes.inf.DAO.RecursoEmOcorrenciaVooDAO;
import sara.nemo.br.ufes.inf.DAO.TipoAeronaveDAO;
import sara.nemo.br.ufes.inf.DAO.VooChegadaGrupoIDAO;
import sara.nemo.br.ufes.inf.DAO.VooGrupoIDAO;
import sara.nemo.br.ufes.inf.DAO.VooNaoRegularGrupoIDAO;
import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.domain.VooChegadaGrupoI;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosOcorrenciaVoo;
import sara.nemo.br.ufes.inf.view.tables.TableViewAeronaves;
import sara.nemo.br.ufes.inf.view.tables.TableViewCategoria;
import sara.nemo.br.ufes.inf.view.tables.TableViewChegadas;
import sara.nemo.br.ufes.inf.view.tables.TableViewPartidas;
import sara.nemo.br.ufes.inf.view.tables.TableViewRecursosPorProprietario;

public class MenuMain {

	
	private JPanel contentPane;
	private JFrame frame;
	private JMenu menuInserir_1;
	private JMenu menuAlterar_1;
	private JMenu menuExcluir_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MenuMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuMain() {
		frame = new JFrame("Menu Principal");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 662, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		menuInserir_1 = new JMenu("Inserir");
		menuBar.add(menuInserir_1);
		opcaoInserir(menuInserir_1);
		
		JMenu menuConsultar = new JMenu("Consultar");
		menuBar.add(menuConsultar);
		opcaoConsultar(menuConsultar);
		
		menuAlterar_1 = new JMenu("Alterar");
		menuBar.add(menuAlterar_1);
		opcaoAlterar(menuAlterar_1);
		
		menuExcluir_1 = new JMenu("Excluir");
		menuExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoExcluir(menuExcluir_1);
			}
		});
		menuBar.add(menuExcluir_1);
		opcaoExcluir(menuExcluir_1);
		
		JMenu menuTabelas = new JMenu("Tabelas");
		menuBar.add(menuTabelas);
		
		JMenuItem menuItemChegadas = new JMenuItem("Chegadas");
		menuItemChegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewChegadas.showTableViewChegadas();
			}
		});
		
		JMenuItem menuItemGerarTabelas = new JMenuItem("Gerar Tabelas de Voos");
		menuItemGerarTabelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processarChegadas();
			}
		});
		menuTabelas.add(menuItemGerarTabelas);
		menuTabelas.add(menuItemChegadas);
		
		JMenuItem menuItemPartidas = new JMenuItem("Partidas");
		menuItemPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TableViewPartidas.showTableViewPartidas();
			}
		});
		menuTabelas.add(menuItemPartidas);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// inicia a janela maximizada
	}
	
	
	public void opcaoInserir(JMenu menuInserir) {
		JMenuItem menuItemAeronave = new JMenuItem("Aeronave");
		menuItemAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CadastroAeronave.showWindow(frame, "Inserir"); // Inserir= acao a ser executada
			}
		});
		menuInserir.add(menuItemAeronave);
		
		
		JMenuItem menuItemCategoria = new JMenuItem("Categoria");
		menuItemCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoria.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemCategoria);
		
		
		JMenuItem menuItemEsteira = new JMenuItem("Esteira");
		menuItemEsteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroEsteira.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemEsteira);
		
		
		JMenuItem menuItemHotram = new JMenuItem("Hotram");
		menuItemHotram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroHotran.showWindow(frame, "Inserir");
			}
		});
		
		JMenuItem menuItemFrequencia = new JMenuItem("Frequencia");
		menuItemFrequencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFrequencia.showWindow(frame, "Inserir");
			}
		});
		menuInserir_1.add(menuItemFrequencia);
		menuInserir.add(menuItemHotram);
		
		
		JMenuItem menuItemMovimento = new JMenuItem("Movimento");
		menuInserir.add(menuItemMovimento);
		
		
		JMenuItem menuItemOcorrenciaDeVoo = new JMenuItem("Ocorrencia de Voo");
		menuInserir.add(menuItemOcorrenciaDeVoo);
		
		
		JMenuItem menuItemPista = new JMenuItem("Pista");
		menuItemPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPista.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemPista);
		
		
		JMenuItem menuItemPortaoDeEmbarque = new JMenuItem("Portão de Embarque");
		menuItemPortaoDeEmbarque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPortaoDeEmbarque.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemPortaoDeEmbarque);
		
		
		JMenuItem menuItemPosicaoNoHeliponto = new JMenuItem("Posição no Heliponto");
		menuItemPosicaoNoHeliponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoHeliponto.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemPosicaoNoHeliponto);
		
		
		JMenuItem menuItemPosicaoNoPatio = new JMenuItem("Posição no Pátio");
		menuItemPosicaoNoPatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoPatio.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemPosicaoNoPatio);
		
		
		JMenuItem menuItemProprietarioCiaAerea = new JMenuItem("Proprietario Cia Aérea");
		menuItemProprietarioCiaAerea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioCiaAerea.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemProprietarioCiaAerea);
		
		
		JMenuItem menuItemProprietarioParticular = new JMenuItem("Proprietario Particular");
		menuItemProprietarioParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioParticular.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemProprietarioParticular);
		
		
		JMenuItem menuRecursoEmOcorrenciaDeVoo = new JMenuItem("Recurso em Ocorremcia de Voo");
		menuInserir.add(menuRecursoEmOcorrenciaDeVoo);
		
		
		JMenuItem menuItemRecursoPorProprietario = new JMenuItem("Recurso por Proprietario");
		menuItemRecursoPorProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRecursosPorProprietario.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemRecursoPorProprietario);
		
		
		JMenuItem menuItemSituacaoChegadas = new JMenuItem("Situacao Chegadas");
		menuInserir.add(menuItemSituacaoChegadas);
		
		
		JMenuItem menuItemTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		menuItemTipoDeAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTipoAeronave.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemTipoDeAeronave);
		
		
		JMenuItem menuItemVooGrupoI = new JMenuItem("Voo Grupo I");
		menuInserir.add(menuItemVooGrupoI);
		
		
		JMenuItem menuItemVooGrupoII = new JMenuItem("Voo Grupo II");
		menuItemVooGrupoII.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CadastroVooGrupoII.showWindow(frame, "Inserir");
			}
		});
		menuInserir.add(menuItemVooGrupoII);
		
		
		JMenuItem menuItemVooNaoRegularGrupoI = new JMenuItem("Voo Não Regular Grupo I");
		menuInserir.add(menuItemVooNaoRegularGrupoI);
		
		frame.setVisible(true);
	}

	
	public void opcaoConsultar(JMenu menuConsultar) {
		frame.setVisible(false);
		JMenuItem menuItemAeronave = new JMenuItem("Aeronave");
		menuItemAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastroAeronave cad= new CadastroAeronave(frame, "Consultar");
				TableViewAeronaves.showTableViewAeronaves(cad);
			}
		});
		menuConsultar.add(menuItemAeronave);
		
		
		JMenuItem menuItemCategoria = new JMenuItem("Categoria");
		menuItemCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewCategoria.showTableViewCategoria();
			}
		});
		menuConsultar.add(menuItemCategoria);
		
		
		JMenuItem menuItemEsteira = new JMenuItem("Esteira");
		menuItemEsteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EsteiraDAO esteiraDAO= new EsteiraDAO();
				esteiraDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemEsteira);
		
		
		
		JMenuItem menuItemFrequencia = new JMenuItem("Frequencia");
		menuItemFrequencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrequenciaDAO frequenciaDAO= new FrequenciaDAO();
				frequenciaDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemFrequencia);
		
		
		JMenuItem menuItemHotram = new JMenuItem("Hotram");
		menuItemHotram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotranDAO hotranDAO= new HotranDAO();
				try {
					hotranDAO.selecionar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuConsultar.add(menuItemHotram);
		
		
		JMenuItem menuItemMovimento = new JMenuItem("Movimento");
		menuItemMovimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovimentoDAO movimentoDAO= new MovimentoDAO();
				movimentoDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemMovimento);
		
		
		JMenuItem menuItemOcorrenciaDeVoo = new JMenuItem("Ocorrencia de Voo");
		menuItemOcorrenciaDeVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OcorrenciaVooDAO ocorrenciaVooDAO= new OcorrenciaVooDAO();
				List<AcessoriosOcorrenciaVoo> lista= ocorrenciaVooDAO.selecionarLista();
				for (AcessoriosOcorrenciaVoo ocorrenciaVoo: lista) {
					System.out.println(ocorrenciaVoo.toString());
				}
			}
		});
		menuConsultar.add(menuItemOcorrenciaDeVoo);
		
		
		JMenuItem menuItemPista = new JMenuItem("Pista");
		menuItemPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PistaDAO pistaDAO= new PistaDAO();
				pistaDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemPista);
		
		
		JMenuItem menuItemPortaoDeEmbarque = new JMenuItem("Portão de Embarque");
		menuItemPortaoDeEmbarque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PortaoDeEmbarqueDAO portaoDeEmbarqueDAO= new PortaoDeEmbarqueDAO();
				portaoDeEmbarqueDAO.selecionar();
				
			}
		});
		menuConsultar.add(menuItemPortaoDeEmbarque);
		
		
		JMenuItem menuItemPosicaoNoHeliponto = new JMenuItem("Posição no Heliponto");
		menuItemPosicaoNoHeliponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PosicaoHelipontoDAO posicaoHelipontoDAO= new PosicaoHelipontoDAO();
				posicaoHelipontoDAO.selecionar();
				
			}
		});
		menuConsultar.add(menuItemPosicaoNoHeliponto);
		
		
		JMenuItem menuItemPosicaoNoPatio = new JMenuItem("Posição no Pátio");
		menuItemPosicaoNoPatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PosicaoPatioDAO posicaoPatioDAO= new PosicaoPatioDAO();
				posicaoPatioDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemPosicaoNoPatio);
		
		
		JMenuItem menuItemProprietarioCiaAerea = new JMenuItem("Proprietario Cia Aérea");
		menuItemProprietarioCiaAerea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
				proprietarioCiaAereaDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemProprietarioCiaAerea);
		
		
		JMenuItem menuItemProprietarioParticular = new JMenuItem("Proprietario Particular");
		menuItemProprietarioParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProprietarioParticularDAO proprietarioParticularDAO= new ProprietarioParticularDAO();
				proprietarioParticularDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemProprietarioParticular);
		
		
		JMenuItem menuRecursoEmOcorrenciaDeVoo = new JMenuItem("Recurso em Ocorremcia de Voo");
		menuRecursoEmOcorrenciaDeVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecursoEmOcorrenciaVooDAO recursoEmOcorrenciaVooDAO= new RecursoEmOcorrenciaVooDAO();
				recursoEmOcorrenciaVooDAO.selecionar();
			}
		});
		menuConsultar.add(menuRecursoEmOcorrenciaDeVoo);
		
		
		JMenuItem menuItemRecursoPorProprietario = new JMenuItem("Recurso por Proprietario");
		menuItemRecursoPorProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewRecursosPorProprietario.showTableViewRecursosPorProprietario(frame, "Consultar");
				
			}
		});
		menuConsultar.add(menuItemRecursoPorProprietario);
		
		
		JMenuItem menuItemSituacaoChegadas = new JMenuItem("Situacao Chegadas");
		menuItemSituacaoChegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewChegadas.showTableViewChegadas();
			}
		});
		menuConsultar.add(menuItemSituacaoChegadas);
		
		
		JMenuItem menuItemTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		menuItemTipoDeAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoAeronaveDAO tipoAeronaveDAO= new TipoAeronaveDAO();
				tipoAeronaveDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemTipoDeAeronave);
		
		
		JMenuItem menuItemVooGrupoI = new JMenuItem("Voo Grupo I");
		menuItemVooGrupoI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VooGrupoIDAO vooGrupoIDAO= new VooGrupoIDAO();
				vooGrupoIDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemVooGrupoI);
		
		
		JMenuItem menuItemVooGrupoII = new JMenuItem("Voo Grupo II");
		menuItemVooGrupoII.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VooGrupoIIDAO vooGrupoIIDAO= new VooGrupoIIDAO();
				//vooGrupoIIDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemVooGrupoII);
		
		
		JMenuItem menuItemVooNaoRegularGrupoI = new JMenuItem("Voo Não Regular Grupo I");
		menuItemVooNaoRegularGrupoI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VooNaoRegularGrupoIDAO vooNaoRegularGrupoIDAO= new VooNaoRegularGrupoIDAO();
				vooNaoRegularGrupoIDAO.selecionar();
			}
		});
		menuConsultar.add(menuItemVooNaoRegularGrupoI);
		
		
		frame.setVisible(true);
	}
	
	
	public void opcaoAlterar(JMenu menuAlterar) {
		JMenuItem menuItemAeronave = new JMenuItem("Aeronave");
		menuItemAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CadastroAeronave.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemAeronave);
		
		
		JMenuItem menuItemCategoria = new JMenuItem("Categoria");
		menuItemCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastroCategoria.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemCategoria);
		
		
		JMenuItem menuItemEsteira = new JMenuItem("Esteira");
		menuItemEsteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroEsteira.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemEsteira);
		
		
		JMenuItem menuItemHotram = new JMenuItem("Hotram");
		menuItemHotram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroHotran.showWindow(frame, "Alterar");
			}
		});
		
		JMenuItem menuItemFrequencia = new JMenuItem("Frequencia");
		menuItemFrequencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFrequencia.showWindow(frame, "Alterar");
			}
		});
		menuAlterar_1.add(menuItemFrequencia);
		menuAlterar.add(menuItemHotram);
		
		
		JMenuItem menuItemMovimento = new JMenuItem("Movimento");
		menuAlterar.add(menuItemMovimento);
		
		
		JMenuItem menuItemOcorrenciaDeVoo = new JMenuItem("Ocorrencia de Voo");
		menuAlterar.add(menuItemOcorrenciaDeVoo);
		
		
		JMenuItem menuItemPista = new JMenuItem("Pista");
		menuItemPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPista.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemPista);
		
		
		JMenuItem menuItemPortaoDeEmbarque = new JMenuItem("Portão de Embarque");
		menuItemPortaoDeEmbarque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPortaoDeEmbarque.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemPortaoDeEmbarque);
		
		
		JMenuItem menuItemPosicaoNoHeliponto = new JMenuItem("Posição no Heliponto");
		menuItemPosicaoNoHeliponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoHeliponto.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemPosicaoNoHeliponto);
		
		
		JMenuItem menuItemPosicaoNoPatio = new JMenuItem("Posição no Pátio");
		menuItemPosicaoNoPatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoPatio.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemPosicaoNoPatio);
		
		
		JMenuItem menuItemProprietarioCiaAerea = new JMenuItem("Proprietario Cia Aérea");
		menuItemProprietarioCiaAerea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioCiaAerea.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemProprietarioCiaAerea);
		
		
		JMenuItem menuItemProprietarioParticular = new JMenuItem("Proprietario Particular");
		menuItemProprietarioParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioParticular.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemProprietarioParticular);
		
		
		JMenuItem menuRecursoEmOcorrenciaDeVoo = new JMenuItem("Recurso em Ocorremcia de Voo");
		menuAlterar.add(menuRecursoEmOcorrenciaDeVoo);
		
		
		JMenuItem menuItemRecursoPorProprietario = new JMenuItem("Recurso por Proprietario");
		menuItemRecursoPorProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRecursosPorProprietario.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemRecursoPorProprietario);
		
		
		JMenuItem menuItemSituacaoChegadas = new JMenuItem("Situacao Chegadas");
		menuAlterar.add(menuItemSituacaoChegadas);
		
		
		JMenuItem menuItemTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		menuItemTipoDeAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTipoAeronave.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemTipoDeAeronave);
		
		
		JMenuItem menuItemVooGrupoI = new JMenuItem("Voo Grupo I");
		menuAlterar.add(menuItemVooGrupoI);
		
		
		JMenuItem menuItemVooGrupoII = new JMenuItem("Voo Grupo II");
		menuItemVooGrupoII.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CadastroVooGrupoII.showWindow(frame, "Alterar");
			}
		});
		menuAlterar.add(menuItemVooGrupoII);
		
		
		JMenuItem menuItemVooNaoRegularGrupoI = new JMenuItem("Voo Não Regular Grupo I");
		menuAlterar.add(menuItemVooNaoRegularGrupoI);
		
		frame.setVisible(true);
	}
	
	public void opcaoExcluir(JMenu menuExcluir) {
		JMenuItem menuItemAeronave = new JMenuItem("Aeronave");
		menuItemAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CadastroAeronave.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemAeronave);
		
		
		JMenuItem menuItemCategoria = new JMenuItem("Categoria");
		menuItemCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoria.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemCategoria);
		
		
		JMenuItem menuItemEsteira = new JMenuItem("Esteira");
		menuItemEsteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroEsteira.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemEsteira);
		
		
		JMenuItem menuItemHotram = new JMenuItem("Hotram");
		menuItemHotram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroHotran.showWindow(frame, "Excluir");
			}
		});
		
		JMenuItem menuItemFrequencia = new JMenuItem("Frequencia");
		menuItemFrequencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFrequencia.showWindow(frame, "Excluir");
			}
		});
		menuItemFrequencia.setHorizontalAlignment(SwingConstants.LEFT);
		menuExcluir_1.add(menuItemFrequencia);
		menuExcluir.add(menuItemHotram);
		
		
		JMenuItem menuItemMovimento = new JMenuItem("Movimento");
		menuExcluir.add(menuItemMovimento);
		
		
		JMenuItem menuItemOcorrenciaDeVoo = new JMenuItem("Ocorrencia de Voo");
		menuExcluir.add(menuItemOcorrenciaDeVoo);
		
		
		JMenuItem menuItemPista = new JMenuItem("Pista");
		menuItemPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPista.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemPista);
		
		
		JMenuItem menuItemPortaoDeEmbarque = new JMenuItem("Portão de Embarque");
		menuItemPortaoDeEmbarque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPortaoDeEmbarque.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemPortaoDeEmbarque);
		
		
		JMenuItem menuItemPosicaoNoHeliponto = new JMenuItem("Posição no Heliponto");
		menuItemPosicaoNoHeliponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoHeliponto.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemPosicaoNoHeliponto);
		
		
		JMenuItem menuItemPosicaoNoPatio = new JMenuItem("Posição no Pátio");
		menuItemPosicaoNoPatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPosicaoPatio.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemPosicaoNoPatio);
		
		
		JMenuItem menuItemProprietarioCiaAerea = new JMenuItem("Proprietario Cia Aérea");
		menuItemProprietarioCiaAerea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioCiaAerea.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemProprietarioCiaAerea);
		
		
		JMenuItem menuItemProprietarioParticular = new JMenuItem("Proprietario Particular");
		menuItemProprietarioParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProprietarioParticular.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemProprietarioParticular);
		
		
		JMenuItem menuRecursoEmOcorrenciaDeVoo = new JMenuItem("Recurso em Ocorremcia de Voo");
		menuExcluir.add(menuRecursoEmOcorrenciaDeVoo);
		
		
		JMenuItem menuItemRecursoPorProprietario = new JMenuItem("Recurso por Proprietario");
		menuItemRecursoPorProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRecursosPorProprietario.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemRecursoPorProprietario);
		
		
		JMenuItem menuItemSituacaoChegadas = new JMenuItem("Situacao Chegadas");
		menuExcluir.add(menuItemSituacaoChegadas);
		
		
		JMenuItem menuItemTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		menuItemTipoDeAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTipoAeronave.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemTipoDeAeronave);
		
		
		JMenuItem menuItemVooGrupoI = new JMenuItem("Voo Grupo I");
		menuExcluir.add(menuItemVooGrupoI);
		
		
		JMenuItem menuItemVooGrupoII = new JMenuItem("Voo Grupo II");
		menuItemVooGrupoII.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CadastroVooGrupoII.showWindow(frame, "Excluir");
			}
		});
		menuExcluir.add(menuItemVooGrupoII);
		
		
		JMenuItem menuItemVooNaoRegularGrupoI = new JMenuItem("Voo Não Regular Grupo I");
		menuExcluir.add(menuItemVooNaoRegularGrupoI);
		
		frame.setVisible(true);
	}
	
	public void processarChegadas() {
		ChegadasDAO chegadasDAO= new ChegadasDAO();
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		VooChegadaGrupoIDAO vooChegadaGrupoIDAO= new VooChegadaGrupoIDAO();
		LocalDate dataHoje= getData();
		Date dataPrevistaPouso= Converte.converterLocalDateToJavaSqlDate(dataHoje);
		VooChegadaGrupoI vooChegadaGrupoI= vooChegadaGrupoIDAO.selecionarByDataPrevistaPouso(dataPrevistaPouso);
		String dia= getDiadaSemana(dataHoje);
		if (vooChegadaGrupoI != null) {
			JOptionPane.showMessageDialog(null, "Já foi cadastrada a Tabela de Voos desta data "+dataHoje);
			return;
		}
		
		CadastroSituacaoChegadas cadastroSituacaoChegadas= new CadastroSituacaoChegadas();
		
		for(AcessoriosChegada c: chegadasDAO.selecionarDadosDeChegada(dataHoje, dia)) {
			int idCategoria= categoriaDAO.selecionarIdCategoria("Domestico","Passageiro", "Regular", "Regular");	
			Categoria cat= categoriaDAO.selecionarById(idCategoria);
			c.setTipo(cat.abreviarCategoria());
			cadastroSituacaoChegadas.cadastrarVoo(c); 
			
		}
		
	}
	
	public String getDiadaSemana(LocalDate dataHoje) {
		
		int diaHoje;
		String dia= null;
		
		if (dataHoje!= null) {
			diaHoje= dataHoje.getDayOfWeek().getValue();																
			
			switch (diaHoje) {
				
			case(1):
				dia= "segundaFeira";
				break;
			case(2):
				dia= "tercaFeira";
				break;
			case(3):
				dia= "quartaFeira";
				break;
			case(4):
				dia= "quintaFeira";
				break;
			case(5):
				dia= "sextaFeira";
				break;
			case(6):
				dia= "sabado";
				break;
				
			case(7):
				dia= "domingo";
				break;
			
				default:
					dia= null;
			}
		}
		return dia;
	}
	
	public LocalDate getData() {
		String data= (JOptionPane.showInputDialog("Digite a data dos Voos no formato dd/mm/aaaa : "));
		LocalDate dataHoje= null;
		if (data != null) {
			dataHoje= Converte.converteStringToLocalDateNoFormatoDDMMAAAA(data, "dd/MM/yyyy");
		}
		return dataHoje;
	}
	
	/*
	public void cadastrarVooChegada(AcessoriosChegada acessoriosChegada, LocalDate dataHoje) {
		//VooGrupoIDAO vooGrupoIDAO= new VooGrupoIDAO();
		//VooGrupoI vooGrupoI= new VooGrupoI();
		
		CategoriaDAO categoriaDAO= new CategoriaDAO(); 
		
		VooDAO vooDAO= new VooDAO();
		Voo voo= null;
		
		
		voo= vooDAO.selecionarByDataPrevistaPouso(dataHoje);
		if (voo == null) {
			voo= new Voo();
			voo.setDataPrevistaParaDecolagem(null);
			voo.setDataPrevistaParaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalDate());
			voo.setDestino(acessoriosChegada.getDestino());
			voo.setHoraPrevistaParaDecolagem(null);
			voo.setHoraPrevistaParaPouso(Converte.converteStringToLocalDateTime(acessoriosChegada.getDataHoraPrevista()).toLocalTime());
			voo.setIdAeronave(32);
			
			int id= categoriaDAO.selecionarIdCategoria("Domestico", "Passageiro", "Regular", "Regular");
			voo.setIdCategoria(id);
			
			//voo.setIdVoo(idVoo);
			voo.setOrigem(acessoriosChegada.getProcedencia());
			voo.setSituacao(acessoriosChegada.getSituacao());
			try {
				vooDAO.inserirVooChegada(voo);
				System.out.println("Voo Cadastrado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	} */
}
