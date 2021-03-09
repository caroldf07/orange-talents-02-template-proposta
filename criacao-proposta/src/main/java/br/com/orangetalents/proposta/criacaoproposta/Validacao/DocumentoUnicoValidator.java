package br.com.orangetalents.proposta.criacaoproposta.Validacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentoUnicoValidator implements ConstraintValidator<DocumentoUnico, Object> {

    Logger logger = LoggerFactory.getLogger(DocumentoUnicoValidator.class);

    private String field;
    private Class<?> domainClass;

    @Autowired
    private EntityManager em;


    @Override
    public void initialize(DocumentoUnico constraintAnnotation) {
        field = constraintAnnotation.field();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = em.createQuery("Select 1 from " + domainClass.getName() + " WHERE " + field + " =:value")
                .setParameter("value", value);

        if (query.getResultList().size() > 1) {
            logger.warn("Tem um bug de documento duplicado");
        }
        Assert.isTrue(query.getResultList().size() <= 1, "Tem um bug de documento duplicado");


        return query.getResultList().isEmpty();
    }
}
