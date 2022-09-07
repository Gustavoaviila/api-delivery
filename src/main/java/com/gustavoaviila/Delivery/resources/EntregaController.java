package com.gustavoaviila.Delivery.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavoaviila.Delivery.domain.entity.Entrega;
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
}
