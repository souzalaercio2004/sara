package sara.nemo.br.ufes.view;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.tables.TableViewCategoria;


public class CadCategoria extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadCategoria frame = new CadCategoria();
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
	
	
	public CadCategoria() {
		Categoria cat= new Categoria();
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		
		
		setTitle("Cadastro de Categoria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Tipo Categoria");
		lblNewLabel.setBounds(70, 85, 123, 15);
		
		JLabel lblClasse = new JLabel("Classe");
		lblClasse.setBounds(115, 115, 78, 15);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblClasse);
		
		JLabel lblEspecificao = new JLabel("Especificação");
		lblEspecificao.setBounds(70, 150, 123, 15);
		contentPane.add(lblEspecificao);
		
		JLabel lblPassageiroOuCargueiro = new JLabel("Passageiro ou Cargueiro");
		lblPassageiroOuCargueiro.setBounds(12, 203, 181, 15);
		contentPane.add(lblPassageiroOuCargueiro);
		
		JComboBox<String> comboBoxCategoria = new JComboBox<String>();
		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setTipoCategoria((String)comboBoxCategoria.getSelectedItem());
			}
		});
		comboBoxCategoria.setBounds(207, 80, 198, 24);
		
		comboBoxCategoria.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma categoria", "Doméstico", "Internacional"}));
		contentPane.add(comboBoxCategoria);
		
		JComboBox<String> comboBox_Classe = new JComboBox<String>();
		comboBox_Classe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setClasse((String)comboBox_Classe .getSelectedItem());
			}
		});
		comboBox_Classe.setBounds(207, 110, 198, 24);
		comboBox_Classe.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma classe", "Regular", "Não Regular"}));
		contentPane.add(comboBox_Classe);
		
		JComboBox<String> comboBox_Especificacao = new JComboBox<String>();
		comboBox_Especificacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setEspecificacao((String)comboBox_Especificacao.getSelectedItem());
			}
		});
		comboBox_Especificacao.setBounds(207, 145, 231, 24);
		comboBox_Especificacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma especificacao", "Regular", "Alternado", "Charter", "Fretado", "Militar", "Translado"}));
		contentPane.add(comboBox_Especificacao);
		
		JComboBox<String> PassageiroOuCargueiro = new JComboBox<String>();
		
		PassageiroOuCargueiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setPassageiroOuCargueiro((String)PassageiroOuCargueiro.getSelectedItem());
			}
		});
		PassageiroOuCargueiro.setBounds(207, 198, 231, 24);
		PassageiroOuCargueiro.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione uma opção", "Passageiro", "Cargueiro"}));
		contentPane.add(PassageiroOuCargueiro);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					categoriaDAO.inserir(cat);
					JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInserir.setBounds(12, 269, 90, 25);
		contentPane.add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo= Integer.parseInt(JOptionPane.showInputDialog("Digite o código da Categoria: "));
				System.out.println("O codigo digitado foi: "+codigo);
					Categoria categoria= categoriaDAO.selecionarById(codigo);
					if (categoria != null) {
						cat.setId(codigo);
					}
					
				try {	
					categoriaDAO.alterar(cat);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(122, 269, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableViewCategoria.showTableViewCategoria();
			}
		});
		btnConsultar.setBounds(12, 306, 117, 25);
		contentPane.add(btnConsultar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(JOptionPane.showInputDialog("Digite o código da categoria a ser deletada: "));
				categoriaDAO.apagar(id);
			}
		});
		btnDeletar.setBounds(149, 306, 90, 25);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		btnCancelar.setBounds(251, 269, 117, 25);
		contentPane.add(btnCancelar);
	}
}
