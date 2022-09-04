package com.gustavoaviila.Delivery.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.repository.ClienteRepository;
import com.gustavoaviila.Delivery.service.exceptions.DatabaseException;
import com.gustavoaviila.Delivery.service.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public Cliente insert(Cliente cliente) {
		return repository.save(cliente);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}

	public Cliente update (Integer id, Cliente cliente) {
		try {
			Optional<Cliente> entity = repository.findById(id);
			updateData(entity, cliente);
			return repository.save(entity.get());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Optional<Cliente> entity, Cliente cliente) {
		entity.get().setNome(cliente.getNome());
		entity.get().setCpf(cliente.getCpf());
		entity.get().setRua(cliente.getRua());
		entity.get().setNumero(cliente.getNumero());
		entity.get().setCidade(cliente.getCidade());
		entity.get().setTelefone(cliente.getTelefone());
	}
}
