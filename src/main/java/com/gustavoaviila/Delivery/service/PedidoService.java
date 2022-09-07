package com.gustavoaviila.Delivery.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavoaviila.Delivery.domain.entity.Cliente;
import com.gustavoaviila.Delivery.domain.entity.Entrega;
import com.gustavoaviila.Delivery.domain.entity.ItemPedido;
import com.gustavoaviila.Delivery.domain.entity.Pedido;
import com.gustavoaviila.Delivery.domain.entity.Produto;
import com.gustavoaviila.Delivery.domain.enums.StatusPedido;
import com.gustavoaviila.Delivery.domain.repository.ClienteRepository;
import com.gustavoaviila.Delivery.domain.repository.EntregaRepository;
import com.gustavoaviila.Delivery.domain.repository.ItemsPedidoRepository;
import com.gustavoaviila.Delivery.domain.repository.PedidoRepository;
import com.gustavoaviila.Delivery.domain.repository.ProdutoRepository;
import com.gustavoaviila.Delivery.resources.DTO.ItemPedidoDTO;
import com.gustavoaviila.Delivery.resources.DTO.PedidoDTO;
import com.gustavoaviila.Delivery.service.exceptions.DatabaseException;
import com.gustavoaviila.Delivery.service.exceptions.RegraNegocioException;
import com.gustavoaviila.Delivery.service.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemsPedidoRepository itemsPedidoRepository;
	
	@Autowired
	private EntregaService entregaService;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	@Transactional
    public Pedido insert( PedidoDTO dto ) {
        Cliente cliente = clienteRepository
                .findById(dto.getIdCliente())
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        pedido.setStatus(StatusPedido.REALIZADO);
        repository.save(pedido);
        Entrega entrega = new Entrega();
        entrega.setData(LocalDateTime.now());
        entrega.setPedido(pedido);
        entregaService.insert(entrega);
        return pedido;
    }	
	
	@Transactional
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Transactional
	public Pedido update (Integer id, Pedido pedido) {
		try {
			Optional<Pedido> obj = repository.findById(id);
			updateData(obj, pedido);
			return repository.save(obj.get());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Optional<Pedido> obj, Pedido pedido) {
		obj.get().setCliente(pedido.getCliente());
		obj.get().setItens(pedido.getItens());
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Produto produto = produtoRepository
                            .findById(dto.getIdProduto())
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ dto.getIdProduto()
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
