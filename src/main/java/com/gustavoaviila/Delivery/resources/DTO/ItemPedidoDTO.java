package com.gustavoaviila.Delivery.resources.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
	
	private Integer idProduto;
	private Integer quantidade;

}
