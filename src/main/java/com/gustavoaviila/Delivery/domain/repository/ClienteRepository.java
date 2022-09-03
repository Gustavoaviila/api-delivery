package com.gustavoaviila.Delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoaviila.Delivery.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer > {

}
