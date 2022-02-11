package br.com.orangetalents.proposta.criarproposta.validacao;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
public @interface CpfCnpj {
  String message() default "Número de documento inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
