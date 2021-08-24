package ru.job4j.job4j_passport.exceptionHandler;

public class IllegalSeriesOrNumberException extends RuntimeException {

    public IllegalSeriesOrNumberException() {
    }

    public IllegalSeriesOrNumberException(String message) {
        super(message);
    }

    public IllegalSeriesOrNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSeriesOrNumberException(Throwable cause) {
        super(cause);
    }

    public IllegalSeriesOrNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
