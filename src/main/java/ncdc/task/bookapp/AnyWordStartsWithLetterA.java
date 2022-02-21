package ncdc.task.bookapp;

import ru.lanwen.verbalregex.VerbalExpression;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AnyWordStartsWithLetterAValidator.class)
@Documented
public @interface AnyWordStartsWithLetterA {

    String message() default "Any word should start with letter 'A'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

class AnyWordStartsWithLetterAValidator implements ConstraintValidator<AnyWordStartsWithLetterA, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        VerbalExpression regex = VerbalExpression.regex()
                .then("A")
                .word()
                .build();
        return regex.test(value);
    }
}
