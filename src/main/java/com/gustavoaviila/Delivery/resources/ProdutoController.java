package com.gustavoaviila.Delivery.resources;

import java.net.URI;
import java.util.List;

import org.h2.result.UpdatableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.entity.Produto;
import com.gustavoaviila.Delivery.service.ClienteService;
import com.gustavoaviila.Delivery.service.ProdutoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/produtos")
@Api("Api Produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping("{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert (@RequestBody Produto produto){
		produto = service.insert(produto); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update (@PathVariable Integer id, @RequestBody Produto produto){
		produto = service.update(id, produto);
		return ResponseEntity.ok().body(produto);
	}
	

}
