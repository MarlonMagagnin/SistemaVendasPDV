package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DaoClientes;
import model.ModelClientes;

public class ControllerCliente {
	
	private DaoClientes daoCliente = new DaoClientes();
	
	public int salvarClienteController(ModelClientes modelClientes) throws SQLException {
		return this.daoCliente.salvarClienteDAO(modelClientes);
	}
	
	public boolean excluirClienteController(int id_cliente) throws SQLException {
		return this.daoCliente.excluirClienteDAO(id_cliente);
		
	}
	
	public boolean alterarClienteController(ModelClientes modelClientes) throws SQLException {
		return this.daoCliente.alterarClienteDAO(modelClientes);
	}
	
	public ModelClientes getClienteController(int idCliente) throws SQLException {
		return this.daoCliente.getClienteDAO(idCliente);
		
	}
	public ModelClientes getClienteController(String nomeCliente) throws SQLException {
		return this.daoCliente.getClienteDAO(nomeCliente);
		
	}
	public ArrayList<ModelClientes> retornarListaClientesController() throws SQLException{
		return this.daoCliente.retornarListaClientesDAO();
	}

}
