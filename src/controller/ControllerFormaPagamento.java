package controller;

import model.ModelFormaPagamento;
import DAO.DAOFormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
*
* @author Marlon
*/
public class ControllerFormaPagamento {

    private DAOFormaPagamento daoFormaPagamento = new DAOFormaPagamento();

    /**
    * grava FormaPagamento
    * @param modelFormaPagamento
    * @return int
    */
    public int salvarFormaPagamentoController(ModelFormaPagamento modelFormaPagamento) throws SQLException{
        return this.daoFormaPagamento.salvarFormaPagamentoDAO(modelFormaPagamento);
    }

    /**
    * recupera FormaPagamento
    * @param idFormaPagamento
    * @return ModelFormaPagamento
    */
    public ModelFormaPagamento getFormaPagamentoController(int idFormaPagamento) throws SQLException{
        return this.daoFormaPagamento.getFormaPagamentoDAO(idFormaPagamento);
    }

    /**
    * recupera uma lista deFormaPagamento
    * @param pIdFormaPagamento
    * @return ArrayList
    */
    public ArrayList<ModelFormaPagamento> getListaFormaPagamentoController() throws SQLException{
        return this.daoFormaPagamento.getListaFormaPagamentoDAO();
    }

    /**
    * atualiza FormaPagamento
    * @param modelFormaPagamento
    * @return boolean
    */
    public boolean atualizarFormaPagamentoController(ModelFormaPagamento modelFormaPagamento) throws SQLException{
        return this.daoFormaPagamento.atualizarFormaPagamentoDAO(modelFormaPagamento);
    }

    /**
    * exclui FormaPagamento
    * @param idFormaPagamento
    * @return boolean
    */
    public boolean excluirFormaPagamentoController(int idFormaPagamento) throws SQLException{
        return this.daoFormaPagamento.excluirFormaPagamentoDAO(idFormaPagamento);
    }
}