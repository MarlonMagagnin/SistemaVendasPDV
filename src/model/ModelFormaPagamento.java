package model;
/**
*
* @author Marlon
*/
public class ModelFormaPagamento {

    private int idFormaPagamento;
    private String descricao_forma_pagamento;
    private float desconto_forma_pagamento;
    private int parcelas_forma_pagamento;
    private float situacao_pagamento;

    /**
    * Construtor
    */
    public ModelFormaPagamento(){}

    /**
    * seta o valor de idFormaPagamento
    * @param pIdFormaPagamento
    */
    public void setIdFormaPagamento(int pIdFormaPagamento){
        this.idFormaPagamento = pIdFormaPagamento;
    }
    /**
    * @return pk_idFormaPagamento
    */
    public int getIdFormaPagamento(){
        return this.idFormaPagamento;
    }

    /**
    * seta o valor de descricao_forma_pagamento
    * @param pDescricao_forma_pagamento
    */
    public void setDescricao_forma_pagamento(String pDescricao_forma_pagamento){
        this.descricao_forma_pagamento = pDescricao_forma_pagamento;
    }
    /**
    * @return descricao_forma_pagamento
    */
    public String getDescricao_forma_pagamento(){
        return this.descricao_forma_pagamento;
    }

    /**
    * seta o valor de desconto_forma_pagamento
    * @param pDesconto_forma_pagamento
    */
    public void setDesconto_forma_pagamento(float pDesconto_forma_pagamento){
        this.desconto_forma_pagamento = pDesconto_forma_pagamento;
    }
    /**
    * @return desconto_forma_pagamento
    */
    public float getDesconto_forma_pagamento(){
        return this.desconto_forma_pagamento;
    }

    /**
    * seta o valor de parcelas_forma_pagamento
    * @param pParcelas_forma_pagamento
    */
    public void setParcelas_forma_pagamento(int pParcelas_forma_pagamento){
        this.parcelas_forma_pagamento = pParcelas_forma_pagamento;
    }
    /**
    * @return parcelas_forma_pagamento
    */
    public int getParcelas_forma_pagamento(){
        return this.parcelas_forma_pagamento;
    }

    /**
    * seta o valor de situacao_pagamento
    * @param pSituacao_pagamento
    */
    public void setSituacao_pagamento(float pSituacao_pagamento){
        this.situacao_pagamento = pSituacao_pagamento;
    }
    /**
    * @return situacao_pagamento
    */
    public float getSituacao_pagamento(){
        return this.situacao_pagamento;
    }

    @Override
    public String toString(){
        return "ModelFormaPagamento {" + "::idFormaPagamento = " + this.idFormaPagamento + "::descricao_forma_pagamento = " + this.descricao_forma_pagamento + "::desconto_forma_pagamento = " + this.desconto_forma_pagamento + "::parcelas_forma_pagamento = " + this.parcelas_forma_pagamento + "::situacao_pagamento = " + this.situacao_pagamento +  "}";
    }
}