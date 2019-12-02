package sara.nemo.br.ufes.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class CadVoo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dataPrevistaParaPouso;
	private JTextField horaPrevistaParaPouso;
	private JTextField dataPrevistaParaDecolagem;
	private JTextField txtHoraPrevistaParaDecolagem;
	private JTextField situacao;
	private JTextField origem;
	private JTextField destino;
	private JTextField categoria;
	private JLabel lblocorrenciaDoVoo;
	private JTextField ocorrenciaDoVoo;

	/**
	 * Launch the application.
	 */
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVoo frame = new CadVoo();
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
	public CadVoo() {
		setTitle("CADASTRO DE VOOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 434);
		contentPane = 
				new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbldataPrevistaParaPouso = new JLabel("Data Prevista Para Pouso");
		lbldataPrevistaParaPouso.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dataPrevistaParaPouso = new JFormattedTextField("dd/MM/aaaa");
		dataPrevistaParaPouso.setColumns(10);
		
		JLabel lblHoraprevistaparapouso = new JLabel("horaPrevistaParaPouso");
		lblHoraprevistaparapouso.setHorizontalAlignment(SwingConstants.RIGHT);
		
		horaPrevistaParaPouso = new JFormattedTextField("HH:mm");
		horaPrevistaParaPouso.setColumns(10);
		
		JLabel lbldataPrevistaParaDecolagem = new JLabel("Data Prevista Para Decolagem");
		lbldataPrevistaParaDecolagem.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dataPrevistaParaDecolagem = new JFormattedTextField("dd/MM/aaaa");
		dataPrevistaParaDecolagem.setColumns(10);
		
		JLabel lblhoraPrevistaParaDecolagem = new JLabel("Hora Prevista Para Decolagem");
		lblhoraPrevistaParaDecolagem.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtHoraPrevistaParaDecolagem = new JFormattedTextField("HH:mm");
		txtHoraPrevistaParaDecolagem.setColumns(10);
		
		JLabel lblsituacao = new JLabel("Situacao");
		lblsituacao.setHorizontalAlignment(SwingConstants.RIGHT);
		
		situacao = new JTextField();
		situacao.setHorizontalAlignment(SwingConstants.LEFT);
		situacao.setColumns(10);
		
		JLabel lblorigem = new JLabel("Origem");
		lblorigem.setHorizontalAlignment(SwingConstants.RIGHT);
		
		origem = new JTextField();
		origem.setHorizontalAlignment(SwingConstants.LEFT);
		origem.setColumns(10);
		
		JLabel lbldestino = new JLabel("Destino");
		lbldestino.setHorizontalAlignment(SwingConstants.RIGHT);
		
		destino = new JTextField();
		destino.setHorizontalAlignment(SwingConstants.LEFT);
		destino.setColumns(10);
		
		JLabel lblcategoria = new JLabel("Categoria");
		lblcategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		
		categoria = new JTextField();
		categoria.setHorizontalAlignment(SwingConstants.LEFT);
		categoria.setColumns(10);
		
		ocorrenciaDoVoo = new JTextField();
		ocorrenciaDoVoo.setHorizontalAlignment(SwingConstants.LEFT);
		ocorrenciaDoVoo.setColumns(10);
		
		lblocorrenciaDoVoo = new JLabel("Ocorrencia Do Voo");
		lblocorrenciaDoVoo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnIncluir = new JButton("Incluir");
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		
		JButton btnExcluir = new JButton("Excluir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lbldataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(dataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblorigem, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(origem, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lbldestino, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(destino, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblcategoria, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(categoria, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(ocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(btnIncluir)
							.addGap(32)
							.addComponent(btnAlterar)
							.addGap(54)
							.addComponent(btnCancelar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(btnConsultar)
							.addGap(18)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblHoraprevistaparapouso, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(horaPrevistaParaPouso, 0, 0, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lbldataPrevistaParaDecolagem)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dataPrevistaParaDecolagem, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblhoraPrevistaParaDecolagem)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtHoraPrevistaParaDecolagem, 0, 0, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblsituacao, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(situacao, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))))
					.addGap(96))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lbldataPrevistaParaPouso))
						.addComponent(dataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblHoraprevistaparapouso))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(horaPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lbldataPrevistaParaDecolagem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(dataPrevistaParaDecolagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblhoraPrevistaParaDecolagem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(txtHoraPrevistaParaDecolagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblsituacao))
						.addComponent(situacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblorigem))
						.addComponent(origem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lbldestino))
						.addComponent(destino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblcategoria))
						.addComponent(categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblocorrenciaDoVoo))
						.addComponent(ocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnCancelar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConsultar)
						.addComponent(btnExcluir)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
