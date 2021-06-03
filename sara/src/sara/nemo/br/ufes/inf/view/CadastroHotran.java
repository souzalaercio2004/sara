package sara.nemo.br.ufes.inf.view;

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

public class CadastroHotran extends JFrame {
	ProprietarioCiaAereaDAO proprietarioCiaAereaDAO= new ProprietarioCiaAereaDAO();
	ProprietarioCiaAerea proprietarioCiaAerea= new ProprietarioCiaAerea();
	static Hotran hotran;
	static HotranDAO hotranDAO= new HotranDAO();
	FrequenciaDAO frequenciaDAO= new FrequenciaDAO();

	public static CadastroHotran frame;

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
	private JTextField txtEquipamento;

	JRadioButton rdbDomingo;
	JRadioButton rdbSegunda;
	JRadioButton rdbTerca;
	JRadioButton rdbQuarta;
	JRadioButton rdbQuinta;
	JRadioButton rdbSexta;
	JRadioButton rdbSabado;

	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				if (acao.equals("Inserir")) {
					frame = new CadastroHotran(frmMenuPrincipal, acao);
					frame.setVisible(true);
				}else if (acao.equals("Alterar")) {
					frame = new CadastroHotran(frmMenuPrincipal, acao);
					hotran= frame.getHotran();
					frame.setDados(hotran);
					frame.setVisible(true);
				}else if (acao.equals("Excluir")) {
					hotran=frame.getHotran();
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						hotranDAO.apagar(hotran.getId());
					}
				}

			}
		});
	}

	public CadastroHotran(JFrame frmMenuPrincipal, String acao) {
		setTitle("CADASTRO DE HOTRAN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 391);
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
		txtHorarioPrevPouso.setBounds(278, 103, 54, 19);
		contentPane.add(txtHorarioPrevPouso);
		txtHorarioPrevPouso.setColumns(10);

		JLabel lblHorarioPrevDecolagem = new JLabel("Horário previsto para decolagem");
		lblHorarioPrevDecolagem.setBounds(39, 130, 238, 15);
		contentPane.add(lblHorarioPrevDecolagem);

		txtHorarioPrevDecolagem = new JFormattedTextField("HH:mm");
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
		lblEscalasDeDestino.setBounds(113, 224, 136, 15);
		contentPane.add(lblEscalasDeDestino);

		txtEscalasDeDestino = new JTextField();
		txtEscalasDeDestino.setBounds(268, 224, 114, 19);
		contentPane.add(txtEscalasDeDestino);
		txtEscalasDeDestino.setColumns(10);

		JLabel lblEquipamento = new JLabel("Equipamento");
		lblEquipamento.setBounds(123, 184, 126, 15);
		contentPane.add(lblEquipamento);

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(278, 182, 114, 19);
		contentPane.add(txtEquipamento);
		txtEquipamento.setColumns(10);

		JLabel lblInicioDaVigencia = new JLabel("Inicio da vigencia");
		lblInicioDaVigencia.setBounds(128, 251, 121, 15);
		contentPane.add(lblInicioDaVigencia);

		txtInicioDaVigencia = new JFormattedTextField("dd/MM/aaaa");
		txtInicioDaVigencia.setBounds(268, 251, 87, 19);
		contentPane.add(txtInicioDaVigencia);
		txtInicioDaVigencia.setColumns(10);

		JLabel lblFimDaVigencia = new JLabel("Fim da vigencia");
		lblFimDaVigencia.setBounds(138, 278, 111, 15);
		contentPane.add(lblFimDaVigencia);

		txtFimDaVigencia = new JFormattedTextField("dd/MM/aaaa");
		txtFimDaVigencia.setBounds(268, 278, 87, 19);
		contentPane.add(txtFimDaVigencia);
		txtFimDaVigencia.setColumns(10);

		rdbDomingo = new JRadioButton("D");
		rdbDomingo.setBounds(126, 305, 35, 23);
		contentPane.add(rdbDomingo);

		rdbSegunda = new JRadioButton("S");
		rdbSegunda.setBounds(165, 305, 34, 23);
		contentPane.add(rdbSegunda);

		rdbTerca = new JRadioButton("T");
		rdbTerca.setBounds(203, 305, 35, 23);
		contentPane.add(rdbTerca);

		rdbQuarta = new JRadioButton("Q");
		rdbQuarta.setBounds(297, 305, 35, 23);
		contentPane.add(rdbQuarta);

		rdbQuinta = new JRadioButton("Q");
		rdbQuinta.setBounds(244, 305, 35, 23);
		contentPane.add(rdbQuinta);

		rdbSexta = new JRadioButton("S");
		rdbSexta.setBounds(349, 305, 43, 23);
		contentPane.add(rdbSexta);

		rdbSabado = new JRadioButton("S");
		rdbSabado.setBounds(388, 305, 35, 23);
		contentPane.add(rdbSabado);

		JLabel lblFrequencia = new JLabel("Frequencia");
		lblFrequencia.setBounds(39, 305, 111, 15);
		contentPane.add(lblFrequencia);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(189, 336, 117, 25);
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmMenuPrincipal.setVisible(false);
				if(acao.equals("Inserir")){
					hotran= getDados();
					try {
						hotranDAO.inserir(hotran);
						limpar();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if (acao.equals("Alterar")) {
					
					try {
						Hotran aux= getDados();
						
						hotran.setEquipamento(aux.getEquipamento());
						hotran.setEscalasDestino(aux.getEscalasDestino());
						hotran.setEscalasOrigem(aux.getEscalasOrigem());
						hotran.setFimVigencia(aux.getFimVigencia());
						hotran.setHorarioPrevistoDecolagem(aux.getHorarioPrevistoDecolagem());
						hotran.setHorarioPrevistoPouso(aux.getHorarioPrevistoPouso());
						hotran.setIdCiaAerea(aux.getIdCiaAerea());
						hotran.setIdFrequencia(aux.getIdFrequencia());
						hotran.setInicioVigencia(aux.getInicioVigencia());
						hotran.setNumeroVooDecola(aux.getNumeroVooDecola());
						hotran.setNumeroVooPousa(aux.getNumeroVooPousa());
						
						hotranDAO.alterar(hotran);
						limpar();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Dados Invalidos "+e1.getMessage());
					}

				}else if (acao.equals("Excluir")) {
					hotran= getHotran();
					if (hotran!= null)
						frame.setDados(hotran);
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						hotranDAO.apagar(hotran.getId());
					}

				}

				frmMenuPrincipal.setVisible(true);	
			}

		});
		contentPane.add(btnOK);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(366, 336, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnCancelar);

		ArrayList<String> sigla= proprietarioCiaAereaDAO.selecionarSigla();

		for (String s: sigla) {
			cbxCompanhia.addItem(s);
		}
	}

	public void limpar() {
		txtNumeroDoVooPouso.setText("");
		txtNumeroVooDecola.setText("");
		txtHorarioPrevPouso.setText("");
		txtHorarioPrevDecolagem.setText("");
		txtEscalasHorigem.setText("");
		txtEscalasDeDestino.setText("");
		txtInicioDaVigencia.setValue(null);
		txtFimDaVigencia.setValue(null);
		cbxCompanhia.setSelectedIndex(-1);
		txtEquipamento.setText("");
	}

	public Hotran getDados() {
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
		hotran.setEquipamento(txtEquipamento.getText().toUpperCase());
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

		return hotran;
	}

	public void setDados(Hotran hotran) {
		try {
			int idFreq= hotran.getIdFrequencia();
			int resto;
	
			txtNumeroDoVooPouso.setText(String.valueOf(hotran.getNumeroVooPousa()));
			txtNumeroVooDecola.setText(String.valueOf(hotran.getNumeroVooDecola()));
			txtHorarioPrevPouso.setText((hotran.getHorarioPrevistoPouso().toString()));
			txtHorarioPrevDecolagem.setText((hotran.getHorarioPrevistoDecolagem().toString()));
			txtEscalasHorigem.setText(hotran.getEscalasOrigem());
			txtEscalasDeDestino.setText(hotran.getEscalasDestino());
			txtEquipamento.setText(hotran.getEquipamento());
			txtInicioDaVigencia.setText(Converte.converteLocalDateToString(hotran.getInicioVigencia()));
			txtFimDaVigencia.setText(Converte.converteLocalDateToString(hotran.getFimVigencia()));
			
	
			rdbDomingo.setSelected(false);
			if (idFreq >= 64) {
				resto= idFreq% 64;
				rdbDomingo.setSelected(true);
				idFreq= resto;
			}
			rdbSegunda.setSelected(false);
			if (idFreq>= 32) {
				resto= idFreq% 32;
				rdbSegunda.setSelected(true);
				idFreq= resto;
			}
			rdbTerca.setSelected(false);
			if (idFreq >= 16) {
				resto= idFreq % 16;
				rdbTerca.setSelected(true);
				idFreq= resto;
			}
			rdbQuarta.setSelected(false);
			if (idFreq >= 8) {
				resto= idFreq% 8;
				rdbQuarta.setSelected(true);
				idFreq= resto;
			}
			rdbQuinta.setSelected(false);
			if(idFreq>= 4) {
				resto= idFreq% 4;
				rdbQuinta.setSelected(true);
				idFreq= resto;
			}
			rdbSexta.setSelected(false);
			if (idFreq>= 2) {
				resto= idFreq% 2;
				rdbSexta.setSelected(true);
			}
			rdbSabado.setSelected(false);
			if (idFreq>=1) {
				rdbSabado.setSelected(true);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Dados incorretos!"+ e.getMessage());
		}
	}

	public Hotran getHotran() {
		int id;
		Hotran hotran;
		String idStr=JOptionPane.showInputDialog("Digite o código do Hotran: ");
		if (idStr!= null) {
			id= Integer.parseInt(idStr);
			hotran= hotranDAO.selecionarById(id);
			return hotran;
		}else return null;

	}


}
