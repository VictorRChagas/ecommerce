package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.consumer.ConsumerService;
import br.com.chagas.ecommerce.delivery.DeliveryService;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import br.com.chagas.ecommerce.payment.PaymentService;
import br.com.chagas.ecommerce.product.ProductService;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverterImpl implements OrderConverter {

    private final ConsumerService consumerService;
    private final DeliveryService deliveryService;
    private final PaymentService paymentService;
    private final ProductService productService;

    public OrderConverterImpl(ConsumerService consumerService, DeliveryService deliveryService,
                              PaymentService paymentService, ProductService productService) {
        this.consumerService = consumerService;
        this.deliveryService = deliveryService;
        this.paymentService = paymentService;
        this.productService = productService;
    }


    @Override
    public Order buildOrder(OrderPersistDto orderPersistDto) {
        var order = new Order(consumerService.findById(orderPersistDto.getConsumerId()),
                                paymentService.findById(orderPersistDto.getPaymentId()),
                                deliveryService.findById(orderPersistDto.getDeliveryId()));

        var productList = productService.findAllByIdList(orderPersistDto.getProductIds());

        var productOrderList = productList.stream()
                .map(product -> new ProductOrder(product, order))
                .collect(Collectors.toSet());

        order.setProductSet(productOrderList);
        return order;
    }
}
