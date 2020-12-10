package br.com.chagas.ecommerce.productOrder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public List<ProductOrder> findAllByPedidoId(Long pedidoId) {
        return productOrderRepository.findAllByOrderId(pedidoId);
    }
}
