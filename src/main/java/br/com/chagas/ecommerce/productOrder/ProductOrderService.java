package br.com.chagas.ecommerce.productOrder;

import java.util.List;

public interface ProductOrderService {

    List<ProductOrder> findAllByPedidoId(Long pedidoId);
}
