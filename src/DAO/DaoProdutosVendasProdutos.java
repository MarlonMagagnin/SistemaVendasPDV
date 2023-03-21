package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexoes.ConexaoBD;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendasProdutos;

public class DaoProdutosVendasProdutos extends ConexaoBD{
	public List<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosDao(int codigoVenda) throws SQLException{
		List<ModelProdutosVendasProdutos> listaModelProdutosVendaProdutos = new ArrayList<>();
		ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
		ModelProdutos modelProdutos = new ModelProdutos();
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
		try {
			this.getConnection();
			String sql = "SELECT tbl_produto.id_produto, "
					+ "tbl_produto.nome_produto, "
					+ "tbl_produto.valor_produto, "
					+ "tbl_produto.estoque_produto, "
					+ "tbl_vendas_produtos.id_venda_produto, "
					+ "tbl_vendas_produtos.valor_venda_produto, "
					+ "tbl_vendas_produtos.quantidade_venda_produto, "
					+ "tbl_vendas_produtos.fk_produto, "
					+ "tbl_vendas_produtos.fk_vendas "
					+ "FROM tbl_vendas_produtos "
					+ "INNER JOIN tbl_produto ON tbl_produto.id_produto = tbl_vendas_produtos.fk_produto "
					+ "WHERE tbl_vendas_produtos.fk_vendas = ?";
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, codigoVenda);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
				modelProdutos = new ModelProdutos();
				modelVendasProdutos = new ModelVendasProdutos();
				
				modelProdutos.setIdProduto(rs.getInt(1));
				modelProdutos.setNomeProduto(rs.getString(2));
				modelProdutos.setValorProduto(rs.getDouble(3));
				modelProdutos.setEstoqueProduto(rs.getInt(4));
				
				modelVendasProdutos.setIdVendaProduto(rs.getInt(5));
				modelVendasProdutos.setValorProdutoVenda(rs.getDouble(6));
				modelVendasProdutos.setQuantiadeProdutoVenda(rs.getDouble(7));
				modelVendasProdutos.setProduto(rs.getInt(8));
				modelVendasProdutos.setVenda(rs.getInt(9));
				
				modelProdutosVendasProdutos.setModelProdutos(modelProdutos);
				modelProdutosVendasProdutos.setModelVendasProdutos(modelVendasProdutos);
				
				listaModelProdutosVendaProdutos.add(modelProdutosVendasProdutos);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnection().close();
		}
		return listaModelProdutosVendaProdutos;
	}
	

}
