package br.com.chagas.ecommerce.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString(of = "name")
public class ConsumerPersistDto {

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;

    public ConsumerPersistDto() {
    }
}
