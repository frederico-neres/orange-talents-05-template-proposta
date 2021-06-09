package br.com.zupacademy.frederico.microservice_transacoes.annotations;

import br.com.zupacademy.frederico.microservice_transacoes.annotations.validator.IsBase64Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = IsBase64Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBase64 {

    String message() default "Precisa ser um arquivo em base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
