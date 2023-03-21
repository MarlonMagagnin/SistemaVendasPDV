package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexoes.ConexaoBD;
import model.ModelProdutos;

public class DaoProdutos extends ConexaoBD {

	private Connection connection;

	public int salvarProdutoDAO(ModelProdutos modelProdutos) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "INSERT INTO tbl_produto (nome_produto, valor_produto, estoque_produto) VALUES(?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelProdutos.getNomeProduto());
			stmt.setDouble(2, modelProdutos.getValorProduto());
			stmt.setInt(3, modelProdutos.getEstoqueProduto());
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

	public boolean excluirProdutoDAO(int idProduto) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "DELETE FROM tbl_produto WHERE id_produto=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idProduto);
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

	public boolean alterarProdutoDAO(ModelProdutos modelProdutos) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "UPDATE tbl_produto SET nome_produto = ? , valor_produto = ?,estoque_produto = ? WHERE id_produto = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelProdutos.getNomeProduto());
			stmt.setDouble(2, modelProdutos.getValorProduto());
			stmt.setInt(3, modelProdutos.getEstoqueProduto());
			stmt.setInt(4, modelProdutos.getIdProduto());
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

	public ModelProdutos getProdutoDAO(int idProduto) throws SQLException {
		ModelProdutos modelProdutos = new ModelProdutos();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_produto, nome_produto, valor_produto, estoque_produto FROM tbl_produto WHERE id_produto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idProduto);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelProdutos.setIdProduto(rs.getInt(1));
				modelProdutos.setNomeProduto(rs.getString(2));
				modelProdutos.setValorProduto(rs.getDouble(3));
				modelProdutos.setEstoqueProduto(rs.getInt(4));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelProdutos;
	}

	public ArrayList<ModelProdutos> retornarListaProdutosDAO() throws SQLException {
		ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
		ModelProdutos modelProdutos = new ModelProdutos();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT * FROM tbl_produto";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelProdutos = new ModelProdutos();
				modelProdutos.setIdProduto(rs.getInt(1));
				modelProdutos.setNomeProduto(rs.getString(2));
				modelProdutos.setValorProduto(rs.getDouble(3));
				modelProdutos.setEstoqueProduto(rs.getInt(4));
				listaModelProdutos.add(modelProdutos);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelProdutos;
	}

	public ModelProdutos getProdutoDAO(String nomeProduto) throws SQLException {
		ModelProdutos modelProdutos = new ModelProdutos();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_produto, nome_produto, valor_produto, estoque_produto FROM tbl_produto WHERE nome_produto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeProduto);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelProdutos.setIdProduto(rs.getInt(1));
				modelProdutos.setNomeProduto(rs.getString(2));
				modelProdutos.setValorProduto(rs.getDouble(3));
				modelProdutos.setEstoqueProduto(rs.getInt(4));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelProdutos;
	}

	public boolean alterarEstoqueProdutoDao(List<ModelProdutos> listaModelProduto) throws SQLException {

		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "UPDATE tbl_produto SET estoque_produto = ? WHERE id_produto = ? ";

			for (int i = 0; i < listaModelProduto.size(); i++) {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1, listaModelProduto.get(i).getEstoqueProduto());
				stmt.setInt(2, listaModelProduto.get(i).getIdProduto());
				stmt.executeUpdate();
				stmt.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
	}
}
