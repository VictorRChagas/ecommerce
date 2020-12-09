package br.com.chagas.ecommerce.validator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Validator {

    public static Predicate<String> isEmail = email -> {
        var emailValidationRegex = "^(.+)@(.+)$";
        var pattern = Pattern.compile(emailValidationRegex);
        return pattern.matcher(email).matches();
    };

    public static Predicate<String> isEmpty = String::isEmpty;

    public static Predicate<String> isNull = Objects::isNull;

    public static <T> void ifTrueOrElse(Predicate<T> predicate, Consumer<T> consumer, Runnable runnable) {

    }

}
