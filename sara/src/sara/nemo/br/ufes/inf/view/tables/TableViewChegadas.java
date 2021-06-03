package sara.nemo.br.ufes.inf.view.tables;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import sara.nemo.br.ufes.inf.DAO.AcessoriosChegadaDAO;
import sara.nemo.br.ufes.inf.DAO.AcessoriosDAO;
import sara.nemo.br.ufes.inf.DAO.AcessoriosPartidaDAO;
import sara.nemo.br.ufes.inf.DAO.CategoriaDAO;
import sara.nemo.br.ufes.inf.DAO.ChegadasDAO;
import sara.nemo.br.ufes.inf.DAO.PartidasDAO;
import sara.nemo.br.ufes.inf.DAO.VooDAO;
import sara.nemo.br.ufes.inf.DAO.VooPartidaGrupoIDAO;
import sara.nemo.br.ufes.inf.controller.ControlSituacaoChegada;
import sara.nemo.br.ufes.inf.domain.Categoria;
import sara.nemo.br.ufes.inf.domain.Voo;
import sara.nemo.br.ufes.inf.domain.VooGrupoI;
import sara.nemo.br.ufes.inf.view.Converte;
import sara.nemo.br.ufes.inf.view.accessorios.Acessorios;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosChegada;
import sara.nemo.br.ufes.inf.view.accessorios.AcessoriosPartida;


public class TableViewChegadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static boolean flag= true;
	ChegadasTableModel modelo= new ChegadasTableModel();

	Voo voo= new Voo();
	VooDAO vooDAO= new VooDAO();
	VooGrupoI vooGrupoI= new VooGrupoI();
	VooPartidaGrupoIDAO vooPartidaGrupoIDAO= new VooPartidaGrupoIDAO();

	public TableViewChegadas() {
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		AcessoriosChegada chegada= new AcessoriosChegada();
		setTitle("CHEGADAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		table = new JTable(modelo);
		table.setName(" ");
		table.setForeground(Color.BLACK);
		table.setBorder(UIManager.getBorder("Button.border"));	

		JScrollPane scrollPane = new JScrollPane(table);

		//TRATAMENTO DO CLICK DO MOUSE
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()== 3) {
					JPopupMenu situacao= ControlSituacaoChegada.menuSituacao(chegada, table); // chegadas -> tela que aciona o JPopupMenu
					situacao.show(e.getComponent(), e.getX(), e.getY());

				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(30, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1260, GroupLayout.PREFERRED_SIZE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(49, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);

		//DAR NOME AS COLUNAS
		table.getColumnModel().getColumn(0).setHeaderValue(modelo.getColumName(0));
		table.getColumnModel().getColumn(1).setHeaderValue(modelo.getColumName(1));
		table.getColumnModel().getColumn(2).setHeaderValue(modelo.getColumName(2));
		table.getColumnModel().getColumn(3).setHeaderValue(modelo.getColumName(3));
		table.getColumnModel().getColumn(4).setHeaderValue(modelo.getColumName(4));
		table.getColumnModel().getColumn(5).setHeaderValue(modelo.getColumName(5));
		table.getColumnModel().getColumn(6).setHeaderValue(modelo.getColumName(6));
		table.getColumnModel().getColumn(7).setHeaderValue(modelo.getColumName(7));
		table.getColumnModel().getColumn(8).setHeaderValue(modelo.getColumName(8));
		table.getColumnModel().getColumn(9).setHeaderValue(modelo.getColumName(9));
		table.getColumnModel().getColumn(10).setHeaderValue(modelo.getColumName(10));
		table.getColumnModel().getColumn(11).setHeaderValue(modelo.getColumName(11));
		table.getColumnModel().getColumn(12).setHeaderValue(modelo.getColumName(12));
		table.getColumnModel().getColumn(13).setHeaderValue(modelo.getColumName(13));
		table.getColumnModel().getColumn(14).setHeaderValue(modelo.getColumName(14));

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(65);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		table.getColumnModel().getColumn(10).setPreferredWidth(30);
		table.getColumnModel().getColumn(11).setPreferredWidth(30);
		table.getColumnModel().getColumn(12).setPreferredWidth(30);
		table.getColumnModel().getColumn(13).setPreferredWidth(0);// Armazena o IdHotran escondido
		table.getColumnModel().getColumn(14).setPreferredWidth(0);

		table.getColumnModel().getColumn(13).setMinWidth(0);
		table.getColumnModel().getColumn(13).setMaxWidth(0);
		table.getColumnModel().getColumn(14).setMinWidth(0);
		table.getColumnModel().getColumn(14).setMaxWidth(0);

		// CENTRALISAR OS VALORES NAS COLUNAS		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(9).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(10).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(11).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(12).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(13).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(14).setCellRenderer( centerRenderer );

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		ChegadasDAO chegadasDAO= new ChegadasDAO();
		LocalDate dataHoje= null;
		int diaHoje;

		String data= (JOptionPane.showInputDialog("Digite a data dos Voos no formato dd/mm/aaaa : "));
		if (data != null) {
			dataHoje= Converte.converteStringToLocalDateNoFormatoDDMMAAAA(data, "dd/MM/yyyy");

			flag= true;
		}else flag= false;
		if (dataHoje!= null) {
			diaHoje= dataHoje.getDayOfWeek().getValue();																
			String dia;


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
			Acessorios acessorioDado;
			AcessoriosDAO acessoriosDAO= new AcessoriosDAO();
			AcessoriosChegadaDAO acessoriosChegadaDAO= new AcessoriosChegadaDAO();

			ArrayList<Acessorios> acessorios;
			//VooPartidaGrupoI vooPartidaGrupoI= null;
			try {
				acessorios = acessoriosDAO.obterAcessorios(dataHoje);
				System.out.println("Esta vazio: "+ acessorios.isEmpty());
				if(acessorios.isEmpty()) {

					for(AcessoriosChegada c: chegadasDAO.selecionarDadosDeChegada(dataHoje, dia)) {
						int idCategoria= categoriaDAO.selecionarIdCategoria("Domestico","Passageiro", "Regular", "Regular");	
						Categoria cat= categoriaDAO.selecionarById(idCategoria);
						c.setTipo(cat.abreviarCategoria());
						acessorioDado= new Acessorios();

						try {
							acessorioDado.setDataHoraAtualizada(c.getDataHoraAtualizada());
							acessorioDado.setDataHoraPrevista(c.getDataHoraPrevista());
							acessorioDado.setEquipamento(c.getEquipamento());
							acessorioDado.setIdHotran(c.getIdHotran());
							acessorioDado.setMatricula(c.getMatricula());
							acessorioDado.setSituacao(c.getSituacao());
							acessorioDado.setTipo(c.getTipo());
							acessorioDado.setNomeBox(c.getNomeBox());

							acessoriosDAO.inserir(acessorioDado);
							int id= acessoriosDAO.selecionarMaximoID();

							if(id >0) {
								c.setIdAcessoriosChegada(id);
								acessoriosChegadaDAO.inserir(c);
							}

							//modelo.addlinha(c);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

					PartidasDAO partidasDAO= new PartidasDAO();
					AcessoriosPartidaDAO acessoriosPartidaDAO= new AcessoriosPartidaDAO();
					acessorioDado= new Acessorios();
					/*
					Date dataPartida= Converte.converterLocalDateToJavaSqlDate(dataHoje.minusDays(1));
					System.out.println("Data da Partida...........: "+dataPartida);
					vooPartidaGrupoI= vooPartidaGrupoIDAO.selecionarVooGrupoIPartidabyDate(dataPartida);
					*/
					//if (vooPartidaGrupoI== null) {
						//System.out.println("Voo partida retornou nulo..........");
						for(AcessoriosPartida c: partidasDAO.selecionarDadosDePartida(dataHoje, dia)) {
							int idCategoria= categoriaDAO.selecionarIdCategoria("Domestico","Passageiro", "Regular", "Regular");	
							Categoria cat= categoriaDAO.selecionarById(idCategoria);
							c.setTipo(cat.abreviarCategoria());
							acessorioDado= new Acessorios();
							try {

								acessorioDado.setDataHoraPrevista(c.getDataHoraPrevista());
								acessorioDado.setDataHoraAtualizada(c.getDataHoraAtualizada());
								acessorioDado.setMatricula(c.getMatricula());
								acessorioDado.setSituacao(c.getSituacao());
								acessorioDado.setTipo(c.getTipo());
								acessorioDado.setEquipamento(c.getEquipamento());
								acessorioDado.setNomeBox(c.getNomeBox());
								acessorioDado.setIdHotran(c.getIdHotran());

								acessoriosDAO.inserir(acessorioDado);
								int id= acessoriosDAO.selecionarMaximoID();

								if(id >0) {
									c.setIdAcessoriosPartida(id);
									acessoriosPartidaDAO.inserir(c);
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

				}

				for (AcessoriosChegada lst: acessoriosChegadaDAO.obterAcessoriosChegadaDeVoos()) {

					lst.converte(lst);// Retorna data e hora no padrao dd/mm/aaaa hh:mm
					if (!lst.getSituacao().equals("ETC")) modelo.addlinha(lst);

				}

				for (AcessoriosChegada lst: acessoriosChegadaDAO.obterAcessoriosChegada()) {
					lst.converte(lst); // Retorna data e hora no padrao dd/mm/aaaa hh:mm

					if (!lst.getSituacao().equals("ETC")) modelo.addlinha(lst);
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}else {
			flag= false;
		}
		getContentPane().add(scrollPane);

	}

	public static void showTableViewChegadas(){
		EventQueue.invokeLater(new Runnable() {
			public void run() throws DateTimeParseException {
				TableViewChegadas frame;
				frame = new TableViewChegadas();
				frame.setVisible(flag);
			}
		});


	}
	public ChegadasTableModel getModel() {
		return modelo;
	} 	
}
