package com.gustavoaviila.Delivery.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Produto;
import com.gustavoaviila.Delivery.domain.repository.ProdutoRepository;
import com.gustavoaviila.Delivery.service.exceptions.DatabaseException;
import com.gustavoaviila.Delivery.service.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public Produto insert(Produto produto) {
		return repository.save(produto);
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

	public Produto update (Integer id, Produto produto) {
		try {
			Optional<Produto> obj = repository.findById(id);
			updateData(obj, produto);
			return repository.save(obj.get());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Optional<Produto> obj, Produto produto) {
		obj.get().setDescricao(produto.getDescricao());
		obj.get().setPreco(produto.getPreco());
	}

}
