package br.com.chagas.ecommerce.order;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PostApproveOrderEvent extends ApplicationEvent {

    @Getter
    private final Long orderId;

    public PostApproveOrderEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }
}
