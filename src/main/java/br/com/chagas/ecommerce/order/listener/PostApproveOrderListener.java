package br.com.chagas.ecommerce.order.listener;

import br.com.chagas.ecommerce.order.PostApproveOrderEvent;
import br.com.chagas.ecommerce.product.ProductService;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import br.com.chagas.ecommerce.productOrder.ProductOrderService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PostApproveOrderListener {

    private final ProductOrderService productOrderService;
    private final ProductService productService;

    public PostApproveOrderListener(ProductOrderService productOrderService, ProductService productService) {
        this.productOrderService = productOrderService;
        this.productService = productService;
    }

    @EventListener
    public void setDefaultValues(PostApproveOrderEvent event) {
        this.updateAmountsOnItems(event);
    }

    private void updateAmountsOnItems(PostApproveOrderEvent orderEvent) {
        var orderId = orderEvent.getOrderId();
        var productOrderList = productOrderService.findAllByPedidoId(orderId);

        var productsUpdated = productOrderList.stream()
                .map(updateProductsAmount)
                .collect(Collectors.toList());

        productService.saveAll(productsUpdated);
    }

    private Function<ProductOrder, Product> updateProductsAmount = productOrder -> {
        var units = productOrder.getUnits();
        var product = productOrder.getProduct();
        var updatedAmount = product.getAmountStored() - productOrder.getUnits();
        product.setAmountStored(updatedAmount);
        return product;
    };
}
