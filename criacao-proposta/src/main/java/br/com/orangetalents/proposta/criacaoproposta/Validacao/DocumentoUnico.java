package br.com.orangetalents.proposta.criacaoproposta.Validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DocumentoUnicoValidator.class})
public @interface DocumentoUnico {
    String message() default "Documento já registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    Class<?> domainClass();
}
