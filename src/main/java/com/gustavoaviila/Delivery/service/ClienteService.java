package com.gustavoaviila.Delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.repository.ClienteRepository;
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
		repository.deleteById(id);
	}

	public Cliente update (Integer id, Cliente cliente) {
		Optional<Cliente> entity = repository.findById(id);
		if (entity.isPresent()) {
			updateData(entity, cliente);
		return repository.save(entity.get());
		}
		return new Cliente();
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
