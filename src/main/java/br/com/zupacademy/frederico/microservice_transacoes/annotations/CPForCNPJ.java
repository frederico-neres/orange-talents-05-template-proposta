package br.com.zupacademy.frederico.microservice_transacoes.annotations;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
public @interface CPForCNPJ {

    String message() default "Formato inválido para CPF ou CNPJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
