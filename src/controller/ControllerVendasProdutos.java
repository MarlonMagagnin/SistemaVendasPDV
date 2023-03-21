package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DaoVendaProdutos;
import model.ModelVendasProdutos;



public class ControllerVendasProdutos {
	DaoVendaProdutos daoVendaProdutos = new DaoVendaProdutos();
	
	public int salvarVendasProdutosController (ModelVendasProdutos modelVendasProdutos) throws SQLException {
		return this.daoVendaProdutos.salvarVendaProdutosDAO(modelVendasProdutos);
	}
	
	public boolean excluirVendaProdutoController(int idVendaProduto) throws SQLException {
		return this.daoVendaProdutos.excluirVendaProdutoDAO(idVendaProduto);
	}
	
	public boolean alterarVendaProdutoController(ModelVendasProdutos modelVendasProdutos) throws SQLException {
		return this.daoVendaProdutos.alterarVendaProdutoDAO(modelVendasProdutos);
	}
	public ModelVendasProdutos getVendaProdutoController(int idVendaProduto) throws SQLException {
		return this.daoVendaProdutos.getVendaProdutoDAO(idVendaProduto);
		
	}
	public ArrayList<ModelVendasProdutos> retornarListaVendasProdutosController() throws SQLException{
		return this.daoVendaProdutos.retornarListaVendasProdutosDAO();
	}
	public boolean salvarVendasProdutosController (List<ModelVendasProdutos> listaModelVendasProdutos) throws SQLException {
		return this.daoVendaProdutos.salvarVendaProdutosDAO(listaModelVendasProdutos);
	}

}
