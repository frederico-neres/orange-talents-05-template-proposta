package br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler;

import br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler.customException.ApiErroException;
import br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler.dto.ArgumentError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ArgumentNotValidException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentError> commonArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Collection<String> messages = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            messages.add(fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(new ArgumentError(messages));

    }

    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<ArgumentError> handleApiErroException(ApiErroException apiErroException) {
        Collection<String> messages = new ArrayList<>();
        messages.add(apiErroException.getReason());

       return ResponseEntity.status(apiErroException.getHttpStatus()).body(new ArgumentError(messages));
    }
}
