package model;

import java.sql.Date;


public class ModelVendas {
	
	private int idVenda;
	private Date dataVendas;
	private double valorLiquidoVenda;
	private double valorBrutoVenda;
	private double descontoVenda;
	private int cliente;
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public Date getDataVendas() {
		return dataVendas;
	}
	public void setDataVendas(Date dataVendas) {
		this.dataVendas = dataVendas;
	}
	public double getValorLiquidoVenda() {
		return valorLiquidoVenda;
	}
	public void setValorLiquidoVenda(double valorLiquidoVenda) {
		this.valorLiquidoVenda = valorLiquidoVenda;
	}
	public double getValorBrutoVenda() {
		return valorBrutoVenda;
	}
	public void setValorBrutoVenda(double valorBrutoVenda) {
		this.valorBrutoVenda = valorBrutoVenda;
	}
	public double getDescontoVenda() {
		return descontoVenda;
	}
	public void setDescontoVenda(double descontoVenda) {
		this.descontoVenda = descontoVenda;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public int getCliente() {
		return cliente;
	}
	

}
