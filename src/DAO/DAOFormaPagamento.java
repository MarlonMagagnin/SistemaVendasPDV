package DAO;

import conexoes.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ModelFormaPagamento;

import java.util.ArrayList;

/**
 *
 * @author Marlon
 */
public class DAOFormaPagamento extends ConexaoBD {

    private Connection connection;

    /**
     * grava FormaPagamento
     *
     * @param modelFormaPagamento
     * @return int
     */
    public int salvarFormaPagamentoDAO(ModelFormaPagamento modelFormaPagamento) throws SQLException {
        try {
            this.connection = new ConexaoBD().getConnection();
            String sql = "INSERT INTO tbl_forma_pagamento ("
                    + "descricao_forma_pagamento,"
                    + "desconto_forma_pagamento,"
                    + "parcelas_forma_pagamento,"
                    + "situacao_pagamento VALUES (?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelFormaPagamento.getDescricao_forma_pagamento());
            stmt.setFloat(2, modelFormaPagamento.getDesconto_forma_pagamento());
            stmt.setInt(3, modelFormaPagamento.getParcelas_forma_pagamento());
            stmt.setFloat(4, modelFormaPagamento.getSituacao_pagamento());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            connection.close();
        }
        return 0;
    }

    /**
     * recupera FormaPagamento
     *
     * @param idFormaPagamento
     * @return ModelFormaPagamento
     */
    public ModelFormaPagamento getFormaPagamentoDAO(int idFormaPagamento) throws SQLException {
        ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
        try {
            this.connection = new ConexaoBD().getConnection();
            String sql = "SELECT "
                    + "id_forma_pagamento,"
                    + "descricao_forma_pagamento,"
                    + "desconto_forma_pagamento,"
                    + "parcelas_forma_pagamento,"
                    + "situacao_pagamento"
                    + " FROM tbl_forma_pagamento WHERE id_forma_pagamento = ? ";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFormaPagamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelFormaPagamento.setIdFormaPagamento(rs.getInt(1));
                modelFormaPagamento.setDescricao_forma_pagamento(rs.getString(2));
                modelFormaPagamento.setDesconto_forma_pagamento(rs.getFloat(3));
                modelFormaPagamento.setParcelas_forma_pagamento(rs.getInt(4));
                modelFormaPagamento.setSituacao_pagamento(rs.getFloat(5));
            }
            rs.close();
            stmt.close();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return modelFormaPagamento;
    }

    /**
     * recupera uma lista de FormaPagamento
     *
     * @return ArrayList
     */
    public ArrayList<ModelFormaPagamento> getListaFormaPagamentoDAO() throws SQLException {
        ArrayList<ModelFormaPagamento> listamodelFormaPagamento = new ArrayList<>();
        ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
        try {
             this.connection = new ConexaoBD().getConnection();
             String sql = "SELECT "
                    + "id_forma_pagamento,"
                    + "descricao_forma_pagamento,"
                    + "desconto_forma_pagamento,"
                    + "parcelas_forma_pagamento,"
                    + "situacao_pagamento"
                    + " FROM"
                    + " tbl_forma_pagamento";
 
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelFormaPagamento = new ModelFormaPagamento();
                modelFormaPagamento.setIdFormaPagamento(rs.getInt(1));
                modelFormaPagamento.setDescricao_forma_pagamento(rs.getString(2));
                modelFormaPagamento.setDesconto_forma_pagamento(rs.getFloat(3));
                modelFormaPagamento.setParcelas_forma_pagamento(rs.getInt(4));
                modelFormaPagamento.setSituacao_pagamento(rs.getFloat(5));
                listamodelFormaPagamento.add(modelFormaPagamento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           connection.close();
        }
        return listamodelFormaPagamento;
    }

    /**
     * atualiza FormaPagamento
     *
     * @param modelFormaPagamento
     * @return boolean
     */
    public boolean atualizarFormaPagamentoDAO(ModelFormaPagamento modelFormaPagamento) throws SQLException {
        try {
            this.connection = new ConexaoBD().getConnection();
            String sql = "UPDATE tbl_forma_pagamento SET "
                    + "descricao_forma_pagamento = ?,"
                    + "desconto_forma_pagamento = ?,"
                    + "parcelas_forma_pagamento = ?,"
                    + "situacao_pagamento = ? "
                    + " WHERE "
                    + "id_forma_pagamento = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelFormaPagamento.getDescricao_forma_pagamento());
            stmt.setFloat(2, modelFormaPagamento.getDesconto_forma_pagamento());
            stmt.setInt(3, modelFormaPagamento.getParcelas_forma_pagamento());
            stmt.setFloat(4, modelFormaPagamento.getSituacao_pagamento());
            stmt.setInt(5, modelFormaPagamento.getIdFormaPagamento());
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

    /**
     * exclui FormaPagamento
     *
     * @param idFormaPagamento
     * @return boolean
     */
    public boolean excluirFormaPagamentoDAO(int idFormaPagamento) throws SQLException {
        try {
           this.connection = new ConexaoBD().getConnection();
               String sql = "DELETE FROM tbl_forma_pagamento "
                    + " WHERE id_forma_pagamento = ? ";
           
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idFormaPagamento);
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
}
