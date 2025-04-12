package com.ada.pedidoservice.service;

import com.ada.pedidoservice.dto.*;
import com.ada.pedidoservice.model.*;
import com.ada.pedidoservice.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoResponseDTO processarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setUf(dto.getUf().toUpperCase());

        List<Item> itens = new ArrayList<>();
        double subtotal = 0.0;

        for (ItemDTO itemDto : dto.getItens()) {
            Item item = new Item();
            item.setDescricao(itemDto.getDescricao());
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(itemDto.getPrecoUnitario());
            item.setPedido(pedido);

            subtotal += item.getQuantidade() * item.getPrecoUnitario();
            itens.add(item);
        }

        pedido.setItens(itens);
        
        String regiao = obterRegiao(pedido.getUf());
        int aliquota = obterAliquota(regiao);
        double imposto = subtotal * aliquota / 100;
        double total = subtotal + imposto;
        
        pedido.setSubtotal(subtotal);
        pedido.setImposto(imposto);
        pedido.setTotal(total);
        
        pedidoRepository.save(pedido);

        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setId(pedido.getId());
        response.setCliente(pedido.getCliente());
        response.setUf(pedido.getUf());
        response.setRegiao(regiao);
        response.setItens(dto.getItens());
        response.setSubtotal(subtotal);
        response.setImposto(imposto);
        response.setTotal(total);
        response.setAliquotaAplicada(aliquota);

        return response;
    }

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return mapToResponse(pedido);
    }

    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setCliente(dto.getCliente());
        pedido.setUf(dto.getUf().toUpperCase());

        pedido.getItens().clear();

        List<Item> itens = new ArrayList<>();
        for (ItemDTO itemDto : dto.getItens()) {
            Item item = new Item();
            item.setDescricao(itemDto.getDescricao());
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(itemDto.getPrecoUnitario());
            item.setPedido(pedido);
            itens.add(item);
        }

        pedido.getItens().addAll(itens);
        pedidoRepository.save(pedido);

        return mapToResponse(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO mapToResponse(Pedido pedido) {
        List<ItemDTO> itensDto = pedido.getItens().stream().map(item -> {
            ItemDTO dto = new ItemDTO();
            dto.setId(item.getId());
            dto.setDescricao(item.getDescricao());
            dto.setQuantidade(item.getQuantidade());
            dto.setPrecoUnitario(item.getPrecoUnitario());
            return dto;
        }).toList();

        double subtotal = pedido.getItens().stream()
                .mapToDouble(i -> i.getQuantidade() * i.getPrecoUnitario()).sum();

        String regiao = obterRegiao(pedido.getUf());
        int aliquota = obterAliquota(regiao);
        double imposto = subtotal * aliquota / 100;
        double total = subtotal + imposto;

        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setId(pedido.getId()); 
        response.setCliente(pedido.getCliente());
        response.setUf(pedido.getUf());
        response.setRegiao(regiao);
        response.setItens(itensDto);
        response.setSubtotal(subtotal);
        response.setImposto(imposto);
        response.setTotal(total);
        response.setAliquotaAplicada(aliquota);
        return response;
    }

    private String obterRegiao(String uf) {
        return switch (uf.toUpperCase()) {
            case "AC", "AP", "AM", "PA", "RO", "RR", "TO" -> "Norte";
            case "AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE" -> "Nordeste";
            case "DF", "GO", "MT", "MS" -> "Centro-Oeste";
            case "ES", "MG", "RJ", "SP" -> "Sudeste";
            case "PR", "RS", "SC" -> "Sul";
            default -> throw new IllegalArgumentException("UF inválida");
        };
    }

    private int obterAliquota(String regiao) {
        return switch (regiao) {
            case "Norte" -> 10;
            case "Nordeste" -> 12;
            case "Centro-Oeste" -> 8;
            case "Sudeste" -> 15;
            case "Sul" -> 11;
            default -> 0;
        };
    }
}
