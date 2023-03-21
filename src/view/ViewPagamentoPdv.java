package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.ControllerCliente;
import controller.ControllerFormaPagamento;
import model.ModelFormaPagamento;

public class ViewPagamentoPdv extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtSubTotal;
	private JTextField txtDesconto;
	private JTextField txtValorRecebido;
	private JTextField txtTroco;
	private JComboBox cbFormaPagamento;
	private JComboBox cbTipoDesconto;
	private JLabel lblValorTotal;

	ControllerCliente controllerCliente = new ControllerCliente();
	ArrayList<ModelFormaPagamento> listaModelFormaPagamento = new ArrayList<>();
	ControllerFormaPagamento controllerFormaPagamento = new ControllerFormaPagamento();
	private float valorTotal;
	private String formaPagamento;
	private float desconto;
	private float valorRecebido;
	private float troco;
	private boolean terminarPagamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewPagamentoPdv dialog = new ViewPagamentoPdv(new javax.swing.JFrame(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPagamentoPdv(java.awt.Frame parent, boolean modal) {
		setBounds(300, 150, 450, 400);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Pagamento");
		terminarPagamento = false;
		

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 450, 200);
		contentPanel.add(panelSuperior);
		panelSuperior.setLayout(null);

		JLabel lblPagamento = new JLabel("PAGAMENTO");
		lblPagamento.setBounds(100, 20, 90, 15);
		panelSuperior.add(lblPagamento);

		cbFormaPagamento = new JComboBox();
		cbFormaPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbTipoDesconto.requestFocus();
			}
		});
		cbFormaPagamento.setBounds(200, 20, 125, 24);
		panelSuperior.add(cbFormaPagamento);

		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setBounds(100, 60, 90, 15);
		panelSuperior.add(lblSubtotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(200, 60, 125, 20);
		panelSuperior.add(txtSubTotal);
		txtSubTotal.setColumns(10);

		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setBounds(100, 130, 110, 15);
		panelSuperior.add(lblDesconto);

		JLabel lblTipoDesconto = new JLabel("Tipo Desconto");
		lblTipoDesconto.setBounds(80, 100, 102, 15);
		panelSuperior.add(lblTipoDesconto);

		cbTipoDesconto = new JComboBox();
		cbTipoDesconto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDesconto.requestFocus();
				txtDesconto.selectAll();

			}
		});
		cbTipoDesconto.setModel(new DefaultComboBoxModel(new String[] { "Tipo", "Valor", "Porcentagem" }));
		cbTipoDesconto.setBounds(200, 100, 125, 24);
		panelSuperior.add(cbTipoDesconto);

		JLabel lblValorRecebido = new JLabel("Valor Recebido");
		lblValorRecebido.setBounds(80, 160, 110, 15);
		panelSuperior.add(lblValorRecebido);

		txtDesconto = new JTextField();
		txtDesconto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculaValorTotal();
				txtValorRecebido.requestFocus();
				txtValorRecebido.selectAll();
			}
		});
		txtDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculaValorTotal();
				txtDesconto.setText(txtDesconto.getText().toString().replaceAll(",", "."));
			}
		});
		txtDesconto.setBounds(200, 130, 125, 19);
		panelSuperior.add(txtDesconto);
		txtDesconto.setColumns(10);

		txtValorRecebido = new JTextField();
		txtValorRecebido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTroco.requestFocus();
			}
		});
		txtValorRecebido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (!txtValorRecebido.equals("") || txtValorRecebido.equals("0")) {
						troco = Float.parseFloat(txtValorRecebido.getText())
								- Float.parseFloat(lblValorTotal.getText());
						txtTroco.setText(String.valueOf(troco));
						txtValorRecebido.setText(txtValorRecebido.getText().toString().replaceAll(",", "."));
					}
				} catch (Exception ex) {
					txtTroco.setText(String.valueOf(0));
				}
			}
		});
		txtValorRecebido.setBounds(200, 160, 125, 19);
		panelSuperior.add(txtValorRecebido);
		txtValorRecebido.setColumns(10);

		JPanel panelValorTotal = new JPanel();
		panelValorTotal
				.setBorder(new TitledBorder(null, "VALOR TOTAL", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelValorTotal.setBounds(80, 210, 310, 100);
		contentPanel.add(panelValorTotal);
		panelValorTotal.setLayout(null);

		lblValorTotal = new JLabel("ValorTotal");
		lblValorTotal.setFont(new Font("Dialog", Font.BOLD, 20));
		lblValorTotal.setBounds(94, 41, 154, 15);
		panelValorTotal.add(lblValorTotal);

		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(0, 300, 450, 50);
		contentPanel.add(panelInferior);
		panelInferior.setLayout(null);

		JLabel lblTroco = new JLabel("Troco:");
		lblTroco.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		lblTroco.setBounds(12, 20, 70, 15);
		panelInferior.add(lblTroco);

		txtTroco = new JTextField();
		txtTroco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarPagamento();
			}
		});
		txtTroco.setBounds(100, 20, 114, 20);
		panelInferior.add(txtTroco);
		txtTroco.setColumns(10);

		// chamando métodos quando inicia a interface
		txtSubTotal.setEnabled(false);
		listarFormaPagamento();
		calculaValorTotal();
		limparCampos();
	}

	private void limparCampos() {
		txtDesconto.setText("0");
		txtValorRecebido.setText("0");

	}

	private void listarFormaPagamento() {
		try {
			listaModelFormaPagamento = controllerFormaPagamento.getListaFormaPagamentoController();
			cbFormaPagamento.removeAll();
			for (int i = 0; i < listaModelFormaPagamento.size(); i++) {
				cbFormaPagamento.addItem(listaModelFormaPagamento.get(i).getDescricao_forma_pagamento());
			}
		} catch (SQLException ex) {

		}
	}

	private void encerrarPagamento() {
		try {
			desconto = Float.parseFloat(txtDesconto.getText());
			valorTotal = Float.parseFloat(lblValorTotal.getText());
			valorRecebido = Float.parseFloat(txtValorRecebido.getText());
			troco = Float.parseFloat(txtTroco.getText());
			formaPagamento = cbFormaPagamento.getSelectedItem().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		terminarPagamento = true;
		dispose();
	}

	private void calculaValorTotal() {
		try {
			if (cbTipoDesconto.getSelectedItem().equals("Valor")) {
				float calculoComValor = Float.parseFloat(txtSubTotal.getText())
						- Float.parseFloat(txtDesconto.getText());
				lblValorTotal.setText(String.valueOf(calculoComValor));
			} else if (cbTipoDesconto.getSelectedItem().equals("Porcentagem")) {
				float calculoComPercentual = ((Float.parseFloat(txtSubTotal.getText())
						* Float.parseFloat(txtDesconto.getText())) / 100);
				float resultado = Float.parseFloat(txtSubTotal.getText()) - calculoComPercentual;
				lblValorTotal.setText(String.valueOf(resultado));
			} else {
				lblValorTotal.setText(String.valueOf(txtSubTotal.getText()));
			}
		} catch (Exception e) {

		}
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

	public float getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public float getTroco() {
		return troco;
	}

	public void setTroco(float troco) {
		this.troco = troco;
	}

	public boolean isTerminarPagamento() {
		return terminarPagamento;
	}

	public void setTerminarPagamento(boolean terminarPagamento) {
		this.terminarPagamento = terminarPagamento;
	}

	public void setTxtSubTotal() {
		this.txtSubTotal.setText(String.valueOf(this.valorTotal));
	}

	public void setTxtValorTotal() {
		lblValorTotal.setText(String.valueOf(valorTotal));
	}

}
