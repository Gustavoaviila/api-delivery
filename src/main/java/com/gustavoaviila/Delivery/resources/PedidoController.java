package com.gustavoaviila.Delivery.resources;

import java.net.URI;
import java.util.List;

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

import com.gustavoaviila.Delivery.domain.entity.Pedido;
import com.gustavoaviila.Delivery.domain.entity.Produto;
import com.gustavoaviila.Delivery.resources.DTO.PedidoDTO;
import com.gustavoaviila.Delivery.service.PedidoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/pedidos")
@Api("Api Pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> pedidos = service.findAll();
		return ResponseEntity.ok().body(pedidos);
	}
	
	@GetMapping("{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@PostMapping
	public ResponseEntity<Integer> insert (@RequestBody PedidoDTO pedidoDTO){
		Pedido pedido = service.insert(pedidoDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedido.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update (@PathVariable Integer id, @RequestBody Pedido pedido){
		pedido = service.update(id, pedido);
		return ResponseEntity.ok().body(pedido);
	}
	

}
