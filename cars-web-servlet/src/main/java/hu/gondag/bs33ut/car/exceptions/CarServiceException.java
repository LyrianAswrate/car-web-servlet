package hu.gondag.bs33ut.car.exceptions;

public class CarServiceException extends RuntimeException {

    private static final long serialVersionUID = 257167228135277860L;

    public CarServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
