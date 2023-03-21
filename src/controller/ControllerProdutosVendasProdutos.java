package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.DaoProdutosVendasProdutos;
import model.ModelProdutosVendasProdutos;

public class ControllerProdutosVendasProdutos {
	private DaoProdutosVendasProdutos daoProdutosVendasProdutos = new DaoProdutosVendasProdutos();
	
	public List<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosController(int condigoVenda) throws SQLException{
		return this.daoProdutosVendasProdutos.getListaProdutosVendasProdutosDao(condigoVenda);
	}

}
