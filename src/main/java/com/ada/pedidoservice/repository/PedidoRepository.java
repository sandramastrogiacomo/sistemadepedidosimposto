package com.ada.pedidoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ada.pedidoservice.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
