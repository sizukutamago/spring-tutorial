package mrs.app.reservation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndTimeMustBeAfterStartTimeValidator implements ConstraintValidator<EndTimeMustBeAfterStartTime, ReservationForm> {

    private String message;

    @Override
    public boolean isValid(ReservationForm reservationForm, ConstraintValidatorContext constraintValidatorContext) {
        if (reservationForm.getStartTime() == null || reservationForm.getEndTime() == null) {
            return true;
        }

        boolean isEndTimeMustBeAfterStartTime = reservationForm.getEndTime().isAfter(reservationForm.getStartTime());
        if(!isEndTimeMustBeAfterStartTime) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("endTime").addConstraintViolation();
        }
        return isEndTimeMustBeAfterStartTime;
    }

    @Override
    public void initialize(EndTimeMustBeAfterStartTime constraintAnnotation) {
        message = constraintAnnotation.message();
    }
}
