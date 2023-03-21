package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexoes.ConexaoBD;
import model.ModelUsuarios;

public class DaoUsuarios extends ConexaoBD {

	private Connection connection;

	public int salvarUsuarioDAO(ModelUsuarios modelUsuarios) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "INSERT INTO tbl_usuario (nome_usuario, login_usuario, senha_usuario) VALUES(?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelUsuarios.getNomeUsuario());
			stmt.setString(2, modelUsuarios.getLoginUsuario());
			stmt.setString(3, modelUsuarios.getSenhaUsuario());
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

	public boolean excluirUsuarioDAO(int idUsuario) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "DELETE FROM tbl_usuario WHERE id_usuario = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
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

	public boolean alterarUsuarioDAO(ModelUsuarios modelUsuarios) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "UPDATE tbl_usuario SET nome_usuario = ? , login_usuario = ?, senha_usuario = ? WHERE id_usuario = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelUsuarios.getNomeUsuario());
			stmt.setString(2, modelUsuarios.getLoginUsuario());
			stmt.setString(3, modelUsuarios.getSenhaUsuario());
			stmt.setInt(4, modelUsuarios.getIdUsuario());
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

	public ModelUsuarios getUsuarioDAO(int idUsuario) throws SQLException {
		ModelUsuarios modelUsuarios = new ModelUsuarios();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM tbl_usuario WHERE id_usuario = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelUsuarios.setIdUsuario(rs.getInt(1));
				modelUsuarios.setNomeUsuario(rs.getString(2));
				modelUsuarios.setLoginUsuario(rs.getString(3));
				modelUsuarios.setSenhaUsuario(rs.getString(4));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelUsuarios;
	}

	public ArrayList<ModelUsuarios> retornarListaUsuarioDAO() throws SQLException {
		ArrayList<ModelUsuarios> listaModelUsuarios = new ArrayList<>();
		ModelUsuarios modelUsuarios = new ModelUsuarios();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT * FROM tbl_usuario";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelUsuarios = new ModelUsuarios();
				modelUsuarios.setIdUsuario(rs.getInt(1));
				modelUsuarios.setNomeUsuario(rs.getString(2));
				modelUsuarios.setLoginUsuario(rs.getString(3));
				modelUsuarios.setSenhaUsuario(rs.getString(4));
				listaModelUsuarios.add(modelUsuarios);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelUsuarios;
	}

	public boolean getValidarUsuarioDAO(ModelUsuarios modelUsuario) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String slq = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM tbl_usuario WHERE login_usuario = ? AND senha_usuario = ?";
			PreparedStatement stmt = connection.prepareStatement(slq);
			stmt.setString(1, modelUsuario.getLoginUsuario());
			stmt.setString(2, modelUsuario.getSenhaUsuario());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				stmt.close();
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}

	}
	
	public ModelUsuarios getUsuarioDAO(String login) throws SQLException {
        ModelUsuarios modelUsuarios = new ModelUsuarios();

        try {
            this.connection = new ConexaoBD().getConnection();
            String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM tbl_usuario WHERE login_usuario = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelUsuarios.setIdUsuario(rs.getInt(1));
                modelUsuarios.setNomeUsuario(rs.getString(2));
                modelUsuarios.setLoginUsuario(rs.getString(3));
                modelUsuarios.setSenhaUsuario(rs.getString(4));
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return modelUsuarios;
    }
}
