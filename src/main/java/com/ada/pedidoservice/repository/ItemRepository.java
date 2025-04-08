package com.ada.pedidoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ada.pedidoservice.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
