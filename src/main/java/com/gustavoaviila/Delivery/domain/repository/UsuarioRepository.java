package com.gustavoaviila.Delivery.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoaviila.Delivery.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer > {

	public Optional<Usuario> findByLogin (String login);
}
