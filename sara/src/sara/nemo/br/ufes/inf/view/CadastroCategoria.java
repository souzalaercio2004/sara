package sara.nemo.br.ufes.inf.view;



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


public class CadastroCategoria extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String acao;
	CadastroCategoria frame;
	public static CategoriaDAO categoriaDAO= new CategoriaDAO();
	
	JComboBox<String> comboBoxCategoria;
	JComboBox<String> comboBox_Classe;
	JComboBox<String> comboBox_Especificacao;
	JComboBox<String> PassageiroOuCargueiro;
	
	public static void showWindow(JFrame frmMenuPrincipal, String acao) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (acao.equals("Inserir")) {
					CadastroCategoria frm = new CadastroCategoria(frmMenuPrincipal, acao);
					frm.setVisible(true);
				}else if (acao.equals("Alterar")||(acao.equals("Excluir"))) {
					CadastroCategoria frm = new CadastroCategoria(frmMenuPrincipal, acao);
					try {
						String id= JOptionPane.showInputDialog("Codigo da categoria: ");
						int idCat= Integer.parseInt(id);
						Categoria categoria= categoriaDAO.selecionarById(idCat);
						frm.setarDados(acao, categoria);
						frm.setVisible(true);
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Dados incorretos!"+ e.getMessage());
					}
				}
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public CadastroCategoria(JFrame frmMenuPrincipal, String acao) {
		this.acao= acao;
		Categoria cat= new Categoria();
		
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
		
		comboBoxCategoria = new JComboBox<String>();
		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setTipoCategoria((String)comboBoxCategoria.getSelectedItem());
			}
		});
		comboBoxCategoria.setBounds(207, 80, 198, 24);
		
		comboBoxCategoria.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma categoria", "Doméstico", "Internacional"}));
		contentPane.add(comboBoxCategoria);
		
		comboBox_Classe = new JComboBox<String>();
		comboBox_Classe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setClasse((String)comboBox_Classe .getSelectedItem());
			}
		});
		comboBox_Classe.setBounds(207, 110, 198, 24);
		comboBox_Classe.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma classe", "Regular", "Não Regular"}));
		contentPane.add(comboBox_Classe);
		
		comboBox_Especificacao = new JComboBox<String>();
		comboBox_Especificacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setEspecificacao((String)comboBox_Especificacao.getSelectedItem());
			}
		});
		comboBox_Especificacao.setBounds(207, 145, 231, 24);
		comboBox_Especificacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha uma especificacao", "Regular", "Alternado", "Charter", "Fretado", "Militar", "Translado"}));
		contentPane.add(comboBox_Especificacao);
		
		PassageiroOuCargueiro = new JComboBox<String>();
		
		PassageiroOuCargueiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setPassageiroOuCargueiro((String)PassageiroOuCargueiro.getSelectedItem());
			}
		});
		PassageiroOuCargueiro.setBounds(207, 198, 231, 24);
		PassageiroOuCargueiro.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione uma opção", "Passageiro", "Cargueiro"}));
		contentPane.add(PassageiroOuCargueiro);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoriaDAO categoriaDAO= new CategoriaDAO();
				Categoria categoria;
				if (acao.equals("Inserir")) {
					categoria= getDados();
					try {
						categoriaDAO.inserir(categoria);
						limpar();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Categoria Inválida");
						e1.printStackTrace();
					}	
				}else if (acao.equals("Alterar")) {
					categoria= getDados();
					int id= categoriaDAO.selecionarIdCategoria(categoria.getTipoCategoria(), categoria.getPassageiroOuCargueiro(),
							categoria.getClasse(), categoria.getEspecificacao());
					Categoria aux= categoriaDAO.selecionarById(id);
					aux.setClasse(categoria.getClasse());
					aux.setEspecificacao(categoria.getEspecificacao());
					aux.setPassageiroOuCargueiro(categoria.getPassageiroOuCargueiro());
					aux.setTipoCategoria(categoria.getTipoCategoria());
					try {
						categoriaDAO.alterar(aux);
						limpar();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Categoria Inválida");
						e1.printStackTrace();
					}
					
				}else if (acao.equals("Excluir")) {
					
					String confirma= JOptionPane.showInputDialog("Confirma Exclusão (S/N)? ");
					if ((confirma.equals("s"))||(confirma.equals("S"))) {
						categoria=getDados();
						int id= categoriaDAO.selecionarIdCategoria(categoria.getTipoCategoria(), categoria.getPassageiroOuCargueiro(),
									categoria.getClasse(), categoria.getEspecificacao());
						
						categoria.setId(id);
						categoriaDAO.apagar(categoria.getId());
						
						limpar();
					}
				}
				frmMenuPrincipal.setVisible(true);
			}
				
		});
		btnOK.setBounds(122, 269, 117, 25);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frmMenuPrincipal.setVisible(true);
				
			}
		});
		btnCancelar.setBounds(321, 269, 117, 25);
		contentPane.add(btnCancelar);
	}
	
	public void setAcao(String acao) {
		this.acao= acao;
	}
	
	public String getAcao() {
		return acao;
	}
	
	public void setDados(Categoria categoria) {
		comboBoxCategoria.setSelectedItem(categoria.getTipoCategoria());
		comboBox_Classe.setSelectedItem(categoria.getClasse());
		comboBox_Especificacao.setSelectedItem(categoria.getEspecificacao());
		PassageiroOuCargueiro.setSelectedItem(categoria.getPassageiroOuCargueiro());
	}
	
	public Categoria getDados() {
		
		Categoria cat= new Categoria();
		
		cat.setTipoCategoria((String)comboBoxCategoria.getSelectedItem());
		cat.setClasse((String)comboBox_Classe.getSelectedItem());
		cat.setEspecificacao((String)comboBox_Especificacao.getSelectedItem());
		cat.setPassageiroOuCargueiro((String)PassageiroOuCargueiro.getSelectedItem());
		
		return cat;
	}
	
	public void limpar() {
		comboBoxCategoria.setSelectedIndex(0);
		comboBox_Classe.setSelectedIndex(0);
		comboBox_Especificacao.setSelectedIndex(0);
		PassageiroOuCargueiro.setSelectedIndex(0);
	}
	
	public void setarDados(String acao, Categoria categoria) {
			comboBoxCategoria.setSelectedItem(categoria.getTipoCategoria());
			comboBox_Classe.setSelectedItem(categoria.getClasse());
			comboBox_Especificacao.setSelectedItem(categoria.getEspecificacao());
			PassageiroOuCargueiro.setSelectedItem(categoria.getPassageiroOuCargueiro());
	}
	public Categoria getCategoria() {
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		Categoria categoria;
		try {
			String idStr=JOptionPane.showInputDialog("Digite o código da Categoria: ");
			int id= Integer.parseInt(idStr);
			categoria= categoriaDAO.selecionarById(id);
			return (categoria);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados incorretos!"+ e.getMessage());
		}
		return null;
	}
}
