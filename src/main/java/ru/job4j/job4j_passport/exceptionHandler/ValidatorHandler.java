package ru.job4j.job4j_passport.exceptionHandler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ValidatorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(value =
            {NullPointerException.class,
            IllegalSeriesOrNumberException.class,
            IllegalArgumentException.class})
    public ResponseEntity<?> handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
