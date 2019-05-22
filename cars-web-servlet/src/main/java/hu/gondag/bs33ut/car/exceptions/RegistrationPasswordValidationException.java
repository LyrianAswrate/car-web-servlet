package hu.gondag.bs33ut.car.exceptions;

public class RegistrationPasswordValidationException extends RuntimeException {

    private static final long serialVersionUID = -2296670521418879091L;

    public RegistrationPasswordValidationException(String message) {
        super(message);
    }

}
