package br.com.chagas.ecommerce.delivery;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "DELIVERY")
@Getter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "MODE", nullable = false, length = 100)
    private String mode;

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public Delivery() {
    }

    public Delivery(Long id) {
        this.id = id;
    }

    public Delivery(@NotNull @NotEmpty String mode) {
        this.setMode(mode);
    }

    public void setMode(@NotNull @NotEmpty String mode) {
        if (Objects.isNull(mode) || mode.isBlank()) {
            throw new IllegalArgumentException("mode must not be empty or null");
        }
        this.mode = mode;
    }
}
