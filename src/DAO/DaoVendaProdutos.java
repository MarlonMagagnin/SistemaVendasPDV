package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexoes.ConexaoBD;
import model.ModelVendasProdutos;

public class DaoVendaProdutos {

	private Connection connection;

	public int salvarVendaProdutosDAO(ModelVendasProdutos modelVendasProdutos) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "INSERT INTO tbl_vendas_produtos (valor_venda_produto, quantidade_venda_produto, fk_produto, fk_vendas) VALUES(?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, modelVendasProdutos.getValorProdutoVenda());
			stmt.setDouble(2, modelVendasProdutos.getQuantiadeProdutoVenda());
			stmt.setInt(3, modelVendasProdutos.getProduto());
			stmt.setInt(4, modelVendasProdutos.getVenda());

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

	public boolean excluirVendaProdutoDAO(int idVendaProduto) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "DELETE FROM tbl_vendas_produtos WHERE fk_vendas = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idVendaProduto);
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

	public boolean alterarVendaProdutoDAO(ModelVendasProdutos modelVendasProdutos) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "UPDATE tbl_vendas_produtos SET valor_venda_produto = ? , quantidade_venda_produto = ? WHERE id_vendas = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, modelVendasProdutos.getValorProdutoVenda());
			stmt.setDouble(2, modelVendasProdutos.getQuantiadeProdutoVenda());
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

	public ModelVendasProdutos getVendaProdutoDAO(int idVendaProduto) throws SQLException {
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_venda_produto, valor_venda_produto, "
					+ "quantidade_venda_produto, fk_produto, fk_vendas "
					+ "FROM tbl_vendas_produtos WHERE id_venda_produto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idVendaProduto);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendasProdutos.setIdVendaProduto(rs.getInt(1));
				modelVendasProdutos.setValorProdutoVenda(rs.getDouble(2));
				modelVendasProdutos.setQuantiadeProdutoVenda(rs.getDouble(3));
				modelVendasProdutos.setProduto(rs.getInt(4));
				modelVendasProdutos.setVenda(rs.getInt(5));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelVendasProdutos;
	}

	public ArrayList<ModelVendasProdutos> retornarListaVendasProdutosDAO() throws SQLException {
		ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT * FROM tbl_vendas_produtos";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendasProdutos = new ModelVendasProdutos();
				modelVendasProdutos.setIdVendaProduto(rs.getInt(1));
				modelVendasProdutos.setValorProdutoVenda(rs.getDouble(2));
				modelVendasProdutos.setQuantiadeProdutoVenda(rs.getDouble(3));
				modelVendasProdutos.setProduto(rs.getInt(4));
				modelVendasProdutos.setVenda(rs.getInt(5));
				listaModelVendasProdutos.add(modelVendasProdutos);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelVendasProdutos;
	}

	public boolean salvarVendaProdutosDAO(List<ModelVendasProdutos> listaModelVendasProdutos) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			int cont = listaModelVendasProdutos.size();
			for (int i = 0; i < cont; i++) {
				String sql = "INSERT INTO tbl_vendas_produtos (valor_venda_produto, quantidade_venda_produto, fk_produto, fk_vendas) VALUES(?,?,?,?)";

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setDouble(1, listaModelVendasProdutos.get(i).getValorProdutoVenda());
				stmt.setDouble(2, listaModelVendasProdutos.get(i).getQuantiadeProdutoVenda());
				stmt.setInt(3, listaModelVendasProdutos.get(i).getProduto());
				stmt.setInt(4, listaModelVendasProdutos.get(i).getVenda());

				stmt.execute();
				stmt.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}
}
