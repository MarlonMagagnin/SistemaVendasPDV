package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DaoVendas;
import model.ModelVendas;

public class ControllerVendas {

	private DaoVendas daoVendas = new DaoVendas();
	
	public int salvarVendasController (ModelVendas modelVendas) throws SQLException {
		return this.daoVendas.salvarVendaDAO(modelVendas);
	}
	
	public boolean excluirVendaController(int idVenda) throws SQLException {
		return this.daoVendas.excluirVendaDAO(idVenda);
	}
	
	public boolean alterarVendaController(ModelVendas modelVendas) throws SQLException {
		return this.daoVendas.alterarVendaDAO(modelVendas);
	}
	public ModelVendas getVendaController(int idVenda) throws SQLException {
		return this.daoVendas.getVendaDAO(idVenda);
		
	}
	public ArrayList<ModelVendas> retornarListaVendasController() throws SQLException{
		return this.daoVendas.retornarListaVendasDAO();
	}
	public int retornarIdVendasController(int id) throws SQLException {
		return this.daoVendas.retornaID(id);
	}
	
}
