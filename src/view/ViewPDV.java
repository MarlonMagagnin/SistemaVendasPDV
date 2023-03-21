package view;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import autocomplete.AutoCompleteDecorator;
import controller.ControllerCliente;
import controller.ControllerProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasProdutos;
import model.ModelClientes;
import model.ModelProdutos;
import model.ModelSessaoUsuario;
import model.ModelVendas;
import model.ModelVendasProdutos;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewPDV extends JFrame {

	private JPanel contentPane;
	private JTable tableVendas;
	private JTextField txtCodigoCliente;
	private JTextField txtCodigoBarra;
	private JTextField txtValorBruto;
	private JComboBox cbCliente;
	private JLabel lblSetaroperador;
	private JLabel lblSetarstatus;

	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();
	ModelVendas modelVendas = new ModelVendas();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	List<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
	List<ModelProdutos> listaModelProdutos = new ArrayList<>();
	ControllerVendas controllerVendas = new ControllerVendas();
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
	ControllerCliente controllerCliente = new ControllerCliente();
	ModelClientes modelCliente = new ModelClientes();
	List<ModelClientes> listaModelCliente = new ArrayList<>();
	private double quantidade;
	private ViewPagamentoPdv viewPagamentoPdv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPDV frame = new ViewPDV();
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
	public ViewPDV() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1280, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Tela Vendas PDV");
		quantidade = 1;
		setResizable(false);
		
		

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu arquivo = new JMenu("Arquivo");
		JMenu comandos = new JMenu("Comandos");
		menuBar.add(arquivo);
		menuBar.add(comandos);

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		arquivo.add(sair);
		sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));

		JMenuItem excluirItem = new JMenuItem("Excluir Item");
		excluirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantidadeLinha = tableVendas.getRowCount();
				if (quantidadeLinha < 1) {
					JOptionPane.showMessageDialog(null, "Não existe itens para excluir");
				} else {
					DefaultTableModel modelo = (DefaultTableModel) tableVendas.getModel();
					int linha = Integer.parseInt(JOptionPane.showInputDialog("Informe o item que deseja excluir"));
					modelo.removeRow(linha - 1);
					txtValorBruto.setText(String.valueOf(somarValorTotal()));
					for (int i = 0; i < quantidadeLinha; i++) {
						modelo.setValueAt(i + 1, i, 0);
					}
				}
			}
		});
		JMenuItem menuQuantidade = new JMenuItem("Quantidade");
		menuQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe a quantidade"));
				} catch (Exception ex) {
				}
			}
		});
		JMenuItem finalizarVenda = new JMenuItem("Finalizar Venda");
		finalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewPagamentoPdv = new ViewPagamentoPdv(null, true);
					viewPagamentoPdv.setModal(true);
					viewPagamentoPdv.setValorTotal(Float.parseFloat(txtValorBruto.getText()));
					viewPagamentoPdv.setTxtSubTotal();
					viewPagamentoPdv.setVisible(true);
					if (viewPagamentoPdv.isTerminarPagamento()) {
						salvarVenda();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Você deve incluir um produto para terminar a venda",
							"INFORMAÇÃO", JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		// JMenuItem excluirItem = new JMenuItem("Excluir Item");
		comandos.add(excluirItem);
		excluirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		comandos.add(menuQuantidade);
		menuQuantidade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		comandos.add(finalizarVenda);
		finalizarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTable = new JPanel();
		panelTable.setBounds(12, 100, 600, 475);
		contentPane.add(panelTable);
		panelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 5, 550, 450);
		panelTable.add(scrollPane);

		tableVendas = new JTable();
		tableVendas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item", "Codigo", "Nome Item", "Quantidade", "Valor Unitario", "Valor Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVendas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableVendas.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableVendas.getColumnModel().getColumn(2).setPreferredWidth(150);
		scrollPane.setViewportView(tableVendas);

		JPanel panelCabecalho = new JPanel();
		panelCabecalho.setBounds(34, 38, 578, 50);
		contentPane.add(panelCabecalho);
		panelCabecalho.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setBounds(12, 25, 70, 15);
		panelCabecalho.add(lblCliente);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbCliente.requestFocus();
			}
		});
		txtCodigoCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					modelCliente = controllerCliente.getClienteController(Integer.parseInt(txtCodigoCliente.getText()));
					cbCliente.setSelectedItem(modelCliente.getNomeCliente());
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtCodigoCliente.setBounds(83, 23, 88, 19);
		panelCabecalho.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		cbCliente = new JComboBox();
		cbCliente.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (cbCliente.isPopupVisible()) {
					preencherCodigoClienteComboBox();
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		cbCliente.setBounds(183, 20, 366, 24);
		AutoCompleteDecorator.decorate(this.cbCliente);
		panelCabecalho.add(cbCliente);

		txtCodigoBarra = new JTextField();
		txtCodigoBarra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pegarConteudo(e);
				txtCodigoBarra.setText(txtCodigoBarra.getText().replaceAll("[^0-9]", ""));
			}
		});
		txtCodigoBarra.setBounds(34, 594, 550, 25);
		contentPane.add(txtCodigoBarra);
		txtCodigoBarra.setColumns(10);

		JPanel panelDireito = new JPanel();
		panelDireito.setBounds(700, 25, 411, 550);
		contentPane.add(panelDireito);
		panelDireito.setLayout(null);

		JLabel lblCaixa = new JLabel("Caixa:");
		lblCaixa.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCaixa.setBounds(25, 32, 70, 15);
		panelDireito.add(lblCaixa);

		JLabel lblOperadorCaixa = new JLabel("Operador Caixa:");
		lblOperadorCaixa.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOperadorCaixa.setBounds(25, 76, 200, 15);
		panelDireito.add(lblOperadorCaixa);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStatus.setBounds(25, 115, 200, 15);
		panelDireito.add(lblStatus);

		JLabel lblNumeroCaixa = new JLabel("01");
		lblNumeroCaixa.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNumeroCaixa.setBounds(205, 32, 70, 15);
		panelDireito.add(lblNumeroCaixa);

		lblSetaroperador = new JLabel("SetarOperador");
		lblSetaroperador.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSetaroperador.setBounds(205, 77, 186, 15);
		panelDireito.add(lblSetaroperador);

		lblSetarstatus = new JLabel("SetarStatus");
		lblSetarstatus.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSetarstatus.setBounds(205, 116, 200, 15);
		panelDireito.add(lblSetarstatus);

		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setFont(new Font("Dialog", Font.BOLD, 20));
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setBounds(94, 188, 220, 15);
		panelDireito.add(lblValorTotal);

		txtValorBruto = new JTextField();
		txtValorBruto.setEditable(false);
		txtValorBruto.setBounds(72, 215, 272, 25);
		panelDireito.add(txtValorBruto);
		txtValorBruto.setColumns(10);

		JLabel lblComandos = new JLabel("Comandos");
		lblComandos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblComandos.setBounds(72, 290, 109, 15);
		panelDireito.add(lblComandos);

		JLabel lblFExcluirItem = new JLabel("F2 Excluir Item");
		lblFExcluirItem.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFExcluirItem.setBounds(72, 320, 150, 15);
		panelDireito.add(lblFExcluirItem);

		JLabel lblFQuantidade = new JLabel("F3 Quantidade");
		lblFQuantidade.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFQuantidade.setBounds(72, 350, 150, 15);
		panelDireito.add(lblFQuantidade);

		JLabel lblFinalizarVenda = new JLabel("Finalizar Venda");
		lblFinalizarVenda.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFinalizarVenda.setBounds(72, 380, 200, 15);
		panelDireito.add(lblFinalizarVenda);

		JLabel lblFSair = new JLabel("F10 Sair");
		lblFSair.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFSair.setBounds(72, 410, 150, 15);
		panelDireito.add(lblFSair);

		// comandos quando inicia a interface
		setarOperador();
		try {
			listarClientes();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		preencherCodigoClienteComboBox();
		limparCampos();
		setarOperador();

	}

	private void pegarConteudo(KeyEvent e) {
		lblSetarstatus.setText("VENDA ABERTA");
		DefaultTableModel modelo = (DefaultTableModel) tableVendas.getModel();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				modelProdutos = controllerProdutos.getProdutoController(Integer.parseInt(txtCodigoBarra.getText()));
				modelo.addRow(new Object[] { modelo.getRowCount() + 1, modelProdutos.getIdProduto(),
						modelProdutos.getNomeProduto(), quantidade, modelProdutos.getValorProduto(),
						modelProdutos.getValorProduto() * quantidade });
				txtValorBruto.setText(String.valueOf(somarValorTotal()));
			} catch (Exception e2) {
				// TODO: handle exception
			}

			txtCodigoBarra.setText("");
			quantidade = 1;

		}

	}

	private float somarValorTotal() {
		float soma = 0, valor = 0;
		int cont = tableVendas.getRowCount();
		for (int i = 0; i < cont; i++) {
			valor = Float.parseFloat(tableVendas.getValueAt(i, 5).toString());
			soma += valor;
		}
		return soma;
	}

	private void setarOperador() {
		lblSetaroperador.setText(modelSessaoUsuario.nome);
	}

	private void salvarVenda() {
		try {
			// TODO add your handling code here:
			int cont = tableVendas.getRowCount();
			int codigoProduto = 0, codigoVenda = 0;

			modelVendas = new ModelVendas();
			modelVendas.setCliente(Integer.parseInt(txtCodigoCliente.getText()));
			Date DataAtual = new Date(System.currentTimeMillis());
			modelVendas.setDataVendas(DataAtual);
			modelVendas.setDescontoVenda(viewPagamentoPdv.getDesconto());
			modelVendas.setValorBrutoVenda(Double.parseDouble(txtValorBruto.getText()));
			modelVendas.setValorLiquidoVenda(viewPagamentoPdv.getValorTotal());

			controllerVendas.salvarVendasController(modelVendas);
			codigoVenda = controllerVendas.retornarIdVendasController(modelVendas.getIdVenda());

			for (int i = 0; i < cont; i++) {
				codigoProduto = (int) tableVendas.getValueAt(i, 1);
				modelVendasProdutos = new ModelVendasProdutos();
				modelProdutos = new ModelProdutos();
				modelVendasProdutos.setValorProdutoVenda((double) tableVendas.getValueAt(i, 4));
				modelVendasProdutos
						.setQuantiadeProdutoVenda(Double.parseDouble(tableVendas.getValueAt(i, 3).toString()));
				modelVendasProdutos.setProduto(codigoProduto);
				modelVendasProdutos.setVenda(codigoVenda);

				// tirar produtos do estoque
				modelProdutos.setIdProduto(codigoProduto);

				try {

					modelProdutos.setEstoqueProduto(
							(int) (controllerProdutos.getProdutoController(codigoProduto).getEstoqueProduto()
									- Double.parseDouble(tableVendas.getValueAt(i, 3).toString())));
					listaModelVendasProdutos.add(modelVendasProdutos);
					listaModelProdutos.add(modelProdutos);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			if (controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
				//controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos);
				controllerProdutos.alterarEtoqueProdutoController(listaModelProdutos);
				quantidade = 1;
				limparCampos();
				lblSetarstatus.setText("CAIXA LIVRE");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar os produtos da venda", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listarClientes() throws SQLException {
		listaModelCliente = controllerCliente.retornarListaClientesController();
		cbCliente.removeAllItems();
		for (int i = 0; i < listaModelCliente.size(); i++) {
			cbCliente.addItem(listaModelCliente.get(i).getNomeCliente());
		}
	}

	private void preencherCodigoClienteComboBox() {
		try {
			modelCliente = controllerCliente.getClienteController(cbCliente.getSelectedItem().toString());
			txtCodigoCliente.setText(String.valueOf(modelCliente.getIdCliente()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void limparCampos() {
		txtValorBruto.setText("");
		DefaultTableModel modelo = (DefaultTableModel) tableVendas.getModel();
		modelo.setNumRows(0);
		try {
			listarClientes();
		} catch (SQLException ex) {
			Logger.getLogger(ViewPDV.class.getName()).log(Level.SEVERE, null, ex);
		}
		preencherCodigoClienteComboBox();
		lblSetarstatus.setText("CAIXA LIVRE");

	}
}
