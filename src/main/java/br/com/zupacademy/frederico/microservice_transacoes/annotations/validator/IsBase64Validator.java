package br.com.zupacademy.frederico.microservice_transacoes.annotations.validator;

import br.com.zupacademy.frederico.microservice_transacoes.annotations.IsBase64;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsBase64Validator implements ConstraintValidator<IsBase64, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        String base64 = (String) value;
        return Base64.isBase64(base64);
    }
}
