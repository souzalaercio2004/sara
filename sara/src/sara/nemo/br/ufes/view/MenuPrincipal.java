package sara.nemo.br.ufes.view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		frmMenuPrincipal.setBounds(100, 100, 450, 300);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCadastroDeAeronaves = new JMenuItem("Aeronaves");
		mntmCadastroDeAeronaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadAeronave cadAeronave= new CadAeronave();
				cadAeronave.setVisible(true);
			}
		});
		
		mnCadastro.add(mntmCadastroDeAeronaves);
		
		JMenuItem mntmCadastroDeCategoria = new JMenuItem("Categoria");
		mntmCadastroDeCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadCategoria cadCategoria= new CadCategoria();
				cadCategoria.setVisible(true);
			}
		});
		mnCadastro.add(mntmCadastroDeCategoria);
		
		JMenuItem mntmCadastroDeHotran = new JMenuItem("Hotran");
		mnCadastro.add(mntmCadastroDeHotran);
		
		JMenuItem mntmCadastroDeMovimento = new JMenuItem("Movimento");
		mnCadastro.add(mntmCadastroDeMovimento);
		
		JMenuItem mntmCadastroDeOcorrencia = new JMenuItem("Ocorrencia de Voo");
		mnCadastro.add(mntmCadastroDeOcorrencia);
		
		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Proprietário");
		mnCadastro.add(mntmNewMenuItem);
		
		JMenuItem mntmCiaArea = new JMenuItem("Cia Aérea");
		mnCadastro.add(mntmCiaArea);
		
		JMenuItem mntmParticular = new JMenuItem("Particular");
		mnCadastro.add(mntmParticular);
		
		JMenuItem mntmRecurso = new JMenuItem("Recurso");
		mnCadastro.add(mntmRecurso);
		
		JMenuItem mntmTipoDeAeronave = new JMenuItem("Tipo de Aeronave");
		mnCadastro.add(mntmTipoDeAeronave);
		
		JSeparator separator_1 = new JSeparator();
		mnCadastro.add(separator_1);
		
		JMenuItem mntmVoo = new JMenuItem("Voo");
		mnCadastro.add(mntmVoo);
		
		JMenuItem mntmVooGrupoI = new JMenuItem("Voo Grupo I");
		mnCadastro.add(mntmVooGrupoI);
		
		JMenu mnRecursos = new JMenu("Recursos");
		menuBar.add(mnRecursos);
		
		JMenuItem mntmEsteira = new JMenuItem("Esteira");
		mnRecursos.add(mntmEsteira);
		
		JMenuItem mntmPista_1 = new JMenuItem("Pista");
		mnRecursos.add(mntmPista_1);
		
		JMenuItem mntmPortoDeEmbarque_1 = new JMenuItem("Portão de Embarque");
		mnRecursos.add(mntmPortoDeEmbarque_1);
		
		JMenuItem mntmPosioNoHeliponto_1 = new JMenuItem("Posição no Heliponto");
		mnRecursos.add(mntmPosioNoHeliponto_1);
		
		JMenuItem mntmPosioNoPatio = new JMenuItem("Posição no Patio");
		mnRecursos.add(mntmPosioNoPatio);
		
		JMenuItem mntmPreferenciaNoUso_1 = new JMenuItem("Preferencia no Uso de Recursos");
		mnRecursos.add(mntmPreferenciaNoUso_1);
	}

}
