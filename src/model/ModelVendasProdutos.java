package model;

public class ModelVendasProdutos {
	private int idVendaProduto;
	private int produto;
	private int venda;
	private double valorProdutoVenda;
	public int getIdVendaProduto() {
		return idVendaProduto;
	}
	public void setIdVendaProduto(int idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}
	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
	public int getVenda() {
		return venda;
	}
	public void setVenda(int venda) {
		this.venda = venda;
	}
	public double getValorProdutoVenda() {
		return valorProdutoVenda;
	}
	public void setValorProdutoVenda(double valorProdutoVenda) {
		this.valorProdutoVenda = valorProdutoVenda;
	}
	public double getQuantiadeProdutoVenda() {
		return quantiadeProdutoVenda;
	}
	public void setQuantiadeProdutoVenda(double quantiadeProdutoVenda) {
		this.quantiadeProdutoVenda = quantiadeProdutoVenda;
	}
	private double quantiadeProdutoVenda;

}
