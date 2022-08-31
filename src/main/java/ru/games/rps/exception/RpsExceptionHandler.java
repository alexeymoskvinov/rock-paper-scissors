package ru.games.rps.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class RpsExceptionHandler {

    @ExceptionHandler({GameIsNotActiveException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(exception.getMessage()));
    }

    private record ErrorMessage(String error) {
    }
}
