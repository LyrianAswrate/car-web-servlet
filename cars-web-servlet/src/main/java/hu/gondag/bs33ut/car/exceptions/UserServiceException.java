package hu.gondag.bs33ut.car.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = -3547524130151187397L;

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
