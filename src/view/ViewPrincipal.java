package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ModelSessaoUsuario;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.InputEvent;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNomeLogado;
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
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
	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(300, 150, 550, 300);
		setTitle("Sistema de vendas");
		setResizable(false);
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		
	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JMenu arquivo = new JMenu("Arquivos");
        JMenu cadastro = new JMenu("Cadastros");
        JMenu vendas = new JMenu("Vendas");
      
        menuBar.add(arquivo);
        menuBar.add(cadastro);
        menuBar.add(vendas);
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_DOWN_MASK));
        sair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        arquivo.add(sair);
        
        JMenuItem clientes = new JMenuItem("Clientes");
        clientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new ViewCliente().setVisible(true);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
        	}
        });
        JMenuItem produtos = new JMenuItem("Produtos");
        produtos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ViewProduto().setVisible(true);
        	}
        });
        JMenuItem usuarios = new JMenuItem("Usuarios");
        usuarios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new ViewUsuario().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        cadastro.add(clientes);
        cadastro.add(produtos);
        cadastro.add(usuarios);
        JMenuItem vendasItem = new JMenuItem("Vendas");
        vendasItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new ViewVenda().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        vendas.add(vendasItem);
        JMenuItem vendasPDVItem = new JMenuItem("Vendas PDV");
        vendasPDVItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new ViewPDV().setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
        	}
        });
        
        vendas.add(vendasPDVItem);
        
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 198, 526, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogadoComo = new JLabel("Logado como:");
		lblLogadoComo.setBounds(5, 10, 99, 15);
		panel.add(lblLogadoComo);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(120, 10, 60, 15);
		panel.add(lblUsuario);
		
		lblNomeLogado = new JLabel("Nome");
		lblNomeLogado.setBounds(200, 10, 78, 15);
		panel.add(lblNomeLogado);
		
		JLabel lblHora = new JLabel("");
		lblHora.setBounds(301, 10, 84, 15);
		panel.add(lblHora);
		//pegando a hora do sistema 
				class hora implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent e) {
						Calendar now = Calendar.getInstance();
						lblHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
					}
				};
		
		JLabel lblData = new JLabel("");
		lblData.setBounds(397, 10, 117, 15);
		panel.add(lblData);
		//pegando a data do sistema
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowOpened(WindowEvent e) {
						Date dataSistema = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						lblData.setText(formato.format(dataSistema));

						Timer timer = new Timer(1000, new hora());
						timer.start();

					}
				});
		setarOperador();
		
	}
	private void setarOperador() {
		lblNomeLogado.setText(modelSessaoUsuario.nome.toUpperCase());
	}
}
