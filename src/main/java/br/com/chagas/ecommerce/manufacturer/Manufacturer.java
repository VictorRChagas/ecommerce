package br.com.chagas.ecommerce.manufacturer;

import br.com.chagas.ecommerce.validator.Validator;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@ToString(of = "id")
@Table(name = "MANUFACTURER")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public Manufacturer() {

    }

    public Manufacturer(@NotNull String name) {
        this.setName(name);
    }

    public void setName(String name) {
        var isInvalid = Validator.isNull
                .and(Validator.isEmpty)
                .negate()
                .test(name);
        if (isInvalid) {
            throw new IllegalArgumentException("Name must no be null or empty");
        }
        this.name = name;
    }
}
