package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexoes.ConexaoBD;
import model.ModelClientes;
import model.ModelVendas;
import model.ModelVendasCliente;

public class DaoVendasClientes {
	private Connection connection;

	public ArrayList<ModelVendasCliente> retornarListaVendasClienteDAO() throws SQLException {
		ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList<>();
		ModelVendas modelVendas = new ModelVendas();
		ModelClientes modelClientes = new ModelClientes();
		ModelVendasCliente modelVendasCliente = new ModelVendasCliente();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT "
					+ "tbl_vendas.id_vendas, "
					+ "tbl_vendas.data_vendas, "
					+ "tbl_vendas.valor_liquido_venda, "
					+ "tbl_vendas.valor_bruto_venda, "
					+ "tbl_vendas.desconto_venda, "
					+ "tbl_vendas.fk_cliente, "
					+ "tbl_cliente.id_cliente, "
					+ "tbl_cliente.nome_cliente, "
					+ "tbl_cliente.cpf_cliente, "
					+ "tbl_cliente.endereco_cliente, "
					+ "tbl_cliente.bairro_cliente, "
					+ "tbl_cliente.cidade_cliente, "
					+ "tbl_cliente.uf_cliente, "
					+ "tbl_cliente.cep_cliente, "
					+ "tbl_cliente.telefone_cliente "
					+ "FROM tbl_vendas "
					+ "INNER JOIN tbl_cliente "
					+ "ON tbl_vendas.fk_cliente = tbl_cliente.id_cliente";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendas = new ModelVendas();
				modelClientes = new ModelClientes();
				modelVendasCliente = new ModelVendasCliente();
				// vendas
				modelVendas.setIdVenda(rs.getInt(1));
				modelVendas.setDataVendas(rs.getDate(2));
				modelVendas.setValorLiquidoVenda(rs.getDouble(3));
				modelVendas.setValorBrutoVenda(rs.getDouble(4));
				modelVendas.setDescontoVenda(rs.getDouble(5));
				modelVendas.setCliente(rs.getInt(6));
				// clientes
				modelClientes.setIdCliente(rs.getInt(7));
				modelClientes.setNomeCliente(rs.getString(8));
				modelClientes.setCpfCliente(rs.getString(9));
				modelClientes.setEnderecoCliente(rs.getString(10));
				modelClientes.setBairroCliente(rs.getString(11));
				modelClientes.setCidadeCliente(rs.getString(12));
				modelClientes.setUfCliente(rs.getString(13));
				modelClientes.setCepCliente(rs.getInt(14));
				modelClientes.setTelefoneCliente(rs.getInt(15));

				modelVendasCliente.setModelVendas(modelVendas);
				modelVendasCliente.setModelClientes(modelClientes);

				listaModelVendasClientes.add(modelVendasCliente);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelVendasClientes;
	}
}
