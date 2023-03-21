package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DaoVendas;
import autocomplete.AutoCompleteDecorator;
import controller.ControllerCliente;
import controller.ControllerProdutos;
import controller.ControllerProdutosVendasProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasCliente;
import controller.ControllerVendasProdutos;
import model.ModelClientes;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendas;
import model.ModelVendasCliente;
import model.ModelVendasProdutos;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewVenda extends JFrame {

	private JPanel contentPane;
	private final JPanel panelConsulta = new JPanel();
	private JTextField txtCodigoCliente;
	private JTextField txtNumeroVenda;
	private JTextField txtCodigoProduto;
	private JTextField txtQuantidade;
	private JTable tableProdutos;
	private JTextField txtDesconto;
	private JTextField txtValorTotal;
	private JTextField txtPesquisar;
	private JTable tableVendas;
	private JComboBox cbNomeCliente;
	private JComboBox cbNomeProduto;
	private JButton btnSalvar;
	private JButton btnAlterar;

	ControllerCliente controllerCliente = new ControllerCliente();
	ModelClientes modelCliente = new ModelClientes();
	List<ModelClientes> listaModelCliente = new ArrayList<>();

	ControllerProdutos controllerProduto = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();
	List<ModelProdutos> listaModelProduto = new ArrayList<>();
	List<ModelVendasCliente> listaModelVendasClientes = new ArrayList<>();
	ControllerVendasCliente controllerVendasCliente = new ControllerVendasCliente();
	ControllerVendas controllerVendas = new ControllerVendas();
	ModelVendas modelVendas = new ModelVendas();
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	List<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
	ControllerProdutosVendasProdutos controllerProdutosVendasProdutos = new ControllerProdutosVendasProdutos();
	ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
	List<ModelProdutosVendasProdutos> listaModelProdutosVendasProdutos = new ArrayList<>();
	private String salvarAlterar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVenda frame = new ViewVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ViewVenda() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 150, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setTitle("Vendas");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 550, 425);
		contentPane.add(tabbedPane);

		JPanel panelCadastro = new JPanel();
		tabbedPane.addTab("Cadastro", null, panelCadastro, null);
		panelCadastro.setLayout(null);

		JLabel lblCodigoCliente = new JLabel("Codigo Cliente:");
		lblCodigoCliente.setBounds(12, 12, 120, 15);
		panelCadastro.add(lblCodigoCliente);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					modelCliente = controllerCliente.getClienteController(Integer.parseInt(txtCodigoCliente.getText()));
					cbNomeCliente.setSelectedItem(modelCliente.getNomeCliente());
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtCodigoCliente.setBounds(12, 30, 115, 20);
		panelCadastro.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		JLabel lblNomeCliente = new JLabel("Nome Cliente:");
		lblNomeCliente.setBounds(135, 12, 110, 15);
		panelCadastro.add(lblNomeCliente);

		cbNomeCliente = new JComboBox();
		cbNomeCliente.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (cbNomeCliente.isPopupVisible()) {
					preencherCodigoClienteComboBox();
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		AutoCompleteDecorator.decorate(this.cbNomeCliente);
		cbNomeCliente.setBounds(135, 30, 225, 20);
		panelCadastro.add(cbNomeCliente);

		JLabel lblNmeroVenda = new JLabel("Número Venda:");
		lblNmeroVenda.setBounds(370, 12, 120, 15);
		panelCadastro.add(lblNmeroVenda);

		txtNumeroVenda = new JTextField();
		txtNumeroVenda.setBounds(370, 30, 160, 20);
		panelCadastro.add(txtNumeroVenda);
		txtNumeroVenda.setColumns(10);

		JLabel lblCdigoProduto = new JLabel("Código Produto:");
		lblCdigoProduto.setBounds(12, 60, 115, 15);
		panelCadastro.add(lblCdigoProduto);

		txtCodigoProduto = new JTextField();
		txtCodigoProduto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					modelProdutos = controllerProduto
							.getProdutoController(Integer.parseInt(txtCodigoProduto.getText()));
					cbNomeProduto.setSelectedItem(modelProdutos.getNomeProduto());
				} catch (NumberFormatException | SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		txtCodigoProduto.setBounds(12, 80, 115, 20);
		panelCadastro.add(txtCodigoProduto);
		txtCodigoProduto.setColumns(10);

		JLabel lblNomeProduto = new JLabel("Nome Produto:");
		lblNomeProduto.setBounds(140, 60, 115, 15);
		panelCadastro.add(lblNomeProduto);

		cbNomeProduto = new JComboBox();
		cbNomeProduto.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (cbNomeProduto.isVisible()) {
					preencherCodigoProdutoComboBox();
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		AutoCompleteDecorator.decorate(this.cbNomeProduto);
		cbNomeProduto.setBounds(135, 80, 225, 20);
		panelCadastro.add(cbNomeProduto);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(370, 60, 115, 15);
		panelCadastro.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(370, 80, 50, 20);
		panelCadastro.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtQuantidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo está vazio", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						modelProdutos = controllerProduto
								.getProdutoController(Integer.parseInt(txtCodigoProduto.getText()));
						DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
						int cont = 0;
						for (int i = 0; i < cont; i++) {
							modelo.setNumRows(0);
						}
						double quantidade = Double.parseDouble(txtQuantidade.getText());
						modelo.addRow(new Object[] { modelProdutos.getIdProduto(), modelProdutos.getNomeProduto(),
								quantidade, modelProdutos.getValorProduto(),
								(quantidade * modelProdutos.getValorProduto()) });
						somar();
						txtQuantidade.setText("");

					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdicionar.setBounds(430, 80, 100, 20);
		panelCadastro.add(btnAdicionar);

		JPanel panelTable = new JPanel();
		panelTable.setBounds(5, 100, 530, 286);
		panelCadastro.add(panelTable);
		panelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 10, 525, 200);
		panelTable.add(scrollPane);

		tableProdutos = new JTable();
		tableProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cód. Produto", "Nome Produto", "Quantidade", "Valor Unitario", "Valor Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableProdutos);
		tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(100);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(5, 208, 525, 80);
		panelTable.add(panelBotoes);
		panelBotoes.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarDesabilitarCampos(false);
			}
		});
		btnCancelar.setBounds(12, 50, 117, 20);
		panelBotoes.add(btnCancelar);

		JButton btnRemoverProdutos = new JButton("Remover itens");
		btnRemoverProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tableProdutos.getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
				modelo.removeRow(linha);
				somar();
			}
		});
		btnRemoverProdutos.setBounds(12, 25, 245, 20);
		panelBotoes.add(btnRemoverProdutos);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarDesabilitarCampos(true);
				salvarAlterar = "salvar";
				limparFormulario();
			}
		});
		btnNovo.setBounds(141, 50, 117, 20);
		panelBotoes.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int codigoVenda = 0, codigoProduto = 0;
				double desconto = 0;
				listaModelVendasProdutos = new ArrayList<>();

				if (txtDesconto.getText().equals("")) {
					desconto = 0;
				} else {
					desconto = Double.parseDouble(txtDesconto.getText());
				}

				if (!txtNumeroVenda.getText().equals("")) {
					modelVendas.setIdVenda(Integer.parseInt(txtNumeroVenda.getText()));
				}

				modelVendas.setCliente(Integer.parseInt(txtCodigoCliente.getText()));
				Date DataAtual = new Date(System.currentTimeMillis());
				modelVendas.setDataVendas(DataAtual);
				modelVendas.setValorLiquidoVenda(Double.parseDouble(txtValorTotal.getText()));
				modelVendas.setValorBrutoVenda(desconto + Double.parseDouble(txtValorTotal.getText()));
				modelVendas.setDescontoVenda(desconto);

				if (salvarAlterar.equals("salvar")) {
					// salvar venda
					try {
						controllerVendas.salvarVendasController(modelVendas);
						codigoVenda = controllerVendas.retornarIdVendasController(modelVendas.getIdVenda());
						JOptionPane.showMessageDialog(null, "Venda salva com sucesso", "ATENÇÃO",
								JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar a venda", "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

					int cont = tableProdutos.getRowCount();
					for (int i = 0; i < cont; i++) {
						codigoProduto = (int) tableProdutos.getValueAt(i, 0);
						modelVendasProdutos = new ModelVendasProdutos();
						modelProdutos = new ModelProdutos();
						modelVendasProdutos.setValorProdutoVenda((double) tableProdutos.getValueAt(i, 3));
						modelVendasProdutos.setQuantiadeProdutoVenda((double) tableProdutos.getValueAt(i, 2));
						modelVendasProdutos.setProduto(codigoProduto);
						modelVendasProdutos.setVenda(codigoVenda);

						// tirar produtos do estoque
						modelProdutos.setIdProduto(codigoProduto);
						try {
							modelProdutos.setEstoqueProduto(
									(int) (controllerProduto.getProdutoController(codigoProduto).getEstoqueProduto()
											- (double) tableProdutos.getValueAt(i, 2)));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						listaModelVendasProdutos.add(modelVendasProdutos);
						listaModelProduto.add(modelProdutos);

					}
					// salvar os produtos da venda
					try {
						controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos);
						controllerProduto.alterarEtoqueProdutoController(listaModelProduto);
						//JOptionPane.showMessageDialog(null, "Produtos da venda salvo com sucesso", "ATENÇÃO",
								//JOptionPane.WARNING_MESSAGE);
						limparFormulario();
						carregarVendas();
						habilitarDesabilitarCampos(false);
					} catch (SQLException e1) {

						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao salvar os produtos da venda", "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE);
					}

				} else if (salvarAlterar.equals("alterar")) {
					// retornar para o estoque e excluir produtos da venda
					int linha = tableVendas.getSelectedRow();
					codigoVenda = Integer.parseInt(tableVendas.getValueAt(linha, 0).toString());
					listaModelProduto = new ArrayList<>();

					try {
						listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos
								.getListaProdutosVendasProdutosController(codigoVenda);
						for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
							modelProdutos = new ModelProdutos();
							modelProdutos.setIdProduto(
									listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto());
							modelProdutos.setEstoqueProduto((int) (listaModelProdutosVendasProdutos.get(i)
									.getModelProdutos().getEstoqueProduto()
									+ listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos()
											.getQuantiadeProdutoVenda()));
							listaModelProduto.add(modelProdutos);
						}

						if (controllerProduto.alterarEtoqueProdutoController(listaModelProduto)) {
							if (controllerVendasProdutos.excluirVendaProdutoController(codigoVenda)) {
								//JOptionPane.showMessageDialog(null, "Venda Excluida com sucesso", "ATENÇÃO",
										//JOptionPane.WARNING_MESSAGE);
								carregarVendas();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao excluir a  venda", "ERRO",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir a  venda", "ERRO",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					// FIM DO retornar para o estoque e excluir produtos da venda

					try {
						if (controllerVendas.alterarVendaController(modelVendas)) {
							JOptionPane.showMessageDialog(null, "Venda Alterada com sucesso", "ATENÇÃO",
									JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao alterar a  venda", "ERRO",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					// adicionar produtos na lista para salvar
					int cont = tableProdutos.getRowCount();
					for (int i = 0; i < cont; i++) {
						codigoProduto = (int) tableProdutos.getValueAt(i, 0);
						modelVendasProdutos = new ModelVendasProdutos();
						modelProdutos = new ModelProdutos();
						modelVendasProdutos.setValorProdutoVenda((double) tableProdutos.getValueAt(i, 3));
						modelVendasProdutos.setQuantiadeProdutoVenda((double) tableProdutos.getValueAt(i, 2));
						modelVendasProdutos.setProduto(codigoProduto);
						modelVendasProdutos.setVenda(Integer.parseInt(txtNumeroVenda.getText()));

						// tirar produtos do estoque
						modelProdutos.setIdProduto(codigoProduto);
						try {
							modelProdutos.setEstoqueProduto(
									(int) (controllerProduto.getProdutoController(codigoProduto).getEstoqueProduto()
											- (double) tableProdutos.getValueAt(i, 2)));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						listaModelVendasProdutos.add(modelVendasProdutos);
						listaModelProduto.add(modelProdutos);
					}

					// salvar os produtos da venda
					try {
						if (controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
							//JOptionPane.showMessageDialog(null, "Produtos salvos com sucesso", "ATENÇÃO",
									//JOptionPane.WARNING_MESSAGE);
							carregarVendas();
							limparFormulario();
							habilitarDesabilitarCampos(false);

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao salvar os produtos da  venda", "ERRO",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Opção invalida");
				}
			}
		});
		btnSalvar.setBounds(390, 50, 114, 20);
		btnSalvar.setEnabled(false);
		panelBotoes.add(btnSalvar);

		JLabel lblDesconto = new JLabel("Desconto:");
		lblDesconto.setBounds(265, 5, 115, 15);
		panelBotoes.add(lblDesconto);

		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(390, 5, 115, 15);
		panelBotoes.add(lblValorTotal);

		txtDesconto = new JTextField();
		txtDesconto.setBounds(265, 25, 114, 20);
		txtDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				somar();
			}
		});
		panelBotoes.add(txtDesconto);
		txtDesconto.setColumns(10);

		txtValorTotal = new JTextField();
		txtValorTotal.setBounds(390, 25, 114, 20);
		panelBotoes.add(txtValorTotal);
		txtValorTotal.setColumns(10);
		tabbedPane.addTab("Consulta", null, panelConsulta, null);
		panelConsulta.setLayout(null);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setBounds(12, 12, 70, 15);
		panelConsulta.add(lblPesquisa);

		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(12, 30, 300, 20);
		panelConsulta.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(325, 30, 117, 20);
		panelConsulta.add(btnPesquisar);

		JPanel panelbtnAbaConsulta = new JPanel();
		panelbtnAbaConsulta.setBounds(5, 320, 525, 50);
		panelConsulta.add(panelbtnAbaConsulta);
		panelbtnAbaConsulta.setLayout(null);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tableVendas.getSelectedRow();
				int codigoVenda = Integer.parseInt(tableVendas.getValueAt(linha, 0).toString());
				listaModelProduto = new ArrayList<>();

				try {
					listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos
							.getListaProdutosVendasProdutosController(codigoVenda);
					for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
						modelProdutos = new ModelProdutos();
						modelProdutos.setIdProduto(
								listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto());
						modelProdutos.setEstoqueProduto(
								(int) (listaModelProdutosVendasProdutos.get(i).getModelProdutos().getEstoqueProduto()
										+ listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos()
												.getQuantiadeProdutoVenda()));
						listaModelProduto.add(modelProdutos);
					}

					if (controllerProduto.alterarEtoqueProdutoController(listaModelProduto)) {
						controllerVendasProdutos.excluirVendaProdutoController(codigoVenda);
						if (controllerVendas.excluirVendaController(codigoVenda)) {
							JOptionPane.showMessageDialog(null, "Venda Excluida com sucesso", "ATENÇÃO",
									JOptionPane.WARNING_MESSAGE);
							carregarVendas();
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir a  venda", "ERRO",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao excluir a  venda", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnExcluir.setBounds(12, 12, 117, 25);
		panelbtnAbaConsulta.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarDesabilitarCampos(true);
				salvarAlterar = "alterar";
				int linha = tableVendas.getSelectedRow();
				int codigoVenda = Integer.parseInt(tableVendas.getValueAt(linha, 0).toString());
				try {
					listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos
							.getListaProdutosVendasProdutosController(codigoVenda);
					DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
					modelo.setNumRows(0);

					for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
						txtNumeroVenda.setText(String
								.valueOf(listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenda()));
						modelo.addRow(new Object[] {
								listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto(),
								listaModelProdutosVendasProdutos.get(i).getModelProdutos().getNomeProduto(),
								listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos()
										.getQuantiadeProdutoVenda(),
								listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getValorProdutoVenda(),
								listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos()
										.getQuantiadeProdutoVenda()
										* listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos()
												.getValorProdutoVenda() });

					}
					somar();
					tabbedPane.setSelectedIndex(0);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				

			}
		});
		btnAlterar.setBounds(152, 12, 117, 25);
		panelbtnAbaConsulta.add(btnAlterar);

		JPanel panelTableConsultaVendas = new JPanel();
		panelTableConsultaVendas.setBounds(5, 60, 525, 248);
		panelConsulta.add(panelTableConsultaVendas);
		panelTableConsultaVendas.setLayout(null);

		JScrollPane scrollPaneAbaConsulta = new JScrollPane();
		scrollPaneAbaConsulta.setBounds(5, 15, 510, 220);
		panelTableConsultaVendas.add(scrollPaneAbaConsulta);

		tableVendas = new JTable();
		tableVendas
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome Cliente", "Data" }));
		tableVendas.getColumnModel().getColumn(0).setPreferredWidth(90);
		tableVendas.getColumnModel().getColumn(0).setMinWidth(90);
		tableVendas.getColumnModel().getColumn(0).setMaxWidth(90);
		tableVendas.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableVendas.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableVendas.getColumnModel().getColumn(2).setMinWidth(90);
		tableVendas.getColumnModel().getColumn(2).setMaxWidth(90);
		scrollPaneAbaConsulta.setViewportView(tableVendas);

		// chamando metodos para carregar quando abre a tela
		listarClientes();
		listarProdutos();
		carregarVendas();
		preencherCodigoClienteComboBox();
		preencherCodigoProdutoComboBox();
		habilitarDesabilitarCampos(false);

	}

	private void preencherCodigoProdutoComboBox() {
		try {
			modelProdutos = controllerProduto.getProdutoController(cbNomeProduto.getSelectedItem().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		txtCodigoProduto.setText(String.valueOf(modelProdutos.getIdProduto()));
	}

	private void preencherCodigoClienteComboBox() {
		try {
			modelCliente = controllerCliente.getClienteController(cbNomeCliente.getSelectedItem().toString());
			txtCodigoCliente.setText(String.valueOf(modelCliente.getIdCliente()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// preenche combobox com todos os clientes
	private void listarClientes() throws SQLException {
		listaModelCliente = controllerCliente.retornarListaClientesController();
		cbNomeCliente.removeAllItems();
		for (int i = 0; i < listaModelCliente.size(); i++) {
			cbNomeCliente.addItem(listaModelCliente.get(i).getNomeCliente());
		}
	}

	// preenche combox com todos os produtos
	private void listarProdutos() throws SQLException {
		listaModelProduto = controllerProduto.retornarListaProdutoController();
		cbNomeProduto.removeAllItems();
		for (int i = 0; i < listaModelProduto.size(); i++) {
			cbNomeProduto.addItem(listaModelProduto.get(i).getNomeProduto());

		}
	}

	// carregar tabela de vendas
	private void carregarVendas() throws SQLException {
		DefaultTableModel modelo = (DefaultTableModel) tableVendas.getModel();
		listaModelVendasClientes = controllerVendasCliente.retornarListaVendaCliente();
		modelo.setNumRows(0);
		int cont = listaModelVendasClientes.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelVendasClientes.get(i).getModelVendas().getIdVenda(),
					listaModelVendasClientes.get(i).getModelClientes().getNomeCliente(),
					listaModelVendasClientes.get(i).getModelVendas().getDataVendas() });
		}
	}

	// somar todos os valores totais da venda
	private void somar() {
		float soma = 0;
		txtValorTotal.setText("");
		for (int i = 0; i < tableProdutos.getRowCount(); i++) {
			soma += Float.parseFloat(tableProdutos.getValueAt(i, 4).toString());
			// txtValorTotal.setText(new DecimalFormat("R$ #,##0.00").format(soma));
		}
		txtValorTotal.setText(String.valueOf(soma));
		aplicarDesconto();
	}

	// aplica o desconto ao valor final de venda
	private void aplicarDesconto() {
		try {
			float soma = Float.parseFloat(txtValorTotal.getText()) - Float.parseFloat(txtDesconto.getText());
			txtValorTotal.setText(String.valueOf(soma));
		} catch (NumberFormatException e) {

		}
	}

	private void limparFormulario() {
		txtDesconto.setText("");
		txtQuantidade.setText("");
		txtValorTotal.setText("");
		txtNumeroVenda.setText("");
		DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
		modelo.setNumRows(0);
	}
	private void habilitarDesabilitarCampos(boolean condicao) {
		btnSalvar.setEnabled(condicao);
		cbNomeCliente.setEnabled(condicao);
		cbNomeProduto.setEnabled(condicao);
		
	}
}
