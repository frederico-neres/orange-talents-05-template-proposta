package br.com.zupacademy.frederico.microservice_transacoes.annotations.validator;

import br.com.zupacademy.frederico.microservice_transacoes.annotations.UniqueValueDocumento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValueDocumento, Object> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM Proposta WHERE documento = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return list.isEmpty();
    }
}