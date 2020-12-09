package br.com.chagas.ecommerce.order;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderStatus {
    PENDING("pending confirmation"),
    APPROVED("order approved"),
    CANCELED("order canceled");

    @Getter
    private final String statusDescription;

    OrderStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public static Map<String, OrderStatus> getGetAllPossibleStatus() {
        return Stream.of(OrderStatus.values()).collect(Collectors.toMap(OrderStatus::getStatusDescription, o -> o));
    }
}
