package hu.gondag.bs33ut.car.exceptions;

public class CarModelServiceException extends RuntimeException {

    private static final long serialVersionUID = -3867909364957782440L;

    public CarModelServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
