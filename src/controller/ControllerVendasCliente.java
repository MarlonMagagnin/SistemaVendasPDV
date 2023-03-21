package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.DaoVendasClientes;
import model.ModelVendasCliente;

public class ControllerVendasCliente {
	
	private DaoVendasClientes daoVendaCliente = new DaoVendasClientes();

	public List<ModelVendasCliente> retornarListaVendaCliente() throws SQLException {
		
		return this.daoVendaCliente.retornarListaVendasClienteDAO();
	}

}
