package za.co.investec.cis.domain.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = SAIDValidator.class)
@Target({ METHOD, CONSTRUCTOR, TYPE })
@Retention(RUNTIME)
@Documented
public @interface SAID {

    String message() default
            "SA ID invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
