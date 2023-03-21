package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexoes.ConexaoBD;
import model.ModelVendas;

public class DaoVendas {

	private Connection connection;

	public int salvarVendaDAO(ModelVendas modelVendas) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "INSERT INTO tbl_vendas (data_vendas, valor_liquido_venda, valor_bruto_venda, desconto_venda, fk_cliente)"
					+ " VALUES(?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, modelVendas.getDataVendas());
			stmt.setDouble(2, modelVendas.getValorLiquidoVenda());
			stmt.setDouble(3, modelVendas.getValorBrutoVenda());
			stmt.setDouble(4, modelVendas.getDescontoVenda());
			stmt.setInt(5, modelVendas.getCliente());
			
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

	public boolean excluirVendaDAO(int idVenda) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();

			String sql = "DELETE FROM tbl_vendas WHERE id_vendas = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idVenda);
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

	public boolean alterarVendaDAO(ModelVendas modelVendas) throws SQLException {
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "UPDATE tbl_vendas SET data_vendas = ? , valor_liquido_venda = ?, valor_bruto_venda = ?, desconto_venda = ? WHERE id_vendas = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, modelVendas.getDataVendas());
			stmt.setDouble(2, modelVendas.getValorLiquidoVenda());
			stmt.setDouble(3, modelVendas.getValorBrutoVenda());
			stmt.setDouble(4, modelVendas.getDescontoVenda());
			stmt.setInt(5, modelVendas.getIdVenda());
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

	public ModelVendas getVendaDAO(int idVenda) throws SQLException {
		ModelVendas modelVendas = new ModelVendas();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT id_vendas, data_vendas, valor_liquido_venda, valor_bruto_venda, desconto_venda, fk_cliente FROM tbl_vendas WHERE id_vendas = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idVenda);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendas.setIdVenda(rs.getInt(1));
				modelVendas.setDataVendas(rs.getDate(2));
				modelVendas.setValorLiquidoVenda(rs.getDouble(3));
				modelVendas.setValorBrutoVenda(rs.getDouble(4));
				modelVendas.setDescontoVenda(rs.getDouble(5));
				modelVendas.setCliente(rs.getInt(6));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return modelVendas;
	}
	
	public ArrayList<ModelVendas> retornarListaVendasDAO() throws SQLException {
		ArrayList<ModelVendas> listaModelVendas = new ArrayList<>();
		ModelVendas modelVendas = new ModelVendas();

		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT * FROM tbl_vendas";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendas = new ModelVendas();
				modelVendas.setIdVenda(rs.getInt(1));
				modelVendas.setDataVendas(rs.getDate(2));
				modelVendas.setValorLiquidoVenda(rs.getDouble(3));
				modelVendas.setValorBrutoVenda(rs.getDouble(4));
				modelVendas.setDescontoVenda(rs.getDouble(5));
				modelVendas.setCliente(rs.getInt(6));
				listaModelVendas.add(modelVendas);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listaModelVendas;
	}
	
	public int retornaID(int id) throws SQLException {
		ModelVendas modelVendas = new ModelVendas();
		try {
			this.connection = new ConexaoBD().getConnection();
			String sql = "SELECT MAX(id_vendas) FROM tbl_vendas";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelVendas.setIdVenda(rs.getInt(1));
			}
			stmt.close();
			rs.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return modelVendas.getIdVenda();
	}
}
