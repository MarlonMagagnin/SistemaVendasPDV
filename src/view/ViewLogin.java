package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerUsuarios;
import model.ModelSessaoUsuario;
import model.ModelUsuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JPasswordField passwordField;
	
	ControllerUsuarios controllerUsuario = new ControllerUsuarios();
	ModelUsuarios modelUsuario = new ModelUsuarios();
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
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
	public ViewLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Login no sistema");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 221, 218));
		panel.setBounds(0, 0, 450, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(50, 60, 100, 15);
		panel.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(50, 100, 100, 15);
		panel.add(lblSenha);
		
		txtNome = new JTextField();
		txtNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.requestFocus();
			}
		});
		txtNome.setBounds(110, 60, 200, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		passwordField.setBounds(110, 100, 200, 20);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
				
			}
		});
		btnLogin.setBounds(100, 150, 120, 25);
		panel.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);
		
		JLabel lblLoginSistema = new JLabel("Login Sistema");
		lblLoginSistema.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblLoginSistema.setBounds(50, 25, 150, 15);
		panel.add(lblLoginSistema);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(250, 150, 120, 25);
		panel.add(btnSair);
	}
	private void entrar() {
		modelUsuario.setLoginUsuario(txtNome.getText());
		modelUsuario.setSenhaUsuario(String.valueOf(passwordField.getPassword()));
		try {
			if(controllerUsuario.getValidarUsuarioController(modelUsuario)) {
				modelUsuario = controllerUsuario.getUsuarioController(txtNome.getText());
                modelSessaoUsuario.codigo = modelUsuario.getIdUsuario();
                modelSessaoUsuario.nome = modelUsuario.getNomeUsuario();
                modelSessaoUsuario.login = modelUsuario.getLoginUsuario();
				new ViewPrincipal().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
