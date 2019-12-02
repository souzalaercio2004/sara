package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import sara.nemo.br.ufes.inf.tables.TableViewChegadas;
import sara.nemo.br.ufes.inf.tables.TableViewPartidas;

public class MenuPrincipal {

	private JFrame frmMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.setBounds(100, 100, 481, 406);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCadastroDeAeronaves = new JMenuItem("Aeronaves");
		mntmCadastroDeAeronaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadAeronave.showWindow();
			}
		});
		
		mnCadastro.add(mntmCadastroDeAeronaves);
		
		JMenuItem mntmCadastroDeCategoria = new JMenuItem("Categoria");
		mntmCadastroDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCategoria.showWindow();
			}
		});
		mntmCadastroDeCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadCategoria cadCategoria= new CadCategoria();
				cadCategoria.setVisible(true);
			}
		});
		mnCadastro.add(mntmCadastroDeCategoria);
		
		JMenuItem mntmCadastroDeHotran = new JMenuItem("Hotran");
		mntmCadastroDeHotran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadHotran cadHotran= new CadHotran();
				cadHotran.setVisible(true);
			}
		});
		
		JMenuItem mntmFrequencia = new JMenuItem("Frequencia");
		mntmFrequencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadFrequencia.showWindow();
			
			}
		});
		mnCadastro.add(mntmFrequencia);
		mnCadastro.add(mntmCadastroDeHotran);
		
		JMenuItem mntmCadastroDeMovimento = new JMenuItem("Movimento");
		mntmCadastroDeMovimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadMovimento.showWindow();
			}
		});
		mnCadastro.add(mntmCadastroDeMovimento);
		
		JMenuItem mntmCadastroDeOcorrencia = new JMenuItem("Ocorrencia de Voo");
		mntmCadastroDeOcorrencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadOcorrenciaVoo.showWindow();
			}
		});
		mnCadastro.add(mntmCadastroDeOcorrencia);
		
		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);
		
		JMenuItem mntmCiaArea = new JMenuItem("Proprietario Cia Aérea");
		mntmCiaArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadProprietarioCiaAerea.showWindow();
			}
		});
		mnCadastro.add(mntmCiaArea);
		
		JMenuItem mntmParticular = new JMenuItem("Proprietario Particular");
		mntmParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadProprietarioParticular.showWindow();
			}
		});
		mnCadastro.add(mntmParticular);
		
		JMenuItem mntmRecurso = new JMenuItem("Recurso");
		mntmRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadRecurso.showWindow();
			}
		});
		mnCadastro.add(mntmRecurso);
		
		JMenuItem mntmTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		mntmTipoDeAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadTipoAeronave.showWindow();
			}
		});
		mnCadastro.add(mntmTipoDeAeronave);
		
		JSeparator separator_1 = new JSeparator();
		mnCadastro.add(separator_1);
		
		JMenuItem mntmVooNaoRegularGrupoI = new JMenuItem("Voo Nao Regular Grupo I");
		mntmVooNaoRegularGrupoI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadVooNaoRegularGrupoI.showWindow();
			}
		});
		mnCadastro.add(mntmVooNaoRegularGrupoI);
		
		JMenuItem mntmVooGrupoII = new JMenuItem("Voo Grupo II");
		mntmVooGrupoII.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadVooGrupoII.showWindow();
			}
		});
		mnCadastro.add(mntmVooGrupoII);
		
		JMenu mnRecursos = new JMenu("Recursos");
		menuBar.add(mnRecursos);
		
		JMenuItem mntmEsteira = new JMenuItem("Esteira");
		mntmEsteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadEsteira.showWindow();
			}
		});
		mnRecursos.add(mntmEsteira);
		
		JMenuItem mntmPista_1 = new JMenuItem("Pista");
		mntmPista_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPista.showWindow();
			}
		});
		mnRecursos.add(mntmPista_1);
		
		JMenuItem mntmPortoDeEmbarque_1 = new JMenuItem("Portão de Embarque");
		mntmPortoDeEmbarque_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPortaoDeEmbarque.showWindow();
			}
		});
		mnRecursos.add(mntmPortoDeEmbarque_1);
		
		JMenuItem mntmPosioNoHeliponto_1 = new JMenuItem("Posição no Heliponto");
		mntmPosioNoHeliponto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPosicaoHeliponto.showWindow();
			}
		});
		mnRecursos.add(mntmPosioNoHeliponto_1);
		
		JMenuItem mntmPosioNoPatio = new JMenuItem("Posição no Patio");
		mntmPosioNoPatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPosicaoPatio.showWindow();
			}
		});
		mnRecursos.add(mntmPosioNoPatio);
		
		JMenuItem mntmPreferenciaNoUso_1 = new JMenuItem("Preferencia no Uso de Recursos");
		mntmPreferenciaNoUso_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPreferenciaUsoRecursos.showWindow();
			}
		});
		mnRecursos.add(mntmPreferenciaNoUso_1);
		
		JMenuItem mntmTiposDeRecurso = new JMenuItem("Tipos de Recurso");
		mntmTiposDeRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadRecurso.showWindow();
			}
		});
		mnRecursos.add(mntmTiposDeRecurso);
		
		JMenuItem mntmOcorrenciaRecursos = new JMenuItem("Ocorrencia & Recursos");
		mntmOcorrenciaRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadRecursoEmOcorrenciaVoo.showWindow();
			}
		});
		
		JMenuItem mntmRecursosPorProprietario = new JMenuItem("Recursos por Proprietario");
		mntmRecursosPorProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadRecursosPorProprietario.showWindow();
			}
		});
		mnRecursos.add(mntmRecursosPorProprietario);
		mnRecursos.add(mntmOcorrenciaRecursos);
		
		JMenu mnTabelas = new JMenu("Tabelas");
		menuBar.add(mnTabelas);
		
		JMenuItem mntmChegadas = new JMenuItem("Chegadas");
		mntmChegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewChegadas.showTableViewChegadas();
			}
		});
		mnTabelas.add(mntmChegadas);
		
		JMenuItem mntmPartidas = new JMenuItem("Partidas");
		mntmPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewPartidas.showTableViewPartidas();
			}
		});
		mnTabelas.add(mntmPartidas);
		frmMenuPrincipal.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 481, 1);
		frmMenuPrincipal.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmMenuPrincipal.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSair.setBounds(204, 250, 154, 74);
		frmMenuPrincipal.getContentPane().add(btnSair);
	}
}
