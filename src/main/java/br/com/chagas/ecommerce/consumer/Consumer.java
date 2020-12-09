package br.com.chagas.ecommerce.consumer;

import br.com.chagas.ecommerce.validator.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Entity
@ToString(of = "id")
@Table(name = "CONSUMER")
@EqualsAndHashCode(of = "id")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Email(message = "invalid e-mail")
    @NotNull
    @Column(name = "EMAIL", nullable = false, scale = 10, precision = 2)
    private String email;

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public Consumer() {
    }

    public Consumer(Long id) {
        this.id = id;
    }

    public Consumer(@NotNull String name, @Email @NotNull String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public void setName(@NotNull @NotEmpty String name) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
    }

    public void setEmail(@Email String email) {
        if (!Validator.isEmail.test(email)) {
            throw new IllegalArgumentException("Email is not valid");
        }
        this.email = Objects.requireNonNull(email, "Email must not be null");;
    }
}
