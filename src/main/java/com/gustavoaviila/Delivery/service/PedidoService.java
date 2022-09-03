package com.gustavoaviila.Delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.repository.ClienteRepository;
import com.gustavoaviila.Delivery.domain.repository.PedidoRepository;
import com.gustavoaviila.Delivery.domain.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

}
