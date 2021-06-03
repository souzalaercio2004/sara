package sara.nemo.br.ufes.inf.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.controller.ControlSituacaoPartida;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;

public class CadSituacaoPartidas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtData;
	private JFormattedTextField txtHora;
	private JTextField txtVooChegada;
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
	
	

	/**
	 * Launch the application.
	 */
	public static void showWindow(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadSituacaoPartidas frame = new CadSituacaoPartidas(partida, table, menu);
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
	public CadSituacaoPartidas(AcessoriosPartida partida, JTable table, JPopupMenu menu) {
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
		
		JLabel lblVooChegada = new JLabel("Voo Chegada");
		lblVooChegada.setBounds(378, 24, 127, 15);
		contentPane.add(lblVooChegada);
		
		txtVooChegada = new JTextField();
		txtVooChegada.setBounds(384, 47, 121, 19);
		contentPane.add(txtVooChegada);
		txtVooChegada.setColumns(10);
		
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
		
		setDados(partida, table);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setValueAt(ControlSituacaoPartida.getSelecao(), table.getSelectedRow(), 4);
				atualizarTabela(table);
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
		txtSituacao.setEditable(false); // Bloqueado para edicao
		txtDestino.setText(table.getValueAt(linha, 5).toString());
		txtTipoVoo.setText(table.getValueAt(linha, 6).toString());
		txtVooChegada.setText(table.getValueAt(linha, 7).toString());
		txtProcedencia.setText(table.getValueAt(linha, 8).toString());
		txtEquipamento.setText(table.getValueAt(linha, 9).toString().toUpperCase());
		txtCabeceira.setText(table.getValueAt(linha, 10).toString()); // Deve ser gerado automaticamente
		txtBox.setText(table.getValueAt(linha, 11).toString()); // Deve ser gerado automaticamente
		
		
	}
	public void atualizarTabela(JTable table) {
		String dataHoraAtualizada= txtData.getText()+" "+ txtHora.getText();
		table.setVisible(false);
		
		table.setValueAt(dataHoraAtualizada, table.getSelectedRow(), 1);		
		table.setValueAt(txtVooChegada.getText(), table.getSelectedRow(), 2);
		table.setValueAt(txtMatricula.getText().toUpperCase(), table.getSelectedRow(), 3);
		
		table.setValueAt(txtProcedencia.getText(), table.getSelectedRow(), 5);
		table.setValueAt(txtTipoVoo.getText(), table.getSelectedRow(), 6);
		table.setValueAt(txtVooPartida.getText(), table.getSelectedRow(), 7);
		table.setValueAt(txtDestino.getText().toUpperCase(), table.getSelectedRow(), 8);
		table.setValueAt(txtEquipamento.getText().toUpperCase(), table.getSelectedRow(), 9);
		table.setValueAt(txtCabeceira.getText(), table.getSelectedRow(), 10);
		table.setValueAt(txtBox.getText(), table.getSelectedRow(), 11);
		//Cadastrar voo na tabela como previsto - ocorrencia voo
		//Atualizar para confirmado - ocorrencia de voo
		table.setVisible(true);
	}
}
