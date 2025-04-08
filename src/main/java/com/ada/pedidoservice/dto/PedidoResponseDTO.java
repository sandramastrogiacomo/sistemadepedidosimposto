package com.ada.pedidoservice.dto;

import java.util.List;

public class PedidoResponseDTO {
    private Long id;   
	private String cliente;
    private String uf;
    private String regiao;
    private List<ItemDTO> itens;
    private Double subtotal;
    private Double imposto;
    private Double total;
    private Integer aliquotaAplicada;
	
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public List<ItemDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getImposto() {
		return imposto;
	}
	public void setImposto(Double imposto) {
		this.imposto = imposto;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getAliquotaAplicada() {
		return aliquotaAplicada;
	}
	public void setAliquotaAplicada(Integer aliquotaAplicada) {
		this.aliquotaAplicada = aliquotaAplicada;
	}

   
}
