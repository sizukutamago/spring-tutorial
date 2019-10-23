package mrs.app.reservation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class ThirtyMinutesUnitValidator implements ConstraintValidator<ThirtyMinutesUnit, LocalTime> {
    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localTime == null) {
            return true;
        }
        return localTime.getMinute() % 30 == 0;
    }

    @Override
    public void initialize(ThirtyMinutesUnit constraintAnnotation) {

    }
}
