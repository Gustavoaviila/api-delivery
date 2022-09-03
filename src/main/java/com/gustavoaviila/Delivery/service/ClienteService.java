package com.gustavoaviila.Delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.get();
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
			updateData(cliente, cliente);
		}
		return repository.save(entity.get());
	}

	private void updateData(Cliente entity, Cliente cliente) {
		entity.setNome(cliente.getNome());
		entity.setCpf(cliente.getCpf());
		entity.setRua(cliente.getRua());
		entity.setNumero(cliente.getNumero());
		entity.setCidade(cliente.getCidade());
		entity.setTelefone(cliente.getTelefone());
	}
}
