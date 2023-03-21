package view;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.ControllerProdutos;
import model.ModelProdutos;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ViewProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNomeProduto;
	private JTextField txtEstoque;
	private JTextField txtValor;
	private JTextField txtPesquisar;
	private JTable tableProduto;
	private String salvarAlterar;
	private JButton btnSalvar;
	private ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProduto frame = new ViewProduto();
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
	public ViewProduto() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Tela Produto");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(0, 0, 450, 100);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(12, 12, 70, 15);
		panelCadastro.add(lblCdigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(140, 12, 70, 15);
		panelCadastro.add(lblNome);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(12, 30, 100, 20);
		panelCadastro.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEnabled(false);
		txtCodigo.setToolTipText("Código do produto");

		txtNomeProduto = new JTextField();
		txtNomeProduto.setToolTipText("Digite o nome do produto");
		txtNomeProduto.setBounds(140, 30, 280, 20);
		panelCadastro.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);

		JLabel lblEstoque = new JLabel("Estoque:");
		lblEstoque.setBounds(12, 60, 70, 15);
		panelCadastro.add(lblEstoque);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(140, 60, 70, 15);
		panelCadastro.add(lblValor);

		txtEstoque = new JTextField();
		txtEstoque.setBounds(12, 80, 100, 20);
		panelCadastro.add(txtEstoque);
		txtEstoque.setColumns(10);
		txtEstoque.setToolTipText("Digite a quantidade do estoque");
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtEstoque.setText(txtEstoque.getText().replaceAll("[^0-9 | ^.,]", ""));
				txtEstoque.setText(txtEstoque.getText().replaceAll(",", "."));
			}
		});

		txtValor = new JTextField();
		txtValor.setBounds(140, 80, 100, 20);
		panelCadastro.add(txtValor);
		txtValor.setColumns(10);
		txtValor.setToolTipText("Digite o valor do produto");
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtValor.setText(txtValor.getText().replaceAll("[^0-9 | ^.,]", ""));
				txtValor.setText(txtValor.getText().replaceAll(",", "."));
			}
		});

		JPanel panelPesquisar = new JPanel();
		panelPesquisar.setBounds(5, 105, 440, 30);
		contentPane.add(panelPesquisar);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		panelPesquisar.add(lblPesquisar);

		txtPesquisar = new JTextField();
		panelPesquisar.add(txtPesquisar);
		txtPesquisar.setHorizontalAlignment(SwingConstants.CENTER);
		txtPesquisar.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) tableProduto.getModel();
				final TableRowSorter<TableModel> pesquisa = new TableRowSorter<>(modelo);
				tableProduto.setRowSorter(pesquisa);
				String textoEntrada = txtPesquisar.getText().toLowerCase();
				pesquisa.setRowFilter(RowFilter.regexFilter(textoEntrada, 1));

			}
		});
		panelPesquisar.add(btnPesquisar);

		JPanel panelTable = new JPanel();
		panelTable.setBounds(5, 140, 440, 145);
		contentPane.add(panelTable);
		panelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 410, 140);
		panelTable.add(scrollPane);

		tableProduto = new JTable();
		tableProduto.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Quantidade", "Valor" }));
		tableProduto.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableProduto.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPane.setViewportView(tableProduto);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(5, 300, 430, 50);
		contentPane.add(panelBotoes);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitaDesabilitaCampos(false);
				limparCampos();
			}
		});
		panelBotoes.add(btnCancelar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tableProduto.getSelectedRow();

				if (JOptionPane.showConfirmDialog(null, "Deseja excluir", "ATENÇÃO", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						int codigoLinhaSelecionada = (int) tableProduto.getValueAt(linhaSelecionada, 0);
						controllerProdutos.excluirProdutoController(codigoLinhaSelecionada);
						JOptionPane.showMessageDialog(null, "Excluido com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTableProdutos();
						habilitaDesabilitaCampos(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir", "ERRO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				}

			}
		});
		panelBotoes.add(btnExcluir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "alterar";

				int[] linhas = tableProduto.getSelectedRows();
				if ((linhas == null || linhas.length == 0)) {
					JOptionPane.showMessageDialog(null, "Favor selecionar uma linha para alterar");
				} else {

					int linhaSelecionada = tableProduto.getSelectedRow();
					habilitaDesabilitaCampos(true);

					try {
						int codigoLinhaSelecionada = (int) tableProduto.getValueAt(linhaSelecionada, 0);
						// recupera dados do banco
						modelProdutos = controllerProdutos.getProdutoController(codigoLinhaSelecionada);
						// setar interface
						txtCodigo.setText(String.valueOf(modelProdutos.getIdProduto()));
						txtNomeProduto.setText(modelProdutos.getNomeProduto());
						txtEstoque.setText(String.valueOf(modelProdutos.getEstoqueProduto()));
						txtValor.setText(String.valueOf(modelProdutos.getValorProduto()));

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro ao editar, selecione um campo para editar", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
				}
			}
		});
		panelBotoes.add(btnEditar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitaDesabilitaCampos(true);
				salvarAlterar = "salvar";
			}
		});
		panelBotoes.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelProdutos.setIdProduto(Integer.parseInt(txtCodigo.getText()));
				} catch (NumberFormatException ex) {
				}
				modelProdutos.setNomeProduto(txtNomeProduto.getText());
				modelProdutos.setEstoqueProduto(Integer.parseInt(txtEstoque.getText()));
				modelProdutos.setValorProduto(Double.parseDouble(txtValor.getText()));

				if (salvarAlterar.equals("salvar")) {
					try {
						controllerProdutos.salvarProdutoController(modelProdutos);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTableProdutos();
						limparCampos();
						habilitaDesabilitaCampos(false);

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				} else if (salvarAlterar.equals("alterar")) {

					try {
						controllerProdutos.alterarProdutoController(modelProdutos);
						JOptionPane.showMessageDialog(null, "Alterado com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTableProdutos();
						limparCampos();
						habilitaDesabilitaCampos(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao editar o produto", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		panelBotoes.add(btnSalvar);
		// chamando o método para popular tabela do banco.
		try {
			popularTableProdutos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// desabilita campos
		habilitaDesabilitaCampos(false);
	}

	// popular tabela com os produtos cadastrados no banco
	private void popularTableProdutos() throws SQLException {

		listaModelProdutos = controllerProdutos.retornarListaProdutoController();
		DefaultTableModel modelo = (DefaultTableModel) tableProduto.getModel();

		modelo.setNumRows(0);
		// inserindo os produtos na tabela
		int cont = listaModelProdutos.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelProdutos.get(i).getIdProduto(),
					listaModelProdutos.get(i).getNomeProduto(), listaModelProdutos.get(i).getEstoqueProduto(),
					listaModelProdutos.get(i).getValorProduto() });
		}

	}

	private void habilitaDesabilitaCampos(boolean condicao) {
		txtNomeProduto.setEnabled(condicao);
		txtEstoque.setEnabled(condicao);
		txtValor.setEnabled(condicao);
		btnSalvar.setEnabled(condicao);

	}

	private void limparCampos() {
		txtCodigo.setText("");
		txtNomeProduto.setText("");
		txtEstoque.setText("");
		txtValor.setText("");
	}

}
