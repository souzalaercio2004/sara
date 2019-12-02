package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.FrequenciaDAO;
import sara.nemo.br.ufes.inf.DAO.HotranDAO;
import sara.nemo.br.ufes.inf.DAO.ProprietarioCiaAereaDAO;
import sara.nemo.br.ufes.inf.domain.Hotran;
import sara.nemo.br.ufes.inf.domain.ProprietarioCiaAerea;

public class CadHotran extends JFrame {
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
	HotranDAO hotranDAO= new HotranDAO();
	FrequenciaDAO frequenciaDAO= new FrequenciaDAO();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroDoVooPouso;
	private JTextField txtNumeroVooDecola;
	private JTextField txtHorarioPrevPouso;
	private JTextField txtHorarioPrevDecolagem;
	private JTextField txtEscalasHorigem;
	private JTextField txtEscalasDeDestino;
	private JFormattedTextField txtInicioDaVigencia;
	private JFormattedTextField txtFimDaVigencia;
	private JComboBox<String> cbxCompanhia;
	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadHotran frame = new CadHotran();
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
	public CadHotran() {
		setTitle("CADASTRO DE HOTRAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCompanhia = new JLabel("Companhia");
		lblCompanhia.setBounds(152, 26, 108, 19);
		contentPane.add(lblCompanhia);
		
		cbxCompanhia = new JComboBox<String>();
		cbxCompanhia.setBounds(278, 26, 114, 19);
		contentPane.add(cbxCompanhia);
		
		JLabel lblNumeroDoVooPouso = new JLabel("Numero do vôo que pousa");
		lblNumeroDoVooPouso.setBounds(39, 58, 221, 15);
		contentPane.add(lblNumeroDoVooPouso);
		
		txtNumeroDoVooPouso = new JTextField();
		txtNumeroDoVooPouso.setBounds(278, 56, 114, 19);
		contentPane.add(txtNumeroDoVooPouso);
		txtNumeroDoVooPouso.setColumns(10);
		
		JLabel lblNumeroDoVo = new JLabel("Numero do vôo que decola");
		lblNumeroDoVo.setBounds(39, 76, 220, 15);
		contentPane.add(lblNumeroDoVo);
		
		txtNumeroVooDecola = new JTextField();
		txtNumeroVooDecola.setBounds(278, 76, 114, 19);
		contentPane.add(txtNumeroVooDecola);
		txtNumeroVooDecola.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Horário previsto para pouso");
		lblNewLabel.setBounds(39, 103, 220, 15);
		contentPane.add(lblNewLabel);
		
		txtHorarioPrevPouso = new JFormattedTextField("HH:mm");
		//txtHorarioPrevPouso.setText("HH:mm");
		txtHorarioPrevPouso.setBounds(278, 103, 54, 19);
		contentPane.add(txtHorarioPrevPouso);
		txtHorarioPrevPouso.setColumns(10);
		
		JLabel lblHorarioPrevDecolagem = new JLabel("Horário previsto para decolagem");
		lblHorarioPrevDecolagem.setBounds(39, 130, 238, 15);
		contentPane.add(lblHorarioPrevDecolagem);
		
		txtHorarioPrevDecolagem = new JFormattedTextField("HH:mm");
		//txtHorarioPrevDecolagem.setText("HH:mm");
		txtHorarioPrevDecolagem.setBounds(278, 130, 54, 19);
		contentPane.add(txtHorarioPrevDecolagem);
		txtHorarioPrevDecolagem.setColumns(10);
		
		JLabel lblEscalasDeOrigem = new JLabel("Escalas de origem");
		lblEscalasDeOrigem.setBounds(113, 157, 146, 15);
		contentPane.add(lblEscalasDeOrigem);
		
		txtEscalasHorigem = new JTextField();
		txtEscalasHorigem.setBounds(278, 157, 114, 19);
		contentPane.add(txtEscalasHorigem);
		txtEscalasHorigem.setColumns(10);
		
		JLabel lblEscalasDeDestino = new JLabel("Escalas de destino");
		lblEscalasDeDestino.setBounds(123, 184, 136, 15);
		contentPane.add(lblEscalasDeDestino);
		
		txtEscalasDeDestino = new JTextField();
		txtEscalasDeDestino.setBounds(278, 184, 114, 19);
		contentPane.add(txtEscalasDeDestino);
		txtEscalasDeDestino.setColumns(10);
		
		JLabel lblInicioDaVigencia = new JLabel("Inicio da vigencia");
		lblInicioDaVigencia.setBounds(138, 211, 121, 15);
		contentPane.add(lblInicioDaVigencia);
		
		txtInicioDaVigencia = new JFormattedTextField("dd/MM/aaaa");
		//txtInicioDaVigencia.setText("dd/mm/aaaa");
		txtInicioDaVigencia.setBounds(278, 211, 87, 19);
		contentPane.add(txtInicioDaVigencia);
		txtInicioDaVigencia.setColumns(10);
		
		JLabel lblFimDaVigencia = new JLabel("Fim da vigencia");
		lblFimDaVigencia.setBounds(148, 238, 111, 15);
		contentPane.add(lblFimDaVigencia);
		
		txtFimDaVigencia = new JFormattedTextField("dd/MM/aaaa");
		//txtFimDaVigencia.setText("dd/mm/aaaa");
		txtFimDaVigencia.setBounds(278, 238, 87, 19);
		contentPane.add(txtFimDaVigencia);
		txtFimDaVigencia.setColumns(10);
		
		JRadioButton rdbDomingo = new JRadioButton("D");
		rdbDomingo.setBounds(126, 305, 35, 23);
		contentPane.add(rdbDomingo);
		
		JRadioButton rdbSegunda = new JRadioButton("S");
		rdbSegunda.setBounds(165, 305, 34, 23);
		contentPane.add(rdbSegunda);
		
		JRadioButton rdbTerca = new JRadioButton("T");
		rdbTerca.setBounds(203, 305, 35, 23);
		contentPane.add(rdbTerca);
		
		JRadioButton rdbQuarta = new JRadioButton("Q");
		rdbQuarta.setBounds(297, 305, 35, 23);
		contentPane.add(rdbQuarta);
		
		JRadioButton rdbQuinta = new JRadioButton("Q");
		rdbQuinta.setBounds(244, 305, 35, 23);
		contentPane.add(rdbQuinta);
		
		JRadioButton rdbSexta = new JRadioButton("S");
		rdbSexta.setBounds(349, 305, 43, 23);
		contentPane.add(rdbSexta);
		
		JRadioButton rdbSabado = new JRadioButton("S");
		rdbSabado.setBounds(388, 305, 35, 23);
		contentPane.add(rdbSabado);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(39, 358, 117, 25);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Hotran hotran= new Hotran();
				String sigla= (String)cbxCompanhia.getSelectedItem();
				proprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarBySigla(sigla);
				hotran.setIdCiaAerea(proprietarioCiaAerea.getIdCiaAerea());
				hotran.setNumeroVooPousa((Integer.valueOf(txtNumeroDoVooPouso.getText())));
				hotran.setNumeroVooDecola(Integer.valueOf(txtNumeroVooDecola.getText()));
				hotran.setHorarioPrevistoPouso(LocalTime.parse(txtHorarioPrevPouso.getText()));				
				hotran.setHorarioPrevistoDecolagem(LocalTime.parse((txtHorarioPrevDecolagem.getText())));
				hotran.setEscalasOrigem(txtEscalasHorigem.getText().toUpperCase());
				hotran.setEscalasDestino(txtEscalasDeDestino.getText().toUpperCase());
				hotran.setInicioVigencia(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtInicioDaVigencia.getText(), "dd/MM/yyyy"));
				hotran.setFimVigencia(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtFimDaVigencia.getText(), "dd/MM/yyyy"));
				
				int idFreq=0;
				if (rdbDomingo.isSelected()) idFreq+= 64;
				if (rdbSegunda.isSelected()) idFreq+= 32;
				if (rdbTerca.isSelected()) idFreq+= 16;
				if (rdbQuarta.isSelected()) idFreq+= 8;
				if (rdbQuinta.isSelected()) idFreq+= 4;
				if (rdbSexta.isSelected()) idFreq+= 2;
				if (rdbSabado.isSelected()) idFreq+= 1;
				
				hotran.setIdFrequencia(idFreq);
				
				try {
					hotranDAO.inserir(hotran);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel lblFrequencia = new JLabel("Frequencia");
		lblFrequencia.setBounds(39, 305, 111, 15);
		contentPane.add(lblFrequencia);
		contentPane.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(177, 358, 117, 25);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do Hotran: "));				
				Hotran hotran= hotranDAO.selecionarById(id);
				
				String sigla= (String)cbxCompanhia.getSelectedItem();
				proprietarioCiaAerea= proprietarioCiaAereaDAO.selecionarBySigla(sigla);
				
				hotran.setIdCiaAerea(proprietarioCiaAerea.getIdCiaAerea());
				hotran.setNumeroVooPousa((Integer.valueOf(txtNumeroDoVooPouso.getText())));
				hotran.setNumeroVooDecola(Integer.valueOf(txtNumeroVooDecola.getText()));
				hotran.setHorarioPrevistoPouso(LocalTime.parse(txtHorarioPrevPouso.getText()));				
				hotran.setHorarioPrevistoDecolagem(LocalTime.parse(txtHorarioPrevDecolagem.getText()));
				hotran.setEscalasOrigem(txtEscalasHorigem.getText().toUpperCase());
				hotran.setEscalasDestino(txtEscalasDeDestino.getText().toUpperCase());
				hotran.setInicioVigencia(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtInicioDaVigencia.getText(), "dd/MM/yyyy"));
				hotran.setFimVigencia(Converte.converteStringToLocalDateNoFormatoDDMMAAAA(txtFimDaVigencia.getText(), "dd/MM/yyyy"));
				
				int idFreq=0;
				if (rdbDomingo.isSelected()) idFreq+= 64;
				if (rdbSegunda.isSelected()) idFreq+= 32;
				if (rdbTerca.isSelected()) idFreq+= 16;
				if (rdbQuarta.isSelected()) idFreq+= 8;
				if (rdbQuinta.isSelected()) idFreq+= 4;
				if (rdbSexta.isSelected()) idFreq+= 2;
				if (rdbSabado.isSelected()) idFreq+= 1;
				
				hotran.setIdFrequencia(idFreq);
				
				try {
					hotranDAO.alterar(hotran);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(39, 395, 117, 25);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hotranDAO.selecionar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(177, 395, 117, 25);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do Hotran para apagar: "));	
				hotranDAO.apagar(id);
			}
		});
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(354, 376, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnCancelar);
		
		
		//ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
		ArrayList<String> sigla= proprietarioCiaAereaDAO.selecionarSigla();
		
		for (String s: sigla) {
			cbxCompanhia.addItem(s);
		}
		

		
		/*
		ArrayList<Frequencia> frequencia= frequenciaDAO.obterIdFrequencia();
		//cbxFrequencia.addItem("D S T Q Q S S ");
		for (Frequencia f: frequencia ) {
			String mascaraFrequencia;
					//f.isDomingo()+" "+f.isSegunda()+" "+f.isTerca()+" "+f.isQuarta()+" "+f.isQuinta()+" "+f.isSexta()+" "+f.isSabado();
			if (f.isDomingo()) {
				mascaraFrequencia= " D";
			} else mascaraFrequencia= " -";
			
			if (f.isSegunda()) {
				mascaraFrequencia+= " S";
			} else mascaraFrequencia+= " -";
			
			if (f.isTerca()) {
				mascaraFrequencia+= " T";
			} else mascaraFrequencia+= " -";
			
			if (f.isQuarta()) {
				mascaraFrequencia+= " Q";
			} else mascaraFrequencia+= " -";
			
			if (f.isQuinta()) {
				mascaraFrequencia+= " Q";
			} else mascaraFrequencia+= " -";
			
			if (f.isSexta()) {
				mascaraFrequencia+= " S";
			} else mascaraFrequencia+= " -";
			
			if (f.isSabado()) {
				mascaraFrequencia+= " S";
			} else mascaraFrequencia+= " -";
			
			cbxFrequencia.addItem(mascaraFrequencia);
		} */
	}
}
