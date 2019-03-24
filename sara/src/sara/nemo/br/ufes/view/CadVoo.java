package sara.nemo.br.ufes.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadVoo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dataPrevistaParaPouso;
	private JTextField horaPrevistaParaPouso;
	private JTextField dataPrevistaParaDecolagem;
	private JTextField txtHoraPrevistaPara;
	private JTextField situacao;
	private JTextField origem;
	private JTextField destino;
	private JTextField categoria;
	private JLabel lblocorrenciaDoVoo;
	private JTextField ocorrenciaDoVoo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setBounds(100, 100, 456, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbldataPrevistaParaPouso = new JLabel("Data Prevista Para Pouso");
		
		dataPrevistaParaPouso = new JTextField();
		dataPrevistaParaPouso.setColumns(10);
		
		JLabel lblHoraprevistaparapouso = new JLabel("horaPrevistaParaPouso");
		
		horaPrevistaParaPouso = new JTextField();
		horaPrevistaParaPouso.setColumns(10);
		
		JLabel lbldataPrevistaParaDecolagem = new JLabel("Data Prevista Para Decolagem");
		
		dataPrevistaParaDecolagem = new JTextField();
		dataPrevistaParaDecolagem.setColumns(10);
		
		JLabel lblhoraPrevistaParaDecolagem = new JLabel("Hora Prevista Para Decolagem");
		
		txtHoraPrevistaPara = new JTextField();
		txtHoraPrevistaPara.setColumns(10);
		
		JLabel lblsituacao = new JLabel("Situacao");
		
		situacao = new JTextField();
		situacao.setColumns(10);
		
		JLabel lblorigem = new JLabel("Origem");
		
		origem = new JTextField();
		origem.setColumns(10);
		
		JLabel lbldestino = new JLabel("Destino");
		
		destino = new JTextField();
		destino.setColumns(10);
		
		JLabel lblcategoria = new JLabel("Categoria");
		
		categoria = new JTextField();
		categoria.setColumns(10);
		
		ocorrenciaDoVoo = new JTextField();
		ocorrenciaDoVoo.setColumns(10);
		
		lblocorrenciaDoVoo = new JLabel("Ocorrencia Do Voo");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JButton btnOK = new JButton("OK");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lbldataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(dataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblHoraprevistaparapouso, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(horaPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lbldataPrevistaParaDecolagem)
					.addGap(35)
					.addComponent(dataPrevistaParaDecolagem, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblhoraPrevistaParaDecolagem)
					.addGap(35)
					.addComponent(txtHoraPrevistaPara, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblsituacao, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(situacao, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblorigem, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(origem, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lbldestino, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(destino, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(ocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblcategoria, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(categoria, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(btnCancelar)
					.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
					.addComponent(btnOK)
					.addGap(37))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lbldataPrevistaParaPouso))
						.addComponent(dataPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblHoraprevistaparapouso))
						.addComponent(horaPrevistaParaPouso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lbldataPrevistaParaDecolagem))
						.addComponent(dataPrevistaParaDecolagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblhoraPrevistaParaDecolagem))
						.addComponent(txtHoraPrevistaPara, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblsituacao))
						.addComponent(situacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblorigem))
						.addComponent(origem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ocorrenciaDoVoo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblocorrenciaDoVoo))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOK))
					.addGap(49))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
