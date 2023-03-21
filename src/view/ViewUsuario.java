package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerUsuarios;
import model.ModelUsuarios;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textLogin;
	private JPasswordField passwordFieldSenha;
	private JTable tableUsuario;
	private JButton btnSalvar;
	private String salvarAlterar;

	private ArrayList<ModelUsuarios> listaModelUsuarios = new ArrayList<>();
	ControllerUsuarios controllerUsuarios = new ControllerUsuarios();
	ModelUsuarios modelUsuarios = new ModelUsuarios();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUsuario frame = new ViewUsuario();
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
	public ViewUsuario() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Tela Usuario");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCampos = new JPanel();
		panelCampos.setBounds(5, 0, 490, 130);
		contentPane.add(panelCampos);
		panelCampos.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 12, 70, 15);
		panelCampos.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(120, 12, 70, 15);
		panelCampos.add(lblNome);

		textCodigo = new JTextField();
		textCodigo.setBounds(12, 35, 80, 20);
		panelCampos.add(textCodigo);
		textCodigo.setColumns(10);
		textCodigo.setEnabled(false);

		textNome = new JTextField();
		textNome.setBounds(120, 35, 345, 20);
		panelCampos.add(textNome);
		textNome.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(12, 70, 70, 15);
		panelCampos.add(lblLogin);

		textLogin = new JTextField();
		textLogin.setBounds(12, 90, 200, 20);
		panelCampos.add(textLogin);
		textLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(250, 70, 70, 15);
		panelCampos.add(lblSenha);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(250, 90, 215, 20);
		panelCampos.add(passwordFieldSenha);

		JPanel panel = new JPanel();
		panel.setBounds(5, 130, 490, 200);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 455, 190);
		panel.add(scrollPane);

		tableUsuario = new JTable();
		tableUsuario.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Login", }));
		scrollPane.setViewportView(tableUsuario);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(5, 340, 490, 50);
		contentPane.add(panelBotoes);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				habilitarDesabilitarCampos(false);
			}
		});
		panelBotoes.add(btnCancelar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tableUsuario.getSelectedRow();

				if (JOptionPane.showConfirmDialog(null, "Deseja excluir", "ATENÇÃO", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						int codigoLinhaSelecionada = (int) tableUsuario.getValueAt(linhaSelecionada, 0);
						controllerUsuarios.excluirUsuarioController(codigoLinhaSelecionada);
						JOptionPane.showMessageDialog(null, "Excluido com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTabelaUsuario();
						habilitarDesabilitarCampos(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir", "ERRO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}

			}
		});
		panelBotoes.add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "alterar";

				int[] linhas = tableUsuario.getSelectedRows();
				if ((linhas == null || linhas.length == 0)) {
					JOptionPane.showMessageDialog(null, "Favor selecionar uma linha para alterar");
				} else {

					int linhaSelecionada = tableUsuario.getSelectedRow();
					habilitarDesabilitarCampos(true);

					try {
						int codigoLinhaSelecionada = (int) tableUsuario.getValueAt(linhaSelecionada, 0);
						modelUsuarios = controllerUsuarios.getUsuarioController(codigoLinhaSelecionada);

						textCodigo.setText(String.valueOf(modelUsuarios.getIdUsuario()));
						textNome.setText(modelUsuarios.getNomeUsuario());
						textLogin.setText(modelUsuarios.getLoginUsuario());
						passwordFieldSenha.setText(modelUsuarios.getSenhaUsuario());

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao editar, selecione um campo para editar", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		panelBotoes.add(btnAlterar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarDesabilitarCampos(true);
				salvarAlterar = "salvar";
			}
		});
		panelBotoes.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelUsuarios.setIdUsuario(Integer.parseInt(textCodigo.getText()));
				} catch (NumberFormatException ex) {

				}

				modelUsuarios.setNomeUsuario(textNome.getText());
				modelUsuarios.setLoginUsuario(textLogin.getText());
				modelUsuarios.setSenhaUsuario(String.valueOf(passwordFieldSenha.getPassword()));

				if (salvarAlterar.equals("salvar")) {
					try {
						controllerUsuarios.salvarUsuarioController(modelUsuarios);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTabelaUsuario();
						limparCampos();
						habilitarDesabilitarCampos(false);
					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						exe.printStackTrace();
					}

				} else if (salvarAlterar.equals("alterar")) {
					try {
						controllerUsuarios.alterarUsuarioController(modelUsuarios);
						JOptionPane.showMessageDialog(null, "Alterado com sucesso", "INFORMAÇÃO",
								JOptionPane.WARNING_MESSAGE);
						popularTabelaUsuario();
						limparCampos();
						habilitarDesabilitarCampos(false);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao editar o produto", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Opção inválida");
				}

			}
		});
		panelBotoes.add(btnSalvar);

		habilitarDesabilitarCampos(false);
		popularTabelaUsuario();
	}

	private void limparCampos() {
		textCodigo.setText("");
		textNome.setText("");
		textLogin.setText("");
		passwordFieldSenha.setText("");

	}

	private void habilitarDesabilitarCampos(boolean condicao) {
		textNome.setEnabled(condicao);
		textLogin.setEnabled(condicao);
		passwordFieldSenha.setEnabled(condicao);
		btnSalvar.setEnabled(condicao);
	}

	private void popularTabelaUsuario() throws SQLException {

		listaModelUsuarios = controllerUsuarios.retornarListaProdutoController();

		DefaultTableModel modelo = (DefaultTableModel) tableUsuario.getModel();
		modelo.setNumRows(0);
		int cont = listaModelUsuarios.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelUsuarios.get(i).getIdUsuario(),
					listaModelUsuarios.get(i).getNomeUsuario(), listaModelUsuarios.get(i).getLoginUsuario(), });
		}

	}

}
