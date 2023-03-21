package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerCliente;
import model.ModelClientes;
import util.LimiteCaracteresCampos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoCliente;
	private JTextField txtNomeCliente;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtCEP;
	private JTextField txtTelefone;
	private JTable tableCliente;
	private JTextField txtCpf;
	private JButton btnSalvar;
	private JComboBox comboBox;

	private String salvarAlterar;

	ControllerCliente controllerCliente = new ControllerCliente();
	ModelClientes modelClientes = new ModelClientes();
	ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCliente frame = new ViewCliente();
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
	public ViewCliente() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 150, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Tela Cliente");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 150);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 12, 70, 15);
		panel.add(lblCodigo);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(12, 30, 110, 20);
		panel.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		txtCodigoCliente.setEnabled(false);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(125, 12, 70, 15);
		panel.add(lblNome);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(125, 30, 200, 20);
		panel.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setBounds(12, 60, 120, 15);
		panel.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(12, 80, 210, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(225, 60, 70, 15);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(225, 80, 210, 20);
		panel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 110, 70, 15);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(12, 130, 125, 20);
		panel.add(txtCidade);
		txtCidade.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SC", "PR", "RS", "RJ", "SP", "MG" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(145, 130, 50, 20);
		panel.add(comboBox);

		txtCEP = new JTextField();
		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtCEP.setText(txtCEP.getText().replaceAll("[^0-9]", ""));
			}
		});
		txtCEP.setBounds(200, 130, 114, 20);
		panel.add(txtCEP);
		txtCEP.setColumns(10);
		txtCEP.setDocument(new LimiteCaracteresCampos(8));

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(145, 110, 35, 15);
		panel.add(lblUf);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(200, 110, 70, 15);
		panel.add(lblCep);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(320, 110, 70, 15);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtTelefone.setText(txtTelefone.getText().replaceAll("[^0-9]", ""));
			}
		});
		txtTelefone.setBounds(320, 130, 114, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setDocument(new LimiteCaracteresCampos(9));

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(330, 12, 70, 15);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtCpf.setText(txtCpf.getText().replaceAll("[^0-9]", ""));
			}
		});
		txtCpf.setBounds(330, 30, 105, 20);
		panel.add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new LimiteCaracteresCampos(11));

		JPanel panelTable = new JPanel();
		panelTable.setBounds(5, 151, 450, 200);
		contentPane.add(panelTable);
		panelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 425, 195);
		panelTable.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Cidade", "Telefone" }));
		scrollPane.setViewportView(tableCliente);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(0, 360, 450, 40);
		contentPane.add(panelBotoes);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limparCampos();
				habilitaDesabilitaCampos(false);
			}
		});
		panelBotoes.add(btnCancelar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tableCliente.getSelectedRow();

				if (JOptionPane.showConfirmDialog(null, "Deseja excluir", "ATENÇÃO", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						int codigoLinhaSelecionada = (int) tableCliente.getValueAt(linhaSelecionada, 0);
						controllerCliente.excluirClienteController(codigoLinhaSelecionada);
						JOptionPane.showMessageDialog(null, "Excluido com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTabelaCliente();

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir", "ERRO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				}

			}
		});
		panelBotoes.add(btnExcluir);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "salvar";
				habilitaDesabilitaCampos(true);
				limparCampos();
			}
		});
		panelBotoes.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelClientes.setIdCliente(Integer.parseInt(txtCodigoCliente.getText()));
				} catch (NumberFormatException e2) {
					
				}
				
				modelClientes.setNomeCliente(txtNomeCliente.getText());
				modelClientes.setCpfCliente(txtCpf.getText());
				modelClientes.setEnderecoCliente(txtEndereco.getText());
				modelClientes.setBairroCliente(txtBairro.getText());
				modelClientes.setCidadeCliente(txtCidade.getText());
				modelClientes.setUfCliente(comboBox.getSelectedItem().toString());
				modelClientes.setCepCliente(Integer.parseInt(txtCEP.getText()));
				modelClientes.setTelefoneCliente(Integer.parseInt(txtTelefone.getText()));
				
				
				if (salvarAlterar.equals("salvar")) {
					
					try {
						controllerCliente.salvarClienteController(modelClientes);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "INFORMAÇÃO", JOptionPane.WARNING_MESSAGE);
						popularTabelaCliente();
						limparCampos();
						habilitaDesabilitaCampos(false);

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto", "ERRO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					
					
				} else if (salvarAlterar.equals("alterar")) {
					
					try {
						controllerCliente.alterarClienteController(modelClientes);
						JOptionPane.showMessageDialog(null, "Alterado com sucesso", "INFORMAÇÃO", JOptionPane.WARNING_MESSAGE);
						popularTabelaCliente();
						limparCampos();
						habilitaDesabilitaCampos(false);

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao alterar o produto", "ERRO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					
				}
			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				habilitaDesabilitaCampos(true);

				salvarAlterar = "alterar";
				int linhaSelecionada = tableCliente.getSelectedRow();

				try {
					int codigoLinhaSelecionada = (int) tableCliente.getValueAt(linhaSelecionada, 0);

					modelClientes = controllerCliente.getClienteController(codigoLinhaSelecionada);

					txtCodigoCliente.setText(String.valueOf(modelClientes.getIdCliente()));
					txtNomeCliente.setText(modelClientes.getNomeCliente());
					txtCpf.setText(modelClientes.getCpfCliente());
					txtEndereco.setText(modelClientes.getEnderecoCliente());
					txtBairro.setText(modelClientes.getBairroCliente());
					txtCidade.setText(modelClientes.getCidadeCliente());
					comboBox.setSelectedItem(modelClientes.getUfCliente());
					txtCEP.setText(String.valueOf(modelClientes.getCepCliente()));
					txtTelefone.setText(String.valueOf(modelClientes.getTelefoneCliente()));

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao editar, selecione um campo para editar", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		panelBotoes.add(btnAlterar);
		panelBotoes.add(btnSalvar);
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(25);

		habilitaDesabilitaCampos(false);
		popularTabelaCliente();

	}

	private void limparCampos() {
		txtCodigoCliente.setText("");
		txtNomeCliente.setText("");
		txtCpf.setText("");
		txtEndereco.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtCEP.setText("");
		txtTelefone.setText("");
	}

	private void habilitaDesabilitaCampos(boolean condicao) {
		txtNomeCliente.setEnabled(condicao);
		txtCpf.setEnabled(condicao);
		txtEndereco.setEnabled(condicao);
		txtBairro.setEnabled(condicao);
		txtCidade.setEnabled(condicao);
		comboBox.setEnabled(condicao);
		txtCEP.setEnabled(condicao);
		txtTelefone.setEnabled(condicao);
		btnSalvar.setEnabled(condicao);

	}

	private void popularTabelaCliente() throws SQLException {
		listaModelClientes = controllerCliente.retornarListaClientesController();
		DefaultTableModel modelo = (DefaultTableModel) tableCliente.getModel();
		modelo.setNumRows(0);
		int cont = listaModelClientes.size();

		for (int i = 0; i < cont; i++) {
			modelo.addRow(
					new Object[] { listaModelClientes.get(i).getIdCliente(), listaModelClientes.get(i).getNomeCliente(),
							// listaModelClientes.get(i).getCpfCliente(),
							// listaModelClientes.get(i).getEnderecoCliente(),
							// listaModelClientes.get(i).getBairroCliente(),
							listaModelClientes.get(i).getCidadeCliente(),
							// listaModelClientes.get(i).getUfCliente(),
							// listaModelClientes.get(i).getCepCliente(),
							listaModelClientes.get(i).getTelefoneCliente(),

					});
		}

	}
}
