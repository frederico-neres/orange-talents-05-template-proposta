package br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler;

import br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler.dto.ArgumentError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
public class ArgumentNotValidException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentError> commonArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Collection<String> messages = new ArrayList<>();

        AtomicReference<HttpStatus> status = new AtomicReference<>(HttpStatus.BAD_REQUEST);
        fieldErrors.forEach(fieldError -> {
            if(fieldError.getCode().equals("UniqueValueDocumento")) {
                status.set(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            messages.add(fieldError.getDefaultMessage());
        });


       return ResponseEntity.status(status.get()).body(new ArgumentError(messages));
    }

}
