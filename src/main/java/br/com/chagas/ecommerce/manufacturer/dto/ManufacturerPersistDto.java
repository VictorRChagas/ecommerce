package br.com.chagas.ecommerce.manufacturer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString(of = "name")
@AllArgsConstructor
public class ManufacturerPersistDto {

    @NotNull
    private String name;

    @Deprecated
    public ManufacturerPersistDto() {
    }
}
