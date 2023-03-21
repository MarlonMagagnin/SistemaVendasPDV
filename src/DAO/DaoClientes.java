package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexoes.ConexaoBD;
import model.ModelClientes;


public class DaoClientes extends ConexaoBD{ 

	private Connection connection;

	public int salvarClienteDAO(ModelClientes modelClientes) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "INSERT INTO tbl_cliente (nome_cliente, cpf_cliente, endereco_cliente,"
					+ "bairro_cliente, cidade_cliente, uf_cliente, cep_cliente, telefone_cliente) VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelClientes.getNomeCliente());
			stmt.setString(2, modelClientes.getCpfCliente());
			stmt.setString(3, modelClientes.getEnderecoCliente());
			stmt.setString(4, modelClientes.getBairroCliente());
			stmt.setString(5, modelClientes.getCidadeCliente());
			stmt.setString(6, modelClientes.getUfCliente().toString());
			stmt.setInt(7, modelClientes.getCepCliente());
			stmt.setInt(8, modelClientes.getTelefoneCliente());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
		return 0;
	}
	
	public boolean excluirClienteDAO(int idCliente) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "DELETE FROM tbl_cliente WHERE id_cliente=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
		return true;
	}
	public boolean alterarClienteDAO(ModelClientes modelClientes) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "UPDATE tbl_cliente SET nome_cliente = ? , cpf_cliente = ?, endereco_cliente = ?, "
					+ "bairro_cliente = ?, cidade_cliente = ?, uf_cliente = ?, "
					+ "cep_cliente = ?, telefone_cliente = ? WHERE id_cliente = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelClientes.getNomeCliente());
			stmt.setString(2, modelClientes.getCpfCliente());
			stmt.setString(3, modelClientes.getEnderecoCliente());
			stmt.setString(4, modelClientes.getBairroCliente());
			stmt.setString(5, modelClientes.getCidadeCliente());
			stmt.setString(6, modelClientes.getUfCliente());
			stmt.setInt(7, modelClientes.getCepCliente());
			stmt.setInt(8, modelClientes.getTelefoneCliente());
			stmt.setInt(9, modelClientes.getIdCliente());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
		return true;
	}
	
	public ModelClientes getClienteDAO(int idCliente) throws SQLException {
		ModelClientes modelClientes = new ModelClientes();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, endereco_cliente, bairro_cliente,"
					+ "cidade_cliente, uf_cliente, cep_cliente, telefone_cliente FROM tbl_cliente WHERE id_cliente = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelClientes.setIdCliente(rs.getInt(1));
				modelClientes.setNomeCliente(rs.getString(2));
				modelClientes.setCpfCliente(rs.getString(3));
				modelClientes.setEnderecoCliente(rs.getString(4));
				modelClientes.setBairroCliente(rs.getString(5));
				modelClientes.setCidadeCliente(rs.getString(6));
				modelClientes.setUfCliente(rs.getString(7));
				modelClientes.setCepCliente(rs.getInt(8));
				modelClientes.setTelefoneCliente(rs.getInt(9));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelClientes;
	
	}
	public ArrayList<ModelClientes> retornarListaClientesDAO() throws SQLException {
		ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
		ModelClientes modelClientes = new ModelClientes();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT * FROM tbl_cliente";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelClientes = new ModelClientes();
				modelClientes.setIdCliente(rs.getInt(1));
				modelClientes.setNomeCliente(rs.getString(2));
				modelClientes.setCpfCliente(rs.getString(3));
				modelClientes.setEnderecoCliente(rs.getString(4));
				modelClientes.setBairroCliente(rs.getString(5));
				modelClientes.setCidadeCliente(rs.getString(6));
				modelClientes.setUfCliente(rs.getString(7));
				modelClientes.setCepCliente(rs.getInt(8));
				modelClientes.setTelefoneCliente(rs.getInt(9));
				listaModelClientes.add(modelClientes);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelClientes;
	}
	
	public ModelClientes getClienteDAO(String nomeCliente) throws SQLException {
		ModelClientes modelClientes = new ModelClientes();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, endereco_cliente, bairro_cliente,"
					+ "cidade_cliente, uf_cliente, cep_cliente, telefone_cliente FROM tbl_cliente WHERE nome_cliente = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeCliente);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelClientes.setIdCliente(rs.getInt(1));
				modelClientes.setNomeCliente(rs.getString(2));
				modelClientes.setCpfCliente(rs.getString(3));
				modelClientes.setEnderecoCliente(rs.getString(4));
				modelClientes.setBairroCliente(rs.getString(5));
				modelClientes.setCidadeCliente(rs.getString(6));
				modelClientes.setUfCliente(rs.getString(7));
				modelClientes.setCepCliente(rs.getInt(8));
				modelClientes.setTelefoneCliente(rs.getInt(9));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelClientes;
	
	}
}
