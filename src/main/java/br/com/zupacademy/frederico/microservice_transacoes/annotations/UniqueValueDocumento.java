package br.com.zupacademy.frederico.microservice_transacoes.annotations;

import br.com.zupacademy.frederico.microservice_transacoes.annotations.validator.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface UniqueValueDocumento {

    String message() default "Já existe uma proposta cadastrada para esse documento";

    Class<?>[] groups() default {};

    Class<?  extends Payload>[] payload() default {};

}