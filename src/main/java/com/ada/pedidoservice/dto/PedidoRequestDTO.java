package com.ada.pedidoservice.dto;

import java.util.List;

public class PedidoRequestDTO {
    private String cliente;
    private String uf;
    private List<ItemDTO> itens;
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public List<ItemDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}

    
}
