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
import com.gustavoaviila.Delivery.domain.entity.Entrega;
import com.gustavoaviila.Delivery.service.ClienteService;
import com.gustavoaviila.Delivery.service.EntregaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/entregas")
@Api("Api Entregas")
public class EntregaController {
	
	@Autowired
	private EntregaService service;
	
	@GetMapping
	public ResponseEntity<List<Entrega>> findAll(){
		List<Entrega> entregas = service.findAll();
		return ResponseEntity.ok().body(entregas);
	}
	
	@GetMapping("{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@PostMapping
	public ResponseEntity<Entrega> insert (@RequestBody Entrega entrega){
		entrega = service.insert(entrega); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrega.getId()).toUri();
		return ResponseEntity.created(uri).body(entrega);
	}
}
