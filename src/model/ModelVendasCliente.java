package model;

import java.util.ArrayList;
import java.util.List;

public class ModelVendasCliente {
	
	private ModelVendas modelVendas;
	private ModelClientes modelClientes;
	public ModelVendas getModelVendas() {
		return modelVendas;
	}
	public void setModelVendas(ModelVendas modelVendas) {
		this.modelVendas = modelVendas;
	}
	public ModelClientes getModelClientes() {
		return modelClientes;
	}
	public void setModelClientes(ModelClientes modelClientes) {
		this.modelClientes = modelClientes;
	}
	public List<ModelVendasCliente> getListaModelVendaCliente() {
		return listaModelVendaCliente;
	}
	public void setListaModelVendaCliente(List<ModelVendasCliente> listaModelVendaCliente) {
		this.listaModelVendaCliente = listaModelVendaCliente;
	}
	private List<ModelVendasCliente> listaModelVendaCliente = new ArrayList<>(); 

}
