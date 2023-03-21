package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DaoProdutos;
import model.ModelProdutos;

public class ControllerProdutos {
	
	private DaoProdutos daoProdutos = new DaoProdutos();
	
	public int salvarProdutoController(ModelProdutos modelProdutos) throws SQLException {
		return this.daoProdutos.salvarProdutoDAO(modelProdutos);
		
	}
	
	public boolean excluirProdutoController(int idProduto) throws SQLException {
		return this.daoProdutos.excluirProdutoDAO(idProduto);
	}
	
	public boolean alterarProdutoController(ModelProdutos modelProdutos) throws SQLException {
		return this.daoProdutos.alterarProdutoDAO(modelProdutos);
	}
	
	public ModelProdutos getProdutoController(int idProduto) throws SQLException {
		return this.daoProdutos.getProdutoDAO(idProduto);
	}
	public ModelProdutos getProdutoController(String nomeProduto) throws SQLException {
		return this.daoProdutos.getProdutoDAO(nomeProduto);
	}
	
	public ArrayList<ModelProdutos> retornarListaProdutoController() throws SQLException{
		return this.daoProdutos.retornarListaProdutosDAO();
	}

	public boolean alterarEtoqueProdutoController(List<ModelProdutos> listaModelProduto) throws SQLException {
		return this.daoProdutos.alterarEstoqueProdutoDao(listaModelProduto);
		
	}
}
