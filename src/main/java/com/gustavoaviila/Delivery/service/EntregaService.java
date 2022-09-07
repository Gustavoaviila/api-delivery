package com.gustavoaviila.Delivery.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Entrega;
import com.gustavoaviila.Delivery.domain.repository.EntregaRepository;
import com.gustavoaviila.Delivery.service.exceptions.ResourceNotFoundException;

@Service
public class EntregaService {
	
	@Autowired
	private EntregaRepository repository;
	
	public List<Entrega> findAll() {
		return repository.findAll();
	}

	public Entrega findById(Integer id) {
		Optional<Entrega> entrega = repository.findById(id);
		return entrega.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	@Transactional
	public Entrega insert(Entrega entrega) {
		return repository.save(entrega);
	}
}
