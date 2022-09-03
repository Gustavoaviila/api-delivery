package com.gustavoaviila.Delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.entity.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Integer > {

}
