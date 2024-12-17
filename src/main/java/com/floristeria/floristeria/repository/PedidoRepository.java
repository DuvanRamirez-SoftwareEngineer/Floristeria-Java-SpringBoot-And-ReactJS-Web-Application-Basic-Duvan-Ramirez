package com.floristeria.floristeria.repository;
// PedidoRepository.java


import com.floristeria.floristeria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
