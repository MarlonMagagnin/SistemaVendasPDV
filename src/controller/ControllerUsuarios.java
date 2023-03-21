package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DaoUsuarios;
import model.ModelUsuarios;

public class ControllerUsuarios {

	private DaoUsuarios daoUsuarios = new DaoUsuarios();
	
	public int salvarUsuarioController(ModelUsuarios modelUsuarios) throws SQLException {
		return this.daoUsuarios.salvarUsuarioDAO(modelUsuarios);
		
	}
	
	public boolean excluirUsuarioController(int id_usuario) throws SQLException {
		return this.daoUsuarios.excluirUsuarioDAO(id_usuario);
	}
	
	public boolean alterarUsuarioController(ModelUsuarios modelUsuarios) throws SQLException {
		return this.daoUsuarios.alterarUsuarioDAO(modelUsuarios);
	}
	
	public ModelUsuarios getUsuarioController(int idUsuario) throws SQLException {
		return this.daoUsuarios.getUsuarioDAO(idUsuario);
	}
	public ModelUsuarios getUsuarioController(String login) throws SQLException {
		return this.daoUsuarios.getUsuarioDAO(login);
	}
	
	public ArrayList<ModelUsuarios> retornarListaProdutoController() throws SQLException{
		return this.daoUsuarios.retornarListaUsuarioDAO();
	}

	public boolean getValidarUsuarioController(ModelUsuarios modelUsuario) throws SQLException {
		return daoUsuarios.getValidarUsuarioDAO(modelUsuario);
		
	}
}
